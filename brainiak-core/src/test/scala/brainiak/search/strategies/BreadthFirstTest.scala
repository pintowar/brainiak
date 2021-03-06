package brainiak.search.strategies

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import brainiak.search.Node

/*import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
*/
/**
 * Created by thiago on 1/17/14.
 */
@RunWith(classOf[JUnitRunner])
class BreadthFirstTest extends FunSuite {

  class Mock(val value: Int) extends Node {
    def trackParent: Node = null

    def successors(except: Set[Node]): Set[Node] = null

    def myDepth: Int = 0

    def myCost: Double = 0

    def -(o: Node): Double = 0.0

    override def equals(o: Any) = o match {
      case that: Mock => that.value == value
      case _ => false
    }
  }

  test("teste breadth function") {
    val search = new BreadthFirst()
    assert(search.isEmpty)
    search << new Mock(1) << new Mock(2)
    assert(!search.isEmpty)
    assert(search.contains(new Mock(1)))
    assert(search.actual.equals(new Mock(1)))
//    assert(!search.contains(new Mock(1)))
    assert(!search.isEmpty)
    assert(search.contains(new Mock(2)))
    assert(search.actual.equals(new Mock(2)))
//    assert(!search.contains(new Mock(1)))
    assert(search.isEmpty)
  }
}
