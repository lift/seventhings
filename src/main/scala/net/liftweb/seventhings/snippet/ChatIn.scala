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
  def render =
    "*" #> SHtml.onSubmit(s => {
      ChatServer ! s // send the message
      SetValById("chat_in", "") // clear the input box
    })
}
