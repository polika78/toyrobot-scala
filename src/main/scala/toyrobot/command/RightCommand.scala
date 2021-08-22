package toyrobot.command

import cats.syntax.either._
import toyrobot.errors.{AppError, EmptyVectorError}
import toyrobot.model._


case class RightCommand() extends Command {
  override def execute(space: Space, vector: Option[Vector] = None): Either[AppError, Vector] = {
    vector match {
      case Some(newVector) => Vector.create(newVector.coordinates, newVector.direction.turnRight).asRight
      case None => EmptyVectorError.asLeft
    }
  }
}

object RightCommand {
  def create: RightCommand = {
    RightCommand()
  }
}