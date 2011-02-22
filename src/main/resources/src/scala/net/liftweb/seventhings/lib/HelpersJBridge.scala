package net.liftweb.seventhings.lib

import net.liftweb.util._

/**
 * I was not able to just return the Helpers object. the compiler complained it found Helpers$ instead of Helpers.
 */
class HelpersJBridge extends TimeHelpers with StringHelpers with ListHelpers
with SecurityHelpers with BindHelpers with HttpHelpers
with IoHelpers with BasicTypesHelpers
with ClassHelpers with ControlHelpers
{

}