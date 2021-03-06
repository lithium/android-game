package com.hlidskialf.android.game.scene;

import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;
import com.hlidskialf.android.game.scene.Sprite;
import com.hlidskialf.android.game.scene.TileGroup;

public class SpriteGroup extends ArrayList<Sprite> implements Sprite
{
    public SpriteGroup()
    {
        super();
    }

    public void tick(long elapsed)
    {
        for (Sprite s : this) {
            s.tick(elapsed);
        }
    }
    public void setup(GL10 gl)
    {
        for (Tile s : this) {
            s.setup(gl);
        }
    }
    public void draw(GL10 gl)
    {
        for (Tile s : this) {
            s.draw(gl);
        }
    }
}
