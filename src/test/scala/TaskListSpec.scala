package TaskListSpec
import UnitSpec._
import TaskList.TaskList
import Task._
import fixtures._

class TaskListSpec extends UnitSpec with TaskListFixtures:
  
  describe("TaskList"){
    it("instance of Option TaskList"){
      val name = "name"
      val tl1 = TaskList(name)
      val tl2 = TaskList(name)
      tl1.isInstanceOf[Option[TaskList]] shouldBe true
      tl1.get should be equals tl2.get
    }
    it("return None if names length less then 3"){
      new TaskListFixtures{
        taskListDoOption should be equals(None)
      }
    }  
  }
  describe("method add"){
    it ("succesfull add new task") {
      new TaskListFixtures{
        val lengthBefore = taskListTodo.get.length
        taskListTodo.get should have length 0
        taskCreate should not be None
        taskListTodo.add(taskCreate)
        lengthBefore should be < taskListTodo.get.length
      }
     
    }
  }
  describe("method setCurrentTask"){
      
    it ("succesfull set current Task if task in list"){
      new TaskListFixtures {
        taskListTodo.add(taskCreate)
        val result = taskListTodo.setCurrentTask(Some(taskCreate))
        result shouldBe true

      }
    }
    it("return false if current Task not  in list of TaskList"){
      new TaskListFixtures {
        val result = taskListTodo.setCurrentTask(taskOptionCreate)
        result shouldBe false
      }
    }
  }
  describe("method current"){
    it("return NONE if not seted up"){
      new TaskListFixtures {
        taskListTodo.current should not be ("defined")
      }
    }
    it("return task if it seted up as current"){
      new TaskListFixtures {
        taskListTodo.add(taskCreate)
        taskListTodo.setCurrentTask(Some(taskCreate))
        taskListTodo.current.get should be equals taskOptionCreate
      }
    }
  }
  describe("method toString") {
    it("return name"){new TaskListFixtures {
      taskListTodo.toString() should be equals toDoName
    }}
  }
  describe("method up"){
    it("shift task up on list when index more then 0"){new TaskListFixtures {
      val tl:TaskList = taskListTodoWithTasksCreateAndDebug()
      tl.up(taskOptionDebug.get)
      tl.get.indexOf(taskOptionDebug.get) should be equals 0
    }}
    it("task stayed on  when index of task equals 0"){new TaskListFixtures {
      val tl:TaskList = taskListTodoWithTasksCreateAndDebug()
      tl.up(taskDebug)
      tl.up(taskDebug)
      tl.get.indexOf(taskOptionDebug.get) should be equals 0
    }}
  }
  describe("method down") {
    it("task shift down when index of task more then 0"){new TaskListFixtures {
      val tl:TaskList = taskListTodoWithTasksCreateAndDebug()
      tl.down(taskCreate)//taskCreate added at first
      // tl.up(taskOptionDebug.get)
      tl.get.indexOf(taskCreate) should be > 0
    }}
    it("task stayed on  when index of task equals lists lenght"){new TaskListFixtures {
      val tl:TaskList = taskListTodoWithTasksCreateAndDebug()
      tl.up(taskDebug)
      tl.up(taskDebug)
      val list = tl.get
      list.indexOf(taskDebug) should be equals list.length
    }}
  }
  describe("method remove"){
    it("not found task in list after remove"){new TaskListFixtures {
      val tl:TaskList = taskListTodoWithTasksCreateAndDebug()
      tl.remove(taskCreate) //taskCreate added at first
      tl.get should not contain taskCreate
      // tl.get.contains(taskCreate) should be equals false
    }}
  }
  describe("method moveTo"){
    it("not found task in list todo and found in taskListCreate"){new TaskListFixtures {
      val tl:TaskList = taskListTodoWithTasksCreateAndDebug()
      tl.moveTo(taskCreate,taskListDone) //taskCreate added at first
      // tl.get.contains(taskCreate) should be equals false
      tl.get should not contain taskCreate
      taskListDone.get should contain(taskCreate)
    }}
  }
    
end TaskListSpec
