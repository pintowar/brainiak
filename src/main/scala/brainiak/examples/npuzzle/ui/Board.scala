package brainiak.examples.npuzzle.ui

import scalafx.scene.layout.StackPane
import scala.util.Random
import javafx.scene.input.KeyEvent

/**
 * Created by thiago on 1/24/14.
 */
object Board {
  def apply(): Board = new Board
}

class Board extends StackPane {
  type PuzzleState = List[Int]

  var puzzleState: PuzzleState = Random.shuffle((0 to 8).toList)
  puzzleState.indices.foreach(idx => children.add(createLayer(puzzleState(idx), idx)))

  def reshuffle = {
    puzzleState = Random.shuffle((0 to 8).toList)
    children.clear()
    puzzleState.indices.foreach(idx => children.add(createLayer(puzzleState(idx), idx)))
  }

  def createLayer(value: Int, pos: Int) = new BoardLayer(value, pos)

  def handleCommand(evt: KeyEvent) = reshuffle
}
