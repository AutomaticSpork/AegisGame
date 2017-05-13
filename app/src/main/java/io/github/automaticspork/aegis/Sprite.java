package io.github.automaticspork.aegis;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

/**
 * Created by jaren on 5/12/17.
 */

public class Sprite {
    public Point position;

    public Sprite(Point pos) {
        position = pos;
    }

    public void update() {
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(position.x, position.y, 50, new Paint(Color.BLACK));
    }
}
