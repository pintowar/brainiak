package brainiak.search.strategies

import brainiak.search.{State, Strategy}
import scala.collection.mutable

/**
 * Created by thiago on 1/17/14.
 */
class BestFirst(val heuristic: State => Double) extends Strategy {
  val HeuristicOrdering = new Ordering[State] {
    def compare(a : State, b : State) =
      (b.myCost + heuristic(b)).compare(a.myCost + heuristic(a))
  }
  var queue: mutable.PriorityQueue[State] = new mutable.PriorityQueue[State]()(HeuristicOrdering)

  def <<(state: State): Strategy = {
    queue enqueue state
    this
  }

  def contains(state: State): Boolean = queue.toQueue.contains(state)

  def isEmpty: Boolean = queue.isEmpty

  def actual: State = queue.dequeue()
}
