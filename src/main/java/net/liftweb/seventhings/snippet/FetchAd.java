package net.liftweb.seventhings.snippet;

import net.liftweb.util.Css;
import scala.Function1;
import scala.xml.NodeSeq;

public class FetchAd {

    // return the code that transforms the template input to
    // the dynamic output
    public Function1<NodeSeq, NodeSeq> render() {

        // sleep for 1/2 second
        try {
            Thread.sleep(500);
        } catch (InterruptedException IGNORE) {}

        // send the result back
        return Css.sel(".ad", Thread.currentThread().getName());

    }


}
