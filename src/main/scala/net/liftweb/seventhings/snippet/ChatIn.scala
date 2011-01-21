package net.liftweb.seventhings
package snippet

import comet._

import net.liftweb._
import util._
import Helpers._
import http._
import js.JsCmds._
import js.JE._

/**
 * Handle input by sending the input line
 * to the ChatServer
 */
object ChatIn {
  // max count per session
  private object lineCnt extends SessionVar(0)

  def render =
    "*" #> SHtml.onSubmit(s => {
      if (s.length < 50 && lineCnt < 20) { // 20 lines per session
        ChatServer ! s // send the message
        lineCnt.set(lineCnt.is + 1)
      }
      SetValById("chat_in", "") // clear the input box
    })
}
