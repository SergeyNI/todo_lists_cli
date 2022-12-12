package TaskList

import Task.Task
import scala.collection.mutable.ListBuffer


case class TaskList(name:String,
                    private var tasks: ListBuffer[Task]=ListBuffer.empty[Task],
                    private var currentTask: Option[Task] = None):
  
  def setCurrentTask(task:Task) =
    
    currentTask = Some(task)
    println(s"in list $this current task is $task")
  
  def isCurrent(task:Task):Boolean = currentTask.get == task
  
  def add(task:Task) = 
    // tasks.append(task)
    tasks += task
    if tasks.length == 1 then setCurrentTask(task)
    // task.setList(this)
  def current = currentTask
  def task(index:Int):Option[Task] = 
    try
      Some(tasks(index))
    catch 
      case _ => None

  
  override def toString(): String = name
    // val id = board.taskLists.indexOf(this)
    // s"($id) $name"
  
  def get:ListBuffer[Task] = tasks
  
  def up(task:Task):Unit =
    val index = tasks.indexOf(task)
    val prevNumber = index match
      case 0 => index
      case x => x-1
    
    val topTasks = tasks.take(prevNumber)
    topTasks += task  
  
    if index >0 then 
      val prevTask = tasks(index-1)
      topTasks += prevTask

    val bottomTasks = tasks.drop(index+1)
    tasks = topTasks concat bottomTasks
  
  def down(task:Task):Unit =
    val index = tasks.indexOf(task)
    val numberElementOfListBeforeCurrentElement =  index
    val topTasks = tasks.take(numberElementOfListBeforeCurrentElement)
    
    if index < (tasks.length-1) then
      val nextTask = tasks(index+1)
      topTasks += nextTask
    topTasks += task

    if index <= (tasks.length-2) then
      val bottomTasks = tasks.drop(index+2) //after next element
      tasks = topTasks concat bottomTasks
  
  def moveTo(task:Task, newTaskList:TaskList):Unit =
    newTaskList.add(task)
    remove(task)

  def remove(task:Task) =
    if tasks.length == 1 then setCurrentTask(tasks(0))
    val previndex = tasks.indexOf(task) match
      case 0 => 0
      case x: Int => x-1 
    tasks -= task
    val prevTask = tasks(previndex)
    setCurrentTask(prevTask)
