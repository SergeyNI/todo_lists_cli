package Task

case class Task(name: String, content: String):
  override def toString: String = name
  require(name.length()>3)

object Task:
  def apply(name:String, content:String):Option[Task] =
    if name.length() >3 then Some(new Task(name,content)) else None

