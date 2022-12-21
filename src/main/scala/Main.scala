import Task.*
import TaskBuilder.*
import TaskList.*
import Board.*
import BoardViewer.*
import Commander.Commander
@main def todoBoard: Unit = 
  val boardMain = Board("main")
  val listTODO = TaskList("TODO")
  val listInProgress = TaskList("IN PROGRESS")
  boardMain.add(listTODO)
  boardMain.add(listInProgress)
  // boardMain.add(listTODO)
  // boardMain.add(listInProgress)
  // val taskcreateOption:Option[Task] = Task("Create program TODO","create on scala program TODO BOARD")
  // taskcreateOption match
  //   case Some(taskcreate) => listTODO.add(taskcreate)
  //   case _ =>
  
  val taskCreateOption = TaskBuilder("Create program TODO","create on scala program TODO BOARD",listTODO)
  
  
  TaskBuilder("test program TODO","test program TODO BOARD",listTODO)
  TaskBuilder("DEPLOY program TODO","DEPLOY program TODO BOARD to production",listTODO)
  TaskBuilder("VIEW program TODO","VIEW program TODO BOARD in production",listTODO)
  taskCreateOption match
    case Some(taskCreate) => for{_ <- 1 to 3}{listTODO down taskCreate}
    case _ =>
  
  BoardViewer(boardMain).showBoard
  val cmd = new Commander(boardMain)
  cmd.inputCommand
  

