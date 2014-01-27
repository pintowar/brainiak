package brainiak.examples.npuzzle.ui

import scalafx.scene.layout.StackPane
import javafx.scene.input.KeyEvent
import brainiak.examples.npuzzle.NPuzzleNode
import javafx.animation.TranslateTransitionBuilder
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Node
import brainiak.examples.npuzzle.ui.controller.{HumanController, BasicController}
import scala.util.Random

/**
 * Created by thiago on 1/24/14.
 */
object Board {
  def apply(controls: Controls): Board = new Board(controls)
}

class Board(val controls: Controls) extends StackPane {

  val controller: BasicController = new HumanController(this)
  var puzzleState: NPuzzleNode = null
  var blankIndex: Int = -1
  @volatile var movingAnimation: Boolean = false
  paint(NPuzzleNode())

  def moveAnimation(target: Int) = {
    if (!movingAnimation && puzzleState.canMove(target)) {
      movingAnimation = true
      this.synchronized {
        val next = NPuzzleNode(puzzleState.move(target))
        val movingHoax = children.get(blankIndex + target)
        val transition = createTransition(movingHoax, target)
        transition.setOnFinished(new EventHandler[ActionEvent] {
          override def handle(event: ActionEvent): Unit = {
            paint(next)
            movingAnimation = false
          }
        })
        transition.play()
      }
    }
  }

  def createTransition(node: Node, target: Int) = {
    val builder = TranslateTransitionBuilder.create()
      .node(node)
    if (math.abs(target) == 1) builder.byX(target * -150)
    else builder.byY(if (target > 0) -150 else 150)
    builder.build()
  }

  def paint(newState: NPuzzleNode) = {
    puzzleState = newState
    blankIndex = puzzleState.state.indexOf(0)
    children.synchronized {
      children.clear()
      puzzleState.state.indices.foreach(idx => children.add(createLayer(puzzleState.state(idx), idx)))
    }
  }

  controls.setRandomizeAction(new EventHandler[ActionEvent] {

    def handle(p1: ActionEvent): Unit = {
      controls.movingStatus()
      var clone = puzzleState.clone
      var lastMove = 0
      (0 to 20).foreach {
        i => val aux = Random.shuffle(clone.nextIdx filterNot (List(-lastMove) contains)).head
          controller.addToQueue(aux)
          clone = NPuzzleNode(clone.move(aux))
          lastMove = aux
      }
    }

  })

  def createLayer(value: Int, pos: Int) = new BoardLayer(value, pos)

  def handleCommand(evt: KeyEvent) = controller.handleCommand(evt)
}
