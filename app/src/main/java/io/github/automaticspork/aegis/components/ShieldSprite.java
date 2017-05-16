package io.github.automaticspork.aegis.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import io.github.automaticspork.aegis.CollidableSprite;
import io.github.automaticspork.aegis.Sprite;
import io.github.automaticspork.aegis.Vector;

/**
 * Created by jaren on 5/16/17.
 */

public class ShieldSprite extends CollidableSprite {
    public ShieldSprite() {
        super(new Vector(), new Paint(), 100);
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(10);
        p.setColor(Color.BLUE);
        paint = p;
    }

    @Override
    public void update(List<Sprite> sprites) {
        super.update(sprites);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        position = new Vector(canvas.getWidth() / 2, canvas.getHeight() / 2);

        canvas.drawArc(position.x - radius, position.y - radius, position.x + radius, position.y + radius, 90, 180, false, paint);
    }

    @Override
    public boolean collides(CollidableSprite other) {
        // TODO: Check angle
        return super.collides(other);
    }
}
