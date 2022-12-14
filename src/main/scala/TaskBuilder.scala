package TaskBuilder
import Task._
import TaskList._
case class TaskBuilder(name:String,content:String,list:TaskList)

object TaskBuilder:
  def apply(name:String,content:String,list:TaskList):Option[Task] =
    val taskOption = Task(name,content)
    taskOption match
      case Some(task) => list.add(task)
      case _ => println("can not create Task")
    taskOption

