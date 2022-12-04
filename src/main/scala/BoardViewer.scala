package BoardViewer

import Board.Board
import TaskList.TaskList

case class BoardViewer(board: Board) {
  def showBoard = 
    println(Console.GREEN+s"/////////////////////////////////////////////")
    println('\n'+Console.GREEN+s"$board"+'\n')
    val lists = board.getLists()
    lists.foreach(l => showList(l))
  
  def showList(list:TaskList) = 
    val someCurrentList:Option[TaskList] = board.getCurrentTaskList()

    val  mark:String = if someCurrentList.contains(list) then "*" else ""
    println('\n'+Console.MAGENTA+s"$mark$list"+'\n')
    list.get.foreach(task=>println(Console.CYAN+task))

}
