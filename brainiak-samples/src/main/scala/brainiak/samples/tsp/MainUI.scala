package brainiak.samples.tsp

/**
 * Created by thiago on 1/23/14.
 */

import java.awt.Color
import java.io.{InputStream, ByteArrayInputStream}
import javafx.concurrent.Task

import brainiak.metaheuristics.SimulatedAnnealing
import brainiak.samples.tsp.ui.PathNode
import com.xeiam.xchart.StyleManager.{LegendPosition, ChartTheme}
import com.xeiam.xchart._

import scala.util.Random
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.chart.{NumberAxis, CategoryAxis}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.VBox

object MainUI extends JFXApp {
  def showPath(current: Seq[(Double, Double)], best: Seq[(Double, Double)]): InputStream = {
    val cxs = current.map(_._1)
    val cys = current.map(_._2)

    val bxs = best.map(_._1)
    val bys = best.map(_._2)

    val chart: Chart = new ChartBuilder().width(800).height(300).theme(ChartTheme.Matlab).title("Path").build()
    chart.getStyleManager.setPlotGridLinesVisible(false)

    val cSeries = chart.addSeries("Current", (cxs :+ cxs.head).toArray, (cys :+ cys.head).toArray)
    val bSeries = chart.addSeries("Best", (bxs :+ bxs.head).toArray, (bys :+ bys.head).toArray)
    cSeries.setLineColor(Color.blue)
    bSeries.setLineStyle(SeriesLineStyle.DASH_DASH)
    bSeries.setLineColor(Color.red)
    chart.getStyleManager.setLegendVisible(false)

    new ByteArrayInputStream(BitmapEncoder.getPNGBytes(chart))
  }

  def showPath(points: Seq[(Double, Double)]): InputStream = showPath(points, points)

  def showSolutionEvo(current: Seq[Double], best: Seq[Double], temp: Double): InputStream = {
    val chart: Chart = new ChartBuilder().width(800).height(300).theme(ChartTheme.Matlab)
      .title(s"Solutions Evolution (Actual Temperature: $temp)").build()
    chart.getStyleManager.setPlotGridLinesVisible(false)
    chart.getStyleManager.setLegendPosition(LegendPosition.InsideNE)

    val cSeries = chart.addSeries("Current", (0 until current.size).map(_.toDouble).toArray, current.toArray)
    val bSeries = chart.addSeries("Best", (0 until best.size).map(_.toDouble).toArray, best.toArray)
    cSeries.setLineColor(Color.blue)
    cSeries.setMarker(SeriesMarker.NONE)
    bSeries.setLineColor(Color.red)
    bSeries.setMarker(SeriesMarker.NONE)

    new ByteArrayInputStream(BitmapEncoder.getPNGBytes(chart))
  }

  def createPoints(size: Int = 20, max: Int = 100): Seq[(Double, Double)] = {
    (1 to size).map(x => (Random.nextInt(max).toDouble, Random.nextInt(max).toDouble))
  }

  stage = new PrimaryStage {
    val xAxis = new CategoryAxis()
    val yAxis = new NumberAxis()
    val pathImg = new ImageView()
    val evoImg = new ImageView()
    val points = createPoints(50)
    pathImg.setImage(new Image(showPath(points)))
    val anneal = new SimulatedAnnealing(new PathNode(points), 50, 0.003, 10)

    val runner = new Task[Unit]() {
      override def call: Unit = {
        var currentHistory = Seq.empty[Double]
        var bestHistory = Seq.empty[Double]

        anneal.cooling { (nCurrent, nBest, temp) =>
          val current: PathNode = nCurrent.asInstanceOf[PathNode]
          val best: PathNode = nBest.asInstanceOf[PathNode]
          currentHistory = currentHistory :+ current.myCost
          bestHistory = bestHistory :+ best.myCost

          pathImg.setImage(new Image(showPath(current.points, best.points)))
          evoImg.setImage(new Image(showSolutionEvo(currentHistory, bestHistory, Math.floor(temp * 100) / 100)))
        }

      }
    }

    val t = new Thread(runner).start()

    title = "TSP"
    resizable = false
    scene = new Scene {
      root = new VBox {
        prefWidth = 800
        prefHeight = 600
        content = Seq(pathImg, evoImg)
      }
    }
  }

}