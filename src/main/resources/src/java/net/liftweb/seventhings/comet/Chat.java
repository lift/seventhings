package net.liftweb.seventhings.comet;

import net.liftweb.common.SimpleActor;
import net.liftweb.common.SimpleVector;
import net.liftweb.http.CometActorJWithCometListener;
import net.liftweb.http.RenderOut;
import net.liftweb.util.ClearClearable;
import net.liftweb.util.SeqStringIterableConst;

/**
 * The Comet (server push) Chat component
 */
public class Chat extends CometActorJWithCometListener {
    // private state
    private SimpleVector<String> msgs = new SimpleVector<String>(); // private state

    // register this component
    public SimpleActor registerWith() {
        return ChatServer.j();
    }

    // handle an incoming message containing a List<String>
    @Receive public void messages(SimpleVector updatedMessages) {
        msgs = updatedMessages;
        reRender();
    }

    // render the component
    public RenderOut render() {
        return nsToNsFuncToRenderOut(new ClearClearable().
				      sel("li *", new SeqStringIterableConst(msgs)));
    }

}
