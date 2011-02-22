package net.liftweb.seventhings
package snippet 

import net.liftweb._
import http._
import SHtml._
import util._
import Helpers._
import js._
import js.JsCmds._
import js.jquery._

import scala.xml.{NodeSeq, Text}

case class Line(guid: String, name: String, price: Double, taxable: Boolean)

/**
 * An invoice system with subtotals, tax, etc.
 */
class InvoiceWiring {
  /**
   * Define the relationships among the items
   */
  private object Info {
    val invoices = ValueCell(List(newLine))
    val taxRate = ValueCell(0.05d)
    val subtotal = invoices.lift(_.foldLeft(0d)(_ + _.price))
    val taxable = invoices.lift(_.filter(_.taxable).
                                foldLeft(0D)(_ + _.price))

    val tax = taxRate.lift(taxable) {_ * _}

    val total = subtotal.lift(tax) {_ + _}    
  }

  /**
   * wire an element to subtotal
   */
  def subtotal = WiringUI.toNode(Info.subtotal)(doubleDraw)

  /**
   * Wire an element to taxable
   */
  def taxable = WiringUI.toNode(Info.taxable)(doubleDraw)

  def tax = WiringUI.toNode(Info.tax, JqWiringSupport.fade)(doubleDraw)

  def total = WiringUI.toNode(Info.total, JqWiringSupport.fade)(doubleDraw)


  /** *
   * The tax rate input
   */
  def taxRate = ajaxText(Info.taxRate.get.toString,
                         doubleToJsCmd(Info.taxRate.set))

  /**
   * Draw all the input lines
   */
  def showLines = "* *" #> (Info.invoices.get.flatMap(renderLine): NodeSeq)

  /**
   * Add a line to the input
   */
  def addLine = 
    "* [onclick]" #> ajaxInvoke(() => 
      JqJsCmds.AppendHtml("invoice_lines", renderLine(appendLine)))

  /**
   * render a line of input fields
   */
  private def renderLine(theLine: Line): NodeSeq = {
    import theLine._

    <div id={guid}>
    {ajaxText(name, s => mutateLine(guid)(_.copy(name = s)))}
  
    {ajaxText(price.toString,
               (d: Double) => mutateLine(guid) {_.copy(price = d)})}

    {ajaxCheckbox(theLine.taxable,
                   b => mutateLine(guid) {_.copy(taxable = b)})}
    </div>
  }

  private def newLine = Line(nextFuncName, "", 0, false)
    
  private def appendLine: Line = {
    val ret = newLine
    Info.invoices.set(ret :: Info.invoices.get)
    ret
  }

  /**
   * Mutate a line and update the Info field
   */
  private def mutateLine(guid: String)(f: Line => Line) {
    val all = Info.invoices.get
    val head = all.filter(_.guid == guid).map(f)
    val rest = all.filter(_.guid != guid)
    Info.invoices.set(head ::: rest)
  }

  // convert a Double to a NodeSeq
  private def doubleDraw: (Double, NodeSeq) => NodeSeq = 
    (d, ns) => Text(java.text.NumberFormat.getCurrencyInstance.format(d))


  // Some helpful implicit conversions
  private implicit def unitToJsCmd(in: Unit): JsCmd = Noop
  private implicit def doubleToJsCmd(in: Double => Any): String => JsCmd =
    str => {asDouble(str).foreach(in)}
}
