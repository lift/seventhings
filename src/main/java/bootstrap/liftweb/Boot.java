package bootstrap.liftweb;


import net.liftweb.http.*;
import net.liftweb.http.js.JsCmd;
import net.liftweb.http.provider.HTTPRequest;
import net.liftweb.sitemap.Menu;
import net.liftweb.sitemap.MenuJ;
import net.liftweb.sitemap.SiteMap;
import net.liftweb.sitemap.SiteMapJ;
import net.liftweb.util.Func;
import net.liftweb.util.Func0;
import net.liftweb.util.Func1;

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
public class Boot {
    SiteMap makeSiteMap() {
        return SiteMapJ.build(
                MenuJ.j().i("Home").path("index"),
                MenuJ.j().i("Lazy Loading").path("lazy"),
                MenuJ.j().i("Parallel Rendering").path("parallel"),
                MenuJ.j().i("Comet & Ajax").path("comet"),
                MenuJ.j().i("Wiring").path("wiring"),
                MenuJ.j().i("Designer Friendly Templates").path("templates"),
                MenuJ.j().i("Wizard").path("Wizard"),
                MenuJ.j().i("Security").path("security"));

        /*
         * FIXME -- We need Menu.apply() equiv in Java for:
           Menu("lazy", "Lazy Loading") / "lazy",
           Menu("parallel", "Parallel Rendering") / "parallel",
           Menu("comet", "Comet & Ajax") / "comet",
           Menu("wiring", "Wiring") / "wiring",
           Menu("templates", "Designer Friendly Templates") / "templates",
           Menu("wizard", "Wizard") / "wizard",
           Menu("security", "Security") / "security")
         */
    }


    public void boot() {
        // where to search snippet
        LiftRulesJ.j().addToPackages("net.liftweb.seventhings");

        // Set the sitemap
        LiftRulesJ.j().setSiteMapFunc(Func.lift(new Func0<SiteMap>() {
            public SiteMap apply() {
                return makeSiteMap();
            }
        }));


        //Show the spinny image when an Ajax call starts
        LiftRulesJ.j().
                setAjaxStart(new Func0<JsCmd>() {
                    public JsCmd apply() {
                        return LiftRulesJ.j().
                                jsArtifacts().show("ajax-loader").cmd();
                    }
                });

        // Make the spinny image go away when it ends
        LiftRulesJ.j().
                setAjaxEnd(new Func0<JsCmd>() {
                    public JsCmd apply() {
                        return LiftRulesJ.j().
                                jsArtifacts().hide("ajax-loader").cmd();
                    }
                });

        // Force the request to be UTF-8
        LiftRulesJ.
                j().
                early().append(Func.lift(new Func1<HTTPRequest, Object>() {
            public Object apply(HTTPRequest req) {
                req.setCharacterEncoding("UTF-8");
                return "";
            }
        }));


        // Use HTML5 for rendering
        LiftRulesJ.j().
                htmlProperties().
                theDefault().
                set(Func.vendor(Func.lift(new Func1<Req, HtmlProperties>() {
                    public HtmlProperties apply(Req r) {
                        return new Html5Properties(r.userAgent());
                    }
                })));

    }
}
