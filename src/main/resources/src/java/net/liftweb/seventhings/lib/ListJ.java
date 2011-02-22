package net.liftweb.seventhings.lib;

import scala.collection.Seq;
import scala.collection.mutable.ListBuffer;

public class ListJ {

    public static Seq Nil() {
        return scala.collection.immutable.Nil$.MODULE$;
    }

    public static Seq toSeq(Object... obj) {
        ListBuffer buf = new ListBuffer();
        for (Object o : obj) {
           buf.$plus$eq(o);
        }
        return buf.toSeq();
    }

}
