package toyrobot.model

import Coordinates._

case class Coordinates private (x: X, y: Y) {
  override def toString: String = s"$x, $y"
  def updateX(x: Int): Coordinates = this.copy(x = X(x))
  def updateY(y: Int): Coordinates = this.copy(y = Y(y))
}


object Coordinates {
  case class X(value: Int) extends AnyVal {
    override def toString: String = s"$value"
  }
  case class Y(value: Int) extends AnyVal {
    override def toString: String = s"$value"
  }

  def create(x: Int, y: Int): Coordinates = Coordinates(X(x), Y(y))
}
