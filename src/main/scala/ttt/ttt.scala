package ttt

import scala.collection.mutable.ArrayBuffer


object game extends gameFunctions with App {

  //BITBOARD TIC-TAC-TOE
  //---------------------------
  // Tic Tac Toe is a simple game. In fact, we can store the current game state in
  // just an integer. We do this by starting with a blank board, a 0 integer. In binary,
  // we consider 18 bits (eg. 000000000000000000). Each pair of bits, from least significant
  // to most significant, represents a spot on the board. The rightmost pair of bits
  // represents the top left square, or position 0. The next pair represents the top middle
  // square, or position 1, etc.

  // An empty square is represented by a 00, an X by a 01 (a shifted 1),
  // and a Y by a 11 (a shifted 3).

  // Calling the Game() function with either 1 for X to start or 3 for O to start will
  // return the winner and final board of a random game.


}


trait gameFunctions {


  def newBoard(): Int = {
    0
  }


  def placePiece(player: Int, position: Int, board: Int): Int = {

    val mask = player << (2 * position)
    return (board | mask)

  }


  def pickSpot(openSpots: ArrayBuffer[Int]): (Int,ArrayBuffer[Int]) = {

    val r = new scala.util.Random

    if (openSpots.length > 1) {
      val spot = openSpots.remove(r.nextInt(openSpots.length - 1))
      (spot,openSpots)

    } else {
      val spot = openSpots.remove(0)
      (spot,openSpots)

    }

  }


  def getWinner(board: Int): (Boolean,Option[String]) = {

    if ((board & 0x3F) == 0x3F) (true, Some("O"))
    else if ((board & 0xFC0) == 0xFC0) (true, Some("O"))
    else if ((board & 0x3F000) == 0x3F000) (true, Some("O"))
    else if ((board & 0x30C3) == 0x30C3) (true, Some("O"))
    else if ((board & 0xC30C) == 0xC30C) (true, Some("O"))
    else if ((board & 0x30C30) == 0x30C30) (true, Some("O"))
    else if ((board & 0x30303) == 0x30303) (true, Some("O"))
    else if ((board & 0x3330) == 0x3330) (true, Some("O"))

    else if ((board & 0x3F) == 0x15) (true, Some("X"))
    else if ((board & 0xFC0) == 0x540) (true, Some("X"))
    else if ((board & 0x3F000) == 0x15000) (true, Some("X"))
    else if ((board & 0x30C3) == 0x1041) (true, Some("X"))
    else if ((board & 0xC30C) == 0x4104) (true, Some("X"))
    else if ((board & 0x30C30) == 0x10410) (true, Some("X"))
    else if ((board & 0x30303) == 0x10101) (true, Some("X"))
    else if ((board & 0x3330) == 0x1110) (true, Some("X"))

    else (false,None)

  }

  def turn(player: Int, spots: ArrayBuffer[Int], board: Int): (Int,Int) = {

    if (spots.isEmpty) (-1,board) //cats game

    else {

      val (spot,newSpots) = pickSpot(spots)
      val nextBoard = placePiece(player,spot,board)

      //Board states can be accessed and stored if needed for the neural net
//      println("Player: " + player)
//      println("Spot: " + spot)
//      println("Board: " + nextBoard)

      val (win,_) = getWinner(nextBoard)

      if (win) return (player,nextBoard)
      else {
        if (player == 1) turn(3,newSpots,nextBoard) else turn(1, newSpots, nextBoard)
      }

    }

  }

  //returns either "X", "O", "CAT"
  def game(startPlayer: Int): (Int,Int) = {

    val board = newBoard()
    val spots = ArrayBuffer(0,1,2,3,4,5,6,7,8)
    turn(startPlayer,spots,board)

  }




}
