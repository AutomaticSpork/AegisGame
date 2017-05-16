package io.github.automaticspork.aegis;

import android.graphics.Paint;

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
        Vector diff = other.position.clone();
        diff.subtract(position);
        return diff.magnitude() < (radius + other.radius);
    }

}
