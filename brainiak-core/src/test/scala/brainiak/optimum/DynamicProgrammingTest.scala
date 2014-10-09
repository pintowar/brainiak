package brainiak.optimum

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import scala.collection.BitSet

/*import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
*/
/**
 * Created by thiago on 1/17/14.
 */
@RunWith(classOf[JUnitRunner])
class DynamicProgrammingTest extends FunSuite {

  val dyn = new DynamicProgramming(List(8, 10, 15, 4), List(4, 5, 8, 3), 4, 11)

  test("test mountTable") {
    val sol = dyn.mountTable
    assert(sol == List(BitSet(4, 5, 6, 7, 8, 9, 10, 11), BitSet(5, 6, 7, 8, 9, 10, 11),
                  BitSet(8), BitSet(3, 7, 11)))
  }

  test("test backTracking") {
    val sol = List(BitSet(4, 5, 6, 7, 8, 9, 10, 11), BitSet(5, 6, 7, 8, 9, 10, 11),
      BitSet(8), BitSet(3, 7, 11))

    assert(dyn.backTracking(sol) == List(0, 0, 1, 1))
  }

  test("test solve") {

    val result = dyn.solve
    assert(result == List(0, 0, 1, 1))
    assert((0 until dyn.items).map(it => dyn.values(it) * result(it)).sum == 19)
  }
}

