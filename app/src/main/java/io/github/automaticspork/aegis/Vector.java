package io.github.automaticspork.aegis;

/**
 * Created by jaren on 5/15/17.
 */

public class Vector implements Cloneable {
    public float x;
    public float y;

    public Vector() {
        x = 0;
        y = 0;
    }

    public Vector(float val) {
        x = val;
        y = val;
    }

    public Vector(float sX, float sY) {
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

    public float magnitude() {
        return (float)Math.sqrt(magnitudeSquared());
    }

    public float magnitudeSquared() { return x * x + y * y; }

    public void normalize() {
        double mag = magnitude();
        x /= mag;
        y /= mag;
    }
}
