package sui.generator

case class ComponentMeta(name : String,interfaceName : String = "default")

case class PropMeta(name : String,tpe : String,isRequired : Boolean) {
  override def hashCode(): Int = name.hashCode

  override def equals(obj: scala.Any): Boolean = obj match  {
    case o:PropMeta => name == o.name
    case _ => false
  }
}

