package ttt

import scala.collection.mutable.ArrayBuffer
import org.scalatest._

class tttSpec extends FlatSpec {

  //TEST newBoard
  //---------------------------------

  "newBoard" should "return a 0" in {
    val board = game.newBoard()
    assert(board == 0)
  }


  //TEST placePiece
  //----------------------------------

  "placePiece" should "return 1 when given X and position 0" in {
    val X = 1
    val nextBoard = game.placePiece(X,0,game.newBoard())
    assert(1 == nextBoard)

  }

  it should "return 3 when given O and position 0" in {
    val O = 3
    val nextBoard = game.placePiece(O,0,game.newBoard())
    assert(3 == nextBoard)
  }

  it should "return 0x4000 when given X and position 7" in {
    val X = 1
    val nextBoard = game.placePiece(X,7,game.newBoard())
    assert(0x4000 == nextBoard)
  }

  it should "return 0xC00 when given O and position 7" in {
    val O = 3
    val nextBoard = game.placePiece(O,7,game.newBoard())
    assert(0xC000 == nextBoard)
  }

  it should "return 0x310 when given X to position 2 and O to position 4" in {
    val X = 1
    val O = 3
    val nextBoard = game.placePiece(X,2,game.newBoard())
    val finalBoard = game.placePiece(O,4,nextBoard)
    assert(0x310 == finalBoard)
  }

  //TEST pickSpot
  //----------------------------------

  "pickSpot" should "return 4 spots after 5 spots have been picked" in {
    val spots = ArrayBuffer(0,1,2,3,4,5,6,7,8)

    val (_,spots2) = game.pickSpot(spots)
    val (_,spots3) = game.pickSpot(spots)
    val (_,spots4) = game.pickSpot(spots)
    val (_,spots5) = game.pickSpot(spots)
    val (_,spots6) = game.pickSpot(spots)

    assert(spots6.length == 4)
  }


  //TEST getWinner
  //----------------------------------

  "getWinner" should "return (false,None) when there is no winner in random board" in {
    val X = 1
    val O = 3
    var board = game.newBoard()
    board = game.placePiece(X,7,board)
    board = game.placePiece(O,0,board)
    board = game.placePiece(X,5,board)
    board = game.placePiece(O,2,board)
    board = game.placePiece(X,1,board)
    board = game.placePiece(O,6,board)

    val result = game.getWinner(board)
    assert(!result._1)

  }

  it should "return (false,None) when there is an empty board" in {
    val board = game.newBoard()
    val result = game.getWinner(board)
    assert(!result._1)

  }

  it should "return (false,None) in this sneaky case" in {
    val board = 0x10F31 //010000111100110001
    val result = game.getWinner(board)
    assert(!result._1)
  }

  it should "return (false,None) when there is a cats game" in {
    val board = 0x375FD //110111010111111101
    val result = game.getWinner(board)
    assert(!result._1)
  }

  it should "return (true, X) when there is a top X win example" in {
    val board = 0x3315 //000011001100010101
    val result = game.getWinner(board)
    assert(result._1 && result._2 == Some(1))
  }

  it should "return (true,X) when there is a middle horiz X win example" in {
    val board = 0x3543 //000011010101000011
    val result = game.getWinner(board)
    assert(result._1 && result._2 == Some(1))
  }

  it should "return (true,X) when there is a bottom X win example" in {
    val board = 0x15330 //010101001100110000
    val result = game.getWinner(board)
    assert(result._1 && result._2 == Some(1))
  }

  it should "return (true,X) when there is a left X win example" in {
    val board = 0x1F41 //000001111101000001
    val result = game.getWinner(board)
    assert(result._1 && result._2 == Some(1))
  }

  it should "return (true,X) when there is a middle vert X win example" in {
    val board = 0x7107 //000111000100000111
    val result = game.getWinner(board)
    assert(result._1 && result._2 == Some(1))
  }

  it should "return (true,X) when there is a right win example" in {
    val board = 0x1071C //010000011100011100
    val result = game.getWinner(board)
    assert(result._1 && result._2 == Some(1))
  }

  it should "return (true,X) when there is a left to right diag X win example" in {
    val board = 0x13131 //010011000100110001
    val result = game.getWinner(board)
    assert(result._1 && result._2 == Some(1))
  }

  it should "return (true,X) when there is a right to left X win" in {
    val board = 0x11D3 //000001000111010011
    val result = game.getWinner(board)
    assert(result._1 && result._2 == Some(1))
  }

  it should "return (true,O) when there is a top O win" in {
    val board = 0x1017F //010000000101111111
    val result = game.getWinner(board)
    assert(result._1 && result._2 == Some(3))
  }

  it should "return (true,O) when there is a middle horiz O win" in {
    val board = 0x1FC5 //000001111111000101
    val result = game.getWinner(board)
    assert(result._1 && result._2 == Some(3))
  }

  it should "return (true,O) when there is a bottom O win" in {
    val board = 0x3F114 //111111000100010100
    val result = game.getWinner(board)
    assert(result._1 && result._2 == Some(3))
  }

  it should "return (true,O) when there is a left O win" in {
    val board = 0x34D7 //000011010011010111
    val result = game.getWinner(board)
    assert(result._1 && result._2 == Some(3))
  }

  it should "return (true,O) when there is a middle vert O win" in {
    val board = 0x1D34C //011101001101001100
    val result = game.getWinner(board)
    assert(result._1 && result._2 == Some(3))
  }

  it should "return (true,O) when there is a right O win" in {
    val board = 0x30D35 //110000110100110101
    val result = game.getWinner(board)
    assert(result._1 && result._2 == Some(3))
  }

  it should "return (true,O) when there is a left to right diag O win" in {
    val board = 0x31353 //110001001101010011
    val result = game.getWinner(board)
    assert(result._1 && result._2 == Some(3))
  }

  it should "return (true,O) when there is a right to left diag O win" in {
    val board = 0x3375 //000011001101110101
    val result = game.getWinner(board)
    assert(result._1 && result._2 == Some(3))
  }


}