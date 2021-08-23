
package toyrobot.command


import cats.syntax.either._
import java.io.ByteArrayOutputStream

import org.scalatest.funspec.AnyFunSpec
import toyrobot.errors.EmptyVectorError
import toyrobot.model._

class ReportCommandSpec extends AnyFunSpec {
  describe(description = "ReportCommand") {
    describe(description = "when it calls") {
      it("print current coordinates") {
        val space = Space.init(10, 10)
        val coordinates = Coordinates.create(3, 3)
        val direction = Direction.create("north")
        val vector = Vector.create(coordinates, direction)
        val reportCommand = ReportCommand.create
        val out = new ByteArrayOutputStream()
        Console.withOut(out) {
          val newVector = reportCommand.execute(space, Some(vector))
          assert(newVector == vector.asRight)
        }
        assert(out.toString == "Report: 3, 3, NORTH\n")

      }
    }

    describe(description = "when it calls without vector") {
      it("returns EmptyVectorError") {
        val space = Space.init(10, 10)
        val expectedError = EmptyVectorError
        val reportCommand = ReportCommand.create
        val error = reportCommand.execute(space)

        assert(error == expectedError.asLeft)
      }
    }
  }
}