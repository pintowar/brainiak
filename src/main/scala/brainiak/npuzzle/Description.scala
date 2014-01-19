package brainiak.npuzzle

import brainiak.search.{State, Problem}

/**
 * Created by thiago on 1/18/14.
 */
class Description(val init: State) extends Problem {
  var actual: State = init

  def initialState: State = init

  def goalAchieved: Boolean = actual equals goal

  def updateGoal(state: State): Boolean = {
    actual = state
    true
  }

  def cutBranch(data: State): Boolean = false

  def current: State = actual

  def goal: State = new Node(List(0, 1, 2, 3, 4, 5, 6, 7, 8))
}
