package io.github.automaticspork.aegis.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.List;

import io.github.automaticspork.aegis.CollidableSprite;
import io.github.automaticspork.aegis.GameView;
import io.github.automaticspork.aegis.Sprite;
import io.github.automaticspork.aegis.Vector;

/**
 * Created by jaren on 5/16/17.
 */

public class ShieldSprite extends CollidableSprite {
    public float minAngle = 270;
    public float sweep = 120;
    public float diffRadius = 10;

    private CollidableSprite innerCollision;

    public ShieldSprite() {
        super(new Vector(), new Paint(), 150);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#00acc1"));

        innerCollision = new CollidableSprite(position, paint, radius - diffRadius);
    }

    @Override
    public void update(List<Sprite> sprites, GameView view) {
        super.update(sprites, view);

        paint.setStrokeWidth(diffRadius);
        position = new Vector(view.screenSize.x / 2, view.screenSize.y / 2);
        innerCollision.radius = radius - diffRadius;
        innerCollision.position = position;

        Vector diff = view.lastTouch.clone();
        diff.subtract(position);
        float angle = (float)Math.toDegrees(Math.atan2(diff.y, diff.x)) - sweep / 2;
        minAngle = (angle < 0 ? 360 + angle : angle);

        for (Sprite s : sprites) {
            if (s instanceof CenterMovingSprite && collides((CollidableSprite)s)) {
                if (s instanceof Enemy) {
                    view.score += ((Enemy)s).score;
                    if (s instanceof EliteEnemy) view.core.charges++;
                }
                s.toDelete = true;
            }
        }
    }

    @Override
    public void draw(Canvas canvas, GameView view) {
        super.draw(canvas, view);

        canvas.drawArc(position.x - radius, position.y - radius, position.x + radius, position.y + radius, minAngle, sweep, false, paint);
    }

    @Override
    public boolean collides(CollidableSprite other) {
        Vector diff = other.position.clone();
        diff.subtract(position);
        double angle = Math.toDegrees(Math.atan2(diff.y, diff.x));
        if (angle < 0) angle = 360 + angle;
        if (!super.collides(other) || (innerCollision.collides(other) && innerCollision.collides(new CollidableSprite(((CenterMovingSprite)other).previousPos, new Paint(), other.radius)))) return false;
        if (minAngle + sweep > 360) return angle > minAngle || angle < (minAngle + sweep) % 360;
        return angle > minAngle && angle < minAngle + sweep;
    }
}
