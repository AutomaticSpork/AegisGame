package io.github.automaticspork.aegis.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import io.github.automaticspork.aegis.GameView;
import io.github.automaticspork.aegis.Vector;

/**
 * Created by jaren on 5/25/17.
 */

public class EliteSprite extends CenterMovingSprite {
    public float shieldDamage;

    public EliteSprite(Vector pos, float radius, float sMult) {
        super(pos, radius, sMult);
        shieldDamage = 20;
    }

    @Override
    public void onCollide(GameView view) {
        super.onCollide(view);

        view.shield.sweep -= 20;
        view.shield.radius -= 20;
    }

    @Override
    public void onShieldCollide(GameView view) {
        super.onShieldCollide(view);

        onCollide(view);
    }

    public void onTap(GameView view) {
        toDelete = true;
    }

    @Override
    public void draw(Canvas canvas, GameView view) {
        super.draw(canvas, view);

        Paint paint2 = new Paint();
        paint2.setColor(Color.GRAY);
        paint2.setStrokeWidth(20);
        paint2.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(position.x, position.y, radius, paint2);
    }
}
