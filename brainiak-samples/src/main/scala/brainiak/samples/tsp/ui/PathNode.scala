package brainiak.samples.tsp.ui

import brainiak.search.Node

import scala.util.Random

/**
 * Created by thiago on 27/05/15.
 */
class PathNode(val points: Seq[(Double, Double)]) extends Node {

  def trackParent: Node = ???

  def myDepth: Int = ???

  def dist(x: Seq[(Double, Double)]): Double = Math.pow(x.head._1 - x.last._1, 2) + Math.pow(x.head._2 - x.last._2, 2)

  def myCost: Double = (points :+ points.head).sliding(2).map(x => Math.sqrt(dist(x))).sum

  def -(o: Node): Double = ???

  def successors(except: Set[Node]): Set[Node] = {
    val (i, j) = Seq(Random.shuffle(0 until points.size toList).slice(0, 2).sorted).map(tup => tup(0) -> tup(1)).head

    Set(new PathNode(points.patch(i, Seq(points(j)), 1).patch(j, Seq(points(i)), 1)), //swap points
      new PathNode(points.patch(i, points.slice(i, j + 1).reverse, Math.abs(i - j) + 1))) //reverse points
  }

  override def toString: String = points.map(p => s"(${p._1}, ${p._2})").mkString(" ")

  override def equals(o: Any) = o match {
    case that: PathNode => that.points == points
    case _ => false
  }

  override def hashCode = points.size * (points.hashCode())

  override def clone: PathNode = new PathNode(points)
}
