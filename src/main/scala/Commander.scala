package Commander

import scala.io.StdIn.readLine
import Board.Board
import CommandLib.*
import BoardLoader.MyJsonProtocol._
import BoardLoader.BoardWriter


class Commander(currentBoard: Board) extends Command:
  val board = currentBoard 

  def showCommands: Unit =
    val str1 = s"Aviable commands:" + '\n' +
      "sl listID # -select current list" + '\n' +
      "st taskID # -select current task" + '\n' +
      "at name/content # -add new task to  current task list" + '\n' +
      "ctm u(d) # -move current task up(down) in current list" + '\n' +
      "al nameOfList # add new list" + '\n' +
      "quit # quit of program" + '\n'
      "show # show current board" + '\n'
      "help # print this info" + '\n'
    println(Console.BLUE + str1)

  private def executeCommand(str: String): Boolean =
    str match
      case s if s.startsWith("sl ") && s.length() > 3 => selectList(s)
      case s if s.startsWith("al ") && s.length() > 3 => addList(s)
      case s if s.startsWith("rl ") && s.length() > 3 => removeList(s)//RemoveList(board,s).doIt
      case s if s.startsWith("st ") && s.length() > 3 => selectTask(s)//SelectTask(board,s).doIt
      case s if s.startsWith("at ") && s.length() > 3 => addTaskToCurrentList(s)//AddTaskToCurrentList(board,s).doIt
      case s if s.startsWith("ctm ") && s.length() > 4 => currentTaskMoveOnList(s)//CurrentTaskMove(board,s).doIt
      case s if s == "help"  => showCommands; true
      case s if s == "show"  => changesHasDone()
      case s if s == "quit" => BoardWriter(board).write();false
      case s =>
        println(s"command '$s' not found")
        showCommands
        true

  def inputCommand =
    println(Console.WHITE + "Input command:")
    var command: String = "help"
    while executeCommand(command) do
      command = readLine(Console.WHITE + "#")

