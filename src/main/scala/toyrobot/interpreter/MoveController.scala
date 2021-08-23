package toyrobot.interpreter

import cats.effect.Sync
import cats.syntax.either._
import toyrobot.algebra._
import toyrobot.command.{Command, PlaceCommand}
import toyrobot.errors._
import toyrobot.model._
import toyrobot.parser.InputCommandParser

import scala.io.Source

final class MoveController[F[_]: Sync] private (space: Space, commands: List[Either[AppError, Command]]) extends Controller[F] {
  override def prepare(inputFile: String): F[Controller[F]] =
    Sync[F]
      .delay({
        val source = Source.fromFile(inputFile)
        val commandLines = source.getLines().toList
        val commands = commandLines.map(InputCommandParser.parse)

        new MoveController[F](space, commands = commands)
      })

  override def run: F[Vector] = {
    this.commands match {
      case Nil => Sync[F].raiseError(EmptyCommandLinesError)
      case placeCommand :: restCommands => placeCommand match {
        case Right(command: PlaceCommand) => loop(restCommands, command.execute(this.space))
        case Right(_) => Sync[F].raiseError(NoPlaceCommandError)
        case Left(error) => Sync[F].raiseError(error)
      }
    }
  }

  private def commandExecute(command: Command, vector: Option[Vector] = None): F[Vector] = {
    command.execute(this.space, vector) match {
      case Right(newVector) => Sync[F].pure(newVector)
      case Left(error) => Sync[F].raiseError(error)
    }
  }

  private def loop(commands: List[Either[AppError, Command]], vector: Either[AppError, Vector]): F[Vector] = {
    Sync[F].defer {
      commands match {
        case Nil => vector match {
          case Right(newVector) => Sync[F].pure(newVector)
          case Left(error) => Sync[F].raiseError(error)
        }
        case command :: Nil => command match {
          case Right(command) =>
            vector match {
              case Right(newVector) => commandExecute(command, Some(newVector))
              case Left(error) => Sync[F].raiseError(error)
            }
          case Left(error) => Sync[F].raiseError(error)
        }
        case command :: commands =>
          command match {
            case Right(command) => {
              vector match {
                case Right(vector) =>
                  loop(commands, command.execute(this.space, Some(vector)))
                case Left(error) => Sync[F].raiseError(error)
              }
            }
            case Left(error) => Sync[F].raiseError(error)
          }
      }
    }
  }
}

object MoveController {
  def make[F[_]: Sync]: Controller[F] = new MoveController[F](space = Space.init(10, 10), commands = List())
}