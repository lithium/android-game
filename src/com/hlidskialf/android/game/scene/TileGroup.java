package com.hlidskialf.android.game.scene;

import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;
import com.hlidskialf.android.game.scene.Tile;

public class TileGroup extends ArrayList<Tile> implements Tile
{
    public TileGroup()
    {
        super();
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
