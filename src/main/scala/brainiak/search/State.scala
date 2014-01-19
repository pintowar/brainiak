package brainiak.search

/**
 * Created by thiago on 1/17/14.
 */
trait State {
  def trackParent: State

  def children(except: Set[State]): Set[State]

  def myDepth: Int

  def myCost: Double

  def -(that: State): Double
}
