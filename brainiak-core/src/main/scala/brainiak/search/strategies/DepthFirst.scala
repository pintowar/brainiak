package brainiak.search.strategies

import brainiak.search.{Node, Strategy}
import scala.collection.immutable.List

/**
 * Created by thiago on 1/17/14.
 */
object DepthFirst {
  def apply: DepthFirst = new DepthFirst
}

class DepthFirst extends Strategy {
  //var stack: Stack[Node] = Stack.empty[Node]
  var stack: List[Node] = List.empty[Node]

  def <<(state: Node): Strategy = {
    if (!stack.contains(state)) stack = state :: stack
    this
  }

  def contains(state: Node) = stack.contains(state)

  def isEmpty: Boolean = stack.isEmpty

  def actual: Node = {
    val aux = stack.head
    stack = stack.tail
    aux
  }
}
