package toyrobot.errors

import scala.util.control.NoStackTrace

sealed trait AppError extends NoStackTrace { def message: String }

case object OutOfSpaceError extends AppError { val message = "Out of Space!"}
case object EmptyVectorError extends AppError { val message = "Empty vector!"}
case object EmptyCommandLinesError extends AppError { val message = "Empty command lines!"}
case class UnknownCommandError(command: String) extends AppError { val message = s"The command '$command' is unknown!"}
