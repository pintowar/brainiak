package brainiak.examples.npuzzle.ui

import javafx.animation.TranslateTransitionBuilder
import javafx.application.Platform
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.Node
import javafx.scene.input.KeyEvent

import brainiak.examples.npuzzle.NPuzzleNode
import brainiak.examples.npuzzle.ui.controller.{BasicController, ControllerFactory, HumanController}

import scalafx.scene.layout.StackPane

/**
 * Created by thiago on 1/24/14.
 */
object Board {
  def apply(controls: Controls, numHoax: Int): Board = {
    val board = new Board(controls, numHoax)
    board.setFocusTraversable(true)
    board
  }
}

class Board(val controls: Controls, numHoax: Int) extends StackPane {

  var controller: BasicController = new HumanController(this)
  var puzzleState: NPuzzleNode = null
  var blankIndex: Int = -1

  paint(NPuzzleNode(List(6, 0, 2, 1, 4, 8, 7, 3, 5)))

  def moveAnimation(target: Int) = {
    if (puzzleState.canMove(target)) {
        val next = NPuzzleNode(puzzleState.move(target))
        val movingHoax = children.get(blankIndex + target)
        val transition = createTransition(movingHoax, target)
        transition.setOnFinished(new EventHandler[ActionEvent] {
          override def handle(event: ActionEvent): Unit = {
            paint(next)
            if (controller.numMoves == 0) controls.stoppedStatus()
          }
        })
        transition.play()
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
      Platform.runLater(new Runnable {
        def run(): Unit = {
          children.clear()
          puzzleState.state.indices.foreach(idx => children.add(createLayer(puzzleState.state(idx), idx)))
        }
      })
  }

  controls.setRandomizeAction(new EventHandler[ActionEvent] {

    def handle(p1: ActionEvent): Unit = {
      controller.randomize
    }

  })

  controls.setStartAction(new EventHandler[ActionEvent] {

    def handle(p1: ActionEvent): Unit = {
      controller = ControllerFactory(controls.combobox.getValue, Board.this)
      controller.startAction()
    }

  })

  def createLayer(value: Int, pos: Int) = new BoardLayer(value, pos, numHoax)

  def handleCommand(evt: KeyEvent) = controller.handleCommand(evt)
}
