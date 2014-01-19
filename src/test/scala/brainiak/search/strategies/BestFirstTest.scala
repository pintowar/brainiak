package brainiak.search.strategies

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import brainiak.search.State

/*import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
*/
/**
 * Created by thiago on 1/17/14.
 */
@RunWith(classOf[JUnitRunner])
class BestFirstTest extends FunSuite {

  class Mock(val value: Int) extends State {
    def trackParent: State = null

    def children(except: Set[State]): Set[State] = null

    def myDepth: Int = 0

    def myCost: Double = 0

    def -(o: State): Double = o match {
      case that: Mock => value - that.value
      case _ => Double.MaxValue
    }

    override def equals(o: Any) = o match {
      case that: Mock => that.value == value
      case _ => false
    }

    override def toString:String = value.toString
  }

  test("teste best function") {
    val search = new BestFirst(st => st - new Mock(1))
    assert(search.isEmpty)
    search << new Mock(2) << new Mock(1)
    assert(!search.isEmpty)
    assert(search.contains(new Mock(1)))
    assert(search.actual == new Mock(1))
    assert(!search.contains(new Mock(1)))
    assert(!search.isEmpty)
    assert(search.contains(new Mock(2)))
    assert(search.actual == new Mock(2))
    assert(!search.contains(new Mock(1)))
    assert(search.isEmpty)
  }
}
