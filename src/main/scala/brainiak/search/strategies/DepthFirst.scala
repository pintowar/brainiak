package brainiak.search.strategies

import brainiak.search.{State, Strategy}
import scala.collection.immutable.Stack

/**
 * Created by thiago on 1/17/14.
 */
class DepthFirst extends Strategy {
  var stack: Stack[State] = Stack.empty[State]

  def <<(state: State): Strategy = {
    stack = stack push state
    this
  }

  def contains(state: State) = stack.contains(state)

  def isEmpty: Boolean = stack.isEmpty

  def actual: State = {
    val aux = stack.pop2
    stack = aux._2
    aux._1
  }
}
