package com.hlidskialf.android.game.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.hlidskialf.android.game.input.GamepadView;

public class DemoActivity extends Activity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_scene);


        mSceneView = (DemoScene)findViewById(R.id.renderer);
        mSceneView.start();
        GamepadView mGamepad = (GamepadView)findViewById(R.id.gamepad);
        //mGamepad.setOnJoystickListener(mSceneView);
        //mGamepad.setOnButtonsListener(mSceneView);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        mSceneView.onPause();
    }
    @Override
    public void onResume()
    {
        super.onResume();
        mSceneView.onResume();
    }



    /* private */
    DemoScene mSceneView;
}
