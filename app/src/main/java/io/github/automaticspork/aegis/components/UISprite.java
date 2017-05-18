package io.github.automaticspork.aegis.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import io.github.automaticspork.aegis.GameView;
import io.github.automaticspork.aegis.Sprite;
import io.github.automaticspork.aegis.Vector;

/**
 * Created by jaren on 5/16/17.
 */

public class UISprite extends Sprite {
    private float lastHealth;
    private float lastMaxHealth;
    private int lastScore;

    public UISprite() {
        super(new Vector(), new Paint(Color.BLACK));
        z = 100;
    }

    @Override
    public void update(List<Sprite> sprites, GameView view) {
        lastScore = view.score;

        for (Sprite s : sprites) {
            if (s instanceof CoreSprite) {
                lastHealth = ((CoreSprite)s).health;
                lastMaxHealth = ((CoreSprite)s).maxHealth;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        float rSize = 100;
        float bSize = 10;
        float ratio = lastHealth / lastMaxHealth;

        Paint p2 = new Paint();
        p2.setColor(Color.rgb((int)(255 * (1 - ratio)), (int)(255 * ratio), 0));
        canvas.drawRect(bSize, bSize, canvas.getWidth() * ratio - bSize, rSize - bSize, p2);

        Paint p3 = new Paint();
        p3.setColor(Color.WHITE);
        p3.setTextAlign(Paint.Align.CENTER);
        p3.setTextSize(rSize / 2);
        canvas.drawText((int)lastHealth + "/" + (int)lastMaxHealth, canvas.getWidth() / 2, rSize / 2 + 2 * bSize, p3);

        Paint p4 = new Paint();
        p4.setColor(Color.BLACK);
        p4.setTextAlign(Paint.Align.CENTER);
        p4.setTextSize(rSize / 2);
        canvas.drawText("" + lastScore, canvas.getWidth() / 2, canvas.getHeight() - rSize, p4);
    }
}
