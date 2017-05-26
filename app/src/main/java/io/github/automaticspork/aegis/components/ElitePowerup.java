package io.github.automaticspork.aegis.components;

import android.graphics.Color;

import java.util.Random;

import io.github.automaticspork.aegis.GameView;
import io.github.automaticspork.aegis.Vector;

/**
 * Created by jaren on 5/25/17.
 */

public class ElitePowerup extends EliteSprite {
    public PowerupType type;

    public ElitePowerup(Vector pos, float radius, float sMult, PowerupType t) {
        super(pos, radius, sMult);
        String colorString = "#000000";
        type = t;
        switch (type) {
            case Health:
                colorString = "#4caf50";
                break;
            case Sweep:
                colorString = "#9c27b0";
                break;
            case Radius:
                colorString = "#ff9800";
                break;
        }
        paint.setColor(Color.parseColor(colorString));
    }

    @Override
    public void onTap(GameView view) {
        super.onTap(view);

        switch (type) {
            case Health:
                view.core.health = Math.max(view.core.health, view.core.maxHealth);
                break;
            case Sweep:
                view.core.charges++;
                break;
            case Radius:
                view.score += 20;
                break;
        }
    }
}
