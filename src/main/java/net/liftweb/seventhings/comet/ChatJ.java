package net.liftweb.seventhings.comet;

import net.liftweb.actor.JavaActorBase.Receive;
import net.liftweb.http.JavaCometActor;
import net.liftweb.http.RenderOut;
import net.liftweb.util.ClearClearable;
import net.liftweb.util.Css;

import java.util.Vector;

public class ChatJ extends JavaCometActor {

    private Vector<String> msgs = new Vector<String>(); // private state

    // listen for messages
    @Receive
    protected void messages(Vector<String> m) {
        this.msgs = m;
        render();
    }

    // render the component
    public RenderOut render() {
        return nsToNsFuncToRenderOut(new ClearClearable().and(Css.sel("li *", "!!! here the msgs should appear")));
    }

}
