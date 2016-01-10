package brainiak.search.strategies

import brainiak.search.{Node, Strategy}
import scala.collection.mutable

/**
  * Created by thiago on 1/17/14.
  */
object BestFirst {
  def apply(heuristic: Node => Double): BestFirst = {
    new BestFirst(heuristic)
  }
}

class BestFirst(val heuristic: Node => Double) extends Strategy {
  val HeuristicOrdering = new Ordering[Node] {
    def compare(a: Node, b: Node) =
      (b.myCost + heuristic(b)).compare(a.myCost + heuristic(a))
  }
  var queue: mutable.PriorityQueue[Node] = new mutable.PriorityQueue[Node]()(HeuristicOrdering)
  var visited: Set[Node] = Set.empty[Node]

  def <<(state: Node): Strategy = {
    if (!contains(state)) {
      visited = visited + state
      queue enqueue state
    }
    this
  }

  def contains(state: Node): Boolean = visited contains state //queue.exists(n => n == state)

  def isEmpty: Boolean = queue.isEmpty

  def actual: Node = {
    val n = queue.dequeue()
    visited = visited - n
    n
  }
}
