package brainiak

/**
 * Created by thiago on 1/23/14.
 */

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import brainiak.examples.npuzzle.ui.{StatusBar, Controls, Board}
import javafx.event.EventHandler
import javafx.scene.input.KeyEvent
import scalafx.scene.layout.VBox

object MainUI extends JFXApp {
  stage = new PrimaryStage {
    val board = Board()
    val status = StatusBar()
    val controls = Controls(status, board)
    title = "8 Puzzle"
    resizable = false
    scene = new Scene {
      root = new VBox {
        content = Seq(controls, board, status)
        onKeyPressed = new EventHandler[KeyEvent] {
          def handle(evt: KeyEvent): Unit = board.handleCommand(evt)
        }
      }

    }

  }
}