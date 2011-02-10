package com.hlidskialf.android.game.test;

import android.util.AttributeSet;
import android.opengl.GLU;
import android.content.Context;
import javax.microedition.khronos.opengles.GL10;
import com.hlidskialf.android.game.scene.SceneView;
import com.hlidskialf.android.game.scene.Sprite;
import com.hlidskialf.android.game.util.GLHelper;
import java.nio.FloatBuffer;

import android.util.Log;

public class DemoScene extends SceneView
{

    public DemoScene(Context context, AttributeSet attrs)
    {
        super(context,attrs);

        mSprite = new DemoSprite();
        addSprite(mSprite);
    }

    public void setup(GL10 gl)
    {
        super.setup(gl);
    }

    public void reshape(GL10 gl, int w, int h)
    {
        super.reshape(gl,w,h);

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 60.0f, viewWidth/viewHeight, 0.1f, 100f);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        GLU.gluLookAt(gl, 0f,0f,6f, 0f,0f,0f, 0f,1f,0f);

    }


    Sprite mSprite;
    public class DemoSprite implements Sprite
    {
        FloatBuffer v;
        float vertices[] = {
            .4f, .5f, 0f,
            0f, -.5f, 0f,
            -.4f, .5f, 0f,
            -.15f, .25f, 0f,
            .15f, .25f, 0f,
        };
        DemoSprite()
        {
            v = GLHelper.nativeFloatBuffer(vertices.length);
            v.put(vertices);
            v.rewind();
        }

        public void setup(GL10 gl)
        {
        }
        public void draw(GL10 gl)
        {
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, v);
            gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, v.capacity()/3);

            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        }
        public void tick(long elapsed)
        {
        }
    }
}
