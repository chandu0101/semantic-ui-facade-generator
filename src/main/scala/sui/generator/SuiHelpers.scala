package sui.generator

import SuiLibrary._
import java.io.File
import java.io.PrintWriter

trait SuiHelpers {

  def isEnumType(str: String) = str.contains("type ") && str.contains("=") && str.contains("'")

  def generateScalaJSEnum(name: String, value: String): String = {
    s"""
       | @js.native
       | trait $name extends js.Object
       |
       | object $name {
       |
       |  ${value.trim.replaceAll("'", "").split("\\|").map(_.trim).map(v => s""" val ${v.split(" ").map(_.toUpperCase).mkString("_")} = "$v".asInstanceOf[$name] """).mkString("\n")}
       |
       | }
     """.stripMargin
  }

  def getSuperClassesProps(interfaceName: String, file: String): Seq[PropMeta] = {
    val line = file.split("\n").find(_.contains(s"interface $interfaceName")).getOrElse("")
    if (line.contains("extends")) {
      val i = line.indexOf("extends")
      line.substring(i + 7).replace("{", "").trim.split(",").map(_.trim)
        .flatMap(s => {
          val name = if (s.contains("<")) s.substring(0, s.indexOf("<")) else s
          val genericName = if (s.contains("<")) Some(s.substring(s.indexOf("<") + 1, s.indexOf(">"))) else None
          val isEvent = events.contains(name)
          if(isEvent) propsCache.get(name).map(spm => spm.map(_.copy(tpe = eventElementMapper.get(genericName.getOrElse("")).getOrElse("")))).getOrElse(Seq())
          else propsCache.get(name).getOrElse(Seq())
        })
    } else Seq()
  }




  def getInterfaceProps(interfaceName: String, file: String, componentName: String) = {
    val i = file.indexOf(s"interface $interfaceName")
    val s = file.substring(i)
    val j = s.indexOf("{")
    val k = s.indexOf("}")
    s.substring(j + 1, k - 1)
      .split("\n")
      .filter(_.contains(":"))
      .map(s => {
        val pt = s.split(":")
        val name = pt.head.trim
        val isRequired = !name.contains("?")
        val fieldName = name.replace("?", "")
        val tpe = pt.last.trim.replace(";", "")
        val sjsTpe = if (componentName.nonEmpty) SuiTypeMapper(componentName, fieldName, tpe) else "PLACEHOLDER" // hack for event interfaces
        PropMeta(fieldName, sjsTpe, isRequired)
      })
  }

  val preInclude =
    s"""
       |package chandu0101.scalajs.react.components
       |package $packagename
       |import chandu0101.macros.tojs.JSMacro
       |import japgolly.scalajs.react._
       |import scala.scalajs.js
       |import scala.scalajs.js.`|`
       |
       |/**
       | * This file is generated - submit issues instead of PR against it
       | */
     """.stripMargin

  def generateComponentFileContent(cm: ComponentMeta, file: String) = {
    val interfaceName = if (cm.interfaceName == "default") s"${cm.name}Props" else cm.interfaceName
    val superclassProps = getSuperClassesProps(interfaceName,file)
    val currentProps = getInterfaceProps(interfaceName, file, cm.name)
    val props = superclassProps ++ currentProps
    propsCache = propsCache.updated(interfaceName,props)
    val isChildren = props.find(_.name == "children").isDefined
    val allProps = props.filterNot(_.name == "children") ++ keyRefProps
    val className = s"$prefix${cm.name}"
    s"""
       |$preInclude
       |
       |case class $className(
       |     ${allProps.map(pm => s"${pm.name}: ${if (pm.isRequired) pm.tpe else s"js.UndefOr[${pm.tpe}] = js.undefined"}").mkString(",\n")}
       |){
       |  def apply(${if (isChildren) "children: ReactNode*" else ""}) = {
       |     val props = JSMacro[$className](this)
       |     ReactJS.createElement($prefix.${cm.name},props${if (isChildren) ",children: _*" else ""})
       |   }
       |}
     """.stripMargin
  }

  def writeToFile(name: String, content: String) = {
    val pw = new PrintWriter(new File(name))
    try pw.write(content) finally pw.close()
  }

  def generateEnumFile() = {
    val content =
      s"""
         |$preInclude
         |
         |${(predefinedEnums.values ++ onFlyGeneratedEnums.values).mkString("\n")}
         |
       """.stripMargin
    writeToFile(s"$outputPath/gen-enums.scala", content)
  }

  def generateSuiFile() = {
    val content =
      s"""
         |$preInclude
         |
         |@js.native @JSName("$jsname")
         |object $prefix extends js.Object {
         |  ${components.map(cm => s"val ${cm.name}: js.Dynamic = js.native").mkString("\n")}
         |}
         |
       """.stripMargin
    writeToFile(s"$outputPath/Sui.scala", content)
  }
}
