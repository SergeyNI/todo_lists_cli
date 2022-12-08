package Board
import TaskList.TaskList
import scala.collection.mutable.ListBuffer

case class Board(name:String,var taskLists:ListBuffer[TaskList] = ListBuffer.empty[TaskList], var currentTaskList:Option[TaskList] = None){
  override def toString(): String = name
  def setCurrentList(list:TaskList) = 
    println(s"On board $this seted current tasklist $list")
    currentTaskList = Some(list)
  
  def getCurrentTaskList():Option[TaskList] = currentTaskList
  
  def getLists() = taskLists
  def isCurrent(list:TaskList) = currentTaskList.get == list
  
  def add(list:TaskList) =
    taskLists.append(list)
    if taskLists.length == 1 then setCurrentList(list) 
  
  def list(index:Int):Option[TaskList] = if index >=0 && index < taskLists.length then Some(taskLists(index)) else None

  def remove(list:TaskList) = {
    taskLists = taskLists.filterNot(currlist => currlist == list)
    if taskLists.length == 1 then setCurrentList(taskLists(0)) 
  }


}
