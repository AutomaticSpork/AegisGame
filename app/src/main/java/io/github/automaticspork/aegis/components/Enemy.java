package io.github.automaticspork.aegis.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import io.github.automaticspork.aegis.CollidableSprite;
import io.github.automaticspork.aegis.MovingSprite;
import io.github.automaticspork.aegis.Sprite;
import io.github.automaticspork.aegis.Vector;

/**
 * Created by jaren on 5/15/17.
 */

public class Enemy extends MovingSprite {
    public float damage;

    public Enemy(Vector pos, float radius, float s, float d) {
        super(pos, new Paint(), radius, s);
        damage = d;
    }

    @Override
    public void update(List<Sprite> sprites) {
        super.update(sprites);

        for (Sprite s : sprites) {
            if (s instanceof ShieldSprite && ((CollidableSprite)s).collides(this)) {
                toDelete = true;
            }
            if (s instanceof CoreSprite) {
                Vector diff = s.position.clone();
                diff.subtract(position);
                speed = 500 / diff.magnitude();
                moveTo(s.position);

                if (((CollidableSprite)s).collides(this)) {
                    ((CoreSprite)s).health -= damage;
                    toDelete = true;
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawCircle(position.x, position.y, radius, paint);
    }
}
