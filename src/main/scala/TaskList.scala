package TaskList
import NamedObject.*
import Task.Task
import scala.collection.mutable.ListBuffer


class TaskList(id:Int, name:String, private var tasks: ListBuffer[Task]=ListBuffer.empty[Task]) extends NamedObject {

  def show:Unit= 
    println(s"$this"+'\n')
    tasks.foreach(task=>println(task))
  def add(task:Task) = tasks.append(task)
  override def toString(): String = s"($id) $name"
}