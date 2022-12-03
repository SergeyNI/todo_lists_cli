package TaskList
import NamedObject.*
import Task.Task
import scala.collection.mutable.ListBuffer


class TaskList(id:Int, name:String, private var tasks: ListBuffer[Task]=ListBuffer.empty[Task]) extends NamedObject {

  def show:Unit= 
    println('\n'+Console.MAGENTA+s"$this"+'\n')
    tasks.foreach(task=>println(Console.CYAN+task))
  def add(task:Task) = tasks.append(task)
  
  override def toString(): String = s"($id) $name"
  
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
    // val nextIndex = index match
    //   case tasks.length => index
    //   case x => x+1
    if index < (tasks.length-1) then
      val nextTask = tasks(index+1)
      topTasks.append(nextTask)
    topTasks.append(task)

    if index <= (tasks.length-2) then
      val bottomTasks = tasks.drop(index+2) //after next element
      tasks = topTasks concat bottomTasks
  def moveTo(task:Task, newTaskList:TaskList):Unit =
    newTaskList.add(task)
    val oldList = task.getList
    remove(task)
    //(list.indexOf(task))
    //print(task)
  private def remove(task:Task) = 
    tasks -= task
    

}