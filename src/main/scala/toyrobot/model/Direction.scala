package toyrobot.model

trait CardinalPoint {
  def value: String
}

object West extends CardinalPoint { val value = "WEST" }
object East extends CardinalPoint { val value = "EAST" }
object North extends CardinalPoint { val value = "NORTH" }
object South extends CardinalPoint { val value = "SOUTH" }


case class Direction private (point: CardinalPoint) {
  override def toString: String = point.value

  def turnLeft: Direction = point match {
    case North => Direction.create("west")
    case East => Direction.create("north")
    case South => Direction.create("east")
    case West => Direction.create("south")
  }

  def turnRight: Direction = point match {
    case North => Direction.create("east")
    case East => Direction.create("south")
    case South => Direction.create("west")
    case West => Direction.create("north")
  }
}


object Direction {

  def create(value: String): Direction = value.toLowerCase() match {
    case "north" =>  Direction(point = North)
    case "west" =>  Direction(point = West)
    case "east" =>  Direction(point = East)
    case "south" =>  Direction(point = South)
  }
}