package toyrobot

import scala.io.Source
import cats.syntax.either._
import cats.effect.Sync
import toyrobot.algebra._
import toyrobot.errors.{AppError, EmptyCommandLinesError}
import toyrobot.model._
import toyrobot.parser.{InputCommandParser}

final class MoveController[F[_]: Sync] private (space: Space, commands: List[String]) extends Controller[F] {
  override def prepare(inputFile: String): F[Controller[F]] =
    Sync[F]
      .delay({
        val source = Source.fromFile(inputFile)
        val commandLines = source.getLines().toList

        new MoveController[F](space, commands = commandLines)
      })

  override def run: F[Vector] = {
    this.commands match {
      case commandLines => {
        commandLines match {
          case Nil => Sync[F].raiseError(EmptyCommandLinesError)
          case _ => {
            loop(commandLines, None) match {
              case Right(vector) => Sync[F].point(vector)
              case Left(error) => Sync[F].raiseError(error)
            }
          }
        }
      }
    }
  }

  private def commandExecute(commandLine: String, vector: Option[Vector] = None): Either[AppError, Vector] = {
    InputCommandParser.parse(commandLine) match {
      case Right(command) => command.execute(space, vector)
      case Left(error) => error.asLeft
    }
  }

  private def loop(commands: List[String], vector: Option[Vector] = None): Either[AppError, Vector] = {
    commands match {
      case Nil => vector match { case Some(newVector) => newVector.asRight }
      case commandLine :: Nil => commandExecute(commandLine, vector)
      case commandLine :: commandLines => {
        val result = commandExecute(commandLine, vector)
        result match {
          case Right(newVector) => loop(commandLines, Some(newVector))
          case _   => result
        }
      }
    }
  }
}

object MoveController {
  def make[F[_]: Sync]: Controller[F] = new MoveController[F](space = Space.init(10, 10), commands = List())
}