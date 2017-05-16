package io.github.automaticspork.aegis;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.List;

/**
 * Created by jaren on 5/15/17.
 */

public class MovingSprite extends CollidableSprite {
    public float speed;
    public Vector velocity;

    public MovingSprite(Vector pos, Paint p, float r, float s) {
        super(pos, p, r);
        speed = s;
        velocity = new Vector();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    public void update(List<Sprite> sprites) {
        super.update(sprites);
        position.add(velocity);
    }

    protected void moveTo(Vector p) {
        Vector diff = p.clone();
        diff.subtract(position);
        diff.normalize();
        diff.multiply(speed);
        position.add(diff);
    }
}
