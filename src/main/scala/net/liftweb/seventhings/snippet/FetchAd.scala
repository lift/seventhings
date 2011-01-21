package net.liftweb.seventhings
package snippet

import net.liftweb._
import util._
import Helpers._

/**
 * Fetch an ad from an ad server (takes about 1/2 second
 */
object FetchAd {
  def render = {
    // sleep for 1/2 second
    Thread.sleep(500 millis)

    // send the result back
    ".ad" #> Thread.currentThread.getName
  }
}
