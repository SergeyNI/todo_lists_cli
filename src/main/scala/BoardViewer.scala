package BoardViewer

import Board.Board
import TaskList.TaskList
import Task.*

case class BoardViewer(board: Board) {
  def showBoard = 
    println(Console.GREEN+s"/////////////////////////////////////////////")
    println('\n'+Console.GREEN+s"$board"+'\n')
    val lists = board.getLists()
    lists.foreach(l => 
      showList(l))
  
  def showList(list:TaskList) =
    def markCurrentList(list:TaskList):String = 
      val someCurrentList:Option[TaskList] = board.getCurrentTaskList()
      if someCurrentList.contains(list) then "*" else ""
    def markCurrentTask(task:Task):String =
      val isCurrentList = this.board.isCurrent(list)
      if list.isCurrent(task) && isCurrentList then "*" else ""
    val mark = markCurrentList(list)
    
    val listIndex = board.getLists().indexOf(list)
    println('\n'+Console.MAGENTA+s"$mark ($listIndex)$list"+'\n')
    val l = list.get
    l.foreach(task => 
      val index = l.indexOf(task)
      val mark = markCurrentTask(task)
      println(Console.CYAN+s"$mark $index. $task"))


}
