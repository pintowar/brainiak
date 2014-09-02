package brainiak.examples.npuzzle.ui.controller

import brainiak.examples.npuzzle.ui.Board
import javafx.scene.input.KeyEvent

import javafx.concurrent.Task
import java.util.concurrent.LinkedBlockingQueue
import scala.util.Random
import brainiak.examples.npuzzle.NPuzzleNode

/**
 * Created by thiago on 1/25/14.
 */
abstract class BasicController {
  var queue = new LinkedBlockingQueue[Int]()

  def addToQueue(move: Int) = queue.offer(move)

  def randomize = {
    new Thread(new Task[Unit]() {
      override def call(): Unit = {
        board.controls.movingStatus()
        var clone = board.puzzleState.clone
        var lastMove = 0
        (0 to 20).foreach {
          i => val aux = Random.shuffle(clone.nextIdx filterNot (List(-lastMove) contains)).head
            addToQueue(aux)
            clone = NPuzzleNode(clone.move(aux))
            lastMove = aux
        }
      }
    }).start()
  }

  val refreshTask = new Task[Unit]() {
    override def call(): Unit = {
      while (!board.isDisabled) {
        queue.synchronized {
          if (!queue.isEmpty && !board.movingAnimation) {
            board.moveAnimation(queue.poll())
            board.controls.setBufferSize(queue.size())
          } else if (queue.isEmpty && !board.solving) board.controls.stoppedStatus()
        }
        Thread.sleep(100)
      }
    }
  }
  val background = new Thread(refreshTask)
  background.setDaemon(true)
  background.start()

  def board: Board

  def handleCommand(evt: KeyEvent): Unit

  def startAction(): Unit

}
