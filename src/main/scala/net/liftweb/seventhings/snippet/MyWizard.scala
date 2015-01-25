package net.liftweb.seventhings
package snippet

import net.liftweb._
import http._
import js.jquery.JqJsCmds._
import wizard._
import util.Helpers._

/**
 * An example of a wizard in Lift
 */
object MyWizard extends Wizard {
  override protected def calcAjaxOnDone = Unblock

  // define the first screen
  val nameAndAge = new Screen {
    // it has a name field
    val name = field(S ? "First Name", "",
                     valMinLen(2, S ? "Name Too Short"),
                     valMaxLen(40, S ? "Name Too Long"))

    // and an age field
    val age = field(S ? "Age", 0, minVal(5, S ? "Too young"),
      maxVal(120, S ? "You should be dead"))

    // choose the next screen based on the age
    override def nextScreen = if (age.is < 18) parentName else favoritePet
  }

  // We ask the parent's name if the person is under 18
  val parentName = new Screen {
    val parentName = field(S ? "Mom or Dad's name", "",
                           valMinLen(2, S ? "Name Too Short"),
      valMaxLen(40, S ? "Name Too Long"))
  }

  // we ask for the favorite pet
  val favoritePet = new Screen {
    val petName = field(S ? "Pet's name", "",
                        valMinLen(2, S ? "Name Too Short"),
                        valMaxLen(40, S ? "Name Too Long"))
  }

  // what to do on completion of the wizard
  def finish() {
    S.notice(S ? "Thank you for registering your pet" + ": "+
             favoritePet.petName+
             " " + S ? "your age" + "* 3: "+nameAndAge.age * 3)
  }
}

object AjaxRunner {
  def render = "* [onclick]" #> SHtml.ajaxInvoke(() => ModalDialog(<div><lift:MyWizard ajax="true"/></div>))
}
