package toyrobot.model



case class Vector(coordinates: Coordinates, direction: Direction) {
  override def toString: String = {
    s"$coordinates, $direction"
  }

  def move: Vector = direction.point match {
    case North => this.copy(coordinates = coordinates.updateY(coordinates.y.value + 1))
    case West => this.copy(coordinates = coordinates.updateX(coordinates.x.value - 1))
    case East => this.copy(coordinates = coordinates.updateX(coordinates.x.value + 1))
    case South => this.copy(coordinates = coordinates.updateY(coordinates.y.value - 1))
  }
}

object Vector {
  def create(coordinates: Coordinates, direction: Direction): Vector = Vector(coordinates, direction)
}
