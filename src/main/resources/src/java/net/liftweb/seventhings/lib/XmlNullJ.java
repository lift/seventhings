package net.liftweb.seventhings.lib;

import com.sun.istack.internal.Nullable;
import scala.xml.MetaData;
import scala.xml.Null;

/**
 * This source file belongs to the AGYNAMIX GUI AppFramework.
 * User: tuhlmann
 * Date: 11.02.11
 * Time: 10:58
 * (c) AGYNAMIX Torsten Uhlmann
 */
public class XmlNullJ {

    private static final MetaData j = new XmlNullJBridge().getNull();

    public static MetaData j() {
        return j;
    }

}
