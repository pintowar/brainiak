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
  var blankIndex: Int = -1
  @volatile var movingAnimation: Boolean = false
  paint(NPuzzleNode())

  def moveAnimation(target: Int) = synchronized {
    if (!movingAnimation) {
      movingAnimation = true
      val next = NPuzzleNode(puzzleState.move(target))
      val movingHoax = children.get(blankIndex + target)
      val tt = createTransition(movingHoax, target)
      tt.setOnFinished(new EventHandler[ActionEvent] {
        override def handle(event: ActionEvent): Unit = {
          paint(next)
          movingAnimation = false
        }
      })
      tt.play()
    }
  }

  def createTransition(node: Node, target: Int) = {
    val tt = TranslateTransitionBuilder.create()
      .node(node)
    //.duration(Duration.millis(700))
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
