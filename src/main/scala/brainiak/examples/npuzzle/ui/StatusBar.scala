package brainiak.examples.npuzzle.ui

import scalafx.scene.control.Label
import scalafx.scene.layout.BorderPane
import javafx.application.Platform


/**
 * Created by thiago on 1/26/14.
 */
object StatusBar {
  def apply(): StatusBar = new StatusBar()
}

class StatusBar extends BorderPane {
  val status: Label = Label("Stopped")
  val commandBuffer: Label = Label("Command buffer: 0")
  this.delegate.setLeft(status)
  this.delegate.setRight(commandBuffer)

  def setBufferSize(size: Int): Unit = {
    Platform.runLater(new Runnable {
      def run(): Unit = commandBuffer.setText(s"Command buffer: $size")
    })
  }

  def setStatus(stat: String): Unit =
    Platform.runLater(new Runnable {
      def run(): Unit = status.setText(stat)
    })
}
