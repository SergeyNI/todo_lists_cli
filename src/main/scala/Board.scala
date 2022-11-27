object Board(taskLists:List[TaskList]) {
  private var currentTaskList:TaskList
  
  def setCurrentList(list:TaskList):Unit= currentTaskList = list
  def getCurrentTaskList():TaskList = currentTaskList
}
