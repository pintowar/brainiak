package brainiak.examples.npuzzle.ui.controller

import brainiak.examples.npuzzle.ui.Board
import rx.lang.scala.subjects.ReplaySubject
import javafx.scene.input.KeyEvent

/**
 * Created by thiago on 1/25/14.
 */
abstract class BasicController {
  val channel = ReplaySubject[Int]()

  channel.subscribe(t => board.moveAnimation(t))

  def board: Board

  def handleCommand(evt: KeyEvent): Unit

}
