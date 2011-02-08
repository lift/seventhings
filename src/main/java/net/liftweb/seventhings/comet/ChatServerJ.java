package net.liftweb.seventhings.comet;

import net.liftweb.actor.LiftActor;
import net.liftweb.http.ListenerManager;
import scala.PartialFunction;

import java.util.Vector;

/**
   * The chat server
   */
  public class ChatServerJ implements LiftActor, ListenerManager {

    private static ChatServerJ INSTANCE = null;

    Vector<String> msgs = new Vector<String>(); // the private data

    private ChatServerJ() {
      msgs.add("Welcome");
    }

    public static ChatServerJ j() {
        if (INSTANCE == null) {
            INSTANCE = new ChatServerJ();
        }
        return INSTANCE;
    }

    // what we send to listeners on update
    protected Object createUpdate() {
        return msgs;
    }

    // handle incoming messages
    public PartialFunction<Object, Void> lowPriority() {
//    case s: String => {
//      msgs = (msgs :+ s.trim).filter(_.length > 0).takeRight(20)
//      updateListeners();
//    }

      return null;
    }

  }


