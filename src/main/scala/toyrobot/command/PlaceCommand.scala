package toyrobot.command

import cats.syntax.either._
import toyrobot.errors._
import toyrobot.model._


case class PlaceCommand private(vector: Option[Vector] = None) extends Command {
  override def execute(space: Space, vector: Option[Vector] = None): Either[AppError, Vector] = {
    this.vector match {
      case Some(vector) => {
        space.isInside(vector.coordinates) match {
          case true => vector.asRight
          case false => OutOfSpaceError.asLeft
        }
      }
      case None => EmptyVectorError.asLeft
    }
  }
}

object PlaceCommand {
  def create(x: Int, y: Int, facing: String): PlaceCommand = {
    PlaceCommand(Option(Vector.create(Coordinates.create(x, y), Direction.create(facing))))
  }
}