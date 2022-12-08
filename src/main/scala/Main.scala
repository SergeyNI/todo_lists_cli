import Task.*
import TaskList.*
import Board.*
import BoardViewer.*
// import Commander.Commander
@main def todoBoard: Unit = 
  val boardMain = Board("main")
  val listTODO = TaskList("TODO")
  val listInProgress = TaskList("IN PROGRESS")
  boardMain.add(listTODO)
  boardMain.add(listInProgress)
  // boardMain.add(listTODO)
  // boardMain.add(listInProgress)
  val taskcreate = Task("Create program TODO","create on scala program TODO BOARD")
  val taskTest = Task("Test program ","TEST program")
  val taskDeploy = Task("DEPLOY ","DEPLOY program")
  val taskView = Task("VIEW ","VIEW program")
  listTODO.add(taskcreate)
  listTODO.add(taskTest)
  listTODO.add(taskDeploy)
  listTODO.add(taskView)
  // listTODO.down(taskcreate)
  // listTODO.down(taskcreate)
  // listTODO.down(taskcreate)
  
  listTODO.moveTo(taskDeploy,listInProgress)
  
  BoardViewer(boardMain).showBoard
  // val cmd = new Commander(boardMain)
  // cmd.showCommands
  // cmd.inputCommand
  

