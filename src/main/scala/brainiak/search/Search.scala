package brainiak.search

/**
 * Created by thiago on 1/17/14.
 */
trait Search {

  def graph: Boolean

  def inTime(init: Long, timeLimit: Long): Boolean =
    timeLimit <= 0 || (System.currentTimeMillis() - init) <= timeLimit

  def find(problem: Problem, strategy: Strategy, timeLimit: Long): Problem = {
    var visited: Set[State] = Set.empty[State]
    strategy << problem.initialState
    val init: Long = System.currentTimeMillis()
    while (!strategy.isEmpty && !problem.goalAchieved && inTime(init, timeLimit) ) {
      val actual = strategy.actual
      visited = visited + actual
      problem updateGoal actual
      val next = actual children (if (graph) visited else Set.empty[State])
      next.foreach(s => if (!problem.cutBranch(s)) strategy << s)
    }
    problem
  }

  def find(problem: Problem, strategy: Strategy): Problem =
    find(problem, strategy, -1)
}
