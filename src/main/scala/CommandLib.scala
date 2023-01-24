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
    changesHasDone()
  
  def addList(str: String):Boolean =
    val strArg = str.substring(3)
    val newlist = TaskList(strArg)
    newlist match
      case Some(tl) =>
        board.add(tl)
        changesHasDone()
      case None => println(s"Too small name of task list"); true
    
  
  
  def selectTask(str: String):Boolean = 
    val strArg = str.substring(3) // get argument of command as string
    val argOption = toInt(strArg)
    argOption match
      case None => println(s"can't convert index  of list $strArg to Int");true
      case Some(x) =>
        val currentTasklist = board.currentTaskList match
          case None => println("not found current tasklist");true
          case Some(taskList) =>
            taskList.task(x) match
              case Some(task) => taskList.setCurrentTask(Some(task))
              case None =>
                println(
                  s"in current task list '$taskList' not found task by index $x"
                )
        changesHasDone()
    

  def currentTaskMoveOnList(str:String):Boolean =
    val currentTasklist = board.currentTaskList match
      case None => println("not found current tasklist")
      case Some(taskList) =>
        taskList.current match
          case Some(task) =>
            val strArg = str.substring(4)
            strArg match
              case "u" => taskList.up(task);changesHasDone()
              case "d" => taskList.down(task);changesHasDone()
              // case "n" =>taskList.moveTo(task,)
              case other => println(s"don't recognize direction $other")
          case _ =>
            println(
              s"in current task list '$taskList' not found current task"
            )
    true
  
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
              case None => println("can not create task. Lenght of task name must be more then 2")
          case _ => println("Count of parameters must be equals 2: task name/task content ")
    true
  //def removeList(str: String):Boolean =
// 