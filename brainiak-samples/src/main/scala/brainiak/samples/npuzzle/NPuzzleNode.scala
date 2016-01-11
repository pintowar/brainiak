package brainiak.samples.npuzzle

import brainiak.search.Node
import scala.util.Random

/**
  * Created by thiago on 1/18/14.
  */
object NPuzzleNode {
  def apply(parent: Node, depth: Int, cost: Double, state: List[Int]): NPuzzleNode =
    new NPuzzleNode(parent, depth, cost, state, 0)

  def apply(state: List[Int]): NPuzzleNode = new NPuzzleNode(state)

  def apply(n: Int): NPuzzleNode = new NPuzzleNode(n)

  def apply(): NPuzzleNode = new NPuzzleNode()
}

class NPuzzleNode(val parent: Node, val depth: Int, val cost: Double, val state: List[Int], val movement: Int) extends Node {
  def this(state: List[Int]) = this(null, 0, 0, state, 0)

  def this(n: Int) = this(null, 0, 0, Random.shuffle((0 until n).toList), 0)

  def this() = this(9)

  val zeroIdx = state.indexOf(0)
  val rowSize: Int = Math.sqrt(state.size).toInt
  assert(rowSize == Math.sqrt(state.size))


  def move(direction: Int): List[Int] = state.updated(zeroIdx, state(zeroIdx + direction)).updated(zeroIdx + direction, 0)

  def canMove(direction: Int): Boolean = nextIdx.contains(direction)

  def trackParent: Node = parent

  val nextIdx: Set[Int] = {
    Set(rowSize, 1, -rowSize, -1).filter { p =>
      val next = p + zeroIdx
      (next >= 0 && next < state.size) &&
        !(zeroIdx % rowSize == 0 && next % rowSize == rowSize - 1) &&
        !(zeroIdx % rowSize == rowSize - 1 && next % rowSize == 0)
    }
  }

  def myDepth: Int = depth

  def myCost: Double = cost

  def -(o: Node): Double = o match {
    //    case that: NPuzzleNode => this.state.zip(that.state).count(t => t._1 != t._2)
    case that: NPuzzleNode => (0 until state.size).zip(this.state).filter(_._2 != 0).map(t => (t._1 / rowSize - (t._2 - 1) / rowSize).abs + (t._1 % rowSize - (t._2 - 1) % rowSize).abs).sum
    case _ => Double.PositiveInfinity
  }

  def successors(except: Set[Node]): Set[Node] =
    nextIdx.map(it => new NPuzzleNode(this, depth + 1, cost + 1, move(it), it)).toSet -- except

  override def toString: String =
    state.sliding(rowSize, rowSize).map(seg => seg.mkString("|")).mkString("\n")

  override def equals(o: Any) = o match {
    case that: NPuzzleNode => that.state == state
    case _ => false
  }

  override def hashCode = state.size * (rowSize + state.hashCode())

  override def clone: NPuzzleNode = new NPuzzleNode(parent, depth, cost, state, movement)

}
