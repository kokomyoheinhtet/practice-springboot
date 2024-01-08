package com.koko.practicespringboot.genericexample;

import java.util.Arrays;

public interface Mappable {
    void render();

    static double[] stringToLatLong(String location) {

        String[] split = location.split(",");
        Double lat = Double.valueOf(split[0]);
        Double lon = Double.valueOf(split[1]);
        return new double[]{lat, lon};
    }
}

abstract class Point implements Mappable {
    private double[] location = new double[2];

    public Point(String location) {
        this.location = Mappable.stringToLatLong(location);
    }

    @Override
    public void render() {
        System.out.println("Render " + this + " as POINT (" + location() + ")");
    }

    private String location() {
        return Arrays.toString(location);
    }
}

abstract class Line implements Mappable {
    private double[][] locations;

    public Line(String... locations) {
        this.locations = new double[locations.length][];
        int index = 0;
        for (String location : locations) {
            this.locations[index++] = Mappable.stringToLatLong(location);
        }
    }

    @Override
    public void render() {
        System.out.println("Render " + this + " as LINE (" + locations() + ")");
    }

    private String locations() {
        return Arrays.deepToString(locations);
    }
}