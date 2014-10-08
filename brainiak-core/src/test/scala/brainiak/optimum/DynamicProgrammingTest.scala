package brainiak.optimum

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

/*import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
*/
/**
 * Created by thiago on 1/17/14.
 */
@RunWith(classOf[JUnitRunner])
class DynamicProgrammingTest extends FunSuite {

  val dyn = new DynamicProgramming()

  test("test mountTable") {
    //TODO
  }

  test("test backTracking") {
    //TODO
  }

  test("test solve") {
    val items = 4
    val capacity = 11
    val vals = List(8, 10, 15, 4)
    val weis = List(4, 5, 8, 3)

    val result = dyn.solve(vals, weis, items, capacity)
    assert(result == List(0, 0, 1, 1))
    assert((0 until items).map(it => vals(it) * result(it)).sum == 19)
  }
}

