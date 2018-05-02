package org.brohede.marcus.fragmentsapp;

/**
 * Created by marcus on 2018-04-25.
 */

public class Mountain {
    private String name;
    private String location;
    private int height;

    public Mountain (String inName, String inLocation, int inHeight) {
        name = inName;
        location = inLocation;
        height = inHeight;
    }
    public String info() {
        String str = location;
        return str;
    }

    public String height() {
        return Integer.toString(height);
    }

    @Override
    public String toString() {
        return name;
    }
}
