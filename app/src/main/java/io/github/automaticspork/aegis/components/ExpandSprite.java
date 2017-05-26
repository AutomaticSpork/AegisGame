package io.github.automaticspork.aegis.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import io.github.automaticspork.aegis.GameView;
import io.github.automaticspork.aegis.Sprite;
import io.github.automaticspork.aegis.Vector;

/**
 * Created by jaren on 5/25/17.
 */

public class ExpandSprite extends Sprite {
    float radius;

    public ExpandSprite(Vector pos) {
        super(pos, new Paint());
        radius = 0;
        paint.setColor(Color.parseColor("#9c27b0"));
        paint.setStrokeWidth(30);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void update(List<Sprite> sprites, GameView view) {
        super.update(sprites, view);

        radius += 20;

        for (Sprite sprite : sprites) {
            if (sprite instanceof Enemy) {
                Vector diff = sprite.position.clone();
                diff.subtract(position);
                if (diff.magnitude() < radius) sprite.toDelete = true;
            }
        }

        if (radius > Math.min(view.screenSize.x, view.screenSize.y)) toDelete = true;
    }

    @Override
    public void draw(Canvas canvas, GameView view) {
        super.draw(canvas, view);

        //canvas.drawArc(position.x - radius, position.y - radius, position.x - radius, position.y - radius, 0, 360, false, paint);
        canvas.drawCircle(position.x, position.y, radius, paint);
    }
}
