package brainiak.search

/**
 * Created by thiago on 1/17/14.
 */
trait Strategy {
  def <<(state: State): Strategy

  def isEmpty: Boolean

  def contains(state: State): Boolean

  def actual: State
}
