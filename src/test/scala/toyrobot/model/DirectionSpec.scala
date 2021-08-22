package toyrobot.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DirectionSpec extends AnyFlatSpec with Matchers {
  "The Direction object" should "have North, East, West or South" in {
    val northDirection = Direction.create("north")
    val eastDirection = Direction.create("east")
    val westDirection = Direction.create("west")
    val southDirection = Direction.create("south")

    northDirection.point shouldEqual North
    eastDirection.point shouldEqual East
    westDirection.point shouldEqual West
    southDirection.point shouldEqual South
  }

  "The Direction object" should "create West direction by turning left from North" in {
    val direction = Direction.create("north")
    val newDirection = direction.turnLeft
    newDirection.point shouldEqual West
  }

  "The Direction object" should "create North direction by turning left from East" in {
    val direction = Direction.create("east")
    val newDirection = direction.turnLeft
    newDirection.point shouldEqual North
  }

  "The Direction object" should "create East direction by turning left from South" in {
    val direction = Direction.create("south")
    val newDirection = direction.turnLeft
    newDirection.point shouldEqual East
  }

  "The Direction object" should "create South direction by turning left from West" in {
    val direction = Direction.create("west")
    val newDirection = direction.turnLeft
    newDirection.point shouldEqual South
  }

  "The Direction object" should "create East direction by turning right from North" in {
    val direction = Direction.create("north")
    val newDirection = direction.turnRight
    newDirection.point shouldEqual East
  }

  "The Direction object" should "create North direction by turning right from East" in {
    val direction = Direction.create("east")
    val newDirection = direction.turnRight
    newDirection.point shouldEqual South
  }

  "The Direction object" should "create West direction by turning right from South" in {
    val direction = Direction.create("south")
    val newDirection = direction.turnRight
    newDirection.point shouldEqual West
  }

  "The Direction object" should "create North direction by turning right from West" in {
    val direction = Direction.create("west")
    val newDirection = direction.turnRight
    newDirection.point shouldEqual North
  }
}
