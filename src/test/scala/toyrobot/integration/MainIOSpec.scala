package toyrobot

import org.scalatest.funspec.AnyFunSpec
import java.io.ByteArrayOutputStream


class MainIOSpec extends AnyFunSpec {
  describe(description = "MainIO") {
    describe(description = "when it runs with normal command input") {
      it("reads command strings and runs commands") {
        val mainIO = MainIO.run(List("./resources/integration/input-normal"))
        val out = new ByteArrayOutputStream()
        Console.withOut(out) {
          mainIO.unsafeRunSync()
        }
        val expectedOutput =
          """Preparing robot move controller... done!
Moving the robot...
Report: 2, 5, EAST
The robot has moved!
Here's robot vector: 2, 5, EAST
"""
        assert(s"$out" == expectedOutput)
      }
    }

    describe(description = "when it runs with empty command input") {
      it("reads command strings and shows an error message") {
        val mainIO = MainIO.run(List("./resources/integration/input-empty"))
        val out = new ByteArrayOutputStream()
        Console.withOut(out) {
          mainIO.unsafeRunSync()
        }
        val expectedOutput =
          """Preparing robot move controller... done!
Moving the robot...
The Robot does not have command lines! :(
Here's robot vector: 0, 0, NORTH
"""
        assert(s"$out" == expectedOutput)
      }
    }

    describe(description = "when it runs with command input exceeding space limit") {
      it("reads command strings and runs commands by ignoring exceeded moving") {
        val mainIO = MainIO.run(List("./resources/integration/input-exceedspace"))
        val out = new ByteArrayOutputStream()
        Console.withOut(out) {
          mainIO.unsafeRunSync()
        }
        val expectedOutput =
          """Preparing robot move controller... done!
Moving the robot...
Report: 0, 3, WEST
The robot has moved!
Here's robot vector: 0, 3, WEST
"""
        assert(s"$out" == expectedOutput)
      }
    }

    describe(description = "when it runs with command input placing out of space") {
      it("reads command strings and and shows an error message") {
        val mainIO = MainIO.run(List("./resources/integration/input-outofspace"))
        val out = new ByteArrayOutputStream()
        Console.withOut(out) {
          mainIO.unsafeRunSync()
        }
        val expectedOutput =
          """Preparing robot move controller... done!
Moving the robot...
The Robot placed out of space! :(
Here's robot vector: 0, 0, NORTH
"""
        assert(s"$out" == expectedOutput)
      }

      describe(description = "when it runs with command input with unknown command") {
        it("reads command strings and and shows an error message") {
          val mainIO =
            MainIO.run(List("./resources/integration/input-unknowncommand"))
          val out = new ByteArrayOutputStream()
          Console.withOut(out) {
            mainIO.unsafeRunSync()
          }
          val expectedOutput =
            """Preparing robot move controller... done!
Moving the robot...
Report: 1, 3, WEST
The Robot cannot handle 'FOO' command!  :(
Here's robot vector: 0, 0, NORTH
"""
          assert(s"$out" == expectedOutput)
        }
      }

      describe(description = "when it runs with command input without place command") {
        it("reads command strings and and shows an error message") {
          val mainIO =
            MainIO.run(List("./resources/integration/input-withoutplacecommand"))
          val out = new ByteArrayOutputStream()
          Console.withOut(out) {
            mainIO.unsafeRunSync()
          }
          val expectedOutput =
            """Preparing robot move controller... done!
Moving the robot...
The Robot requires first place command! :(
Here's robot vector: 0, 0, NORTH
"""
          assert(s"$out" == expectedOutput)
        }
      }
    }
  }
}
