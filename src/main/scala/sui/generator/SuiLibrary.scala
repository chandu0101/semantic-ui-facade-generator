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

  val eventElementMapper = Map(
    "React.MouseEventHandler<HTMLButtonElement>" -> "ReactMouseEventB => Callback",
    "React.DragEventHandler<HTMLButtonElement>" -> "ReactDragEventB => Callback",
    "React.MouseEventHandler<any>" -> "ReactMouseEventH => Callback",
    "React.DragEventHandler<any>" -> "ReactDragEventH => Callback",
    "React.MouseEventHandler<HTMLElement>" -> "ReactMouseEventH => Callback",
    "React.DragEventHandler<HTMLElement>" -> "ReactDragEventH => Callback",
    "React.MouseEventHandler<HTMLInputElement>" -> "ReactMouseEventI => Callback",
    "React.DragEventHandler<HTMLInputElement>" -> "ReactDragEventI => Callback",
    "React.FocusEventHandler<HTMLInputElement>" -> "ReactFocusEventI => Callback",
    "React.FocusEventHandler<HTMLSelectElement>" -> "ReactFocusEventS => Callback",
    "React.FocusEventHandler<HTMLTextAreaElement>" -> "ReactFocusEventTA => Callback",
    "React.FocusEventHandler<HTMLElement>" -> "ReactFocusEventH => Callback",
    "FormEventHandler<React.FormEvent<HTMLElement>>" -> "(e:ReactEventH, data:js.Dynamic) => Callback",
    "FormEventHandler<React.FormEvent<HTMLInputElement>>" -> "(ReactEventI,js.Dynamic) => Callback",
    "FormEventHandler<React.FormEvent<HTMLSelectElement>>" -> "(ReactEventS,js.Dynamic) => Callback",
    "FormEventHandler<React.FormEvent<HTMLTextAreaElement>>" -> "(ReactEventTA,js.Dynamic) => Callback",
    "any" -> "ReactEventH => Callback")

  val keyRefProps = Seq(PropMeta(name = "key", tpe = "String", isRequired = false),PropMeta(name = "ref", tpe = "String", isRequired = false))

  val Button = ComponentMeta(name = "Button")
  val Icon = ComponentMeta(name = "Icon")
  val IconGroup = ComponentMeta(name = "IconGroup")
  val Container = ComponentMeta(name = "Container")
  val Divider = ComponentMeta(name = "Divider")
  val Header = ComponentMeta(name = "Header")
  val HeaderContent = ComponentMeta(name = "HeaderContent")
  val HeaderSubHeader = ComponentMeta(name = "HeaderSubHeader")
  val Image = ComponentMeta(name = "Image")
  val ImageGroup = ComponentMeta(name = "ImageGroup")
  val Input = ComponentMeta(name = "Input")
  val Label = ComponentMeta(name = "Label")
  val LabelDetail = ComponentMeta(name = "LabelDetail")
  val LabelGroup = ComponentMeta(name = "LabelGroup")
  val List = ComponentMeta(name = "List")
  val ListContent = ComponentMeta(name = "ListContent")
  val ListDescription = ComponentMeta(name = "ListDescription")
  val ListHeader = ComponentMeta(name = "ListHeader")
  val ListIcon = ComponentMeta(name = "ListIcon")
  val ListItem = ComponentMeta(name = "ListItem")
  val ListList = ComponentMeta(name = "ListList")
  val Form = ComponentMeta(name = "Form")
  val FormField = ComponentMeta(name = "FormField")
  val FormButton = ComponentMeta(name = "FormButton")
  val FormCheckbox = ComponentMeta(name = "FormCheckbox")
  val FormDropdown = ComponentMeta(name = "FormDropdown")
  val FormGroup = ComponentMeta(name = "FormGroup")
  val FormInput = ComponentMeta(name = "FormInput")
  val FormRadio = ComponentMeta(name = "FormRadio")
  val FormSelect = ComponentMeta(name = "FormSelect")
  val FormTextArea = ComponentMeta(name = "FormTextArea")
  val Grid = ComponentMeta(name = "Grid")
  val GridColumn = ComponentMeta(name = "GridColumn")
  val GridRow = ComponentMeta(name = "GridRow")
  val Flag = ComponentMeta(name = "Flag")
  val Segment = ComponentMeta(name = "Segment")
  val SegmentGroup = ComponentMeta(name = "SegmentGroup")


  val components = Seq(Button,
    Icon,
    IconGroup,
    Container,
    Divider,
    Header,
    HeaderContent,
    HeaderSubHeader,
    Image,
    ImageGroup,
    Input,
    Label,
    LabelDetail,
    LabelGroup,
    List,
    ListContent,
    ListDescription,
    ListHeader,
    ListIcon,
    ListItem,
    ListList,
    Form,
    FormField,
    FormButton,
    FormCheckbox,
    FormDropdown,
    FormGroup,
    FormInput,
    FormRadio,
    FormSelect,
    FormTextArea,
    Grid,
    GridColumn,
    GridRow,
    Flag,
    Segment,
    SegmentGroup)

}
