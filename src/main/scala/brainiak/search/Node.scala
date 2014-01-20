package brainiak.search

/**
 * Created by thiago on 1/17/14.
 */
trait Node {
  type Path = List[Node]

  def trackParent: Node

  def children(except: Set[Node]): Set[Node]

  def myDepth: Int

  def myCost: Double

  def -(that: Node): Double

  def path: Path = {
    var states = List.empty[Node]
    var aux: Node = this
    while (aux.trackParent != null) {
      states = states :+ aux
      aux = aux.trackParent
    }
    states = states :+ aux
    states.reverse
  }
}
