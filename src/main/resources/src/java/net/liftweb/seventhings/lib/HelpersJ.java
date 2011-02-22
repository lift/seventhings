package net.liftweb.seventhings.lib;


public class HelpersJ {

    private final static HelpersJBridge j = new HelpersJBridge();

    public final static HelpersJBridge j() {
        return j;
    }

}
