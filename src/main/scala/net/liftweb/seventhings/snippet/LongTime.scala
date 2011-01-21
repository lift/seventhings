package net.liftweb.seventhings
package snippet

import net.liftweb._
import util._
import Helpers._

/**
 * Do something that takes a long time to do
 */
object LongTime {
  def render = {
    // capture the start time
    val start = now

    // sleep for up to 15 seconds
    Thread.sleep(randomLong(15 seconds))

    // send the result back
    "#start" #> start.toString &
    "#end" #> now.toString
   }
}
