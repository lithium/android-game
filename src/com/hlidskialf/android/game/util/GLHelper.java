package com.hlidskialf.android.game.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class GLHelper
{

    public static FloatBuffer nativeFloatBuffer(int num_floats)
    {
        ByteBuffer byte_buf = ByteBuffer.allocateDirect(num_floats * 4);
        byte_buf.order(ByteOrder.nativeOrder());
        return byte_buf.asFloatBuffer();
    }
}
