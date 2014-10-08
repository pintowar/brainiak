package brainiak.optimum

import scala.collection.BitSet

/**
 * Created by thiago on 08/10/14.
 */
class DynamicProgramming {

  def mountTable(values: List[Int], weights: List[Int], items: Int, capacity: Int): List[BitSet] = {
    var sol = List.empty[BitSet]
    var pre = (0 to capacity).map(it => 0)
    var aux = (0 to capacity).map(it => 0)

    (1 to items).foreach { n =>
      var lineSet = BitSet()
      (1 to capacity).foreach { w =>
        val opt1 = pre(w)
        val opt2 = if (weights(n) <= w) values(n) + pre(w-weights(n))
        else Int.MinValue

        aux = aux.updated(w, List(opt1, opt2).max)
        if (opt2 > opt1) lineSet = lineSet + w
      }
      pre = aux
      sol = lineSet :: sol
    }
    sol
  }

  def backTracking(sol: List[BitSet], weights: List[Int], items: Int, capacity: Int): List[Int] = ???

  def solve(values: List[Int], weights: List[Int], items: Int, capacity: Int): List[Int] = {
    val vals = 0 :: values
    val weis = 0 :: weights

    val sol = mountTable(vals, weis, items, capacity)
    backTracking(sol, weights, items, capacity)
  }

}
