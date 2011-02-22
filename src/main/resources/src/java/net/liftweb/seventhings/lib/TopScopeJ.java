package net.liftweb.seventhings.lib;

import scala.xml.NamespaceBinding;

public class TopScopeJ {

    private static final NamespaceBinding j = new XmlJBridge().topScope();

    public static NamespaceBinding j() {
        return j;
    }

}
