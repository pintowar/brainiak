package brainiak.metaheuristics

import brainiak.search.Node

import scala.util.Random

/**
 * Created by thiago on 27/05/15.
 */
class SimulatedAnnealing(val currentSol: Node, val initialTemp: Double, val coolingRate: Double, val iterations: Int) {
  def this(currentSol: Node) = this(currentSol, 20, 0.009, 5)

  def acceptanceProbability(energy: Double, newEnergy: Double, temperature: Double): Double = {
    // If the new solution is better, accept it
    if (newEnergy < energy) 1.0
    // If the new solution is worse, calculate an acceptance probability
    else Math.exp((energy - newEnergy) / temperature)
  }

  def coolingDown(start: Double, factor: Double): Stream[Double] = start #:: coolingDown(start * (1 - factor), factor)

  def cooling(cback: (Node, Node, Double) => Unit): Node = {
    val main = Map('current -> currentSol, 'best -> currentSol)
    coolingDown(initialTemp, coolingRate).takeWhile(_ >= 0).foldLeft(main) { (args, temp) =>
      (1 to iterations).foldLeft(args) { (turn, it) =>
        val current = turn('current)
        val next = Random.shuffle(current.successors).head
        val prob = acceptanceProbability(current.myCost, next.myCost, temp)

        val aux = Map('current -> (if (prob > Math.random()) next else current),
          'best -> (if (current.myCost < turn('best).myCost) current else turn('best)))
        cback(aux('current), aux('best), temp)
        aux
      }
    }('best)
  }

}
