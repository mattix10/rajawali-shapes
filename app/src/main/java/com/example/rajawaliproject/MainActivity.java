package com.example.rajawaliproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.view.ISurface;
import org.rajawali3d.view.SurfaceView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    BasicRenderer renderer;
    private Button buttonOut;
    private Button buttonIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonOut = findViewById(R.id.button_out);
        buttonIn = findViewById(R.id.button_in);

        // Spinner
        Spinner modelSpinner = findViewById(R.id.model_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.model_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelSpinner.setAdapter(adapter);

        final SurfaceView surface = findViewById(R.id.rajawali_surface);
        surface.setFrameRate(60.0);
        surface.setRenderMode(ISurface.RENDERMODE_WHEN_DIRTY);

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
                if(vector.z - 5>=0)
                    renderer.getCurrentCamera().setPosition(vector.x,vector.y, vector.z - 5);
            }
        });


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
        TextView fileInfoText = (TextView) findViewById(R.id.FileInfoText);
         if (position == 0) {
             renderer.loadModel(R.raw.cos2_obj);
             String fileSize = getFileSize(R.raw.cos2_obj);
             String extension = getExtension(R.raw.cos2_obj);
             String fileName = getFileName(R.raw.cos2_obj);
             String text = fileSize +"\n"+extension +"\n"+ fileName;
             fileInfoText.setText(text);
         }
         if (position == 1) {
             renderer.loadModel(R.raw.kostka_obj);
             String fileSize = getFileSize(R.raw.kostka_obj);
             String extension = getExtension(R.raw.kostka_obj);
             String fileName = getFileName(R.raw.kostka_obj);
             String text = fileSize +"\n"+extension +"\n"+ fileName;
             fileInfoText.setText(text);
         }
        if (position == 2) {
            renderer.loadModel(R.raw.gwiazdka_obj);
            String fileSize = getFileSize(R.raw.gwiazdka_obj);
            String extension = getExtension(R.raw.gwiazdka_obj);
            String fileName = getFileName(R.raw.gwiazdka_obj);
            String text = fileSize +"\n"+extension +"\n"+ fileName;
            fileInfoText.setText(text);
        }
        if (position == 3) {
            renderer.loadModel(R.raw.zegarek_obj);
            String fileSize = getFileSize(R.raw.zegarek_obj);
            String extension = getExtension(R.raw.zegarek_obj);
            String fileName = getFileName(R.raw.zegarek_obj);
            String text = fileSize +"\n"+extension +"\n"+ fileName;
            fileInfoText.setText(text);
        }
        if (position == 4) {
            renderer.loadModel(R.raw.toilet_obj);
            String fileSize = getFileSize(R.raw.toilet_obj);
            String extension = getExtension(R.raw.toilet_obj);
            String fileName = getFileName(R.raw.toilet_obj);
            String text = fileSize +"\n"+extension +"\n"+ fileName;
            fileInfoText.setText(text);
        }
        if (position == 5) {
            renderer.loadModel(R.raw.test_obj);
            String fileSize = getFileSize(R.raw.test_obj);
            String extension = getExtension(R.raw.test_obj);
            String fileName = getFileName(R.raw.test_obj);
            String text = fileSize +"\n"+extension +"\n"+ fileName;
            fileInfoText.setText(text);
        }

    }

    private String getFileSize(int rawResourceId) {
        InputStream inputStream = null;
        try {
            inputStream = getResources().openRawResource(rawResourceId);
            int size = inputStream.available();
            return "Size: " + size + " bytes";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error";
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getExtension(int rawResourceId){
        String textExtension = "Extension: ";
        /*if(rawResourceId == R.raw.cos2_obj) return textExtension + "mtl";
        if(rawResourceId == R.raw.kostka_obj) return textExtension + "mtl";
        if(rawResourceId == R.raw.gwiazdka2) return textExtension + "dxf";*/
        return textExtension+"obj";
    }

    private String getFileName(int rawResourceId){
        Resources resources = this.getResources();
        String fileName = resources.getResourceEntryName(rawResourceId);
        return "FileName: "+ fileName;
    }


}