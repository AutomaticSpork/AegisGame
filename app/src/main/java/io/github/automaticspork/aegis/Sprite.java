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

public class Sprite implements Comparable {
    public Vector position;
    public boolean toDelete;
    public Paint paint;
    public int z;

    public Sprite(Vector pos, Paint p) {
        position = pos;
        paint = p;
        toDelete = false;
        z = 0;
    }

    @Override
    public int compareTo(Object s) {
        return z - ((Sprite)s).z;
    }

    public void update(List<Sprite> sprites) {
    }

    public void draw(Canvas canvas) {
    }
}
