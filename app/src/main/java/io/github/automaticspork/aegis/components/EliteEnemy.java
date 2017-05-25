package io.github.automaticspork.aegis.components;

import android.graphics.Color;

import java.util.List;

import io.github.automaticspork.aegis.GameView;
import io.github.automaticspork.aegis.Sprite;
import io.github.automaticspork.aegis.Vector;

/**
 * Created by jaren on 5/25/17.
 */

public class EliteEnemy extends Enemy {
    public EliteEnemy(Vector pos, float radius, float sMult) {
        super(pos, radius, sMult);
        damage = 100;
        score = 5;
        paint.setColor(Color.parseColor("#212121"));
    }

    @Override
    public void update(List<Sprite> sprites, GameView view) {
        super.update(sprites, view);
    }
}
