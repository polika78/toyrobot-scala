package toyrobot
import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._
import toyrobot.errors._
import toyrobot.interpreter.MoveController
import toyrobot.model.{Coordinates, Direction, Vector}

object MainIO extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    val inputFilePath :: _ = args
    val initVector = Vector.create(Coordinates.create(0, 0), Direction.create("North"))
    val moveController = MoveController.make[IO]

    val robotProgram = for {
      _ <- IO(println("Preparing robot move controller... done!"))
      preparedRobot <- moveController.prepare(inputFile = inputFilePath)
      _ <- IO(println(s"Moving the robot..."))
      vector <- preparedRobot.run
      _ <- IO(println(s"The robot has moved!"))
    } yield vector

    robotProgram
      .recoverWith({
        case EmptyCommandLinesError =>
          IO(
            println("The Robot does not have command lines! :(")
          ) >> IO(initVector)
        case NoPlaceCommandError =>
          IO(
            println("The Robot requires first place command! :(")
          ) >> IO(initVector)
        case OutOfSpaceError =>
          IO(
            println("The Robot placed out of space! :(")
          ) >> IO(initVector)
        case EmptyVectorError =>
          IO(
            println("The Robot does not have initial vector! :(")
          ) >> IO(initVector)
        case UnknownCommandError(command) =>
          IO(
            println(s"The Robot cannot handle '$command' command!  :(")
          ) >> IO(initVector)
      })
      .flatMap(vector => IO(println(s"Here's robot vector: $vector")))
      .as(ExitCode.Success)
  }
}
