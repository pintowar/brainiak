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
class DepthFirstTest extends FunSuite {

  class Mock(val value: Int) extends State {
    def trackParent: State = null

    def children(except: Set[State]): Set[State] = null

    def myDepth: Int = 0

    def myCost: Double = 0

    def -(o: State): Double = 0.0

    override def equals(o: Any) = o match {
      case that: Mock => that.value == value
      case _ => false
    }
  }

  test("teste depth function") {
    val search = new DepthFirst()
    assert(search.isEmpty)
    search << new Mock(1) << new Mock(2)
    assert(!search.isEmpty)
    assert(search.actual.equals(new Mock(2)))
    assert(!search.isEmpty)
    assert(search.actual.equals(new Mock(1)))
    assert(search.isEmpty)
  }
}
