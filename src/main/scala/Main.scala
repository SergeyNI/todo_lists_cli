import Task.*
import TaskList.*
import Board.*
import BoardViewer.*
import Commander.Commander
@main def todoBoard: Unit = 
  val boardMain = new Board("main",None,None)
  val listTODO = new TaskList(1,"TODO")
  val listInProgress = new TaskList(2,"IN PROGRESS")
  boardMain.add(listTODO)
  boardMain.add(listInProgress)
  val taskcreate = new Task(1,"Create program TODO","create on scala program TODO BOARD",listTODO)
  val taskTest = new Task(2,"Test program ","TEST program",listTODO)
  val taskDeploy = new Task(3,"DEPLOY ","DEPLOY program",listTODO)
  val taskView = new Task(4,"VIEW ","VIEW program",listTODO)
  //listTODO.show
   listTODO.up(taskDeploy)
  // listTODO.up(taskDeploy)
  // listTODO.up(taskDeploy)
  listTODO.down(taskcreate)
  listTODO.down(taskcreate)
  listTODO.down(taskcreate)
  //listTODO.down(taskcreate)
  //listTODO.show
  listTODO.moveTo(taskDeploy,listInProgress)
  //listTODO.show
  //listInProgress.show
  new BoardViewer(boardMain).show
  val cmd = new Commander
  cmd.showCommands
  cmd.inputCommand
  

