package Commander

import scala.io.StdIn.readLine
import TaskList.TaskList
import Board.Board
import BoardViewer.BoardViewer

import scala.compiletime.ops.any
import scala.compiletime.ops.boolean
// trait Command
// case class sl(str:String) extends Сommand
// case class al(str:String) extends Сommand
// case class quit(str:String) extends Сommand

class Commander(board: Board):
  private def toInt(s: String): Option[Int] = {
    try {
      Some(s.toInt)
    } catch {
      case e: Exception => None
    }
  }
  def showCommands: Unit =
    val str1 = s"Aviable commands:" + '\n' +
      "sl listID # -select current list" + '\n' +
      "st taskID # -select current task" + '\n' +
      "ctm u(d) # -move current task up(down) in current list" + '\n' +
      "al nameOfList # add new list" + '\n' +
      "quit # quit of program" + '\n'
    "help # print this info" + '\n'

    println(Console.BLUE + str1)

  private def sl: PartialFunction[String, Boolean] =
    new PartialFunction[String, Boolean] {
      // States that this partial function will take on the task
      def isDefinedAt(str: String) = str.startsWith("sl ") && str.length() >= 3
      // What we do if this partial function matches
      def apply(str: String) =

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
    }
  private def st = new PartialFunction[String, Boolean]:
    def isDefinedAt(str: String) = str.startsWith("st ") && str.length() >= 3
    def apply(str: String): Boolean =
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
              case _ => println(s"in current task list '$taskList' not found task by index $x")
            BoardViewer(board).showBoard
      true

  private def ctm = new PartialFunction[String, Boolean]:
    def isDefinedAt(str: String) =  str.startsWith("ctm") && str.length() > 4
    def apply(str: String): Boolean =
      val currentTasklist = board.currentTaskList match
        case None => println("not found current tasklist") 
        case Some(taskList) =>
          taskList.current match
            case Some(task) => 
               val strArg = str.substring(4)
               strArg match
                case "u" =>taskList.up(task)
                case "d" =>taskList.down(task)
                // case "n" =>taskList.moveTo(task,)
                case other => println(s"don't recognize direction $other")
            case _ => println(s"in current task list '$taskList' not found current task")
      BoardViewer(board).showBoard
      true

  private def al: PartialFunction[String, Boolean] =
    new PartialFunction[String, Boolean] {
      // States that this partial function will take on the task
      def isDefinedAt(str: String) = str.startsWith("al")
      // What we do if this partial function matches
      def apply(str: String) =
        val strArg = str.substring(3)
        val newlist = new TaskList(strArg)
        board.add(newlist)
        BoardViewer(board).showBoard
        true
    }

  private def quit: PartialFunction[String, Boolean] =
    new PartialFunction[String, Boolean] {
      // States that this partial function will take on the task
      def isDefinedAt(str: String) = str.startsWith("quit")
      // What we do if this partial function matches
      def apply(str: String) = false
    }
  private def help: PartialFunction[String, Boolean] =
    new PartialFunction[String, Boolean] {
      // States that this partial function will take on the task
      def isDefinedAt(str: String) = str.startsWith("help")
      // What we do if this partial function matches
      def apply(str: String) =
        showCommands
        true
    }
  private def notfound: PartialFunction[String, Boolean] =
    new PartialFunction[String, Boolean] {
      // States that this partial function will take on the task
      def isDefinedAt(str: String) = true
      // What we do if this partial function matches
      def apply(str: String) =
        println(s"command $str not found")
        showCommands
        true
    }

  private def getCommandList(): List[String] = List("sl", "st")
  // sl -select current List
  // st - select current Task
  private def executeCommand(str: String): Boolean =
    val command = sl orElse st  orElse ctm orElse help orElse al orElse quit orElse notfound
    command(str)

  def inputCommand =
    println(Console.WHITE + "Input command:")
    // val command: String = "sl 0"
    var continue = true
    while continue do
      val command: String = readLine(Console.WHITE + "#")
      continue = executeCommand(command)
