package com.hlidskialf.android.game.scene;

import android.util.Log;

import java.nio.FloatBuffer;
import java.util.Random;
import com.hlidskialf.android.game.util.Point3;
import com.hlidskialf.android.game.scene.Sprite;
import com.hlidskialf.android.game.util.GLHelper;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

public class Emitter implements Sprite
{
    Point3 origin;
    Particle[] particles;
    FloatBuffer v_buf;
    FloatBuffer size_buf;
    Random rand;

    public static final float ENERGY=0.3f;
    public static final float RATE=0.5f;
    public static final float MIN_SIZE=1f;
    public static final float MAX_SIZE=4f;

    public Emitter(int num_particles)
    {
        rand = new Random();
        origin = new Point3();
        v_buf = GLHelper.nativeFloatBuffer(num_particles * 3);
        size_buf = GLHelper.nativeFloatBuffer(num_particles);

        particles = new Particle[num_particles];
        init_particles();
    }

    private void init_particles()
    {
        int l=particles.length;
        for (int i=0; i < l; i++)
        {
            particles[i] = new Particle();
            particles[i].pos_x = origin.x;
            particles[i].pos_y = origin.y;
            particles[i].pos_z = origin.z;
            particles[i].life = 1.0f;
            particles[i].randomize(ENERGY, RATE, MIN_SIZE, MAX_SIZE);
        }
    }


    public void setup(GL10 gl)
    {
        gl.glEnable(GL11.GL_POINT_SPRITE_OES);
        gl.glEnableClientState(GL11.GL_POINT_SIZE_ARRAY_BUFFER_BINDING_OES);
        gl.glEnableClientState(GL11.GL_POINT_SIZE_ARRAY_OES);
        gl.glEnableClientState(GL11.GL_POINT_SPRITE_OES);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    }

    public void draw(GL10 gl)
    {

        ((GL11)gl).glPointSizePointerOES(GL10.GL_FLOAT, 0, size_buf);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, v_buf);
        gl.glDrawArrays(GL10.GL_POINTS, 0, particles.length);

    }

    public void tick(long elapsed)
    {
        v_buf.clear();
        size_buf.clear();
        for (Particle p : particles) {
            if (p.life > 0) {
                p.pos_x += p.vel_x;
                p.pos_y += p.vel_y;
                p.pos_z += p.vel_z;
            } else {
                p.pos_x = origin.x;
                p.pos_y = origin.y;
                p.pos_z = origin.z;
                p.randomize(ENERGY, RATE, MIN_SIZE, MAX_SIZE);
            }

            v_buf.put(p.pos_x);
            v_buf.put(p.pos_y);
            v_buf.put(p.pos_z);
            size_buf.put(p.size);

            p.life -= p.decay;
        }
        v_buf.rewind();
        size_buf.rewind();
    }


    private class Particle
    {
        float pos_x;
        float pos_y;
        float pos_z;

        float vel_x;
        float vel_y;
        float vel_z;

        float life;
        float decay;
        float size;

        void randomize(float energy, float rate, float min_size, float max_size)
        {
            vel_x = rand.nextFloat() * energy*2 - energy;
            vel_y = rand.nextFloat() * energy*2 - energy;
            vel_z = rand.nextFloat() * energy*2 - energy;

            life = 1.0f;
            decay = rand.nextFloat() * rate;
            size = rand.nextFloat() * (max_size - min_size + 1) + min_size;

            //Log.v("Emitter", vel_x+","+vel_y+","+vel_z+" x"+size+" /"+decay);
        }
    }
}
