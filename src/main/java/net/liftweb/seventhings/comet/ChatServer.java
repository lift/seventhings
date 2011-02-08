package net.liftweb.seventhings.comet;

import net.liftweb.http.LiftActorJWithListenerManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The chat server
 */
public class ChatServer extends LiftActorJWithListenerManager {

    private static ChatServer INSTANCE = null;

    // private state.  The messages
    private ArrayList<String> msgs = new ArrayList<String>(); // the private data

    private ChatServer() {
        msgs.add("Welcome");
    }

    public synchronized static ChatServer j() {
        if (INSTANCE == null) {
            INSTANCE = new ChatServer();
        }
        return INSTANCE;
    }

    @Receive
    /**
     * A String is sent as a message to this Actor.  Process it
     */
    private void gotAString(String s) {
        // add to the messages.  No need to synchronize because
        // the method will only be invoked by the Actor thread and
        // only one message will be processed at once
        msgs.add(s);

        // cap the length of our messages at 20 messages
        if (msgs.size() > 20) {
            msgs = new ArrayList(msgs.subList(msgs.size() - 20, msgs.size()));
        }

        // update the listeners
        updateListeners();
    }

    // what we send to listeners on update
    public Object createUpdate() {
        // make a copy of the list and send it to the listeners as unmodifable
        // Note, if we used Scala's immutable collections or even Functional
        // Java's immutable List, we would not have to make a defensive copy
        // of the messages
        return Collections.unmodifiableList((List<String>) msgs.clone());
    }
}


