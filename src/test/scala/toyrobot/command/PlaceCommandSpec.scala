package toyrobot.command


import cats.syntax.either._
import org.scalatest.funspec.AnyFunSpec
import toyrobot.errors.{EmptyVectorError, OutOfSpaceError}
import toyrobot.model._


class PlaceCommandSpec extends AnyFunSpec {
  describe(description = "PlaceCommand") {
    describe(description = "when it place within space") {
      it("create new vector") {
        val space = Space.init(10, 10)
        val direction = Direction.create("north")
        val expectedVector = Vector.create(Coordinates.create(3, 3), direction)
        val placeCommand = PlaceCommand.create(3, 3, facing = "North")
        val newVector = placeCommand.execute(space)

        assert(newVector == expectedVector.asRight)
      }
    }

    describe(description = "when it place outside of space") {
      it("raise an error") {
        val space = Space.init(5, 5)
        val expectedError = OutOfSpaceError
        val placeCommand = PlaceCommand.create(8, 3, facing = "North")
        val error = placeCommand.execute(space)

        assert(error == expectedError.asLeft)
      }
    }
  }
}
