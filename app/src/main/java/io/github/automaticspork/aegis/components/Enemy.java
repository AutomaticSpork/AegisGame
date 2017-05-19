package io.github.automaticspork.aegis.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import io.github.automaticspork.aegis.CollidableSprite;
import io.github.automaticspork.aegis.GameView;
import io.github.automaticspork.aegis.MovingSprite;
import io.github.automaticspork.aegis.Sprite;
import io.github.automaticspork.aegis.Vector;

/**
 * Created by jaren on 5/15/17.
 */

public class Enemy extends CenterMovingSprite {
    public float damage;

    public Enemy(Vector pos, float radius, float sMult, float d) {
        super(pos, radius, sMult);
        damage = d;
        score = 1;
        paint.setColor(Color.parseColor("#f44336"));
    }

    @Override
    public void onCollide(GameView view) {
        super.onCollide(view);

        view.core.health -= damage;
    }
}
