package io.github.automaticspork.aegis.components;

import io.github.automaticspork.aegis.GameView;
import io.github.automaticspork.aegis.Vector;

/**
 * Created by jaren on 5/25/17.
 */

public class EliteSprite extends CenterMovingSprite {
    public float shieldDamage;

    public EliteSprite(Vector pos, float radius, float sMult) {
        super(pos, radius, sMult);
        shieldDamage = 20;
    }

    @Override
    public void onCollide(GameView view) {
        super.onCollide(view);

        view.shield.sweep -= 20;
        view.shield.radius -= 20;
    }

    @Override
    public void onShieldCollide(GameView view) {
        super.onShieldCollide(view);

        onCollide(view);
    }

    public void onTap(GameView view) {
        toDelete = true;
    }
}
