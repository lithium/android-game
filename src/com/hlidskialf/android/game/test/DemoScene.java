package com.hlidskialf.android.game.test;

import android.util.AttributeSet;
import android.opengl.GLU;
import android.content.Context;
import javax.microedition.khronos.opengles.GL10;
import com.hlidskialf.android.game.scene.SceneView;
import com.hlidskialf.android.game.scene.Sprite;
import com.hlidskialf.android.game.util.GLHelper;
import com.hlidskialf.android.game.util.Point3;
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

        gl.glClearColor(0f,0f,0f, 0.5f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
        gl.glShadeModel(GL10.GL_FLAT);
    }

    public void reshape(GL10 gl, int w, int h)
    {
        viewWidth=w;
        viewHeight=h;
        gl.glViewport(0,0,w,h);

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 60.0f, viewWidth/viewHeight, 0.1f, 100f);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        GLU.gluLookAt(gl, 
            0f,0f,13f, 
            0f,0f,0f, 
            0f,1f,0f);

        mSprite.origin.set(0f, 0f, 0f);


    }


    DemoSprite mSprite;
    public class DemoSprite implements Sprite
    {
        Point3 origin;
        FloatBuffer v;
        float vertices[] = {
            -3f, -3f, 0f,
            -3f, 3f, 0f,
            3f, -3f, 0f,
            3f, 3f, 0f,
        };
        DemoSprite()
        {
            v = GLHelper.nativeFloatBuffer(vertices.length);
            v.put(vertices);
            v.rewind();

            origin = new Point3();
        }

        public void setup(GL10 gl)
        {
        }
        public void draw(GL10 gl)
        {
            gl.glPushMatrix();
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);


            gl.glTranslatef(origin.x, origin.y, origin.z);


            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, v);
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, v.capacity()/3);


            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glPopMatrix();
        }
        public void tick(long elapsed)
        {
        }
    }
}
