package Task
import NamedObject._
import TaskList._
class Task(id:Int, name: String, content: String, list:TaskList) extends NamedObject {
  override def toString: String = s"$id. $name"
  list.add(this)
}
