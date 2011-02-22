package net.liftweb.seventhings.lib

import net.liftweb.wizard.Wizard


abstract class WizardJ extends Wizard {

  abstract class ScreenJ extends Screen {

    /*
  protected def field[T](underlying: => BaseField{type ValueType=T},
                         stuff: FilterOrValidate[T]*)(implicit man: Manifest[T]): Field{type ValueType=T} = {

     */
    // field(java.lang.String,java.lang.String,scala.Function1<java.lang.String,scala.collection.immutable.List<net.liftweb.util.FieldError>>,scala.Function1<java.lang.String,scala.collection.immutable.List<net.liftweb.util.FieldError>>)

    def fieldj(s1: String, arg2: scala.Function1[java.lang.String, scala.collection.immutable.List[net.liftweb.util.FieldError]]*) = field(s1, arg2)

  }

}