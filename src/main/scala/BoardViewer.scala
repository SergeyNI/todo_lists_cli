package BoardViewer

import Board.Board
import TaskList.TaskList
import Task.*
import cats.Show
//import cats.instances.int._
// for Show
import cats.instances.string._ // for Show
import cats.syntax.show._
// for show
implicit val taskShow:Show[Task] = Show.show[Task] { 
  task =>
    val name = task.name.show
    val content = task.name.show
    s"$name: $content"  

  }
implicit val taskListShow:Show[TaskList] = Show.show[TaskList] { 
  tl =>
    val name = tl.name.show
    val count = tl.get.length.toString()
    s"$name:count - $count"  

  }

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
      someCurrentList match
        case Some(l) if l == list => "*"
        case _=>""
    
    def markCurrentTask(task:Task):String =
      val isCurrentList = this.board.isCurrent(list)
      if list.isCurrent(task) && isCurrentList then "*" else ""
    
    val mark = markCurrentList(list)
    
    val listIndex = board.getLists().indexOf(list)
    val listshow = list.show
    println('\n'+Console.MAGENTA+s"$mark ($listIndex)$listshow"+'\n')
    val l = list.get
    l.foreach(task => 
      val index = l.indexOf(task)
      val mark = markCurrentTask(task)
      val taskShow = task.show
      println(Console.CYAN+s"$mark $index. $taskShow"))


}
