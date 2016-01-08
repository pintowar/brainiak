package brainiak.search.strategies

import brainiak.search.{Node, Strategy}
import scala.collection.immutable.Queue

/**
 * Created by thiago on 1/17/14.
 */
object BreadthFirst {
  def apply(): BreadthFirst = new BreadthFirst
}

class BreadthFirst extends Strategy {
  var queue: Queue[Node] = Queue.empty[Node]

  def <<(state: Node): Strategy = {
    if (!queue.contains(state)) queue = queue.enqueue(state)
    this
  }

  def contains(state: Node) = queue.contains(state)

  def isEmpty: Boolean = queue.isEmpty

  def actual: Node = {
    val aux = queue.dequeue
    queue = aux._2
    aux._1
  }
}
