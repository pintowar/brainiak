package brainiak.samples.npuzzle

/**
 * Created by thiago on 1/23/14.
 */

import brainiak.samples.npuzzle.ui.{Board, Controls, StatusBar}

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.VBox

object MainUI extends JFXApp {
  stage = new PrimaryStage {
    val status = StatusBar()
    val controls = Controls(status)
    val board = Board(controls, 8)

    title = "8 Puzzle"
    resizable = false
    scene = new Scene {
      root = new VBox {
        prefHeight = 150 * 3 + 40
        prefWidth = 150 * 3
        content = Seq(controls, board, status)
      }
    }
  }
}