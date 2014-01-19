package brainiak

import brainiak.npuzzle.{Description, Node}
import brainiak.search.GraphSearch
import brainiak.search.strategies.BestFirst

/**
 * Created by thiago on 1/18/14.
 */
object Main {
  def main(args: Array[String]) {
    val desc = new Description(new Node(List(8, 1, 7, 4, 5, 6, 2, 0, 3)))
    val search = new GraphSearch()
    val strategy = new BestFirst(st => st - desc.goal)
    val init = System.currentTimeMillis()
    val path = search.find(desc, strategy).path
    val end = System.currentTimeMillis()
    path foreach println
    println(path.size)
    println(s"${end - init} ms")
  }
}
