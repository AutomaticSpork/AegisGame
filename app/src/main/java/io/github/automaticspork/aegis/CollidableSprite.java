package io.github.automaticspork.aegis;

import android.graphics.Paint;
import android.util.Log;

/**
 * Created by jaren on 5/16/17.
 */

public class CollidableSprite extends Sprite {
    public float radius;

    public CollidableSprite(Vector pos, Paint p, float r) {
        super(pos, p);
        radius = r;
    }

    public boolean collides(CollidableSprite other) {
        if (other.position != null) { // Does this for some reason
            Vector diff = other.position.clone();
            diff.subtract(position);
            return diff.magnitude() < (radius + other.radius);
        }
        Log.e("POSITION WAS NULL", "OTHER: " + other);
        return false;
    }

}
