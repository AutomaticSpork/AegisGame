package io.github.automaticspork.aegis.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import io.github.automaticspork.aegis.GameView;
import io.github.automaticspork.aegis.MovingSprite;
import io.github.automaticspork.aegis.Sprite;
import io.github.automaticspork.aegis.Vector;

/**
 * Created by jaren on 5/19/17.
 */

public class CenterMovingSprite extends MovingSprite {
    public int score;
    public float speedMultipler;
    public Vector previousPos;
    private Vector initialPos;

    public CenterMovingSprite(Vector pos, float radius, float sMult) {
        super(pos, new Paint(), radius, 0);
        score = 0;
        speedMultipler = sMult;
        initialPos = pos.clone();
    }

    @Override
    public void update(List<Sprite> sprites, GameView view) {
        super.update(sprites, view);

        previousPos = position.clone();
        Vector diff = view.core.position.clone();
        diff.subtract(position);
        float radius = diff.magnitude();
        Vector initialDiff = view.core.position.clone();
        initialDiff.subtract(initialPos);
        float initialRadius = initialDiff.magnitude() + 10;
        speed = speedMultipler * (view.screenSize.x / 30) * (float)Math.sqrt(1/radius - 1/initialRadius);
        moveTo(view.core.position);

        if (view.core.collides(this)) {
            onCollide(view);
            toDelete = true;
        }
    }

    public void onCollide(GameView view) {}

    @Override
    public void draw(Canvas canvas, GameView view) {
        super.draw(canvas, view);
        canvas.drawCircle(position.x, position.y, radius, paint);
    }
}
