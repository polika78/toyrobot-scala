package toyrobot.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CoordinatesSpec extends AnyFlatSpec with Matchers {
  "The Coordinates object" should "have x, y" in {
    val coordinates = Coordinates.create(10, 10)
    coordinates.x.value shouldEqual 10
    coordinates.y.value shouldEqual 10
  }

  "The Coordinates object" should "create by updating x" in {
    val coordinates = Coordinates.create(10, 10)
    val updatedCoordinates = coordinates.updateX(9)
    updatedCoordinates.x.value shouldEqual 9
  }

  "The Coordinates object" should "create by updating y" in {
    val coordinates = Coordinates.create(10, 10)
    val updatedCoordinates = coordinates.updateY(9)
    updatedCoordinates.y.value shouldEqual 9
  }
}
