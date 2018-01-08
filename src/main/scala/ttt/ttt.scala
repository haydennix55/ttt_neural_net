package ttt

object game extends gameFunctions with App {


}


trait gameFunctions {

  def newBoard(): Int = {
    0
  }

  def placePiece(player: Int, position: Int, board: Int): Int = {

    val mask = player << (2 * position)
    return (board | mask)

  }

  def openSpot(spot: Int, board: Int): Boolean = {

    val mask = 3 << (2 * spot)
    if ((mask & board) == 0) true else false

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




}
