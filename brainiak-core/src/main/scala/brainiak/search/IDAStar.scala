package brainiak.search

/**
  * Created by thiago on 10/01/16.
  */
object IDAStar {
  def apply(heuristic: Node => Double): IDAStar = {
    new IDAStar(heuristic)
  }
}

class IDAStar(val heuristic: Node => Double) {
  var exploredNodes: Long = 0

  def find(problem: Problem): Node = {
    var solution = Option.empty[Node]
    var nextCostBound = heuristic(problem.initialState)

    while (solution.isEmpty) {
      val currentCostBound = nextCostBound

      solution = depthFirstSearch(problem.initialState, currentCostBound, problem.goal)
      nextCostBound += 2
    }

    solution.get
    /*Stream.from(nextCostBound.toInt, 2)
      .map(depthFirstSearch(problem.initialState, _, problem.goal))
      .takeWhile(!_.isEmpty).last.get*/
  }

  def depthFirstSearch(current: Node, currentCostBound: Double, goal: Node, parent: Node = null): Option[Node] = {
    if (current.equals(goal)) Option(current)
    else {
      exploredNodes = exploredNodes + 1
      val nexts = current.successors(if (parent == null) Set.empty[Node] else Set(parent))
      nexts.foreach { n =>
        val cost = n.myCost + heuristic(n)
        if (cost <= currentCostBound) {
          val result = depthFirstSearch(n, currentCostBound, goal, current)
          if (!result.isEmpty) return result
        }
      }
      return Option.empty[Node]
    }
  }
}