package io.github.automaticspork.aegis.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import io.github.automaticspork.aegis.CollidableSprite;
import io.github.automaticspork.aegis.GameView;
import io.github.automaticspork.aegis.Sprite;
import io.github.automaticspork.aegis.Vector;

/**
 * Created by jaren on 5/15/17.
 */

public class CoreSprite extends CollidableSprite {
    public float maxHealth;
    public float health;
    public int charges;

    public CoreSprite(float h) {
        super(new Vector(), new Paint(), 50);
        health = h;
        maxHealth = health;
        charges = 3;
        paint.setColor(Color.parseColor("#ffa000"));
    }

    @Override
    public void update(List<Sprite> sprites, GameView view) {
        super.update(sprites, view);

        position = new Vector(view.screenSize.x / 2, view.screenSize.y / 2);

        if (!view.handledTouch && charges > 0) {
            Vector diff = view.lastTouch.clone();
            diff.subtract(position);
            if (diff.magnitude() < radius) {
                view.spritesToAdd.add(new ExpandSprite(position.clone()));
                charges--;
                view.handledTouch = true;
            }
        }

        if (health <= 0) view.endGame(false);
    }

    @Override
    public void draw(Canvas canvas, GameView view) {
        super.draw(canvas, view);

        canvas.drawCircle(position.x, position.y, radius, paint);

        Paint textPaint = new Paint();
        textPaint.setTextSize(40);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("" + charges, position.x, position.y + 15, textPaint);
    }
}
