package Board
import TaskList.TaskList
import scala.collection.mutable.ListBuffer

case class Board(name:String,var taskLists:Option[ListBuffer[TaskList]], var currentTaskList:Option[TaskList]){

  def setCurrentList(list:TaskList) = currentTaskList = Some(list)
  def getCurrentTaskList():Option[TaskList] = currentTaskList
  def getLists() = taskLists match
    case None => new ListBuffer[TaskList]
    case Some(lists) => lists
  def add(list:TaskList) =
    val lists = taskLists match
      case None => 
        val l = new ListBuffer[TaskList]
        l.append(list)
      case Some(lists) => lists.append(list)
    taskLists = Some(lists)

  def remove(list:TaskList) = {
    val lists = taskLists match {
      case Some(slists) => slists
      case None => new ListBuffer[TaskList]
    }
    Some(lists.filterNot(currlist => currlist == list))
  }


}
