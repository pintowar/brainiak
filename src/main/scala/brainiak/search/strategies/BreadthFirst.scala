package brainiak.search.strategies

import brainiak.search.{State, Strategy}
import scala.collection.immutable.Queue

/**
 * Created by thiago on 1/17/14.
 */
class BreadthFirst extends Strategy {
  var queue: Queue[State] = Queue.empty[State]

  def <<(state: State): Strategy = {
    queue = queue enqueue state
    this
  }

  def contains(state: State) = queue.contains(state)

  def isEmpty: Boolean = queue.isEmpty

  def actual: State = {
    val aux = queue.dequeue
    queue = aux._2
    aux._1
  }
}
