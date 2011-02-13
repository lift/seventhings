package net.liftweb.seventhings.lib;

import scala.collection.mutable.ListBuffer;
import scala.xml.MetaData;
import scala.xml.NamespaceBinding;
import scala.xml.NodeSeq;

public class XmlJ {
    private static final XmlJBridge bridge = new XmlJBridge();

    private static final NamespaceBinding topScope = bridge.topScope();
    private static final MetaData xmlNull = bridge.getNull();

    public static NamespaceBinding topScope() {
        return topScope;
    }

    public static MetaData xmlNull() {
        return xmlNull;
    }

    public static NodeSeq elemSeq(scala.xml.Node... nodes) {
        ListBuffer buf = new ListBuffer();

        for (scala.xml.Node n : nodes) {
            buf.$plus$eq(n);
        }
        NodeSeq seq1 = scala.xml.NodeSeq.seqToNodeSeq(buf);
        return seq1;
    }

    public static scala.xml.Elem elem(String label, MetaData attributes, scala.xml.Node... children) {
        scala.xml.Elem el = new scala.xml.Elem(null, label, attributes, topScope(), elemSeq(children));
        return el;
    }

    public static scala.xml.Elem elem(String label, scala.xml.Node... children) {
        scala.xml.Elem el = new scala.xml.Elem(null, label, xmlNull(), topScope(), elemSeq(children));
        return el;
    }

    public static MetaData attr(String key, String value) {
        return new scala.xml.UnprefixedAttribute(key, new scala.xml.Text(value), xmlNull());
    }

    public static scala.xml.Node text(String text) {
        return new scala.xml.Text(text);
    }

    public static NodeSeq toNodeSeq(scala.xml.Elem el) {
        return scala.xml.NodeSeq.fromSeq(el.theSeq());
    }



}
