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
  val taskListTodo = TaskList(toDoName).get
  val doneName = "Done"
  val taskListDone = TaskList(doneName).get
  val taskListDoOption = TaskList("do")
  
  val deployTaskname = "deploy";
  val deployTaskContent = "Deploy program to production"
  val deployTask = Task(deployTaskname,deployTaskContent).get
  
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
  def boardMainWithTaskListsAndCurrentTaskListTodo():Board =
    boardMain.add(taskListTodo)
    boardMain.add(taskListDone)
    boardMain.setCurrentList(taskListTodo)
    boardMain
  def taskListDoneWithCurrentTaskDeploy():TaskList= //boardMainWithTaskListsAndAndCurrentTask
    taskListDone.add(deployTask)
    taskListDone.setCurrentTask(Some(deployTask))
    taskListDone
  
  def boardMainWithTaskListsAndCurrentTaskListDoneWithCurrentTaskDeploy():Board =
    boardMain.add(taskListTodo)
    boardMain.add(taskListDoneWithCurrentTaskDeploy())
    boardMain.setCurrentList(taskListDone)
    boardMain
  def boardMainWithTaskListsAndCurrentTaskListTodoAndCurrentTask(task:Task):Board =
    val tl = taskListTodoWithTasksCreateAndDebug()
    tl.setCurrentTask(Some(task))
    boardMainWithTaskListsAndCurrentTaskListTodo()

}
