package net.liftweb.seventhings.lib;

import net.liftweb.http.WiringUI;

public class WiringUIJ {

    private static final Object j = new WiringUIBridge().wiringUI();

    public static Object j() {
        return j;
    }



}
