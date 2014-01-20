package brainiak.search

/**
 * Created by thiago on 1/17/14.
 */
trait Problem {

  def initialState: Node

  def goalAchieved: Boolean

  def updateGoal(state: Node): Boolean

  def cutBranch(data: Node): Boolean

  def goal: Node

  def current: Node

}
