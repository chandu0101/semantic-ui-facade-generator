package sui.generator

import SuiLibrary._

object SuiTypeMapper extends SuiHelpers{

  def isEnum(tpe : String) = tpe.split("\\|").forall(_.contains("'"))

  def isKnownTypes(tpe : String) = {
    tpe.split("\\|").map(_.trim).forall(s => predefinedEnums.keys.toSet.contains(s) || typeScriptCoreTypes.contains(s))
  }

  def isKnownTypesAndEnum(tpe : String) = tpe.split("\\|").map(_.trim).forall(s => predefinedEnums.keys.toSet.contains(s) || typeScriptCoreTypes.contains(s) || s.contains("'"))

  def apply(compName : String,fieldName : String,tpe : String) : String = (compName,fieldName,tpe) match {

    case (cn,fn,tpe)  if(isEnum(tpe)) => {
      val name = s"Semantic${cn}${fieldName.toUpperCase()}"
      val sjscode = generateScalaJSEnum(name,tpe)
      onFlyGeneratedEnums = onFlyGeneratedEnums.updated(name,sjscode)
      name
    }

    case (Header.name,"image",_) => "String | ReactElement"

    case (Image.name,"wrapped",_) => "Boolean | js.Object"

    case (Input.name,"action",_) => "Boolean | String | ReactNode "

    case (Label.name,"detail",_) => "ReactNode"

    case (Label.name,"empty",_) => "Boolean | ReactNode"

    case (Label.name,"image",_) => "Boolean | ReactNode"

    case (Label.name,"onClick",_) => "(ReactMouseEventH,js.Dynamic) => Callback"

    case (Label.name,"onRemove",_) => "(ReactEventH,js.Dynamic) => Callback"

    case (List.name,"items",_) => "js.Array[js.Object]"

    case (Form.name,"serializer",_) => "() => Callback"

    case (Form.name,"onSubmit",_) => "(ReactEventH,js.Dynamic) => Callback"

    case (FormField.name,"disabled",_) => "Boolean"

    case (FormField.name,"required",_) => "Boolean | js.Array[String]"

    case (FormRadio.name,"onChange",_) => "(ReactEventH,js.Dynamic) => Callback"

    case (cn,"control",_) if(cn.startsWith("Form")) => "String | ReactElement"

    case (cn,"description",_) if(cn == ListContent.name || cn == ListItem.name) => "ReactNode"

    case (cn,"header",_) if(cn == ListContent.name || cn == ListItem.name) => "ReactNode"

    case (_,"content",_) => "ReactNode"

    case (_,"icon",_) => "String | js.Object | ReactElement"

    case (_,"as",_) => "String | js.Function"

    case (_,"label",_) => "String | js.Object | ReactElement"

    case (_,_,tpe) if(isKnownTypes(tpe)) => {
      tpe.split("\\|").map(_.trim).map(s => if(typeScriptCoreTypes(s)) typescriptCoreToscalajsCore(s) else s).mkString("|")
    }

    case (cn,fn,tpe) if(isKnownTypesAndEnum(tpe)) => {
      val allTypes = tpe.split("\\|").map(_.trim)
      val strs = allTypes.filter(_.contains("'")).mkString("|")
      val name = s"Semantic${cn}${fieldName.toUpperCase()}"
      val sjscode = generateScalaJSEnum(name,strs)
      onFlyGeneratedEnums = onFlyGeneratedEnums.updated(name,sjscode)
      (Seq(name) ++ allTypes.filterNot(_.contains("'")).map(s => if(typeScriptCoreTypes(s)) typescriptCoreToscalajsCore(s) else s)).mkString("|")
    }

    case (_,_,"React.ReactNode") => "ReactNode"
  }
}
