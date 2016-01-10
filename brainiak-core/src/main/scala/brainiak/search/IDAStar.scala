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

  def find(problem: Problem): Node = {
    var solution = Option.empty[Node]
    var nextCostBound = heuristic(problem.initialState)

    while (solution.isEmpty) {
      val currentCostBound = nextCostBound
      solution = depthFirstSearch(problem.initialState, currentCostBound, problem.goal)
      nextCostBound += 2
    }

    solution.get
  }

  def depthFirstSearch(current: Node, currentCostBound: Double, goal: Node, parent: Node = null): Option[Node] = {
    if (current.equals(goal)) Option(current)
    else {
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