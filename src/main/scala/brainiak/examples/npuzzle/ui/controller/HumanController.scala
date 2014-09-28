package brainiak.examples.npuzzle.ui.controller

import javafx.scene.input.KeyEvent

import brainiak.examples.npuzzle.ui.Board

/**
 * Created by thiago on 1/25/14.
 */
class HumanController(val b: Board) extends BasicController {

  override def handleCommand(evt: KeyEvent) = {
    if (evt.getCode.isArrowKey) {
      val directions = Map(16 -> 1, 17 -> 3, 18 -> -1, 19 -> -3)
      val target = directions(evt.getCode.ordinal())
      move(target)
    }
  }

  override def startAction() = {
    board.controls.stoppedStatus()
    board.requestFocus()
  }

  override def board = b
}
