package net.liftweb.seventhings.snippet;

import net.liftweb.common.Box;
import net.liftweb.common.Empty;
import net.liftweb.http.SHtmlJ;
import net.liftweb.http.js.JsCmd;
import net.liftweb.http.js.JsExp;
import net.liftweb.http.js.jquery.JqJsCmds;
import net.liftweb.seventhings.lib.BoxJ;
import net.liftweb.seventhings.lib.JqJsCmdsJ;
import net.liftweb.util.Css;
import scala.Function1;
import scala.xml.Elem;
import scala.xml.NodeSeq;
import static net.liftweb.seventhings.lib.XmlJ.*;

public class AjaxRunner {

    public Function1<NodeSeq, NodeSeq> render() {

        final scala.Tuple2<String, JsExp> ajax = SHtmlJ.j().ajaxInvoke(net.liftweb.util.Func.lift(new net.liftweb.util.Func0<JsCmd>() {
            public JsCmd apply() {
                final Elem el = elem("div", nselem("lift", "MyWizard", attr("ajax", "true")));
                return new JqJsCmds.ModalDialog(toNodeSeq(el), (Box)BoxJ.j().empty());
            }
        }));

        return Css.sel("* [onclick]", ((JsExp)ajax._2()).toJsCmd());
    }

}
