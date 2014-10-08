package brainiak.samples.npuzzle.ui.controller

import javafx.concurrent.Task
import javafx.scene.input.KeyEvent

import brainiak.samples.npuzzle.NPuzzleNode
import brainiak.samples.npuzzle.ui.Board
import rx.lang.scala.Observable
import rx.lang.scala.subjects.PublishSubject

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps
import scala.util.Random

/**
 * Created by thiago on 1/25/14.
 */
abstract class BasicController {
  val channel = PublishSubject[Int]()
  @volatile var numMoves = 0

  def move(move: Int) = {
    channel.onNext(move)
    numMoves += 1
  }

  def randomize = {
    new Thread(new Task[Unit]() {
      override def call(): Unit = {
        board.controls.movingStatus()
        var clone = board.puzzleState.clone
        var lastMove = 0
        (0 to 20).foreach {
          i => val aux = Random.shuffle(clone.nextIdx filterNot (List(-lastMove) contains)).head
            move(aux)
            clone = NPuzzleNode(clone.move(aux))
            lastMove = aux
        }
      }
    }).start()
  }

  val obs = channel.map(x => Observable.interval(450 millisecond).map(y => x).take(1)).concat

  obs.subscribe { x =>
    board.moveAnimation(x)
    numMoves -= 1
    board.controls.setBufferSize(numMoves)
  }

  def board: Board

  def handleCommand(evt: KeyEvent): Unit

  def startAction(): Unit

}
