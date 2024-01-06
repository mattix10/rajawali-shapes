package com.example.rajawaliproject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import org.rajawali3d.Object3D;
import org.rajawali3d.cameras.ArcballCamera;
import org.rajawali3d.cameras.Camera;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.loader.LoaderOBJ;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.methods.DiffuseMethod;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.primitives.Sphere;
import org.rajawali3d.renderer.Renderer;

public class BasicRenderer extends Renderer {
    private ArcballCamera mArcballCamera;
    private final Context context;
    private Sphere mEarthSphere;
    private Camera mCamera;
    private DirectionalLight mDirectionalLight;
    public BasicRenderer(Context context) {
        super(context);
        this.context = context;
        setFrameRate(60);
    }

    @Override
    public void initScene() {
        mDirectionalLight = new DirectionalLight(1f, .2f, -1.0f);
//        mDirectionalLight.setColor(221.0f, 221.0f, 1.0f);
//        mDirectionalLight.setPower(2);
//        getCurrentScene().addLight(mDirectionalLight);
        Material material = new Material();
        material.enableLighting(true);
        material.setDiffuseMethod(new DiffuseMethod.Lambert());
        material.setColorInfluence(0);
        Texture earthTexture = new Texture("Earth", R.drawable.earthtruecolor_nasa_big);
    // Displaying Earth
        //        try{
//            material.addTexture(earthTexture);
//
//        } catch (ATexture.TextureException error){
//            Log.d(".initScene", error.toString());
//        }
//        mEarthSphere = new Sphere(1, 24, 24);
//        mEarthSphere.setMaterial(material);
//        getCurrentScene().addChild(mEarthSphere);
//        getCurrentCamera().setZ(4.2f);
        // Loading objects
        try {
//            getCurrentScene().setBackgroundColor(Color.CYAN & Color.DKGRAY);
            DirectionalLight key = new DirectionalLight(-4,-4,-4);
            key.setPower(3);
            getCurrentScene().addLight(key);

            LoaderOBJ loader = new LoaderOBJ(getContext().getResources(), mTextureManager, R.raw.cos2_obj);
            loader.parse();
            Object3D obj = loader.getParsedObject();
            getCurrentScene().addChild(obj);

            // Dobre kamery:
//            getCurrentCamera().setPosition(6,8,6);
//            getCurrentCamera().setLookAt(obj.getPosition());
            ArcballCamera arcball = new ArcballCamera(mContext, ((Activity) mContext).findViewById(R.id.myView));
            arcball.setPosition(4, 4, 4);
            getCurrentScene().replaceAndSwitchCamera(getCurrentCamera(), arcball);
        } catch (Exception e) {
            Log.d("exception:", e.getMessage());
        }
    }

    @Override
    public void onOffsetsChanged(float x, float y, float z, float w, int i, int j){
    }
    float xpos = 0;


    @Override
    public void onTouchEvent(MotionEvent event) {
        Log.d("event", "event: ");
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d("move", "move down");
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            Log.d("move", "move");
//            float xd = xpos - event.getX();
//            swipeCamera(xd);// while the finger is on the screen move the camera according to X touch position
//            xpos = event.getX();
        }
    }
    @Override
    public void onRender(final long elapsedTime, final double deltaTime) {
        // to jest wazne dla wysiwetlania kostki i mapy
        super.onRender(elapsedTime, deltaTime);
//        mEarthSphere.rotate(Vector3.Axis.Y, 1.0);
    }

    private void swipeCamera(float touchOffset) {
        float zOffset = touchOffset/(10);// 20 = 3-homescreens - 50 = 5-homescreens - 100 = 7-homescreens
        int curZPos = (int) mCamera.getPosition().z;
        if(curZPos < -25 || curZPos > 25){// camera position limits
            if(-25>curZPos){ curZPos = -25;}
            else {curZPos = 25; }
        }
        mCamera.setX(25-(-((curZPos + zOffset)/(10))));//some fuzzy math to soften the motion
        mCamera.setZ((curZPos + (zOffset*.66f)));//More fuzzy math for the Z axis
    }

    public void loadModel(int modelName) {
        // Logika do ładowania modelu
        // Na przykład, usunięcie bieżącego modelu i załadowanie nowego
        getCurrentScene().clearChildren();
        try {
            LoaderOBJ loader = new LoaderOBJ(getContext().getResources(), mTextureManager, modelName);
            loader.parse();
            Object3D obj = loader.getParsedObject();
            getCurrentScene().addChild(obj);
        }  catch (Exception e) {
            Log.d("exception:", e.getMessage());
        }


    }
}