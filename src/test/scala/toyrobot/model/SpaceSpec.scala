package toyrobot.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SpaceSpec extends AnyFlatSpec with Matchers {
  "The Space object" should "have x size and y size" in {
    val space = Space.init(10, 10)
    space.x.value shouldEqual 10
    space.y.value shouldEqual 10
  }

  "The Space object" should "check limit of size" in {
    val space = Space.init(10, 10)
    val coordinatesIn = Coordinates.create(2, 2)
    val coordinatesOut = Coordinates.create(11, 11)

    space.isInside(coordinatesIn) shouldEqual true
    space.isInside(coordinatesOut) shouldEqual false
  }
}
