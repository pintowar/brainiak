package brainiak.examples.npuzzle.ui.controller

import brainiak.examples.npuzzle.ui.Board
import javafx.scene.input.KeyEvent

import javafx.concurrent.Task
import java.util.concurrent.LinkedBlockingQueue

/**
 * Created by thiago on 1/25/14.
 */
abstract class BasicController {
  var queue = new LinkedBlockingQueue[Int]()
  val refreshTask = new Task[Unit]() {
    override def call(): Unit = {
      while (!board.isDisabled) {
        if (!board.movingAnimation && !queue.isEmpty) {
          board.moveAnimation(queue.poll())
        }
        Thread.sleep(100)
      }
    }
  }
  val background = new Thread(refreshTask)
  //background.setDaemon(true)
  background.start()

  def board: Board

  def handleCommand(evt: KeyEvent): Unit

}