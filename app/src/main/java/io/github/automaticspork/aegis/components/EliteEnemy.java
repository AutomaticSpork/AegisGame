package io.github.automaticspork.aegis.components;

import android.graphics.Color;

import java.util.List;
import java.util.Random;

import io.github.automaticspork.aegis.GameView;
import io.github.automaticspork.aegis.Sprite;
import io.github.automaticspork.aegis.Vector;

/**
 * Created by jaren on 5/25/17.
 */

public class EliteEnemy extends EliteSprite {
    public EliteEnemy(Vector pos, float radius, float sMult) {
        super(pos, radius, sMult);
        score = 5;
        paint.setColor(Color.parseColor("#212121"));
    }

    @Override
    public void update(List<Sprite> sprites, GameView view) {
        super.update(sprites, view);
    }

    @Override
    public void onTap(GameView view) {
        super.onTap(view);

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Enemy enemy = new Enemy(new Vector(position.x - 80 + random.nextInt(160), position.y - 80 + random.nextInt(160)), radius / 3, speedMultipler * 2);
            view.spritesToAdd.add(enemy);
        }
    }

    @Override
    public void onCollide(GameView view) {
        super.onCollide(view);

        view.core.health -= 50;
    }
}
