package brainiak.samples.npuzzle.ui.controller

import brainiak.samples.npuzzle.ui.Board

/**
 * Created by thiago on 1/29/14.
 */
object ControllerFactory {

  def apply(name: String, board: Board): BasicController = name match {
    case "Human" => new HumanController(board)
    case "A*" => new AStarController(board)
    case "IDA*" => new IDAStarController(board)
    case "Breadth" => new BreadthController(board)
    case _ => throw new IllegalArgumentException("No Controller found.")
  }

}
