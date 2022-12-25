// package BoardLoader
// import Board._
// import Task.*
// import TaskList.*
// import spray.json._
// import DefaultJsonProtocol._ // if you don't supply your own Protocol (see below)
// import spray.json.JsonFormat
// //import scala.collection.mutable.ListBuffer

// object MyJsonProtocol extends DefaultJsonProtocol {
  
//   // implicit val listbufferFormat = jsonWriter[ListBuffer[Task.Task]]{
//   //   def write(lb:ListBuffer[Task.Task]) =
//   //     JsArray(lb.toList )
//   // }
//   // implicit val listTaskFormat = jsonFormat[List](r:JsonReader, w:JsonWriter){
    
//   // }
  
// //object ExtendedJsonProtocol extends DefaultJsonProtocol {
//   implicit def listJsonWriter[T : JsonWriter]: RootJsonWriter[List[T]] = new  RootJsonWriter[List[T]] {
//    def write(list: List[T]): JsArray = JsArray(list.map(_.toJson).toVector)
//   }
// //}
//   implicit object TaskListformat extends RootJsonFormat[TaskList]:
//     // def write(tl: TaskList): JsValue = JsObject(
//     //   List(("name", JsString(tl.name)),("tasks",JsArray(Some(tl.get.toList))))
//     // )
//     def write(tl: TaskList): JsValue = jsonFormat3(TaskList.apply)
    
//   // implicit val boardFormat = jsonFormat3(Board.apply)
// }

// implicit val taskformat = jsonFormat2(Task.apply)
// class BoardLoader(board:Board) {
//   // def save:String = board.toJson

// }
