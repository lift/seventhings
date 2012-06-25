package net.liftweb.seventhings
package comet

import net.liftweb._
import http._
import actor._
import util._
import Helpers._


/**
 * The comet chat component
 */
class Chat extends CometActor with CometListener {
  private var msgs: Vector[String] = Vector() // private state

  // register this component
  def registerWith = ChatServer

  // listen for messages
  override def lowPriority = {
    case v: Vector[String] => msgs = v; reRender()
  }

  // render the component
  def render = ClearClearable & "li *" #> msgs
}


/**
 * The chat server
 */
object ChatServer extends LiftActor with ListenerManager {
  private var msgs = Vector(S ? "Welcome") // the private data
  
  // what we send to listeners on update
  def createUpdate = msgs

  // handle incoming messages
  override def lowPriority = {
    case s: String => {
      msgs = (msgs :+ s.trim).filter(_.length > 0).takeRight(20)
      updateListeners()
    }
  }
}
