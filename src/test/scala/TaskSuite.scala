import Task.Task
class TaskSuite extends munit.FunSuite{
  test("method toString of ObjectTask return string equoles name of object") {
    val name = "create rpogramm"
    val taskCreateProgramm = Task(name,name)
    assertEquals(taskCreateProgramm.toString, name)
  }
}