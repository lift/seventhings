package net.liftweb.seventhings.snippet;

import net.liftweb.http.SHtml;
import net.liftweb.http.SHtmlJ;
import net.liftweb.http.WiringUI;
import net.liftweb.http.js.JsCmd;
import net.liftweb.http.js.JsCmds;
import net.liftweb.http.js.JsExp;
import net.liftweb.http.js.jquery.JqJsCmds;
import net.liftweb.http.js.jquery.JqWiringSupport;
import net.liftweb.seventhings.lib.*;
import net.liftweb.util.*;
import net.liftweb.util.Css;
import net.liftweb.util.Func;
import net.liftweb.util.Func0;
import net.liftweb.util.Func1;
import net.liftweb.util.Func2;
import scala.Function1;
import scala.Function2;
import scala.collection.Iterator;
import scala.collection.immutable.Nil;
import scala.collection.mutable.ListBuffer;
import scala.runtime.AbstractFunction1;
import scala.runtime.AbstractFunction2;
import scala.xml.Elem;
import scala.xml.NodeSeq;
import scala.xml.Text;
import scala.collection.JavaConversions;

import javax.swing.text.Element;
import java.text.NumberFormat;
import java.util.ArrayList;

import static net.liftweb.seventhings.lib.XmlJ.*;

/**
 * An invoice system with subtotals, tax, etc.
 */
public class InvoiceWiring {

    final Info info;

    class Line {
        public final String  guid;
        public final String  name;
        public final double  price;
        public final boolean taxable;

        Line(String guid, String name, double price, boolean taxable) {
            this.guid = guid;
            this.name = name;
            this.price = price;
            this.taxable = taxable;
        }
    }

  /**
   * Define the relationships among the items
   */
  private class Info {

      final InvoiceWiring iWiring;

      final net.liftweb.util.ValueCell invoices;
      final net.liftweb.util.ValueCell taxRate;
      final net.liftweb.util.Cell subtotal;
      final net.liftweb.util.Cell taxable;
      final net.liftweb.util.Cell tax;
      final net.liftweb.util.Cell total;

      Info(InvoiceWiring iWiring) {
          this.iWiring = iWiring;
          ListBuffer buf = new ListBuffer();
          buf.$plus$eq(iWiring.newLine());
          invoices = new net.liftweb.util.ValueCell(buf.toList());
          taxRate  = new net.liftweb.util.ValueCell(0.05d);

          subtotal = invoices.lift(Func.lift(new Func1<scala.collection.immutable.List<Line>, Double>(){
              public Double apply(scala.collection.immutable.List<Line> in) {
                  return in.foldLeft(0d, Func.lift(new Func2<Double, Line, Double>(){
                      public Double apply(Double in, Line line) {
                          return in + line.price;
                      }
                  }));
              }
          }));

          taxable = invoices.lift(new AbstractFunction1<scala.collection.immutable.List<Line>, Double>(){
              public Double apply(scala.collection.immutable.List<Line> in) {
                  return in.foldLeft(0d, new AbstractFunction2<Double, Line, Double>(){
                      public Double apply(Double in, Line line) {
                          if (line.taxable) {
                              return in + line.price;
                          } else {
                              return in;
                          }
                      }
                  });
              }
          });

          tax = taxRate.lift(taxable, Func.lift(new Func2<Double, Double, Double>(){
              public Double apply(Double left, Double right) {
                  return left * right;
              }
          }));

          total = subtotal.lift(tax, Func.lift(new Func2<Double, Double, Double>(){
              public Double apply(Double left, Double right) {
                  return left + right;
              }
          }));
      }

  }

    public InvoiceWiring() {
        this.info = new Info(this);
    }

    /**
     * wire an element to subtotal
     */
    public Function1<NodeSeq, NodeSeq> subtotal() {
        return WiringUI.toNode(info.subtotal, doubleDraw());
    }

    /**
     * Wire an element to taxable
     */
    public Function1<NodeSeq, NodeSeq> taxable() {
        return WiringUI.toNode(info.taxable, doubleDraw());
    }

    public Function1<NodeSeq, NodeSeq> tax() {
        return WiringUI.toNode(info.tax, JqWiringSupport.fade(), doubleDraw());
    }

    public Function1<NodeSeq, NodeSeq> total() {
        return WiringUI.toNode(info.total, JqWiringSupport.fade(), doubleDraw());
    }

    /** *
     * The tax rate input
     * def ajaxText(value: String, func: String => JsCmd, attrs: ElemAttr*): Elem
     */
    public Elem taxRate() {
        return SHtmlJ.j().ajaxText(info.taxRate.get().toString(),
                doubleToJsCmd(Func.lift(new Func1<Double, Object>() {
                    public Object apply(Double d) {
                        return info.taxRate.set(d);
                    }
                })), ListJ.Nil());
    }

    /**
 * Add a line to the input
       */
    public Function1<NodeSeq, NodeSeq> addLine() {
        final scala.Tuple2<String, JsExp> ajax = SHtmlJ.j().ajaxInvoke(net.liftweb.util.Func.lift(new net.liftweb.util.Func0<JsCmd>() {
            public JsCmd apply() {
                return JqJsCmdsJ.appendHtml("invoice_lines", renderLine(appendLine()));
            }
        }));
        
        return Css.sel("* [onclick]", ((JsExp)ajax._2()).toJsCmd());
    }

    /**
     * Draw all the input lines
     */
    public Function1<NodeSeq, NodeSeq> showLines() {
        return Css.sel("* *", renderLines((scala.collection.immutable.List)info.invoices.get()));
    }

    private NodeSeq renderLines(scala.collection.immutable.List lines) {
        ListBuffer buf = new ListBuffer();
        if (lines != null){
            for (Iterator it = lines.toIterator(); it.hasNext();) {
                final Line l = (Line) it.next(); // or is it a Box?
                buf.$plus$eq(renderLineAsElem(l));
            }
        }
        return scala.xml.NodeSeq.fromSeq(buf.toSeq());
    }

    public NodeSeq renderLine(final Line theLine) {
        return toNodeSeq(renderLineAsElem(theLine));
    }

    /**
     * render a line of input fields
     */
    private Elem renderLineAsElem(final Line theLine) {

        if (theLine == null) {
            return elem("div", text("the Line is NULL"));
        }

        System.out.println("renderLineAsElem");

        Elem el = elem("div", attr("id", theLine.guid),

                SHtmlJ.j().ajaxText(theLine.name, Func.lift(new Func1<String, JsCmd>() {
                    public JsCmd apply(final String s) {
                        System.out.println("JsCmd APPLY. s="+s);
                        return mutateLine(theLine.guid, new Func1<Line, Line>() {
                            public Line apply(Line in) {
                                System.out.println("mutateLine Func. Line="+in);
                                Line out = new Line(in.guid, s, in.price, in.taxable);
                                System.out.println("mutateLine Func. Line Out="+out);
                                return out;
                            }
                        });
                    }
                }), ListJ.Nil()),

                SHtmlJ.j().ajaxText(""+theLine.price, Func.lift(new Func1<String, JsCmd>() {
                    public JsCmd apply(final String s) {
                        final double dbl = Double.parseDouble(s);
                        return mutateLine(theLine.guid, new Func1<Line, Line>() {
                            public Line apply(Line in) {
                                return new Line(in.guid, in.name, dbl, in.taxable);
                            }
                        });
                    }
                }), ListJ.Nil()),

                SHtmlJ.j().ajaxCheckbox(theLine.taxable, Func.lift(new Func1<Boolean, JsCmd>() {
                    @Override
                    public JsCmd apply(final Boolean b) {
                        return mutateLine(theLine.guid, new Func1<Line, Line>(){
                            public Line apply(Line in) {
                                return new Line(in.guid, in.name, in.price, b);
                            }
                        });
                    }
                }), ListJ.Nil()));

        return el;

    }

    private Line appendLine() {
      Line ret = newLine();
      scala.collection.immutable.List l = (scala.collection.immutable.List) info.invoices.get();
      l.$colon$colon(ret);
      info.invoices.set(l);
      return ret;
    }


    public Line newLine() {
        return new Line(HelpersJ.j().nextFuncName(), "", 0, false);
    }

    /**
     * Mutate a line and update the Info field
     */
    private JsCmd mutateLine(final String guid, final Func1<Line, Line> fIn) {
        final Function1<Line, Line> f = Func.lift(fIn);
        scala.collection.immutable.List all = (scala.collection.immutable.List) info.invoices.get();

        java.util.List<Line> head = new java.util.ArrayList<Line>();
        java.util.List<Line> rest = new java.util.ArrayList<Line>();
        for (Object o : JavaConversions.asJavaIterable(all)) {
            Line l = (Line) o;
            if (l.guid.equals(guid)) {
                head.add((Line)f.apply(l));
            } else {
                rest.add(l);
            }
        }

        head.addAll(rest);

        info.invoices.set(JavaConversions.asScalaBuffer(head).toList());
        return JsCmds.Noop();
    }

    private Function2<Double, NodeSeq, NodeSeq> doubleDraw() {
        return Func.lift(new net.liftweb.util.Func2<Double, NodeSeq, NodeSeq>() {
            public NodeSeq apply(Double d, NodeSeq ns) {
                return new Text(NumberFormat.getCurrencyInstance().format(d));
            }
        });
    }


    private Function1<String, JsCmd> doubleToJsCmd(final Function1<Double, Object> in) {
        return Func.lift(new Func1<String, JsCmd>() {
            public JsCmd apply(String str) {
                try {
                    double d = Double.parseDouble(str);
                    return (JsCmd) in.apply(d);
                } catch (Exception IGNORE) {
                    return JsCmds.Noop();
                }
            }
        }) ;
    }

}
