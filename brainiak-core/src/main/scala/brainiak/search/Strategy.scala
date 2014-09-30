package brainiak.search

/**
 * Created by thiago on 1/17/14.
 */
trait Strategy {
  def <<(state: Node): Strategy

  def isEmpty: Boolean

  def contains(state: Node): Boolean

  def actual: Node
}
