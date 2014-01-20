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

  def <<(state: Node): Strategy = {
    if (!queue.exists(n => n == state)) queue enqueue state
    this
  }

  def contains(state: Node): Boolean = queue.exists(n => n == state)

  def isEmpty: Boolean = queue.isEmpty

  def actual: Node = queue.dequeue()
}
