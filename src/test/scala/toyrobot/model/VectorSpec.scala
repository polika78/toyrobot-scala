package toyrobot.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class VectorSpec extends AnyFlatSpec with Matchers {
  "The Vector object" should "have coordinates and a direction" in {
    val coordinates = Coordinates.create(5, 5)
    val direction = Direction.create("north")
    val vector = Vector.create(coordinates, direction)

    vector.coordinates.x.value shouldEqual 5
    vector.coordinates.y.value shouldEqual 5
    vector.direction.point shouldEqual North
  }

  "The Vector object" should "move coordinates to North" in {
    val coordinates = Coordinates.create(5, 5)
    val direction = Direction.create("north")
    val vector = Vector.create(coordinates, direction)
    val newVector = vector.move

    newVector.coordinates.x.value shouldEqual 5
    newVector.coordinates.y.value shouldEqual 6
    newVector.direction.point shouldEqual North
  }

  "The Vector object" should "move coordinates to West" in {
    val coordinates = Coordinates.create(5, 5)
    val direction = Direction.create("west")
    val vector = Vector.create(coordinates, direction)
    val newVector = vector.move

    newVector.coordinates.x.value shouldEqual 4
    newVector.coordinates.y.value shouldEqual 5
    newVector.direction.point shouldEqual West
  }

  "The Vector object" should "move coordinates to East" in {
    val coordinates = Coordinates.create(5, 5)
    val direction = Direction.create("east")
    val vector = Vector.create(coordinates, direction)
    val newVector = vector.move

    newVector.coordinates.x.value shouldEqual 6
    newVector.coordinates.y.value shouldEqual 5
    newVector.direction.point shouldEqual East
  }

  "The Vector object" should "move coordinates to South" in {
    val coordinates = Coordinates.create(5, 5)
    val direction = Direction.create("south")
    val vector = Vector.create(coordinates, direction)
    val newVector = vector.move

    newVector.coordinates.x.value shouldEqual 5
    newVector.coordinates.y.value shouldEqual 4
    newVector.direction.point shouldEqual South
  }
}
