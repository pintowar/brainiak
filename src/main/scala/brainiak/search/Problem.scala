package brainiak.search

/**
 * Created by thiago on 1/17/14.
 */
trait Problem {
  type Path = List[State]

  def initialState: State

  def goalAchieved: Boolean

  def updateGoal(state: State): Boolean

  def cutBranch(data: State): Boolean

  def goal: State

  def current: State

  def path: Path = {
    var states = List.empty[State]
    if (goalAchieved) {
      var aux: State = current
      while (aux.trackParent != null) {
        states = states :+ aux
        aux = aux.trackParent
      }
      states = states :+ aux
    }
    states.reverse
  }
}
