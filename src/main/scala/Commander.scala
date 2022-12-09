package Commander

import scala.io.StdIn.readLine
import TaskList.TaskList
import Board.Board
import BoardViewer.BoardViewer

import scala.compiletime.ops.any
// trait Command
// case class sl(str:String) extends Сommand
// case class al(str:String) extends Сommand
// case class quit(str:String) extends Сommand


class Commander(board: Board):
  def showCommands: Unit =
    val str1 = s"Aviable commands:"+'\n'+
                "sl -listID # -select current list"+'\n'+
                "al nameOfList # add new list"+'\n'+
                "quit # quit of program"+'\n'
                "help # print this info"+'\n'
                
    println(Console.BLUE+str1)

  private def sl: PartialFunction[String, Boolean] =
    new PartialFunction[String, Boolean] {
      // States that this partial function will take on the task
      def isDefinedAt(str: String) = str.startsWith("sl ") && str.length() > 3
      // What we do if this partial function matches
      def apply(str: String) =
        def toInt(s: String): Option[Int] = {
          try {
            Some(s.toInt)
          } catch {
            case e: Exception => None
          }
        }
        val strArg = str.substring(3)
        val argOption = toInt(strArg)
        val arg = argOption match
          case None =>
            println(s"can't convert index  of list $strArg to Int")
            -1
          case Some(x) => x
        
        val sometaskList: Option[TaskList] = board.list(arg)
        sometaskList match
          case Some(taskList) => board.setCurrentList(taskList)
          case None           => println(s"can't find task list by index $arg")
        
        BoardViewer(board).showBoard
        true
    }
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
  private def executeCommand(str:String):Boolean = 
    val command = sl orElse help orElse al orElse quit orElse notfound
    command(str)
    // def toInt(s: String): Option[Int] = 
    //   try 
    //     Some(s.toInt)
    //   catch 
    //     case e: Exception => None
    // str match
    //   case x if x.startsWith("sl ") && x.length() > 3  =>
        
    //     val strArg = str.substring(3)
    //     val argOption = toInt(strArg)
    //     val arg = argOption match
    //       case None =>
    //         println(s"can't convert index  of list $strArg to Int")
    //         -1
    //       case Some(x) => x
        
    //     val sometaskList: Option[TaskList] = board.list(arg)
    //     sometaskList match
    //       case Some(taskList) => board.setCurrentList(taskList)
    //       case None           => println(s"can't find task list by index $arg")
        
    //     BoardViewer(board).showBoard
    //     true 
    

  def inputCommand =
    println(Console.WHITE+"Input command:")
    // val command: String = "sl 0"
    var continue = true
    while continue do 
      val command: String = readLine(Console.WHITE+"#")
      continue = executeCommand(command)
