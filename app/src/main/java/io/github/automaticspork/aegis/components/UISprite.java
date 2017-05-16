package io.github.automaticspork.aegis.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import io.github.automaticspork.aegis.Sprite;
import io.github.automaticspork.aegis.Vector;

/**
 * Created by jaren on 5/16/17.
 */

public class UISprite extends Sprite {
    public float ratio;

    public UISprite() {
        super(new Vector(), new Paint(Color.BLACK));
        z = 100;
    }

    @Override
    public void update(List<Sprite> sprites) {
        for (Sprite s : sprites) {
            if (s instanceof CoreSprite) {
                ratio = ((CoreSprite)s).health / ((CoreSprite)s).maxHealth;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        Paint p1 = new Paint();
        p1.setColor(Color.BLACK);
        canvas.drawRect(0, 0, canvas.getWidth(), 100, p1);

        Paint p2 = new Paint();
        p2.setColor(Color.rgb((int)(255 * (1 - ratio)), (int)(255 * ratio), 0));
        canvas.drawRect(10, 10, canvas.getWidth() * ratio - 10, 90, p2);
    }
}
