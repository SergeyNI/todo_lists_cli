package NamedObject
class NamedObject(id:Int = 0, name: String="") {
  override def toString: String = s"($id) $name"
}
