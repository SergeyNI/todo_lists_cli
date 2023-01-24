package CommandLibSpec
import UnitSpec._
import TaskList.TaskList
import Task._
import Commander._
import Board._
// import scala.collection.View.Empty
import fixtures._

class CommandLibSpec extends UnitSpec with TaskListFixtures:
  describe("executeCommand") {
    
    def executeCommand(b:Board)(command:String):Unit=
      val cmd = new Commander(b)
      val executeCommand = PrivateMethod[Commander](Symbol("executeCommand"))
      cmd invokePrivate executeCommand(command)
    
    describe("al") {
      it("succesfull added tasklist") {
        new TaskListFixtures {
          val b = boardMain // boardMainWithTaskLists()
          executeCommand(b)("al todo")
          b.getLists().length shouldEqual (1)
        }
      }
      it("not added tasklist") {
        new TaskListFixtures {
          val b = boardMain
          executeCommand(b)("al/todo")
          b.getLists().length shouldEqual (0)
        }
      }
    }
    
    describe("at"){
      it("added task to if task name longer then 3 chars"){new TaskListFixtures{
        val b = boardMainWithTaskListsAndCurrentTaskListTodo()
        executeCommand(b)(s"at $deployTaskname/$deployTaskContent")
        b.currentTaskList.get.get should contain (deployTask)

      }}
      it("not create task if not input 2-d parameter"){new TaskListFixtures{
        val b = boardMainWithTaskListsAndCurrentTaskListTodo()
        executeCommand(b)(s"at $deployTaskname")//cmd invokePrivate executeCommand( s"at $deployTaskname")
        b.currentTaskList.get.get should not contain (deployTask)
      }}
      it("not create task if name length less then 2 chars"){new TaskListFixtures{
        val b = boardMainWithTaskListsAndCurrentTaskListTodo()
        
        val prevLength = b.currentTaskList.get.get.length
        executeCommand(b)(s"at do/$deployTaskContent")// cmd invokePrivate executeCommand( s"at do/$deployTaskContent")
        prevLength should be equals b.currentTaskList.get.get.length
      }}
    }
    
    describe("sl"){
      it("succesfull change current list"){new TaskListFixtures{
        val b = boardMainWithTaskListsAndCurrentTaskListTodo()
        val prevTL = b.currentTaskList
        executeCommand(b)(s"sl 1")
        prevTL should not be equals (b.currentTaskList)
      }}
      it("not change current list when index of tasklist more then count of tasklists"){new TaskListFixtures{
        val b = boardMainWithTaskListsAndCurrentTaskListTodo()
        val prevTL = b.currentTaskList
        executeCommand(b)(s"sl 2")
        prevTL should be equals (b.currentTaskList)
      }}
      it("not change current list when index of tasklist less then ZERO"){new TaskListFixtures{
        val b = boardMainWithTaskListsAndCurrentTaskListTodo()
        val prevTL = b.currentTaskList
        executeCommand(b)(s"sl -2")
        prevTL should be equals (b.currentTaskList)
      }}
      it("not change current list when index of tasklist is not number"){new TaskListFixtures{
        val b = boardMainWithTaskListsAndCurrentTaskListTodo()
        val prevTL = b.currentTaskList
        executeCommand(b)(s"sl A")
        prevTL should be equals (b.currentTaskList)
      }}
      it("not change current list when index not splited with command"){new TaskListFixtures{
        val b = boardMainWithTaskListsAndCurrentTaskListTodo()
        val prevTL = b.currentTaskList
        executeCommand(b)(s"sl1")
        prevTL should be equals (b.currentTaskList)
      }}
    }
    
    describe("st"){
      it("succesfull change current task"){new TaskListFixtures {
        val b = boardMainWithTaskListsAndCurrentTaskListTodoAndCurrentTask(taskDebug)
        val tl = b.currentTaskList
        val currentTask = tl.get.current.get
        currentTask should be equals (taskDebug)
        executeCommand(b)(s"st 0")
        val currentTaskAfter = tl.get.current.get
        currentTaskAfter should not be equals (taskDebug)
      }}
      it("not change current task when index is out of length of current list"){new TaskListFixtures {
        val b = boardMainWithTaskListsAndCurrentTaskListTodoAndCurrentTask(taskDebug)
        val tl = b.currentTaskList
        val currentTask = tl.get.current.get
        currentTask should be equals (taskDebug)
        executeCommand(b)(s"st 10")
        val currentTaskAfter1 = tl.get.current.get
        currentTaskAfter1 should be equals (taskDebug)
        executeCommand(b)(s"st -5")
        val currentTaskAfter2 = tl.get.current.get
        currentTaskAfter2 should be equals (taskDebug)
      }}

    }

    describe("ctm"){
      describe("u"){
        it("successfull move current task"){new TaskListFixtures {
          val b = boardMainWithTaskListsAndCurrentTaskListTodoAndCurrentTask(taskDebug)
          val tl = b.currentTaskList
          val currentTask = tl.get.current.get
          val currentIndex = tl.get.get.indexOf(currentTask)
          executeCommand(b)(s"ctm u")
          val newCurrentIndex = tl.get.get.indexOf(currentTask)
          newCurrentIndex should be < (currentIndex)
          
        }}
        it("stay on index of current task"){new TaskListFixtures {
          val b = boardMainWithTaskListsAndCurrentTaskListTodoAndCurrentTask(taskCreate)
          val tl = b.currentTaskList
          val currentTask = tl.get.current.get
          val currentIndex = tl.get.get.indexOf(currentTask)
          executeCommand(b)(s"ctm u")
          val newCurrentIndex = tl.get.get.indexOf(currentTask)
          newCurrentIndex should  be equals (currentIndex)
          
        }}
      }
      
      describe("d"){
        it("successfull move current task"){new TaskListFixtures {
          val b = boardMainWithTaskListsAndCurrentTaskListTodoAndCurrentTask(taskCreate)
          val tl = b.currentTaskList
          val currentTask = tl.get.current.get
          val currentIndex = tl.get.get.indexOf(currentTask)
          executeCommand(b)(s"ctm d")
          val newCurrentIndex = tl.get.get.indexOf(currentTask)
          newCurrentIndex should be > (currentIndex)
        }}
        it("stay on index of current task"){new TaskListFixtures {
          val b = boardMainWithTaskListsAndCurrentTaskListTodoAndCurrentTask(taskDebug)
          val tl = b.currentTaskList
          val currentTask = tl.get.current.get
          val currentIndex = tl.get.get.indexOf(currentTask)
          executeCommand(b)(s"ctm d")
          val newCurrentIndex = tl.get.get.indexOf(currentTask)
          newCurrentIndex should  be equals (currentIndex)
          
        }}
      }
      // it("successfull move current task"){new TaskListFixtures {

      // }}
    }

  }

end CommandLibSpec
