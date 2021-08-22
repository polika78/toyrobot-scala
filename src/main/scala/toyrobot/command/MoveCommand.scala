package toyrobot.command


import cats.syntax.either._
import toyrobot.errors.{AppError, EmptyVectorError}
import toyrobot.model._

case class MoveCommand() extends Command {
  override def execute(space: Space, vector: Option[Vector] = None): Either[AppError, Vector] = {
    vector match {
      case Some(vector) => {
        val newVector = vector.move

        space.isInside(newVector.coordinates) match {
          case true => newVector.asRight
          case false => vector.asRight
        }
      }
      case None => EmptyVectorError.asLeft
    }
  }
}

object MoveCommand {
  def create: MoveCommand = {
    MoveCommand()
  }
}