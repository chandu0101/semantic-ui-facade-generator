package sui.generator

import SuiLibrary._

import scala.io.Source
object SuiGenerator extends SuiHelpers{

  def main(args: Array[String]) {
    val fileLines = Source.fromFile(SUI_FILE_PATH).getLines().toVector
    val fileContent = fileLines.mkString("\n")

    predefinedEnums = fileLines.filter(isEnumType).map(s => {
        val s1 = s.replace("type ","").replace(";","").trim.split("=")
        val name = s1.head.trim
        val values = s1.last.trim
        name -> generateScalaJSEnum(name,values)
    }).toMap

    propsCache = events.map(s => s -> getInterfaceProps(s,fileContent,"").toSeq).toMap

    components.foreach(cm => {
      val content = generateComponentFileContent(cm,fileContent)
      val fileName = s"$outputPath/$prefix${cm.name}.scala"
      writeToFile(fileName,content)
    })
    generateEnumFile()
    generateSuiFile()
  }


}
