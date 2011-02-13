package net.liftweb.seventhings.snippet;

import net.liftweb.http.AbstractScreen;
import net.liftweb.http.SJ;
import net.liftweb.http.js.JsCmd;
import net.liftweb.http.js.jquery.JqJsCmds;
import net.liftweb.seventhings.lib.WizardJ;
import net.liftweb.wizard.Wizard;

public class MyWizardJava extends WizardJ {

  public JsCmd calcAjaxOnDone() {
      return null;
     //JqJsCmds.Unblock;
  }


  // We ask the parent's name if the person is under 18
//  private WizardJ.Screen parentName = new WizardJ.Screen() {
//    String parentName = AbstractScreen.field(SJ.j().$qmark("Mom or Dad's name", "",
//                           AbstractScreen.valMinLen(2, SJ.j().$qmark("Name Too Short")),
//                           AbstractScreen.valMaxLen(40, SJ.j().$qmark("Name Too Long"))));
//  };


  // we ask for the favorite pet
//  private WizardJ.Screen favoritePet = new WizardJ.Screen() {
//    String petName = AbstractScreen.field(SJ.j().$qmark("Pet's name", "",
//            AbstractScreen.valMinLen(2, SJ.j().$qmark("Name Too Short")),
//            AbstractScreen.valMaxLen(40, SJ.j().$qmark("Name Too Long"))));
//  };




  // what to do on completion of the wizard
  public void finish() {
    SJ.j().notice("Thank you for registering your pet: "); // +
//            favoritePet.petName +
//            " your age * 3: " + nameAndAge.age * 3);
  }


}
