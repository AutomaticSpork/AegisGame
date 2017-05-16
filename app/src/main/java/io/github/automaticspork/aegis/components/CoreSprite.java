package io.github.automaticspork.aegis.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import io.github.automaticspork.aegis.CollidableSprite;
import io.github.automaticspork.aegis.Sprite;
import io.github.automaticspork.aegis.Vector;

/**
 * Created by jaren on 5/15/17.
 */

public class CoreSprite extends CollidableSprite {
    public float maxHealth;
    public float health;

    public CoreSprite(float h) {
        super(new Vector(), new Paint(Color.BLACK), 50);
        health = h;
        maxHealth = health;
    }

    @Override
    public void update(List<Sprite> sprites) {
        super.update(sprites);

        for (Sprite s : sprites) {
            if (s instanceof Enemy && collides((CollidableSprite)s)) {
                health -= ((Enemy)s).damage;
                s.toDelete = true;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        position = new Vector(canvas.getWidth() / 2, canvas.getHeight() / 2);

        canvas.drawCircle(position.x, position.y, radius, paint);
    }
}
