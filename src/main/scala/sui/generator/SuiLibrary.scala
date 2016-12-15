package sui.generator

object SuiLibrary {

  val prefix = "Sui"

  val packagename = "semanticui"

  val jsname = packagename

  val SUI_FILE_PATH = "src/main/resources/index.d.ts"

  val outputPath  = "/Users/chandrasekharkode/Desktop/Kode/Programming/scalaprojects/scalajs-react-components/core/src/main/scala/chandu0101/scalajs/react/components/"+packagename

  val typeScriptCoreTypes = Set("any", "number", "boolean", "string","Array<string>","Array<number>","Array<any>")

  val typescriptCoreToscalajsCore = Map("any" -> "js.Any",
    "number" -> "Double",
    "boolean" -> "Boolean",
    "string" -> "String",
    "Array<string>" -> "js.Array[String]",
    "Array<number>" -> "js.Array[Double]",
    "Array<any>" -> "js.Array[js.Any]")

  //key = name ,value = scalajscode
  var predefinedEnums :Map[String,String] = Map()

  var onFlyGeneratedEnums :Map[String,String] = Map()

  val events = Set("ReactMouseEvents","ReactFocusEvents","ReactFormEvents")

  var propsCache : Map[String,Seq[PropMeta]] = Map()

  val eventElementMapper = Map("HTMLButtonElement" -> "ReactEventB => Callback",
  "any" -> "ReactEventH => Callback")

  val keyRefProps = Seq(PropMeta(name = "key", tpe = "String", isRequired = false),PropMeta(name = "ref", tpe = "String", isRequired = false))

  val Button = ComponentMeta(name = "Button")

  val components = Seq(Button)

}
