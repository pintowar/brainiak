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
  var aux: Set[Node] = Set.empty[Node]

  def <<(state: Node): Strategy = {
    if (!contains(state)) {
      aux = aux + state
      queue = queue.enqueue(state)
    }
    this
  }

  def contains(state: Node) = aux.contains(state)

  def isEmpty: Boolean = queue.isEmpty

  def actual: Node = {
    val n = queue.dequeue
    queue = n._2
    aux = aux - n._1
    n._1
  }
}
