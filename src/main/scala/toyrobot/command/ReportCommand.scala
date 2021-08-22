package toyrobot.command


import cats.syntax.either._
import toyrobot.errors.{AppError, EmptyVectorError}
import toyrobot.model._


case class ReportCommand() extends Command{
  override def execute(space: Space, vector: Option[Vector] = None): Either[AppError, Vector] = {
    vector match {
      case Some(newVector) =>
        println(s"Report: $newVector")
        newVector.asRight
      case None => EmptyVectorError.asLeft
    }
  }
}

object ReportCommand {
  def create: ReportCommand = {
    ReportCommand()
  }
}
