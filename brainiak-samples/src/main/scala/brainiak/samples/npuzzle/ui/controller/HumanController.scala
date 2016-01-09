package brainiak.samples.npuzzle.ui.controller

import javafx.scene.input.KeyEvent

import brainiak.samples.npuzzle.ui.Board

/**
  * Created by thiago on 1/25/14.
  */
class HumanController(val b: Board) extends BasicController {

  override def handleCommand(evt: KeyEvent) = {
    if (evt.getCode.isArrowKey) {
      val directions = if (board.numHoax == 8) Map(16 -> 1, 17 -> 3, 18 -> -1, 19 -> -3)
      else Map(16 -> 1, 17 -> 4, 18 -> -1, 19 -> -4)
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
