package BoardLoader
import Board._
import Task.*
import TaskList.*
import spray.json._
import java.io
// import DefaultJsonProtocol._ // if you don't supply your own Protocol (see below)
import spray.json.JsonFormat
import spray.json.CollectionFormats
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.Paths
import scala.io.Source
import javax.tools.FileObject
import java.io.File
import java.io.FileFilter
import java.io.FilenameFilter
import cats.instances.function._ // for Functor
import cats.syntax.functor._ // for map

object MyJsonProtocol extends DefaultJsonProtocol{
  // implicit object taskFormat extends RootJsObjectFormat[Task]:
  implicit object taskFormat extends JsonFormat[Task]:
    def write(obj: Task) = 
      JsObject( Map("name"->JsString(obj.name), "content"->JsString(obj.content)) ) 
      // val filedName:JsField = ("name", JsString(obj.name))
      // val filedcontent:JsField = ("content", JsString(obj.content))
      // JsObject( filedName, filedcontent)

    def read(json: JsValue): Task =

      val objectj = json.asJsObject
      val name:String = objectj.fields("name").convertTo[String]
      val content:String = objectj.fields("content").convertTo[String]
      new Task(name,content)
    

  implicit object taskOptionFormat extends JsonFormat[Option[Task]]{
    override def write(opt: Option[Task]):JsValue = 
      opt match
        case Some(task) => 
          val filedName:JsField = ("name", JsString(task.name))
          val filedcontent:JsField = ("content", JsString(task.content))
          JsObject( filedName, filedcontent)
          // JsObject( 
          //   "name" -> task.name.toJson,
          //   "content" -> task.content.toJson,
          // )

        case _ => JsNull
    override def read(json: JsValue): Option[Task] = 
      json match
        case JsObject(fields) =>
          val task = Task(fields("name").toString,fields("content").toString)
          task
        case _=> None
  }
  implicit val taskListformat:RootJsonFormat[TaskList] = jsonFormat3(TaskList.apply)
  implicit val boardformat:RootJsonFormat[Board] = jsonFormat3(Board.apply)
  // implicit val taskformat:RootJsonFormat[Task] = jsonFormat2(Task.map)
  

}

def pathfiles = "./boards"


class BoardLoader(fileName:String):
  import MyJsonProtocol._
  def read(strJson:String):Board = strJson.parseJson.convertTo[Board]
  def load():String = Source.fromFile(fileName).getLines.mkString
  def newLoad():Board =
    val loadJson: String => String = (fileName:String) => Source.fromFile(fileName).getLines.mkString
    val readJson: String => Board = (strJson:String) => strJson.parseJson.convertTo[Board]
    (loadJson map readJson)(fileName)
case class BoardWriter(board:Board):
  import MyJsonProtocol._
  def write() =
    // val pathfiles = "./boards"
    
    val dir = Files.createDirectories(Paths.get(pathfiles))
    val  boardjson = board.toJson.prettyPrint
    val boardname = board.name
    val writer = FileWriter(s"$pathfiles/$boardname.json")
    writer.write(boardjson)
    writer.close()

case class BoardsLoader(catalog:String):
  def loadAll():List[Board] =
    val dir = new File(catalog)
    if dir.exists() && dir.isDirectory() then
      val listOfFiles = dir.listFiles().filter(file =>file.isFile() && file.getName().contains(".json"))
      val boardArrary:Array[Board] = for {
        file<-listOfFiles
        loader = new BoardLoader(file.getCanonicalPath())
        currentBoard = loader.newLoad()
      } yield currentBoard
      boardArrary.toList
    else
      List()
      
