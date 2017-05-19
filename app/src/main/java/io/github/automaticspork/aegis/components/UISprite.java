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
    public UISprite() {
        super(new Vector(), new Paint(Color.BLACK));
        z = 100;
    }

    @Override
    public void draw(Canvas canvas, GameView view) {
        super.draw(canvas, view);

        float rSize = 100;
        float bSize = 10;
        float ratio = view.core.health / view.core.maxHealth;

        Paint p2 = new Paint();
        if (ratio < 0.33) p2.setColor(Color.parseColor("#e53935"));
        else if (ratio < 0.66) p2.setColor(Color.parseColor("#ffb300"));
        else p2.setColor(Color.parseColor("#2e7d32"));
        canvas.drawRect(bSize, bSize, canvas.getWidth() * ratio - bSize, rSize - bSize, p2);

        Paint p3 = new Paint();
        p3.setColor(Color.WHITE);
        p3.setTextAlign(Paint.Align.CENTER);
        p3.setTextSize(rSize / 2);
        canvas.drawText((int)view.core.health + "/" + (int)view.core.maxHealth, canvas.getWidth() / 2, rSize / 2 + 2 * bSize, p3);

        Paint p4 = new Paint();
        p4.setColor(Color.BLACK);
        p4.setTextAlign(Paint.Align.CENTER);
        p4.setTextSize(rSize / 2);
        canvas.drawText("" + view.score, canvas.getWidth() / 2, canvas.getHeight() - rSize, p4);
    }
}
