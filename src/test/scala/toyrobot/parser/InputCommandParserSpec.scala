package toyrobot.parser


import cats.syntax.either._
import org.scalatest.funspec.AnyFunSpec
import toyrobot.command._
import toyrobot.errors.UnknownCommandError


class InputCommandParserSpec extends AnyFunSpec {
  describe(description = "InputCommandParser") {
    describe(description = "when it parse input string for `LEFT`") {
      it("create LeftCommand") {
        val command = InputCommandParser.parse(inputStr = "LEFT")

        assert(command == LeftCommand.create.asRight)
      }
    }

    describe(description = "when it parse input string for `RIGHT`") {
      it("create RightCommand") {
        val command = InputCommandParser.parse(inputStr = "RIGHT")

        assert(command == RightCommand.create.asRight)
      }
    }

    describe(description = "when it parse input string for `MOVE`") {
      it("create MoveCommand") {
        val command = InputCommandParser.parse(inputStr = "MOVE")

        assert(command == MoveCommand.create.asRight)
      }
    }

    describe(description = "when it parse input string for `REPORT`") {
      it("create ReportCommand") {
        val command = InputCommandParser.parse(inputStr = "REPORT")

        assert(command == ReportCommand.create.asRight)
      }
    }

    describe(description = "when it parse input string for `PLACE 3,3,North`") {
      it("create PlaceCommand") {
        val command = InputCommandParser.parse(inputStr = "PLACE 3,3,North")

        assert(command == PlaceCommand.create(3, 3, "North").asRight)
      }
    }

    describe(description = "when it parse unknown input string") {
      it("create ResetCommand") {
        val command = InputCommandParser.parse(inputStr = "PLACE")

        assert(command == UnknownCommandError.asLeft)
      }
    }
  }
}
