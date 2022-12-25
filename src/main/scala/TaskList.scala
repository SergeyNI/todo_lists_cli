package TaskList

import Task.Task
import scala.collection.mutable.ListBuffer


case class TaskList(name:String,
                    private var tasks: List[Task] = Nil,
                    private var currentTask: Option[Task] = None):
  
  def setCurrentTask(task:Option[Task]) =
    
    currentTask = task
    println(s"in list $this current task is $task")
  
  def isCurrent(task:Task):Boolean = currentTask.get == task
  
  def add(task:Task) = 
    // tasks.append(task)
    tasks = tasks:+task
    if tasks.length == 1 then setCurrentTask(Some(task))
  def current = currentTask
  def task(index:Int):Option[Task] = 
    try
      Some(tasks(index))
    catch 
      case _ => None

  
  override def toString(): String = name
    // val id = board.taskLists.indexOf(this)
    // s"($id) $name"
  
  def get:List[Task] = tasks
  
  def up(task:Task):Unit =
    if  tasks.contains(task) then
      val (leftTaskswithoutCurr,rightTasksWithCurr) = tasks.splitAt(tasks.indexOf(task))
      val newrightTasksWithoutCurr =  rightTasksWithCurr match
        case head :: tail => tail
        case _ => List()
      val newLeftListWithCurr = leftTaskswithoutCurr match
        case headList:+last => headList:+task:+last
        case _ => leftTaskswithoutCurr
       
      tasks = newLeftListWithCurr ::: newrightTasksWithoutCurr
    
  def down(task:Task):Unit =
    if tasks.contains(task) then
      val (leftTaskswithoutCurr,rightTasksWithCurr) = tasks.splitAt(tasks.indexOf(task))
      val newrightTasksWithCurr =  rightTasksWithCurr match
        case curr::head::tail => head::curr::tail
        case curr :: Nil => curr::Nil
        case Nil => Nil
      tasks = leftTaskswithoutCurr:::newrightTasksWithCurr

  def moveTo(task:Task, newTaskList:TaskList):Unit =
    newTaskList.add(task)
    remove(task)
    setCurrentTask(tasks match
      case Nil => None
      case head::tail => Some(head)
      )
  def remove(task:Task) =
    if tasks.contains(task) then
      tasks = tasks.filter(currTask => currTask != task)
      
      
    // if tasks.length == 1 then setCurrentTask(tasks(0))
    // val previndex = tasks.indexOf(task) match
    //   case 0 => 0
    //   case x: Int => x-1 
    // tasks -= task
    // val prevTask = tasks(previndex)
    // setCurrentTask(prevTask)
