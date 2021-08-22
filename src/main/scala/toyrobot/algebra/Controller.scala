package toyrobot.algebra

import toyrobot.model.Vector

trait Controller[F[_]]{
  def prepare(inputFile: String): F[Controller[F]]
  def run: F[Vector]
}
