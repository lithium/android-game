package com.hlidskialf.android.game.scene;

import javax.microedition.khronos.opengles.GL10;

public interface CollisionSprite extends Sprite
{
    public float boundingRadius();
    public boolean collides(CollisionSprite other);
    public void collided(CollisionSprite other);
}
