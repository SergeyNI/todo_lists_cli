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
  val listTODO = TaskList("TODO")
  val listInProgress = TaskList("IN PROGRESS")
  boardMain.add(listTODO)
  boardMain.add(listInProgress)

  
  val taskCreateOption = TaskBuilder("Create program TODO","create on scala program TODO BOARD",listTODO)
  
  
  TaskBuilder("test program TODO","test program TODO BOARD",listTODO)
  TaskBuilder("DEPLOY program TODO","DEPLOY program TODO BOARD to production",listTODO)
  TaskBuilder("VIEW program TODO","VIEW program TODO BOARD in production",listTODO)
  taskCreateOption match
    case Some(taskCreate) => for{_ <- 1 to 3}{listTODO down taskCreate}
    case _ =>
  taskCreateOption match
    case Some(taskCreate) => for{_ <- 1 to 2} {listTODO up taskCreate}
    case _ =>
  boardMain

  
@main def todoBoard: Unit = 

  val boardList:List[Board] = BoardsLoader(pathfiles).loadAll()
  val boardMain = if boardList.isEmpty then
    initializeMainBoard()
  else
    boardList(0)
  BoardViewer(boardMain).showBoard
  val cmd = new Commander(boardMain)
 
  // BoardWriter(boardMain).write()
  // val boardMainnew = loader.read(loader.load)
  cmd.inputCommand
  

