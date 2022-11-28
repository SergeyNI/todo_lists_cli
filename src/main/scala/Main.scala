import Task.*
import TaskList.*
import Board.*
@main def todoBoard: Unit = 
  //val board = new Board()
  val listTODO = new TaskList(1,"TODO")
  val listInProgress = new TaskList(1,"IN PROGRESS")
  val taskcreate = new Task(1,"Create program TODO","create on scala program TODO BOARD",listTODO)
  val taskTest = new Task(2,"Test program ","TEST program",listTODO)
  println(listTODO.show)

