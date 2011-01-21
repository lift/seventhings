package net.liftweb.seventhings
package snippet

import net.liftweb._
import http._
import util._
import Helpers._
import common._

import scala.xml.NodeSeq

/**
 * A snippet that will show part or all of a source file
 * that's part of this project
 */
object ShowCode {
  def render: NodeSeq =
    for {
      fileName <- S.attr("name") ?~ "Name missing"

      code <- (LiftRules.loadResource("/src/scala"+fileName) or
               LiftRules.loadResource("/src/webapp"+fileName)) ?~ 
        ("Cannot find "+fileName)

    } yield {
      def calcBrush = if (fileName.endsWith(".html") ||
                          fileName.endsWith(".xml")) "html" else "scala"

      def gitDir = if (fileName.endsWith(".html") ||
                          fileName.endsWith(".xml")) "webapp" else "scala"

      val start = S.attr("start")
      val end = S.attr("end")

      val rawCode = (new String(code, "UTF-8").
                     split("""\n""").
                     toList.map(_.replace("\t", "        "))).
      dropWhile(s => start.map(st => s.indexOf(st) < 0) openOr false) match {
        case Nil => Nil
        case x :: xs => x :: xs.
        takeWhile(s => end.map(en => s.indexOf(en) < 0) openOr true)
      }


      

      <div style="text-align: center"><tt>Listing:
      <a href={"https://github.com/lift/seventhings/tree/master/src/main/"+
               gitDir+fileName}>{fileName}</a></tt>
      <pre class={"listing brush: "+calcBrush}>{rawCode.mkString("\n")}</pre>
      </div>
    }
    
  private implicit def boxNStoNS(in: Box[NodeSeq]): NodeSeq =
    in match {
      case Full(ns) => ns
      case Failure(msg, _, _) => 
        <div style="display: block; margin: 8px; border: 2px solid red">
      Failed to look up source: {msg}
      </div>
      case _ => <div style="display: block; margin: 8px; border: 2px solid red">
      Failed to look up source
      </div>


    }
}
