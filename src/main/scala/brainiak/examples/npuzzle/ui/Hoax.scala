package brainiak.examples.npuzzle.ui

import scalafx.scene.layout.StackPane
import scalafx.scene.shape.{StrokeType, StrokeLineCap, Rectangle}
import scalafx.scene.paint.{Stop, CycleMethod, LinearGradient}
import scalafx.scene.paint.Color._
import scalafx.scene.control.{ContentDisplay, Label}
import scalafx.geometry.Pos
import scalafx.scene.text.{Font, TextAlignment}

/**
 * Created by thiago on 1/23/14.
 */
object Hoax {
  def apply(value: Int, wid: Double, hei: Double): Hoax = {
    new Hoax(value, wid, hei)
  }
}

class Hoax(val value: Int, val wid: Double, val hei: Double) extends StackPane {
  val self = this
  val aux = Seq(createRectangle())
  content = if (value == 0) aux else aux ++ Seq(createLabel())

  def createRectangle() = {
    if (self.value != 0) new Rectangle {
      arcHeight = 20.0
      arcWidth = 20.0
      height = self.hei
      width = self.wid
      stroke = White
      strokeLineCap = StrokeLineCap.ROUND
      strokeType = StrokeType.Inside
      fill = createGradient()
    } else new Rectangle {
      fill = White
    }
  }

  def createGradient(): LinearGradient = {
    new LinearGradient(0, 0, 1, 1, true, CycleMethod.Reflect,
      Seq(Stop(0, Black), Stop(1, Green))
    )
  }

  def createLabel(): Label = {
    val label = new Label(
      text = self.value.toString
    )
    label.textFill = White
    label.alignment = Pos.Center
    label.contentDisplay = ContentDisplay.Center
    label.textAlignment = TextAlignment.Center
    label.font = new Font("Arial", self.hei / 3)
    label
  }
}
