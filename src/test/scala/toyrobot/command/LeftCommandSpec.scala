package toyrobot.command

import cats.syntax.either._
import org.scalatest.funspec.AnyFunSpec
import toyrobot.errors.EmptyVectorError
import toyrobot.model._

class LeftCommandSpec extends AnyFunSpec {
  describe(description = "LeftCommand") {
    describe(description = "when it calls once from North") {
      it("create new vector with West direction") {
        val space = Space.init(10, 10)
        val coordinates = Coordinates.create(3, 3)
        val direction = Direction.create("north")
        val vector = Vector.create(coordinates, direction)
        val expectedVector = Vector.create(coordinates, Direction.create("west"))
        val leftCommand = LeftCommand.create
        val newVector = leftCommand.execute(space, Some(vector))

        assert(newVector == expectedVector.asRight)
      }
    }

    describe(description = "when it calls twice from North") {
      it("create new vector with South direction") {
        val space = Space.init(10, 10)
        val coordinates = Coordinates.create(3, 3)
        val direction = Direction.create("north")
        val vector = Vector.create(coordinates, direction)
        val expectedVector = Vector.create(coordinates, Direction.create("south"))
        val leftCommand = LeftCommand.create
        val newVector = leftCommand.execute(space, Some(vector)) match {
          case Right(newVector) => {
            val nextLeftCommand = LeftCommand.create
            nextLeftCommand.execute(space, Some(newVector))
          }
        }

        assert(newVector == expectedVector.asRight)
      }
    }

    describe(description = "when it calls without vector") {
      it("returns EmptyVectorError") {
        val space = Space.init(10, 10)
        val expectedError = EmptyVectorError
        val leftCommand = LeftCommand.create
        val error = leftCommand.execute(space, None)

        assert(error == expectedError.asLeft)
      }
    }
  }
}
