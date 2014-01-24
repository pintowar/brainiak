package brainiak

import brainiak.examples.npuzzle.{NPuzzleProblem, NPuzzleNode}

import brainiak.search.strategies.BestFirst
import brainiak.search.types.GraphSearch

/**
 * Created by thiago on 1/18/14.
 */
object Main {
  def main(args: Array[String]) {
    val problem = NPuzzleProblem(NPuzzleNode(List(8, 1, 7, 4, 5, 6, 2, 3, 0)))
    val search = GraphSearch()
    val strategy = BestFirst(st => st - problem.goal)
    val init = System.currentTimeMillis()
    val path = search.find(problem, strategy).path
    val end = System.currentTimeMillis()
    path foreach println
    println(s"states: ${path.size}, action: ${path.size - 1}")
    println(s"${end - init} ms")
  }
}
