package brainiak.samples.npuzzle

import brainiak.search.{Node, Problem}

/**
 * Created by thiago on 1/18/14.
 */
object NPuzzleProblem {
  def apply(init: Node): NPuzzleProblem = new NPuzzleProblem(init)
}

class NPuzzleProblem(val init: Node) extends Problem {
  var actual: Node = init

  def initialState: Node = init

  def goalAchieved: Boolean = actual equals goal

  def updateGoal(state: Node): Boolean = {
    actual = state
    true
  }

  def cutBranch(data: Node): Boolean = false

  def current: Node = actual

  def goal: Node = new NPuzzleNode(List(0, 1, 2, 3, 4, 5, 6, 7, 8))
}
