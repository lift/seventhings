package net.liftweb.seventhings.snippet;

import net.liftweb.util.Css;
import net.liftweb.util.Helpers;
import scala.Function1;
import scala.xml.NodeSeq;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

public class LongTime {

    SecureRandom _random = new SecureRandom();

    long randomLong(long mod) {
        synchronized (_random) {
            return Math.abs(_random.nextLong()) % mod;
        }
    }

    public Function1<NodeSeq, NodeSeq> render() {

        // capture the start time
        //Date start = HelpersJ.j().now();
        Date start = new Date();

        // sleep for up to 15 seconds
        try {
            Thread.sleep(randomLong(15000));
        } catch (InterruptedException IGNORE) {
        }

        // send the result back
        return Css.sel("#start", start.toString()).and(Css.sel("#end", new Date().toString()));
    }


}
