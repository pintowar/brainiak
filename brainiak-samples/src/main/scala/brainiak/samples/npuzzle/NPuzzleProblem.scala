package brainiak.samples.npuzzle

import brainiak.search.{Node, Problem}

/**
  * Created by thiago on 1/18/14.
  */
object NPuzzleProblem {
  def apply(init: Node, numPieces: Int): NPuzzleProblem = new NPuzzleProblem(init, numPieces)
}

class NPuzzleProblem(val init: Node, val numPieces: Int) extends Problem {
  var actual: Node = init

  def initialState: Node = init

  def goalAchieved: Boolean = actual equals goal

  def updateGoal(state: Node): Boolean = {
    actual = state
    true
  }

  def cutBranch(data: Node): Boolean = false

  def current: Node = actual

  def goal: Node = if (numPieces == 8) new NPuzzleNode(List(0, 1, 2, 3, 4, 5, 6, 7, 8))
  else new NPuzzleNode(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15))
}
