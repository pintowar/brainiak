package brainiak.samples.npuzzle.ui.controller

import javafx.concurrent.Task
import javafx.scene.input.KeyEvent

import brainiak.samples.npuzzle.ui.Board
import brainiak.samples.npuzzle.{NPuzzleNode, NPuzzleProblem}
import brainiak.search.strategies.BestFirst
import brainiak.search.types.GraphSearch

/**
 * Created by thiago on 1/29/14.
 */
class AStarController(val b: Board) extends BasicController {

  override def handleCommand(evt: KeyEvent) = {
  }

  override def startAction() = {
    new Thread(new Task[Unit]() {
      override def call(): Unit = {
        val problem = NPuzzleProblem(board.puzzleState, board.numHoax)
        if (!problem.goalAchieved) {
          board.controls.solvingStatus()
          val search = GraphSearch()
          val strategy = BestFirst(st => st - problem.goal)
          val path = search.find(problem, strategy).path
          board.controls.movingStatus()
          path.foreach(n => n match {
            case puzzle: NPuzzleNode => if (puzzle.movement != 0) move(puzzle.movement)
          })
        }
      }
    }).start()

  }

  override def board = b
}
