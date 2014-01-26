package brainiak.examples.npuzzle.ui

import scalafx.scene.layout.StackPane
import javafx.scene.input.KeyEvent
import brainiak.examples.npuzzle.NPuzzleNode
import javafx.animation.TranslateTransitionBuilder
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Node
import brainiak.examples.npuzzle.ui.controller.{HumanController, BasicController}

/**
 * Created by thiago on 1/24/14.
 */
object Board {
  def apply(): Board = new Board
}

class Board extends StackPane {

  val controller: BasicController = new HumanController(this)
  var puzzleState: NPuzzleNode = null
  var movingAnimation: Boolean = false
  var blankIndex: Int = -1
  paint(NPuzzleNode())

  def moveAnimation(target: Int) = {
    if (!movingAnimation) {
      val next = NPuzzleNode(puzzleState.move(target))
      val movingHoax = children.remove(blankIndex + target)
      children.add(movingHoax)
      val tt = createTransition(movingHoax, target)
      tt.onFinishedProperty().set(new EventHandler[ActionEvent] {
        override def handle(event: ActionEvent): Unit = paint(next)
      })
      tt.play()
    }
  }

  def createTransition(node: Node, target: Int) = {
    val tt = TranslateTransitionBuilder.create()
      .node(node)
    //.duration(Duration.millis(400))
    if (math.abs(target) == 1) tt.byX(target * -150)
    else tt.byY(if (target > 0) -150 else 150)
    tt.build()
  }

  def paint(newState: NPuzzleNode) = {
    puzzleState = newState
    blankIndex = puzzleState.state.indexOf(0)
    children.clear()
    puzzleState.state.indices.foreach(idx => children.add(createLayer(puzzleState.state(idx), idx)))
  }

  def createLayer(value: Int, pos: Int) = new BoardLayer(value, pos)

  def handleCommand(evt: KeyEvent) = controller.handleCommand(evt)
}
