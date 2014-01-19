package brainiak.npuzzle

import brainiak.search.State
import scala.util.Random

/**
 * Created by thiago on 1/18/14.
 */
class Node(val parent: State, val depth: Int, val cost: Double, val state: List[Int]) extends State {
  def this(state: List[Int]) = this(null, 0, 0, state)

  def this(n: Int) = this(null, 0, 0, Random.shuffle((0 until n).toList))

  def this() = this(9)

  val zeroIdx = state.indexOf(0)
  val rowSize: Int = Math.sqrt(state.size).toInt
  assert(rowSize == Math.sqrt(state.size))

  def move(direction: Int): List[Int] = {
    state.updated(zeroIdx, state(zeroIdx + direction)).updated(zeroIdx + direction, 0)
  }

  def trackParent: State = parent

  def nextIdx: List[Int] = {
    List(rowSize, 1, -rowSize, -1).filter {
      p =>
        val next = p + zeroIdx
        (next >= 0 && next < state.size) &&
          !(zeroIdx % rowSize == 0 && next % rowSize == 2) &&
          !(zeroIdx % rowSize == 2 && next % rowSize == 0)
    }
  }

  def myDepth: Int = depth

  def myCost: Double = cost

  def -(o: State): Double = o match {
    case that: Node => this.state.zip(that.state).filter(t => t._1 != t._2).size
    case _ => Double.MaxValue
  }

  def children(except: Set[State]): Set[State] =
    (nextIdx.map(it => new Node(this, depth + 1, cost + 1, move(it)))
      .toSet -- except)

  override def toString: String =
    state.sliding(rowSize, rowSize).map(seg => seg.mkString("|")).mkString("\n")

  override def equals(o: Any) = o match {
    case that: Node => that.state == state
    case _ => false
  }

  override def hashCode = state.size * (rowSize + state.hashCode())
}
