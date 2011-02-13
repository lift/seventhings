
package net.liftweb.seventhings.lib

import xml.{Elem, Null, TopScope}
import net.liftweb.http.SHtml
import net.liftweb.http.SHtml.BasicElemAttr

class XmlJBridge {

  def topScope = TopScope

  def getNull = Null

  def noopElemAttr = BasicElemAttr("dummy", "dummy")

}

//class NoopElemAttr extends BasicElemAttr {
//    override def apply(e: Elem): Elem = e
//}


