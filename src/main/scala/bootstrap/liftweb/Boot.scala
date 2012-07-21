package bootstrap.liftweb

import net.liftweb._
import util._
import Helpers._

import common._
import http._
import sitemap._
import Loc._


/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot {
    // where to search snippet
    LiftRules.addToPackages("net.liftweb.seventhings")

    // Build SiteMap
    def sitemap = SiteMap(
      Menu.i("Home") / "index",
      Menu("lazy", S ? "Lazy Loading") / "lazy",
      Menu("parallel", S ? "Parallel Rendering") / "parallel",
      Menu("comet", S ? "Comet & Ajax") / "comet",
      Menu("wiring", S ? "Wiring") / "wiring",
      Menu("templates", S ? "Designer Friendly Templates") / "templates",
      Menu("wizard", S ? "Wizard") / "wizard",
      Menu("security", S ? "Security") / "security")

    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    LiftRules.setSiteMapFunc(() => sitemap)

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)
    
    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))

    LiftRules.resourceNames ::= "i18n/trans"
  }
}
