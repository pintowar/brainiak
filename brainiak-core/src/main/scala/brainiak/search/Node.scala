package brainiak.search

/**
 * Created by thiago on 1/17/14.
 */
trait Node {
  type Path = Vector[Node]

  def trackParent: Node

  def successors(except: Set[Node]): Set[Node]

  def successors: Set[Node] = successors(Set.empty[Node])

  def myDepth: Int

  def myCost: Double

  def -(that: Node): Double

  def path: Path = {
    var states = Vector.empty[Node]
    var aux: Node = this
    while (aux.trackParent != null) {
      states = states :+ aux
      aux = aux.trackParent
    }
    states = states :+ aux
    states.reverse
  }
}
