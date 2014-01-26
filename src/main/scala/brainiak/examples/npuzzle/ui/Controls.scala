package brainiak.examples.npuzzle.ui

import scalafx.scene.layout.FlowPane
import scalafx.scene.control.{Separator, Button, ComboBox, Label}
import javafx.event.{ActionEvent, EventHandler}

/**
 * Created by thiago on 1/26/14.
 */
object Controls {
  def apply(status: StatusBar, board: Board): Controls = new Controls(status, board)
}

class Controls(val status: StatusBar, val board: Board) extends FlowPane {
  val combobox = new ComboBox[String]()
  val randomize = new Button("Randomize")
  val start = new Button("Start")

  combobox.getItems().addAll("Human", "A*")
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

  def solvingStatus: Unit = {
    combobox.setDisable(true)
    start.setDisable(true)
    randomize.setDisable(true)
    status.setStatus("Solving")
  }

  def movingStatus: Unit = {
    combobox.setDisable(true)
    start.setDisable(true)
    randomize.setDisable(true)
    status.setStatus("Moving")
  }

  def stoppedStatus: Unit = {
    combobox.setDisable(false)
    start.setDisable(false)
    randomize.setDisable(false)
    status.setStatus("Stopped")
  }
}
