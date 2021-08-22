package toyrobot.command


import cats.syntax.either._
import toyrobot.errors.{AppError, EmptyVectorError}
import toyrobot.model._

case class LeftCommand() extends Command {
  override def execute(space: Space, vector: Option[Vector] = None): Either[AppError, Vector] = {
    vector match {
      case Some(newVector) => Vector.create(newVector.coordinates, newVector.direction.turnLeft).asRight
      case None => EmptyVectorError.asLeft
    }
  }
}

object LeftCommand {
  def create: LeftCommand = {
    LeftCommand()
  }
}