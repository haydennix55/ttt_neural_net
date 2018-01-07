package ttt

import org.scalatest._

class tttSpec extends FlatSpec {

  "newBoard" should "return a 0" in {
    val board = game.newBoard()
    assert(board == 0)
  }

  "placePiece" should "return 1 when given X and position 0" in {
    val X = 1
    val nextBoard = game.placePiece(X,0,game.newBoard())
    assert(1 == nextBoard)

  }

  it should "return 3 when given Y and position 0" in {
    val Y = 3
    val nextBoard = game.placePiece(Y,0,game.newBoard())
    assert(3 == nextBoard)
  }

  it should "return 0x4000 when given X and position 7" in {
    val X = 1
    val nextBoard = game.placePiece(X,7,game.newBoard())
    assert(0x4000 == nextBoard)
  }

  it should "return 0xC00 when given Y and position 7" in {
    val Y = 3
    val nextBoard = game.placePiece(Y,7,game.newBoard())
    assert(0xC000 == nextBoard)
  }

  it should "return 0x310 when given X to position 2 and Y to position 4" in {
    val X = 1
    val Y = 3
    val nextBoard = game.placePiece(X,2,game.newBoard())
    val finalBoard = game.placePiece(Y,4,nextBoard)
    assert(0x310 == finalBoard)
  }
  

}