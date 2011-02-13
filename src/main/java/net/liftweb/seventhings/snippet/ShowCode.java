package net.liftweb.seventhings.snippet;


import net.liftweb.common.Box;
import net.liftweb.http.LiftRulesJ;
import net.liftweb.http.SJ;
import scala.Option;
import scala.xml.Elem;
import scala.xml.NodeSeq;
import scala.xml.Text;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static net.liftweb.seventhings.lib.XmlJ.*;

public class ShowCode {

    private String calcBrush(String fileName) {
        if (fileName.endsWith(".html") || fileName.endsWith(".xml")) {
            return "html";
        }

        if (fileName.endsWith(".java")) {
            return "scala";
        }

        return "scala";
    }

    private String gitDir(String fileName) {
        if (fileName.endsWith(".html") || fileName.endsWith(".xml")) {
            return "webapp";
        }

        if (fileName.endsWith(".java")) {
            return "java";
        }

        return "scala";
    }


    public NodeSeq render() {

        String codeListing = "";

        Option<String> fileNameO = SJ.j().attrsFlattenToMap().get("name");
        if (fileNameO.isDefined()) {

            String fileName = fileNameO.get();

//            System.out.println("fileName="+fileName);

            // does not work with .or(LiftRulesJ.j().loadResource("/src/webapp"+fileName))
            Box<byte[]> codeBox = (LiftRulesJ.j().loadResource("/src/java"+fileName)).failMsg("Cannot find "+fileName);

            if (!codeBox.isDefined()) {
                codeBox = (LiftRulesJ.j().loadResource("/src/webapp"+fileName)).failMsg("Cannot find "+fileName);
            }

            if (codeBox.isDefined()) {

                byte[] code = codeBox.openTheBox();

                Option<String> startO = SJ.j().attrsFlattenToMap().get("start");
                Option<String> endO = SJ.j().attrsFlattenToMap().get("end");

                // Option.getOrElse did not compile, so this dirty workaround
                String start = "";
                String end = "";

                if (startO.isDefined()) { start = startO.get(); }
                if (endO.isDefined())   { end = endO.get(); }

                codeListing = formatCodeListing(code, start, end );

//                System.out.println("CODE: "+codeListing);

                Elem el = elem("div", attr("style", "text-align: center"),
                        elem("tt",
                            text("Listing:"),
                            elem("a", attr("href", "https://github.com/lift/seventhings/tree/liftj/src/main/" + gitDir(fileName) + fileName),
                                text(fileName))),
                        elem("pre", attr("class", "listing brush: "+calcBrush(fileName)), text(codeListing)));

                return toNodeSeq(el);
            }
        }
        return scala.xml.NodeSeq.seqToNodeSeq(new Text("Failure").theSeq());

    }


    private String formatCodeListing(byte[] code, String start, String end) {

        boolean startDefined = (start == null || start.isEmpty()) ? false : true;
        boolean startFound   = false;
        boolean endDefined   = (end == null || end.isEmpty()) ? false : true;
        boolean endFound     = false;
        boolean firstLineAfterStartRead = startDefined ? false : true;

        try {
            String[] rawCodeArr = new String(code, "UTF-8").split("\\n");
            List<String> rawCode = new ArrayList<String>();
            for (String s : rawCodeArr) {
                String s2 = s.replace("\t", "        ");
                if (startDefined && !startFound) {
                    if (s2.indexOf(start) > -1) {
                        startFound  = true;
                    } else {
                        continue;
                    }
                }
                if (endDefined && firstLineAfterStartRead) {
                    if (!endFound) {
                        if (s2.indexOf(end) > -1) {
                            endFound = true;
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
                rawCode.add(s2);

                if (startFound) {
                    firstLineAfterStartRead = true;
                }
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
