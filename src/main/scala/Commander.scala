package Commander

import scala.io.StdIn.readLine


class Commander:
  def showCommands:Unit = 
    val str1 = s"sl -listID # -select current list"
    println(str1)
  private def getCommand(str:String):(String,String) = 
    val command:(String,String) = if str.startsWith("sl") then 
      Tuple2("sl",str.substring(3)) else ("","")
    // val command:(String,String) =  str match
    //   case x.startsWith("sl") => Tuple2("sl",x.substring(3))
    //   case _=> ("","")
      
    command
  
  def inputCommand =
    println("Input command:")
    val command:String = readLine("#")
    // println(command)
    val selectedCommand = getCommand(command)
    println(selectedCommand)
    // if selectedCommand.isEmpty() then    println("exit")  else      println(selectedCommand)

  


