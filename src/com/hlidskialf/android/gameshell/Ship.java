package com.hlidskialf.android.gameshell;

import java.nio.ByteBuffer;
import android.content.Context;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import android.graphics.PointF;
import android.util.Log;

import com.hlidskialf.android.game.models.ObjModel;
import com.hlidskialf.android.game.util.Point3;

import javax.microedition.khronos.opengles.GL10;

public class Ship extends ObjModel
{
    ObjModel model;
    Point3 origin;
    Point3 accel;
    Point3 rotate;
    boolean is_thrust;


    public Ship(Context context, float scale)
    {
        try {
            model = ObjModel.loadFromAssets(context, "models/hero.obj", null);
        } catch (java.io.IOException e) {
            Log.v("ObjModel", "err: "+e.toString());
        }

        origin = new Point3(0f,0f,0f);
        accel = new Point3(0f,0f,0f);
        rotate = new Point3(0f,0f,0f);
/*
        v_buffer = floatBuffer(vertices, width);
        origin = new PointF(0f,0f);
        accel = new PointF(0f,0f);
        */
    }

    public void tick()
    {
        origin.x += accel.x;
        origin.y += accel.y;
        origin.y += accel.z;
    }

    public void draw(GL10 gl)
    {
        model.draw(gl);
    }

/*
        gl.glPushMatrix();
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glRotatef(angle, 0f, 0f, 1f);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, v_buffer);
        gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, vertices.length / 3);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glPopMatrix();
    }




    public static FloatBuffer floatBuffer(float[] array, float width)
    {
        ByteBuffer byte_buf = ByteBuffer.allocateDirect(array.length * 4);
        byte_buf.order(ByteOrder.nativeOrder());
        FloatBuffer ret = byte_buf.asFloatBuffer();
        int i;
        for (i=0; i < array.length; i++) {
            ret.put(array[i]*width);
        }
        ret.position(0);
        return ret;
    }

    */
}
