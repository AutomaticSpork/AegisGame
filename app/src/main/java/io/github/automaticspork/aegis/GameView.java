package io.github.automaticspork.aegis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.github.automaticspork.aegis.components.CoreSprite;
import io.github.automaticspork.aegis.components.Enemy;

/**
 * Created by jaren on 5/12/17.
 */

public class GameView extends View {
    private Point screenSize;
    private List<Sprite> sprites;
    private Random random;

    public GameView(Context context) {
        super(context);

        random = new Random();

        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        screenSize = new Point();
        wm.getDefaultDisplay().getSize(screenSize);

        sprites = new ArrayList<Sprite>();

        sprites.add(new CoreSprite(100));
        sprites.add(createEnemy());
        sprites.add(createEnemy());
        sprites.add(createEnemy());
        sprites.add(createEnemy());
    }

    private Enemy createEnemy() {
        Vector pos;
        if (random.nextBoolean()) {
            pos = new Vector(random.nextInt(screenSize.x), random.nextBoolean() ? 0 : screenSize.y);
        } else {
            pos = new Vector(random.nextBoolean() ? 0 : screenSize.x, random.nextInt(screenSize.y));
        }
        return new Enemy(pos, random.nextInt(5) + 5, random.nextInt(2) + 1, 10);
    }

    protected void onDraw(Canvas canvas) {
        for (Sprite sprite : sprites) {
            sprite.update(sprites);
        }
        for (int i = 0; i < sprites.size(); i++) {
            if (sprites.get(i).toDelete) {
                sprites.remove(i);
                i--;
            }
        }
        canvas.drawColor(Color.WHITE);
        for (Sprite sprite : sprites) {
            sprite.draw(canvas);
        }
        invalidate();
    }
}
