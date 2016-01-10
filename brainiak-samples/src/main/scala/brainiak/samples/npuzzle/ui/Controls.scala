package brainiak.samples.npuzzle.ui

import scalafx.scene.layout.FlowPane
import scalafx.scene.control.{Separator, Button, ComboBox, Label}
import javafx.event.{ActionEvent, EventHandler}
import javafx.application.Platform

/**
  * Created by thiago on 1/26/14.
  */
object Controls {
  def apply(status: StatusBar): Controls = new Controls(status)
}

class Controls(val status: StatusBar) extends FlowPane {
  val combobox = new ComboBox[String]()
  combobox.setFocusTraversable(false)
  val randomize = new Button("Randomize")
  randomize.setFocusTraversable(false)
  val start = new Button("Start")
  start.setFocusTraversable(false)

  combobox.getItems.addAll("Human", "A*", "IDA*", "Breadth")
  combobox.setValue("Human")
  children.add(new Label(text = "Controller:"))
  children.add(new Separator())
  children.add(combobox)
  children.add(new Separator())
  children.add(randomize)
  children.add(new Separator())
  children.add(start)

  def setRandomizeAction(evt: EventHandler[ActionEvent]): Unit = randomize.onAction = evt

  def setStartAction(evt: EventHandler[ActionEvent]): Unit = start.onAction = evt

  def setBufferSize(size: Int): Unit = status.setBufferSize(size)

  def solvingStatus(): Unit = {
    Platform.runLater(new Runnable {
      def run() {
        combobox.setDisable(true)
        start.setDisable(true)
        randomize.setDisable(true)
        status.setStatus("Solving")
      }
    })
  }

  def movingStatus(): Unit = {
    Platform.runLater(new Runnable {
      def run() {
        combobox.setDisable(true)
        start.setDisable(true)
        randomize.setDisable(true)
        status.setStatus("Moving")
      }
    })
  }

  def stoppedStatus(): Unit = {
    Platform.runLater(new Runnable {
      def run() {
        combobox.setDisable(false)
        start.setDisable(false)
        randomize.setDisable(false)
        status.setStatus("Stopped")
      }
    })
  }
}
