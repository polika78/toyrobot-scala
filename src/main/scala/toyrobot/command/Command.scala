package toyrobot.command

import toyrobot.errors.AppError
import toyrobot.model._

trait Command {
  def execute(space: Space, vector: Option[Vector] = None): Either[AppError, Vector]
}
