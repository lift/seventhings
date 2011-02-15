package net.liftweb.seventhings.lib;

import net.liftweb.http.js.JE;
import net.liftweb.http.js.JsCmd;
import net.liftweb.http.js.jquery.JqJE;
import net.liftweb.http.js.jquery.JqJsCmds;
import scala.xml.NodeSeq;

public class JqJsCmdsJ {

    private final static JqJsCmdsJBridge j = new JqJsCmdsJBridge();

    public static JsCmd appendHtml(String uid, NodeSeq content) {
        return (new JqJE.JqId(new JE.Str(uid)).$tilde$greater(new JqJE.JqAppend(content))).cmd();
    }

    public static JqJsCmdsJBridge j() {
        return j;
    }

}
