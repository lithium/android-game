package com.hlidskialf.android.game.scene;

import javax.microedition.khronos.opengles.GL10;

public interface Sprite
{
    public void setup(GL10 gl);
    public void tick(long elapsed);
    public void draw(GL10 gl);
}
