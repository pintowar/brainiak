package brainiak.metaheuristics

import brainiak.search.Node

import scala.util.Random

/**
 * Created by thiago on 27/05/15.
 */
class SimulatedAnnealing(val currentSol: Node, val initialTemp: Double, val coolingRate: Double) {
  def this(currentSol: Node) = this(currentSol, 10000, 0.003)

  def acceptanceProbability(energy: Double, newEnergy: Double, temperature: Double): Double = {
    // If the new solution is better, accept it
    if (newEnergy < energy) 1.0
    // If the new solution is worse, calculate an acceptance probability
    else Math.exp((energy - newEnergy) / temperature)
  }

  def coolingDown(start: Double, factor: Double): Stream[Double] = start #:: coolingDown(start * (1 - factor), factor)

  def cooling(seed: Double): Node = {
    val main = Map('current -> currentSol, 'best -> currentSol)
    coolingDown(initialTemp, coolingRate).takeWhile(_ >= 1).foldLeft(main) { (args, temp) =>
      val current = args('current)
      val next = Random.shuffle(current.successors).head
      val prob = acceptanceProbability(current.myCost, next.myCost, temp)

      Map('current -> (if (prob > seed) next else current),
        'best -> (if (current.myCost < args('best).myCost) current else args('best)))
    }('best)
  }

  def cooling: Node = cooling(Math.random())
}
