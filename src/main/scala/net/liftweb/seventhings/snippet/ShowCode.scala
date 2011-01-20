package net.liftweb.seventhings
package snippet

import net.liftweb._
import http._
import common._

import scala.xml.NodeSeq

object ShowCode {
  def render: NodeSeq =
    for {
      fileName <- S.attr("name") ?~ 
        "Name missing"

      code <- LiftRules.loadResource("/src/scala"+fileName) ?~ 
        ("Cannot find "+fileName)

    } yield <div style="text-align: center"><tt>Listing: <a href={"https://github.com/lift/seventhings/tree/master/src/main/scala"+fileName}>{fileName}</a></tt>
  <pre class="listing brush: scala">{new String(code, "UTF-8")}</pre>
  </div>
    
  private implicit def boxNStoNS(in: Box[NodeSeq]): NodeSeq =
    in match {
      case Full(ns) => ns
      case Failure(msg, _, _) => throw new SnippetExecutionException(msg)
      case _ => throw new SnippetExecutionException("Couldn't calculate value")
    }
}
