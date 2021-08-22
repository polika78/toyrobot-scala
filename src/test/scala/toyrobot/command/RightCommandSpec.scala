package toyrobot.command


import cats.syntax.either._
import org.scalatest.funspec.AnyFunSpec
import toyrobot.errors.EmptyVectorError
import toyrobot.model._

class RightCommandSpec extends AnyFunSpec {
  describe(description = "RightCommand") {
    describe(description = "when it calls once from North") {
      it("create new vector with West direction") {
        val space = Space.init(10, 10)
        val coordinates = Coordinates.create(3, 3)
        val direction = Direction.create("north")
        val vector = Vector.create(coordinates, direction)
        val expectedVector = Vector.create(coordinates, Direction.create("east"))
        val rightCommand = RightCommand.create
        val newVector = rightCommand.execute(space, Some(vector))

        assert(newVector == expectedVector.asRight)
      }
    }

    describe(description = "when it calls twice from North") {
      it("create new vector with South direction") {
        val space = Space.init(10, 10)
        val coordinates = Coordinates.create(3, 3)
        val direction = Direction.create("east")
        val vector = Vector.create(coordinates, direction)
        val expectedVector = Vector.create(coordinates, Direction.create("west"))
        val rightCommand = RightCommand.create
        val newVector = rightCommand.execute(space, Some(vector)) match {
          case Right(newVector) => {
            val nextRightCommand = RightCommand.create
            nextRightCommand.execute(space, Some(newVector))
          }
        }

        assert(newVector == expectedVector.asRight)
      }
    }
    describe(description = "when it calls without vector") {
      it("returns EmptyVectorError") {
        val space = Space.init(10, 10)
        val expectedError = EmptyVectorError
        val rightCommand = RightCommand.create
        val error = rightCommand.execute(space)

        assert(error == expectedError.asLeft)
      }
    }
  }
}
