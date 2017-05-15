package io.github.automaticspork.aegis.components;

import android.graphics.Canvas;

import java.util.List;

import io.github.automaticspork.aegis.MovingSprite;
import io.github.automaticspork.aegis.Sprite;
import io.github.automaticspork.aegis.Vector;

/**
 * Created by jaren on 5/15/17.
 */

public class Enemy extends MovingSprite {
    public double damage;

    public Enemy(Vector pos, double radius, double s, double d) {
        super(pos, radius, s);
        damage = d;
    }

    @Override
    public void update(List<Sprite> sprites) {
        super.update(sprites);

        for (Sprite s : sprites) {
            if (s instanceof CoreSprite) {
                moveTo(s.position);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
