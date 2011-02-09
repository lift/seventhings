package net.liftweb.seventhings.comet;

import net.liftweb.common.SimpleActor;
import net.liftweb.http.CometActorJ;
import net.liftweb.http.RenderOut;
import net.liftweb.util.*;
import net.liftweb.http.*;
import net.liftweb.util.Css;
import net.liftweb.util.ScheduleJBridge;

import java.util.Date;

public class Yacker extends CometActorJ {
//    public void localSetup() {
//        ping();
//        super.localSetup();
//    }

    private void ping() {
        new ScheduleJBridge().schedule().perform((SimpleActor<Object>) this, new Pinger(), 10000);
    }

    @Receive
    protected void ping(Pinger p) {
        ping();
        super.reRender();
    }

    public RenderOut render() {
        return nsToNsFuncToRenderOut(Css.sel("#yack", new Date().toString()));
    }

    class Pinger {
    }
}
