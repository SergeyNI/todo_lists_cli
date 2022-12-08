package Task
import TaskList._
case class Task(name: String, content: String) {
  override def toString: String = name
    // val id = list.get.indexOf(this)
    // s"$id. $name"
  // def getList: TaskList = list
  // def setList(currentlist:TaskList) = list = currentlist
  // list.add(this)
  
}
