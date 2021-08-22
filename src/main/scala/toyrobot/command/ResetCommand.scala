package toyrobot.command


import toyrobot.model._

case class ResetCommand private (x: Int, y: Int, facing: String) {
  def vector: Vector = Vector.create(Coordinates.create(x, y), Direction.create(facing))
}

object ResetCommand {
  def create(x: Int, y: Int, facing: String): ResetCommand = {
    ResetCommand(x, y, facing)
  }
}