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


  val dyn = new DynamicProgramming(Seq((8, 4), (10, 5), (15, 8), (4, 3)), 11)
  val listBit = Seq(BitSet(4, 5, 6, 7, 8, 9, 10, 11), BitSet(5, 6, 7, 8, 9, 10, 11),
    BitSet(8), BitSet(3, 7, 11))

  test("test mountTable") {
    val sol = dyn.mountTable
    assert(sol == listBit)
  }

  test("test backTracking") {
    assert(dyn.backTracking(listBit) == List(0, 0, 1, 1))
  }

  test("test solve") {

    val result = dyn.solve
    assert(result == List(0, 0, 1, 1))
    assert(dyn.values == List(8, 10, 15, 4))
    assert((0 until dyn.items).map(it => dyn.values(it) * result(it)).sum == 19)
  }
}

