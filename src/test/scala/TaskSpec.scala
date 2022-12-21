package TaskSpec
import UnitSpec._
import Task.Task

class TaskSpec extends UnitSpec {
  describe("instance of Task with:") {
    describe("name property") {
      
      it("succesfully created with correct name") {
        val name = "create rpogramm"
        val taskCreateProgramm = Task(name,name)
        assert(taskCreateProgramm.value.isInstanceOf[Task])
      }

      it("can't created with empty name") {
        val name = ""
        val taskCreateProgramm = Task(name,name)
        taskCreateProgramm shouldEqual None
        // assertThrows[IllegalArgumentException]{
        //   val taskCreateProgramm = Task(name,name)
        // }
      }

      it("can't created with  name's length < 3"){
        val name = "aa"
        Task(name,name) shouldEqual None}
      
  
    }
    describe("content property"){
      it("allow succesfully create with empty value"){
        val name = "create pogramm"
        
        assert(Task(name,"").value.isInstanceOf[Task])
      }
      it("succesfully created with filled value"){
        val name = "create pogramm"
        
        Task(name,"A").value shouldBe a[Task]
      }
    }
  }
  describe("toString method") {
    it ("return name as result"){
      val name = "create program"
      Task(name,name).value.toString should be equals name
    }
  }
}
