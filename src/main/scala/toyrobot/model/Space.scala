package toyrobot.model

import Space._

case class Space private (x: XSize, y: YSize) {
  def isInside(coordinates: Coordinates): Boolean = {
    (0 to x.value contains coordinates.x.value) && (0 to y.value contains coordinates.y.value)
  }
}

object Space {
  case class XSize(value: Int) extends AnyVal
  case class YSize(value: Int) extends AnyVal

  def init(x: Int, y: Int): Space = Space(XSize(x), YSize(y))
}
