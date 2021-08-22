package toyrobot

import scala.io.Source
import org.scalatest.funspec.AnyFunSpec
import java.io.ByteArrayOutputStream

import cats.effect.{ExitCode, IO}
import toyrobot.errors.EmptyCommandLinesError
import toyrobot.model._
import toyrobot.parser.InputCommandParser


class RunnerSpec extends AnyFunSpec {
  describe(description = "Runner") {
    describe(description = "when it runs") {
      it("reads command strings and runs commands") {
        val moveController = MoveController.make[IO]
        println("Running...")
        println(moveController)
        val robotProgram = for {
          _ <- IO(println("Preparing robot move controller... done!"))
          preparedRobot <- moveController.prepare(inputFile = "./resources/input")
          _ <- IO(println(s"Moving the robot..."))
          vector <- preparedRobot.run
          _ <- IO(println(s"The robot has moved!"))
        } yield vector

        val out = new ByteArrayOutputStream()
        Console.withOut(out) {
          robotProgram
            .flatMap(vector => IO(println(s"Here's robot vector: $vector")))
        }

//        val source = Source.fromFile("./resources/input")
//        val space = Space.init(10, 10)
//        val line :: tail = source.getLines().toList
//        val out = new ByteArrayOutputStream()
//        Console.withOut(out) {
//          def run(line: String,
//                  lines: List[String],
//                  vector: Option[Vector] = None): Unit = {
//            val command = InputParser.parse(line)
//            Controller.run(space, vector, command) match {
//              case Right(newVector) => {
//                if (lines.length > 0) {
//                  val newLine :: tail = lines
//                  run(newLine, tail, Some(newVector))
//                }
//              }
//              case Left(error) => println(error.message)
//            }
//          }
//
//          run(line, tail, None)
//        }
//        source.close()

        assert(s"$out" == "Current vector: 2, 5, NORTH\n")
      }
    }
  }
}
