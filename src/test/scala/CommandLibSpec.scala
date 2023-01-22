package CommandLibSpec
import UnitSpec._
import TaskList.TaskList
import Task._
import Commander._
// import scala.collection.View.Empty
import fixtures._

class CommandLibSpec extends UnitSpec with TaskListFixtures:
  describe("executeCommand") {
    describe("al") {
      it("succesfull added tasklist") {
        new TaskListFixtures {
          val b = boardMain // boardMainWithTaskLists()
          val cmd = new Commander(b)
          val executeCommand =
            PrivateMethod[Commander](Symbol("executeCommand"))
          cmd invokePrivate executeCommand("al todo")
          b.getLists().length shouldEqual (1)
        }
      }
      it("not added tasklist") {
        new TaskListFixtures {
          val b = boardMain // boardMainWithTaskLists()
          val cmd = new Commander(b)
          val executeCommand =
            PrivateMethod[Commander](Symbol("executeCommand"))
          cmd invokePrivate executeCommand("al/todo")
          b.getLists().length shouldEqual (0)
        }
      }
    }
    
    describe("at")(pending)
    describe("sl")(pending)
    describe("st")(pending)
    describe("ctm")(pending)

  }

end CommandLibSpec
