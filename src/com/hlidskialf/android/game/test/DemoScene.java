package com.hlidskialf.android.game.test;

import android.util.AttributeSet;
import android.opengl.GLU;
import android.content.Context;
import javax.microedition.khronos.opengles.GL10;
import com.hlidskialf.android.game.scene.SceneView;
import com.hlidskialf.android.game.scene.Sprite;

public class DemoScene extends SceneView
{
    public class DemoSprite implements Sprite
    {
        public void setup(GL10 gl)
        {
        }
        public void draw(GL10 gl)
        {
        }
        public void tick(long elapsed)
        {
        }
    }

    public DemoScene(Context context, AttributeSet attrs)
    {
        super(context,attrs);

        mSprite = new DemoSprite();
        addSprite(mSprite);
    }


    public void reshape(GL10 gl, int w, int h)
    {
        super.reshape(gl,w,h);

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 60.0f, viewWidth/viewHeight, 0.1f, 100f);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        GLU.gluLookAt(gl, 0f,3f,13f, 0f,3f,0f, 0f,1f,0f);

    }


    public void tick(int elapsed)
    {
    }

    Sprite mSprite;
}
