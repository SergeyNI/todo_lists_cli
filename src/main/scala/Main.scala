import Task.*
import TaskList.*
import Board.*
import BoardViewer.*
import Commander.Commander
@main def todoBoard: Unit = 
  val boardMain = new Board("main")
  val listTODO = new TaskList("TODO",boardMain)
  val listInProgress = new TaskList("IN PROGRESS",boardMain)
  // boardMain.add(listTODO)
  // boardMain.add(listInProgress)
  val taskcreate = new Task("Create program TODO","create on scala program TODO BOARD",listTODO)
  val taskTest = new Task("Test program ","TEST program",listTODO)
  val taskDeploy = new Task("DEPLOY ","DEPLOY program",listTODO)
  val taskView = new Task("VIEW ","VIEW program",listTODO)
   listTODO.up(taskDeploy)
  listTODO.down(taskcreate)
  listTODO.down(taskcreate)
  listTODO.down(taskcreate)
  
  listTODO.moveTo(taskDeploy,listInProgress)
  
  BoardViewer(boardMain).showBoard
  val cmd = new Commander(boardMain)
  cmd.showCommands
  cmd.inputCommand
  

