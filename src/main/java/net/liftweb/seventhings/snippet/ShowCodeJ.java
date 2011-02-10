package net.liftweb.seventhings.snippet;


import com.sun.org.apache.xpath.internal.axes.NodeSequence;
import net.liftweb.common.Box;
import net.liftweb.http.LiftRulesJ;
import net.liftweb.http.SJ;
import scala.Function1;
import scala.xml.NodeSeq;

import java.io.UnsupportedEncodingException;
import java.util.*;

/*
      dropWhile(s => start.map(st => s.indexOf(st) < 0) openOr false) match {
        case Nil => Nil
        case x :: xs => x :: xs.
        takeWhile(s => end.map(en => s.indexOf(en) < 0) openOr true)
      }


      <div style="text-align: center"><tt>Listing:
      <a href={"https://github.com/lift/seventhings/tree/master/src/main/"+
               gitDir+fileName}>{fileName}</a></tt>
      <pre class={"listing brush: "+calcBrush}>{rawCode.mkString("\n")}</pre>
      </div>
    }
 */

public class ShowCodeJ {

    private String calcBrush(String fileName) {
        return (fileName.endsWith(".html") || fileName.endsWith(".xml")) ? "html" : "scala";
    }

    private String gitDir(String fileName) {
        return (fileName.endsWith(".html") || fileName.endsWith(".xml")) ? "webapp" : "scala";
    }


    public NodeSeq render() {

        String codeListing = "";

        for (scala.collection.Iterator<String> fileNameIt = SJ.j().get("name").failMsg("Name missing").iterator(); fileNameIt.hasNext();) {
            String fileName = fileNameIt.next();

            // does not work with .or(LiftRulesJ.j().loadResource("/src/webapp"+fileName))
            Box<byte[]> codeBox = (LiftRulesJ.j().loadResource("/src/scala"+fileName)).failMsg("Cannot find "+fileName);
            for (scala.collection.Iterator<byte[]> codeIt = codeBox.iterator(); codeIt.hasNext();) {

                byte[] code = codeIt.next();

                Box<String> start = SJ.j().get("start");
                Box<String> end = SJ.j().get("end");

                codeListing = formatCodeListing(code, start, end);

                return null;

            }
        }
        return null;

    }

    private String formatCodeListing(byte[] code, Box<String> start, Box<String> end) {
        try {
            String[] rawCodeArr = new String(code, "UTF-8").split("\\n");
            List<String> rawCode = new ArrayList<String>();
            for (String s : rawCodeArr) {
                rawCode.add(s.replace("\t", "        "));
            }

            StringBuilder sb = new StringBuilder();
            for (String s : rawCode) {
                sb.append(s).append("\n");
            }

            return sb.toString();

        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }



    }

}
