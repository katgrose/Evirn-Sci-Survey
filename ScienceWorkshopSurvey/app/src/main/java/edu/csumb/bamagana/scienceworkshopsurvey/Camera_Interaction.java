package edu.csumb.bamagana.scienceworkshopsurvey;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



public class Camera_Interaction extends AppCompatActivity {

    private ImageView imgView;
    private Button take_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera__interaction);

        imgView = (ImageView) findViewById(R.id.image_view);
        take_pic = (Button) findViewById(R.id.cam_finish_b);

        //Request for camera permission
        if(ContextCompat.checkSelfPermission(Camera_Interaction.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Camera_Interaction.this,
                    new String[]{
                      Manifest.permission.CAMERA
                    },

                    100);
        }

        take_pic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i_cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i_cam, 100);
            }
        });

    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100){
            Bitmap camImg = (Bitmap) data.getExtras().get("data");
            imgView.setImageBitmap(camImg);
        }
    }


}