package brainiak.search.strategies

import brainiak.search.{Node, Strategy}
import scala.collection.immutable.Stack

/**
 * Created by thiago on 1/17/14.
 */
object DepthFirst {
  def apply: DepthFirst = new DepthFirst
}

class DepthFirst extends Strategy {
  var stack: Stack[Node] = Stack.empty[Node]

  def <<(state: Node): Strategy = {
    if (!stack.contains(state)) stack = stack push state
    this
  }

  def contains(state: Node) = stack.contains(state)

  def isEmpty: Boolean = stack.isEmpty

  def actual: Node = {
    val aux = stack.pop2
    stack = aux._2
    aux._1
  }
}
