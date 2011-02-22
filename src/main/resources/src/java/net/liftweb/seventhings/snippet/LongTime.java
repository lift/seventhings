package net.liftweb.seventhings.snippet;

import net.liftweb.http.SHtmlJ;
import net.liftweb.seventhings.lib.HelpersJ;
import net.liftweb.util.Css;
import net.liftweb.util.Helpers;
import scala.Function1;
import scala.xml.NodeSeq;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

/**
 * Do something that takes a long time to do
 */
public class LongTime {

    public Function1<NodeSeq, NodeSeq> render() {

        // capture the start time
        Date start = HelpersJ.j().now();

        // sleep for up to 15 seconds
        try {
            Thread.sleep(HelpersJ.j().randomLong(15000));
        } catch (InterruptedException IGNORE) {
        }

        // send the result back
        return Css.sel("#start", start.toString()).and(Css.sel("#end", new Date().toString()));
    }


}
