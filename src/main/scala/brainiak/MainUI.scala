package brainiak

/**
 * Created by thiago on 1/23/14.
 */

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import brainiak.examples.npuzzle.ui.Board
import javafx.event.EventHandler
import javafx.scene.input.KeyEvent

object MainUI extends JFXApp {
  stage = new PrimaryStage {
    val board = Board()
    title = "8 Puzzle"
    resizable = false
    scene = new Scene {
      content = board
      onKeyPressed = new EventHandler[KeyEvent] {
        def handle(evt: KeyEvent): Unit = board.handleCommand(evt)
      }
    }

  }
}