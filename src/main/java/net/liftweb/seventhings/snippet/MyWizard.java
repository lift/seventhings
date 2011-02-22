package net.liftweb.seventhings.snippet;

import net.liftweb.http.AbstractScreen;
import net.liftweb.http.SJ;
import net.liftweb.http.js.JsCmd;
import net.liftweb.http.js.jquery.JqJsCmds;
import net.liftweb.seventhings.lib.JqJsCmdsJ;
import net.liftweb.seventhings.lib.ListJ;
import net.liftweb.seventhings.lib.WizardJ;
import net.liftweb.util.Func;
import net.liftweb.util.Func0;
import net.liftweb.wizard.Wizard;

/**
 * An example of a wizard in Lift
 */
public class MyWizard extends WizardJ {

    private MyWizardScala wizardScala = new MyWizardScala();

    private final MyWizardScala.ParentNameScreen parentName;
    private final MyWizardScala.FavoritePetScreen favoritePet;
    private final MyWizardScala.NameAndAgeScreen nameAndAge;

    public MyWizard() {
        parentName  = wizardScala.getParentName();
        favoritePet = wizardScala.getFavoritePet();
        nameAndAge  = wizardScala.getNameAndAge();
    }

    public JsCmd calcAjaxOnDone() {
        return JqJsCmdsJ.j().unblock();
    }

  // define the first screen

  // We ask the parent's name if the person is under 18
//  private WizardJ.ScreenJ parentNameObj = new WizardJ.ScreenJ() {
//    String parentName = fieldj(SJ.j().$qmark("Mom or Dad's name"), ListJ.toSeq(
//            valMinLen(Func.lift(new Func0<Integer>() {
//                public Integer apply() {
//                    return 2;
//                }
//            }), Func.lift(new Func0<String>() {
//                public String apply() {
//                    return SJ.j().$qmark("Name Too Short");
//                }
//            })),
//            valMaxLen(Func.lift(new Func0<Integer>() {
//                public Integer apply() {
//                    return 40;
//                }
//            }), Func.lift(new Func0<String>() {
//                public String apply() {
//                    return SJ.j().$qmark("Name Too Long");
//                }
//            }))));
//  };


  // we ask for the favorite pet
//  private WizardJ.Screen favoritePet = new WizardJ.Screen() {
//    String petName = AbstractScreen.field(SJ.j().$qmark("Pet's name", "",
//            AbstractScreen.valMinLen(2, SJ.j().$qmark("Name Too Short")),
//            AbstractScreen.valMaxLen(40, SJ.j().$qmark("Name Too Long"))));
//  };




//  // what to do on completion of the wizard
//  public void finish() {
//    SJ.j().notice("Thank you for registering your pet: " +
//            favoritePet.petName() +
//            " your age * 3: " + nameAndAge.age() * 3);
//  }

    public void finish() {
        wizardScala.finish();
    }

}
