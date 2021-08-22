package toyrobot.command


import cats.syntax.either._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import toyrobot.errors.EmptyVectorError
import toyrobot.model._

class MoveCommandSpec extends AnyFlatSpec with Matchers {
  "The MoveCommand object" should "create new vector" in {
    val space = Space.init(10, 10)
    val coordinates = Coordinates.create(3, 3)
    val direction = Direction.create("north")
    val vector = Vector.create(coordinates, direction)
    val expectedVector = Vector.create(Coordinates.create(3, 4), direction)
    val moveCommand = MoveCommand.create
    val newVector = moveCommand.execute(space, Some(vector))

    newVector shouldEqual expectedVector.asRight
  }

  "The MoveCommand object" should "not create new vector when coordinates exceed space limit" in {
    val space = Space.init(3, 3)
    val coordinates = Coordinates.create(3, 3)
    val direction = Direction.create("north")
    val vector = Vector.create(coordinates, direction)
    val moveCommand = MoveCommand.create
    val newVector = moveCommand.execute(space, Some(vector))

    newVector shouldEqual vector.asRight
  }

  "The MoveCommand object" should "return EmptyVectorError when it calls without vector" in {
    val space = Space.init(10, 10)
    val expectedError = EmptyVectorError
    val moveCommand = MoveCommand.create
    val error = moveCommand.execute(space, None)

    assert(error == expectedError.asLeft)
  }
}
