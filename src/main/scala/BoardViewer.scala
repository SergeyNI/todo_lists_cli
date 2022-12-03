package BoardViewer

import Board.Board

class BoardViewer(board: Board) {
  def show = 
    val lists = board.getLists()
    lists.foreach(l => l.show)

}
