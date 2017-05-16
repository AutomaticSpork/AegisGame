package io.github.automaticspork.aegis;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

import java.util.List;

/**
 * Created by jaren on 5/12/17.
 */

public class Sprite {
    public Vector position;
    public double radius;
    public boolean toDelete;

    public Sprite(Vector pos, double r) {
        position = pos;
        radius = r;
        toDelete = false;
    }

    public boolean collides(Sprite other) {
        Vector diff = other.position.clone();
        diff.subtract(position);
        return diff.magnitude() < (radius + other.radius);
    }

    public void update(List<Sprite> sprites) {
    }

    public void draw(Canvas canvas) {
    }
}
