package io.github.automaticspork.aegis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import io.github.automaticspork.aegis.components.CenterMovingSprite;
import io.github.automaticspork.aegis.components.CoreSprite;
import io.github.automaticspork.aegis.components.EliteEnemy;
import io.github.automaticspork.aegis.components.ElitePowerup;
import io.github.automaticspork.aegis.components.EliteSprite;
import io.github.automaticspork.aegis.components.Enemy;
import io.github.automaticspork.aegis.components.Powerup;
import io.github.automaticspork.aegis.components.PowerupType;
import io.github.automaticspork.aegis.components.ShieldSprite;
import io.github.automaticspork.aegis.components.TextSprite;
import io.github.automaticspork.aegis.components.UISprite;

/**
 * Created by jaren on 5/12/17.
 */

public class GameView extends View {
    public Point screenSize;
    private List<Sprite> sprites;
    private Random random;
    public List<Sprite> spritesToAdd;
    public boolean clearSpritesNextTick;
    public boolean isRunning;
    public Vector lastTouch;
    public int score;
    public int winScore;
    public int startHealth;
    public boolean handledTouch;

    public CoreSprite core;
    public ShieldSprite shield;

    public GameView(Context context) {
        super(context);

        winScore = 300;
        startHealth = 300;
        score = 0;

        handledTouch = true;

        final GameView self = this;

        lastTouch = new Vector();
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                lastTouch = new Vector(event.getX(), event.getY());
                handledTouch = false;
                for (Sprite sprite : sprites) {
                    if (sprite instanceof EliteSprite) {
                        Vector diff = sprite.position.clone();
                        diff.subtract(lastTouch);
                        if (diff.magnitude() < ((EliteSprite)sprite).radius) {
                            ((EliteSprite)sprite).onTap(self);
                        }
                    }
                }
                return false;
            }
        });
        setOnDragListener(new OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                lastTouch = new Vector(event.getX(), event.getY());
                return false;
            }
        });

        random = new Random();

        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        screenSize = new Point();
        wm.getDefaultDisplay().getSize(screenSize);

        sprites = new ArrayList<Sprite>();
        spritesToAdd = new ArrayList<Sprite>();
        clearSpritesNextTick = false;
        isRunning = true;

        core = new CoreSprite(startHealth);
        shield = new ShieldSprite();
        sprites.add(core);
        sprites.add(shield);
        sprites.add(new UISprite());
    }

    public void endGame(boolean win) {
        Paint p = new Paint();
        p.setTextSize(100);
        p.setColor(win ? Color.GREEN : Color.RED);
        p.setTextAlign(Paint.Align.CENTER);
        spritesToAdd.add(new TextSprite(new Vector(screenSize.x / 2, screenSize.y / 2), p, win ? "YOU WIN!" : "YOU LOSE :("));
        clearSpritesNextTick = true;
        isRunning = false;
    }

    private CenterMovingSprite createSprite() {
        Vector pos;
        if (random.nextBoolean()) {
            pos = new Vector(random.nextInt(screenSize.x), random.nextBoolean() ? 0 : screenSize.y);
        } else {
            pos = new Vector(random.nextBoolean() ? 0 : screenSize.x, random.nextInt(screenSize.y));
        }
        int result = random.nextInt(10);
        int result2 = random.nextInt(10);
        if (result < 2) {
            if (result2 < 2)
                return new ElitePowerup(pos, random.nextInt(4) + 60, random.nextInt(2) + 4, PowerupType.values()[random.nextInt(PowerupType.values().length)]);
            else
                return new EliteEnemy(pos, random.nextInt(4) + 60, random.nextInt(2) + 4);
        } else {
            if (result2 < 2)
                return new Powerup(pos, random.nextInt(4) + 20, random.nextInt(2) + 3, PowerupType.values()[random.nextInt(PowerupType.values().length)]);
            else
                return new Enemy(pos, random.nextInt(4) + 20, random.nextInt(8) + 4);
        }
    }

    protected void onDraw(Canvas canvas) {
        if (isRunning) {
            int val = (int)(100*Math.exp(-score/100.0));
            if (random.nextInt(val) == 0) sprites.add(createSprite());
        }

        if (clearSpritesNextTick) {
            sprites.clear();
            clearSpritesNextTick = false;
        }
        sprites.addAll(spritesToAdd);
        spritesToAdd.clear();

        for (Sprite sprite : sprites) {
            sprite.update(sprites, this);
        }
        for (int i = 0; i < sprites.size(); i++) {
            if (sprites.get(i).toDelete) {
                sprites.remove(i);
                i--;
            }
        }

        if (score > winScore) endGame(true);

        canvas.drawColor(Color.parseColor("#263238"));
        Collections.sort(sprites);
        for (Sprite sprite : sprites) {
            sprite.draw(canvas, this);
        }
        invalidate();
    }
}
