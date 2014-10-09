package brainiak.optimum

import scala.collection.BitSet

/**
 * Created by thiago on 08/10/14.
 */
class DynamicProgramming(data: List[(Int, Int)], val capacity: Int) {
  private val vals = 0 :: data.map(_._1)
  private val weis = 0 :: data.map(_._2)
  val items = data.size

  def values = vals.tail

  def weights = weis.tail

  def mountTable: List[BitSet] = {

    var sol = List.empty[BitSet]
    var pre = (0 to capacity).map(it => 0)
    var aux = (0 to capacity).map(it => 0)

    (1 to items).foreach { n =>
      var lineSet = BitSet()
      (1 to capacity).foreach { w =>
        val opt1 = pre(w)
        val opt2 = if (weis(n) <= w) vals(n) + pre(w - weis(n))
        else Int.MinValue

        aux = aux.updated(w, List(opt1, opt2).max)
        if (opt2 > opt1) lineSet = lineSet + w
      }
      pre = aux
      sol = lineSet :: sol
    }
    sol.reverse
  }

  def backTracking(sol: List[BitSet]): List[Int] = {
    var w = capacity
    val nsol = BitSet() :: sol
    val taken = (1 to items).reverse.map { it =>
      if (nsol(it).contains(w)) {
        w = w - weis(it)
        1
      } else 0
    }
    taken.reverse.toList
  }

  def solve: List[Int] = {
    val sol = mountTable
    backTracking(sol)
  }

}
