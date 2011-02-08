package net.liftweb.seventhings.comet;

import net.liftweb.common.SimpleActor;
import net.liftweb.http.CometActorJWithCometListener;
import net.liftweb.http.RenderOut;
import net.liftweb.util.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The Comet (server push) Chat component
 */
public class Chat extends CometActorJWithCometListener {
    // private state
    private List<String> msgs = new ArrayList<String>(); // private state

    // register this component
    public SimpleActor registerWith() {
        return ChatServer.j();
    }

    // handle an incoming message containing a List<String>
    @Receive public void messages(List<String> updatedMessages) {
        msgs = updatedMessages;
        reRender();
    }

    // render the component
    public RenderOut render() {
        return nsToNsFuncToRenderOut(new ClearClearable().
				      sel("li *", new SeqStringIterableConst(msgs)));
    }

}
