package com.example.rajawaliproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.view.ISurface;
import org.rajawali3d.view.SurfaceView;
public class MainActivity extends AppCompatActivity {
    BasicRenderer renderer;
    private Button buttonOut;
    private Button buttonIn;
    private Button buttonChangeDimension;

//    private Boolean is2D = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonOut = findViewById(R.id.button_out);
        buttonIn = findViewById(R.id.button_in);
//        buttonChangeDimension = findViewById(R.id.button_change_dimension);
        // Spinner
        Spinner modelSpinner = findViewById(R.id.model_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.model_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelSpinner.setAdapter(adapter);

        final SurfaceView surface = findViewById(R.id.rajawali_surface);
        surface.setFrameRate(60.0);
        surface.setRenderMode(ISurface.RENDERMODE_WHEN_DIRTY);

        // Add mSurface to your root view
//        addContentView(surface, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT));

        renderer = new BasicRenderer(this);
        surface.setSurfaceRenderer(renderer);

        buttonOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kod, który zostanie wykonany po kliknięciu przycisku
                Vector3 vector =  renderer.getCurrentCamera().getPosition();
                renderer.getCurrentCamera().setPosition(vector.x,vector.y, vector.z + 5);
            }
        });
        buttonIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kod, który zostanie wykonany po kliknięciu przycisku
                Vector3 vector =  renderer.getCurrentCamera().getPosition();
                renderer.getCurrentCamera().setPosition(vector.x,vector.y, vector.z - 5);
            }
        });
//        buttonChangeDimension.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                if(is2D) {
////                    surface.setSurfaceRenderer(renderer);
////                    is2D = false;
////                } else {
////                    surface2D.setSurfaceRenderer(renderer);
////                    is2D = true;
////                }
//                // Kod, który zostanie wykonany po kliknięciu przycisku
//
//            }
//        });

        modelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadModel(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void loadModel(int position) {
         if (position == 0) { renderer.loadModel(R.raw.cos2_obj );}
         if (position == 1) { renderer.loadModel(R.raw.kostka_obj); }
    }


}