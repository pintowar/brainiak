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

  def goal: Node = new NPuzzleNode(0 to numPieces toList)
}
