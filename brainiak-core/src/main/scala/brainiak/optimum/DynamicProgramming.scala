package brainiak.optimum

import scala.collection.BitSet

/**
 * Created by thiago on 08/10/14.
 */
class DynamicProgramming(dValues: Seq[Int], dWeights: Seq[Int], val capacity: Int) {

  def this(data: Seq[(Int, Int)], capacity: Int) = this(data.map(_._1), data.map(_._2), capacity)

  if (dValues.size != dWeights.size)
    throw new IllegalArgumentException("Values and Weights must contain the same number of items.")

  private val vals = Seq(0) ++ dValues
  private val weis = Seq(0) ++ dWeights
  val items = dValues.size

  def values = vals.tail

  def weights = weis.tail

  def mountTable: Seq[BitSet] = {

    val zeroCapacity = (0 to capacity).map(it => 0)
    val outerVars = (zeroCapacity, Seq.empty[BitSet])

    val sol = (1 to items).foldLeft(outerVars) { (oVar, n) =>
      val innerVars = (zeroCapacity, BitSet.empty)

      val inner = (1 to capacity).foldLeft(innerVars) { (iVar, w) =>
        val opt1 = oVar._1(w)
        val opt2 = if (weis(n) <= w) vals(n) + oVar._1(w - weis(n)) else Int.MinValue

        val lineSet = if (opt2 > opt1) iVar._2 + w else iVar._2
        val aux = iVar._1.updated(w, Seq(opt1, opt2).max)
        (aux, lineSet)
      }
      (inner._1, oVar._2 :+ inner._2)
    }
    sol._2
  }

  def backTracking(sol: Seq[BitSet]): Seq[Int] = {
    val nsol = Seq(BitSet.empty) ++ sol

    (1 to items).reverse.foldLeft((capacity, Seq.empty[Int])) { (vars, it) =>
      val w = vars._1
      val caps = if (nsol(it).contains(w)) (w - weis(it), 1) else (w, 0)
      (caps._1, Seq(caps._2) ++ vars._2)
    }._2
  }

  def solve: Seq[Int] = {
    val sol = mountTable
    backTracking(sol)
  }

}
