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

    case (Button.name,"icon",_) => "String | js.Object | ReactElement"

    case (Button.name,"label",_) => "String | js.Object | ReactElement"

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
