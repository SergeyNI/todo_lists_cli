package TaskList

import Task.Task
import scala.collection.mutable.ListBuffer


class TaskList(name:String,private var board: Board.Board,
               private var tasks: ListBuffer[Task]=ListBuffer.empty[Task],
               private var currentTask: Option[Task] = None)  {

  def add(task:Task) = 
    tasks.append(task)
    task.setList(this)
  
  override def toString(): String = 
    val id = board.taskLists.indexOf(this)
    s"($id) $name"
  
  def get:ListBuffer[Task] = tasks
  
  def up(task:Task):Unit =
    val index = tasks.indexOf(task)
    val prevNumber = index match
      case 0 => index
      case x => x-1

    val topTasks = tasks.take(prevNumber)

    topTasks.append(task)
    if index >0 then 
      val prevTask = tasks(index-1)
      topTasks.append(prevTask)

    val bottomTasks = tasks.drop(index+1)
    tasks = topTasks concat bottomTasks
  
  def down(task:Task):Unit =
    val index = tasks.indexOf(task)
    val numberElementOfListBeforeCurrentElement =  index
    val topTasks = tasks.take(numberElementOfListBeforeCurrentElement)
    
    if index < (tasks.length-1) then
      val nextTask = tasks(index+1)
      topTasks.append(nextTask)
    topTasks.append(task)

    if index <= (tasks.length-2) then
      val bottomTasks = tasks.drop(index+2) //after next element
      tasks = topTasks concat bottomTasks
  
  def moveTo(task:Task, newTaskList:TaskList):Unit =
    newTaskList.add(task)
    remove(task)

  private def remove(task:Task) = 
    tasks -= task
  
  board.add(this)

}