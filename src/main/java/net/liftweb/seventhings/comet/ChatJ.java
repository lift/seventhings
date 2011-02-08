package net.liftweb.seventhings.comet;

import net.liftweb.common.Empty;
import net.liftweb.common.Full;
import net.liftweb.common.SimpleActor;
import net.liftweb.http.CometActor;
import net.liftweb.http.CometListener;
import net.liftweb.http.RenderOut;
import net.liftweb.util.ClearClearable;
import net.liftweb.util.Css;
import scala.PartialFunction;

import java.util.Vector;

public class ChatJ implements CometActor, CometListener {

    private Vector<String> msgs = new Vector<String>(); // private state

    // register this component
    public SimpleActor registerWith() {
        return ChatServerJ.j();
    }

    // listen for messages
    public PartialFunction<Object, Void> lowPriority() {
        //case v: Vector[String] => msgs = v; reRender()
        return null;
    }

    // render the component
    // public  render = ClearClearable & "li *" #> msgs
    public RenderOut render() {
        return new RenderOut(new Full(new ClearClearable().and(Css.sel("li *", "!!! here the msgs should appear"))));
    }

}
