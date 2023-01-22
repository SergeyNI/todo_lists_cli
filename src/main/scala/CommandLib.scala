package CommandLib

import TaskList.TaskList
import Board.Board
import TaskBuilder.TaskBuilder
import BoardViewer.BoardViewer
import org.scalactic.Bool

trait Command:
  val board:Board 
  protected def toInt(s: String): Option[Int] =
    try Some(s.toInt)
    catch case e: Exception => None

  //def doIt: Boolean

  def changesHasDone(): Boolean =
    BoardViewer(board).showBoard
    true
  
  def selectList(str: String):Boolean =
    val strArg = str.substring(3)
    val argOption = toInt(strArg)
    val arg = argOption match
      case None =>
        println(s"can't convert index  of list $strArg to Int")
        -1
      case Some(x) => x

    val someTaskList: Option[TaskList] = board.list(arg)
    someTaskList match
      case Some(taskList) => board.setCurrentList(taskList)
      case None           => println(s"can't find task list by index $arg")
    BoardViewer(board).showBoard
    true
  
  def addList(str: String):Boolean =
    val strArg = str.substring(3)
    val newlist = new TaskList(strArg)
    board.add(newlist)
    changesHasDone()
  
  
  def selectTask(str: String):Boolean = 
    val strArg = str.substring(3) // get argument of command as string
    val argOption = toInt(strArg)
    argOption match
      case None => println(s"can't convert index  of list $strArg to Int")
      case Some(x) =>
        val currentTasklist = board.currentTaskList match
          case None => println("not found current tasklist")
          case Some(taskList) =>
            taskList.task(x) match
              case Some(task) => taskList.setCurrentTask(Some(task))
              case None =>
                println(
                  s"in current task list '$taskList' not found task by index $x"
                )
        BoardViewer(board).showBoard
    true

  def currentTaskMoveOnList(str:String):Boolean =
    val currentTasklist = board.currentTaskList match
      case None => println("not found current tasklist")
      case Some(taskList) =>
        taskList.current match
          case Some(task) =>
            val strArg = str.substring(4)
            strArg match
              case "u" => taskList.up(task)
              case "d" => taskList.down(task)
              // case "n" =>taskList.moveTo(task,)
              case other => println(s"don't recognize direction $other")
          case _ =>
            println(
              s"in current task list '$taskList' not found current task"
            )
    changesHasDone()
  
  def removeList(str: String):Boolean =
   val strArg = str.substring(3)
    val optionIndex = toInt(strArg)
    optionIndex match
      case Some(index) =>
        board.list(index) match
          case Some(taskList) => board.remove(taskList)
          case _              => println(s"not found task list by index $index")
      case _ => println(s"parameter of command is not Integer")
    changesHasDone()
  
  def addTaskToCurrentList(str: String):Boolean =
    board.currentTaskList match
      case None => println("not selected current task list")
      case Some(list) =>
        val strArg = str.substring(3)
        val subStrArr = strArg.split("/")
        Tuple.fromArray(subStrArr) match
          case (name: String, content: String) =>
            val taskOption = TaskBuilder(name, content, list)
            taskOption match
              case Some(task) => changesHasDone()
              case None => println("can not create task"); changesHasDone()
          case _ => println("Too many argument string. Must be 2 parameters")
    changesHasDone()
  //def removeList(str: String):Boolean =
// 