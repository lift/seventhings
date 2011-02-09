package net.liftweb.seventhings.snippet;


import net.liftweb.http.SHtmlJ;
import net.liftweb.http.SessionVar;
import net.liftweb.http.VarsJ;
import net.liftweb.http.js.JE;
import net.liftweb.http.js.JsCmds;
import net.liftweb.seventhings.comet.ChatServer;
import net.liftweb.util.Func;
import net.liftweb.util.Func1;
import scala.Function1;
import scala.xml.NodeSeq;

/**
 * The ChatIn snippet
 */
public class ChatIn {
    // max count per session
    static final SessionVar<Integer> lineCnt = VarsJ.vendSessionVar(0);

    // return the code that transforms the template input to
    // the dynamic output
    public Function1<NodeSeq, NodeSeq> render() {

        // associate a function with the submission of the form element
        return SHtmlJ.j().onSubmit(Func.lift(new Func1<String, Object>() {
          public Object apply(String s) {
            // get the session-specific line count
            int lineCntIs = lineCnt.is();

            // if the message is small enough and the session
            // hasn't sent too many messages, send the String to the ChatServer
            if (s.length() < 50 && lineCntIs < 20) {
              ChatServer.j().send(s);

              // update the line count
              lineCnt.set(lineCntIs + 1);
            }

            // clear the browser's input box
            return new JsCmds.SetValById("chat_in", new JE.Str("")); // clear the input box
          }
        }
        ));
    }


}
