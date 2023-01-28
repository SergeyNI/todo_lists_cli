import Task.*
import TaskBuilder.*
import TaskList.*
import Board.*
import BoardViewer.*
import Commander.Commander
import spray.json.enrichAny
import BoardLoader.pathfiles
import BoardLoader._

def initializeMainBoard():Board =
  val boardMain = Board("main")
 
  val optionListTodo = TaskList("TODO") match
    case Some(listTODO) =>
      boardMain.add(listTODO)
      boardMain.setCurrentList(listTODO)
      TaskBuilder("test program TODO","test program TODO BOARD",listTODO)
      TaskBuilder("DEPLOY program TODO","DEPLOY program TODO BOARD to production",listTODO)
      TaskBuilder("VIEW program TODO","VIEW program TODO BOARD in production",listTODO)
      val taskCreateOption = TaskBuilder("Create program TODO","create on scala program TODO BOARD",listTODO)
      taskCreateOption match
        case Some(taskCreate) => for{_ <- 1 to 3}{listTODO down taskCreate}
        case None =>
      taskCreateOption match
        case Some(taskCreate) => for{_ <- 1 to 2} {listTODO up taskCreate}
        case None =>
    case None => 
  
  
  TaskList("IN PROGRESS") map (listInProgress=>boardMain.add(listInProgress))  
  
  boardMain

  
@main def todoBoard: Unit = 

  val boardList:List[Board] = BoardsLoader(pathfiles).loadAll()
  val boardMain = if boardList.isEmpty then
    initializeMainBoard()
  else
    boardList(0)
  BoardViewer(boardMain).showBoard
  val cmd = new Commander(boardMain)
 
  cmd.inputCommand
  

