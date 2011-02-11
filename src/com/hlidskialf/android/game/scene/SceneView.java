package com.hlidskialf.android.game.scene;


import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.hlidskialf.android.game.scene.SpriteGroup;
import com.hlidskialf.android.game.scene.Sprite;


public class SceneView extends GLSurfaceView
{
    public SceneView(Context context) { this(context,null); }
    public SceneView(Context context, AttributeSet attrs)
    {
        super(context,attrs);

        mSprites = new SpriteGroup();
    }

    public void start()
    {
        mHandler = new Handler();
        mRenderer = new SceneRenderer();
        setRenderer(mRenderer);
    }

    public int addSprite(Sprite s)
    {
        mSprites.add(s);
        return mSprites.size()-1;
    }
    public void removeSprite(int sprite)
    {
        mSprites.remove(sprite);
    }
    public Sprite getSprite(int sprite)
    {
        return mSprites.get(sprite);
    }





    public void setup(GL10 gl)
    {
        mSprites.setup(gl);
    }
    public void reshape(GL10 gl, int w, int h)
    {
        viewWidth=w;
        viewHeight=h;
        gl.glViewport(0,0,w,h);
    }
    public void draw(GL10 gl)
    {
        long now = System.currentTimeMillis();
        long elapsed = mLastTick == 0 ? 0 : now - mLastTick;
        tick(elapsed);
        mLastTick = now;


        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glPushMatrix();
        mSprites.draw(gl);
        gl.glPopMatrix();
    }

    public void tick(long elapsed)
    {
        mSprites.tick(elapsed);
    }


    /* private */
    Handler mHandler;
    SceneRenderer mRenderer;
    SpriteGroup mSprites;
    long mLastTick=0;
    public float viewWidth,viewHeight;


    public class SceneRenderer implements GLSurfaceView.Renderer
    {
        public void onSurfaceCreated(GL10 gl, EGLConfig config)
        {
            setup(gl);
        }

        public void onSurfaceChanged(GL10 gl, int w, int h)
        {
            reshape(gl,w,h);
        }

        public void onDrawFrame(GL10 gl)
        {
            draw(gl);
        }

    }

}
