import org.scalatest.funspec.AnyFunSpec

import Task.Task

class TaskSpec extends AnyFunSpec {
  describe("instance of Task with:") {
    describe("name property") {
      
      it("succesfully created with correct name") {
        val name = "create rpogramm"
        val taskCreateProgramm = Task(name,name)
        assert(taskCreateProgramm.isInstanceOf[Task])
      }

      it("can't created with empty name") {
        val name = ""
        assertThrows[IllegalArgumentException]{
          val taskCreateProgramm = Task(name,name)
        }
      }

      it("can't created with  name's length < 3") {
        val name = "aa"
        assertThrows[IllegalArgumentException]{
          val taskCreateProgramm = Task(name,name)
        }
        
      }
  
    }
    describe("content property"){
      it("succesfully created with empty value"){
        val name = "create pogramm"
        val taskCreateProgramm = Task(name,"")
        assert(taskCreateProgramm.isInstanceOf[Task])
      }
      it("succesfully created with filled value"){
        val name = "create pogramm"
        val taskCreateProgramm = Task(name,"A")
        assert(taskCreateProgramm.isInstanceOf[Task])
      }
    }
  }
  describe("toString method") {
    it("return name as result"){
      val name = "create rpogramm"
      val taskCreateProgramm = Task(name,name)
      assert(taskCreateProgramm.toString == name)
    }
  }
}
