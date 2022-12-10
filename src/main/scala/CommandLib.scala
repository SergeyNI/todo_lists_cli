package CommandLib

import TaskList.TaskList
import Board.Board
import BoardViewer.BoardViewer

trait Command:
  protected def toInt(s: String): Option[Int]=
    try 
      Some(s.toInt)
    catch 
      case e: Exception => None
  
  def doIt:Boolean
  
  def changesHasDone(board:Board):Boolean=
    BoardViewer(board).showBoard
    true

case class SelectList(board: Board,str:String) extends Command:
  def doIt: Boolean =
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
case class SelectTask(board: Board,str:String) extends Command:
  def doIt: Boolean =
    val strArg = str.substring(3) // get argument of command as string
      val argOption = toInt(strArg)
      argOption match
        case None => println(s"can't convert index  of list $strArg to Int")
        case Some(x) =>
          val currentTasklist = board.currentTaskList match
            case None => println("not found current tasklist")
            case Some(taskList) =>
              taskList.task(x) match
                case Some(task) => taskList.setCurrentTask(task)
                case _ =>
                  println(
                    s"in current task list '$taskList' not found task by index $x"
                  )
          BoardViewer(board).showBoard
    true

case class CurrentTaskMove(board: Board,str:String) extends Command:
  def doIt: Boolean =
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
    changesHasDone(board)
case class AddList(board: Board,str:String) extends Command:
  def doIt: Boolean =
    val strArg = str.substring(3)
    val newlist = new TaskList(strArg)
    board.add(newlist)
    changesHasDone(board)
case class RemoveList(board: Board,str:String) extends Command:
  def doIt: Boolean =
    val strArg = str.substring(3)
    val optionIndex = toInt(strArg)
    optionIndex match
      case Some(index) =>
        board.list(index) match
          case Some(taskList) => board.remove(taskList)
          case _ => println(s"not found task list by index $index")
      case _ => println(s"parameter of command is not Integer")
    changesHasDone(board)


