package io.github.automaticspork.aegis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaren on 5/12/17.
 */

public class GameView extends View {
    private Point screenSize;
    private List<Sprite> sprites;

    private Sprite s;
    private Point sVel;

    public GameView(Context context) {
        super(context);

        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        screenSize = new Point();
        wm.getDefaultDisplay().getSize(screenSize);

        sprites = new ArrayList<Sprite>();

        s = new Sprite(new Point(screenSize.x / 2, screenSize.y / 2));
        sVel = new Point(15, 20);
        sprites.add(s);
    }

    protected void onDraw(Canvas canvas) {
        if (s.position.x > screenSize.x || s.position.x < 0) {
            sVel.x *= -1;
            s.position.x = Math.max(0, Math.min(s.position.x, screenSize.x));
        }
        if (s.position.y > screenSize.y || s.position.y < 0) {
            sVel.y *= -1;
            s.position.y = Math.max(0, Math.min(s.position.y, screenSize.y));
        }
        s.position.x += sVel.x;
        s.position.y += sVel.y;
        for (Sprite sprite : sprites) {
            sprite.update();
        }
        canvas.drawColor(Color.WHITE);
        for (Sprite sprite : sprites) {
            sprite.draw(canvas);
        }
        invalidate();
    }
}
