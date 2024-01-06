package com.example.rajawaliproject;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import org.rajawali3d.Object3D;
import org.rajawali3d.cameras.ArcballCamera;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.loader.LoaderOBJ;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.methods.DiffuseMethod;
import org.rajawali3d.renderer.Renderer;

public class BasicRenderer extends Renderer {
    private ArcballCamera mArcballCamera;
    private final Context context;
    private DirectionalLight mDirectionalLight;
    public BasicRenderer(Context context) {
        super(context);
        this.context = context;
        setFrameRate(60);
    }

    @Override
    public void initScene() {
        mDirectionalLight = new DirectionalLight(1f, .2f, -1.0f);
        Material material = new Material();
        material.enableLighting(true);
        material.setDiffuseMethod(new DiffuseMethod.Lambert());
        material.setColorInfluence(0);

        // Loading objects
        try {
            DirectionalLight key = new DirectionalLight(-4,-4,-4);
            key.setPower(3);
            getCurrentScene().addLight(key);

            LoaderOBJ loader = new LoaderOBJ(getContext().getResources(), mTextureManager, R.raw.cos2_obj);
            loader.parse();
            Object3D obj = loader.getParsedObject();
            getCurrentScene().addChild(obj);

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

    @Override
    public void onTouchEvent(MotionEvent event) {
    }
    @Override
    public void onRender(final long elapsedTime, final double deltaTime) {
        super.onRender(elapsedTime, deltaTime);
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