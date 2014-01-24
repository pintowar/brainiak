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
  content = Seq(createRectangle(), createLabel())

  def createRectangle() = {
    if (self.value != 0) new Rectangle {
      arcHeight = 20.0
      arcWidth = 20.0
      height = self.hei
      width = self.wid
      stroke = WHITE
      strokeLineCap = StrokeLineCap.ROUND
      strokeType = StrokeType.INSIDE
      fill = createGradient()
    } else new Rectangle {
      fill = WHITE
    }
  }

  def createGradient(): LinearGradient = {
    new LinearGradient(0, 0, 1, 1, true, CycleMethod.REFLECT,
      Seq(Stop(0, BLACK), Stop(1, GREEN))
    )
  }

  def createLabel(): Label = {
    val label = new Label(
      text = self.value.toString
    )
    label.textFill = WHITE
    label.alignment = Pos.CENTER
    label.contentDisplay = ContentDisplay.CENTER
    label.textAlignment = TextAlignment.CENTER
    label.font = new Font("Arial", self.hei / 3)
    label
  }
}
