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

  "openSpot" should "return true when spot is open" in {
    val O = 3
    val nextBoard = game.placePiece(O,4,game.newBoard())

    assert(game.openSpot(3,nextBoard))
  }

  it should "return false when spot is occupied in X" in {
    val X = 1
    val nextBoard = game.placePiece(X,5,game.newBoard())

    assert(!(game.openSpot(5,nextBoard)))

  }

  it should "return false when spot is occupied with O" in {
    val O = 3
    val nextBoard = game.placePiece(O,7,game.newBoard())

    assert(!(game.openSpot(7,nextBoard)))

  }

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
    val board = 0x3315
    val result = game.getWinner(board)

    assert(result._1 && result._2 == Some("X"))

    //000011001100010101
  }
  it should "return (true,X) when there is a middle horiz X win example" in {
    val board = 0x3543
    val result = game.getWinner(board)

    assert(result._1 && result._2 == Some("X"))
    //000011010101000011
  }
  it should "return (true,X) when there is a bottom X win example" in {
    val board = 0x15330
    val result = game.getWinner(board)

    assert(result._1 && result._2 == Some("X"))
    //010101001100110000
  }
  it should "return (true,X) when there is a left X win example" in {
    val board = 0x1F41
    val result = game.getWinner(board)

    assert(result._1 && result._2 == Some("X"))
    //000001111101000001
  }
  it should "return (true,X) when there is a middle vert X win example" in {
    val board = 0x7107
    val result = game.getWinner(board)

    assert(result._1 && result._2 == Some("X"))
    //000111000100000111
  }
  it should "return (true,X) when there is a right win example" in {
    val board = 0x1071C
    val result = game.getWinner(board)

    assert(result._1 && result._2 == Some("X"))
    //010000011100011100
  }
  it should "return (true,X) when there is a left to right diag X win example" in {
    val board = 0x13131
    val result = game.getWinner(board)

    assert(result._1 && result._2 == Some("X"))
    //010011000100110001
  }
  it should "return (true,X) when there is a right to left X win" in {
    val board = 0x11D3
    val result = game.getWinner(board)

    assert(result._1 && result._2 == Some("X"))
    //000001000111010011
  }
  it should "return (true,O) when there is a top O win" in {
    val board = 0x1017F
    val result = game.getWinner(board)

    assert(result._1 && result._2 == Some("O"))

    //010000000101111111
  }
  it should "return (true,O) when there is a middle horiz O win" in {
    val board = 0x1FC5
    val result = game.getWinner(board)

    assert(result._1 && result._2 == Some("O"))
    //000001111111000101
  }
  it should "return (true,O) when there is a bottom O win" in {
    val board = 0x3F114
    val result = game.getWinner(board)

    assert(result._1 && result._2 == Some("O"))
    //111111000100010100
  }
  it should "return (true,O) when there is a left O win" in {
    val board = 0x34D7
    val result = game.getWinner(board)

    assert(result._1 && result._2 == Some("O"))
    //000011010011010111
  }
  it should "return (true,O) when there is a middle vert O win" in {
    val board = 0x1D34C
    val result = game.getWinner(board)

    assert(result._1 && result._2 == Some("O"))
    //011101001101001100
  }
  it should "return (true,O) when there is a right O win" in {
    val board = 0x30D35
    val result = game.getWinner(board)

    assert(result._1 && result._2 == Some("O"))
    //110000110100110101
  }
  it should "return (true,O) when there is a left to right diag O win" in {
    val board = 0x31353
    val result = game.getWinner(board)

    assert(result._1 && result._2 == Some("O"))
    //110001001101010011
  }
  it should "return (true,O) when there is a right to left diag O win" in {
    val board = 0x3375
    val result = game.getWinner(board)

    assert(result._1 && result._2 == Some("O"))
    //000011001101110101
  }


}