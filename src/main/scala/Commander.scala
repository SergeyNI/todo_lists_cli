package Commander

import scala.io.StdIn.readLine
import TaskList.TaskList
import Board.Board
import BoardViewer.BoardViewer
import CommandLib.*

import scala.compiletime.ops.any
import scala.compiletime.ops.boolean

class Commander(board: Board):

  def showCommands: Unit =
    val str1 = s"Aviable commands:" + '\n' +
      "sl listID # -select current list" + '\n' +
      "st taskID # -select current task" + '\n' +
      "ctm u(d) # -move current task up(down) in current list" + '\n' +
      "al nameOfList # add new list" + '\n' +
      "quit # quit of program" + '\n'
      "help # print this info" + '\n'
    println(Console.BLUE + str1)

  private def executeCommand(str: String): Boolean =
    
    str match
      case s if s.startsWith("sl ") && s.length() > 3 => SelectList(board,s).doIt
      case s if s.startsWith("al ") && s.length() > 3 => AddList(board,s).doIt
      case s if s.startsWith("rl ") && s.length() > 3 => RemoveList(board,s).doIt
      case s if s.startsWith("st ") && s.length() > 3 => SelectTask(board,s).doIt
      case s if s.startsWith("ctm ") && s.length() > 4 => CurrentTaskMove(board,s).doIt
      case s if s == "help"  => showCommands; true
      case s if s == "quit" => false
      case s =>
        println(s"command $s not found")
        showCommands
        true

  def inputCommand =
    println(Console.WHITE + "Input command:")
    var command: String = "help"
    while executeCommand(command) do
      command = readLine(Console.WHITE + "#")

