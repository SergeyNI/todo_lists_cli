package Task
import TaskList._
class Task(name: String, content: String,var list: TaskList) {
  override def toString: String = 
    val id = list.get.indexOf(this)
    s"$id. $name"
  def getList: TaskList = list
  def setList(currentlist:TaskList) = list = currentlist
  list.add(this)
  
}
