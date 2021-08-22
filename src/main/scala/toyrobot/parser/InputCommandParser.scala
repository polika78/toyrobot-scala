package toyrobot.parser

import cats.syntax.either._
import toyrobot.command._
import toyrobot.errors.{AppError, UnknownCommandError}


object InputCommandParser {
  def parse(inputStr: String): Either[AppError, Command] = {
    val placeCommandPattern = raw"(place)\s*(\d+)\s*,\s*(\d+)\s*,\s*(north|south|east|west)\s*".r

    inputStr.toLowerCase() match {
      case "left" => LeftCommand.create.asRight
      case "right" => RightCommand.create.asRight
      case "move" => MoveCommand.create.asRight
      case "report" => ReportCommand.create.asRight
      case placeCommandPattern(_, x, y, facing) => PlaceCommand.create(x.toInt, y.toInt, facing).asRight
      case _ => UnknownCommandError(inputStr).asLeft
    }
  }
}
