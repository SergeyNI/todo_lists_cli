package fixtures

import TaskList.TaskList
import Task._
import Board._

trait TaskListFixtures {
  val boardMain = Board("Main")
  val taskCreateProgramName = "Create program"
  val taskOptionCreate:Option[Task] = Task(taskCreateProgramName,taskCreateProgramName)
  val taskCreate:Task = taskOptionCreate.get
  

  val taskDebugName = "Debug program"
  val taskOptionDebug:Option[Task] = Task(taskDebugName,taskDebugName)
  val taskDebug:Task = taskOptionDebug.get

  val toDoName = "Todo"
  val taskListTodo = TaskList(toDoName)
  val doneName = "Done"
  val taskListDone = TaskList(doneName)
  def taskListTodoWithTaskCreate():TaskList = 
    taskListTodo.add(taskCreate)
    taskListTodo
  def taskListTodoWithTasksCreateAndDebug():TaskList =
    taskListTodo.add(taskCreate)
    taskListTodo.add(taskDebug)
    taskListTodo
  def emptyBoardMain():Board = boardMain
  def boardMainWithTaskLists():Board =
    boardMain.add(taskListTodo)
    boardMain.add(taskListDone)
    boardMain

}
