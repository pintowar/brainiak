package brainiak.samples.npuzzle.ui

import scalafx.scene.layout.{RowConstraints, ColumnConstraints, GridPane}

/**
 * Created by thiago on 1/24/14.
 */
object BoardLayer {
  def apply(value: Int, pos: Int, nPieces: Int, pieceSize: Int): BoardLayer =
    new BoardLayer(value, pos, nPieces, pieceSize)

  def apply(value: Int, pos: Int): BoardLayer = new BoardLayer(value, pos)
}

class BoardLayer(val value: Int, val pos: Int, val nPieces: Int, val pieceSize: Int) extends GridPane {
  def this(value: Int, pos: Int, nPieces: Int) = this(value, pos, nPieces, 150)
  def this(value: Int, pos: Int) = this(value, pos, 8, 150)

  assert(pos <= nPieces)

  val size = math.sqrt(nPieces + 1).toInt
  val nRows = (0 until size).map(x => new RowConstraints(pieceSize)).toSeq
  val nCols = (0 until size).map(x => new ColumnConstraints(pieceSize)).toSeq

  nCols.foreach(c => columnConstraints add c)
  nRows.foreach(r => rowConstraints add r)

  add(new Hoax(value, pieceSize, pieceSize), pos % size, pos / size)
}
