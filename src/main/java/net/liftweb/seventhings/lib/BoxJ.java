package net.liftweb.seventhings.lib;

public class BoxJ {

    private final static BoxJBridge j = new BoxJBridge();

    public static BoxJBridge j() {
        return j;
    }

}
