package net.liftweb.seventhings.snippet;

import net.liftweb.http.SHtmlJ;
import net.liftweb.http.SessionVar;
import net.liftweb.http.js.JE;
import net.liftweb.seventhings.comet.ChatServerJ;
import net.liftweb.util.Css;
import net.liftweb.util.CssSel;
import scala.Function1;

import static net.liftweb.http.js.JsCmds.SetValById;

public class ChatInJ {

/*
These are the compile errors it throws:

[error] /Users/tuhlmann/entw/aktuell/LiftJ/seventhings/src/main/java/net/liftweb/seventhings/ChatInJ.java:24: cannot find symbol
[error] symbol  : constructor SessionVar()
[error] location: class net.liftweb.http.SessionVar<java.lang.Integer>
[error]   final SessionVar<Integer> lineCnt = new SessionVar<Integer>(){}; // because SessionVar is abstract
[error]                                       ^
[error] /Users/tuhlmann/entw/aktuell/LiftJ/seventhings/src/main/java/net/liftweb/seventhings/ChatInJ.java:24: <anonymous net.liftweb.seventhings.snippet.ChatInJ$1> is not abstract and does not override abstract method registerCleanupFunc(scala.Function1<java.lang.Object,java.lang.Object>) in net.liftweb.util.AnyVarTrait
[error]   final SessionVar<Integer> lineCnt = new SessionVar<Integer>(){}; // because SessionVar is abstract
[error]                                                                ^
[error] /Users/tuhlmann/entw/aktuell/LiftJ/seventhings/src/main/java/net/liftweb/seventhings/ChatInJ.java:27: <anonymous net.liftweb.seventhings.snippet.ChatInJ$2> is not abstract and does not override abstract method <A>andThen$mcDD$sp(scala.Function1<java.lang.Double,A>) in scala.Function1
[error]       return Css.sel("*", SHtmlJ.j().onSubmit(new Function1<String, Object>(){
[error]                                                                              ^


 */

//  class SessionVarJ<T> extends SessionVar<T> {
//
//      public SessionVarJ(T value) {
//          super(value);
//      }
//
//  }

  // max count per session
  // private object lineCnt extends SessionVar(0)
  final SessionVar<Integer> lineCnt = new SessionVar<Integer>(){}; // because SessionVar is abstract

  // TODO onSubmit needs to accept the new FuncX
  public CssSel render() {
      return Css.sel("*", SHtmlJ.j().onSubmit(new Function1<String, Object>(){
          public Object apply(String v) {
              String s = v.toString();
              int lineCntIs = (Integer)lineCnt.is();
              if (s.length() < 50 && lineCntIs < 20) {
                  // Call ChatServer: HOW
                  // ChatServerJ.j() ...
                  lineCnt.set(lineCntIs + 1);
              }
              return new SetValById("chat_in", new JE.Str("")); // clear the input box
          }
      }
      ));
  }

   /*
      "*" #> SHtml.onSubmit(s => {
        if (s.length < 50 && lineCnt < 20) { // 20 lines per session
          ChatServer ! s // send the message
          lineCnt.set(lineCnt.is + 1)
        }
        SetValById("chat_in", "") // clear the input box
      })
   */

  public CssSel render2() {
	return Css.sel("#cow", "big").
	    sel("#pig", "oink");
  }

}
