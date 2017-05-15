package io.github.automaticspork.aegis;

/**
 * Created by jaren on 5/15/17.
 */

public class Vector implements Cloneable {
    public double x;
    public double y;

    public Vector() {
        x = 0;
        y = 0;
    }

    public Vector(double val) {
        x = val;
        y = val;
    }

    public Vector(double sX, double sY) {
        x = sX;
        y = sY;
    }

    public Vector clone() {
        return new Vector(x, y);
    }

    public void multiply(double value) {
        x *= value;
        y *= value;
    }

    public void add(Vector other) {
        x += other.x;
        y += other.y;
    }

    public void subtract(Vector other) {
        x -= other.x;
        y -= other.y;
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public void normalize() {
        x /= magnitude();
        y /= magnitude();
    }

    public int getX() {
        return (int)x;
    }

    public int getY() {
        return (int)y;
    }
}
