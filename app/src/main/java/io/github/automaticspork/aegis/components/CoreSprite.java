package io.github.automaticspork.aegis.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import io.github.automaticspork.aegis.Sprite;
import io.github.automaticspork.aegis.Vector;

/**
 * Created by jaren on 5/15/17.
 */

public class CoreSprite extends Sprite {
    public double health;

    public CoreSprite(double h) {
        super(new Vector(), 50);
        health = h;
    }

    @Override
    public void update(List<Sprite> sprites) {
        super.update(sprites);

        for (Sprite s : sprites) {
            if (s instanceof Enemy && collides(s)) {
                health -= ((Enemy)s).damage;
                s.toDelete = true;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        position = new Vector(canvas.getWidth() / 2, canvas.getHeight() / 2);

        canvas.drawCircle(position.getX(), position.getY(), (int)radius, new Paint(Color.BLACK));
    }
}
