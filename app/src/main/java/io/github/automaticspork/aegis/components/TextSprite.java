package io.github.automaticspork.aegis.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import io.github.automaticspork.aegis.GameView;
import io.github.automaticspork.aegis.Sprite;
import io.github.automaticspork.aegis.Vector;

/**
 * Created by jaren on 5/16/17.
 */

public class TextSprite extends Sprite {
    public String text;

    public TextSprite(Vector pos, Paint p, String t) {
        super(pos, p);
        text = t;
    }

    @Override
    public void draw(Canvas canvas, GameView view) {
        super.draw(canvas, view);
        canvas.drawText(text, position.x, position.y, paint);
    }
}
