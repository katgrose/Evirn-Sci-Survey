package com.example.evirn_sci_survey;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.Matrix;

import com.example.evirn_sci_survey.MainActivity;


public class Camera_Interaction extends AppCompatActivity {

    private ImageView imgView;
    private Button take_pic;
    private Button restart_B;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera__interaction);

        imgView = (ImageView) findViewById(R.id.image_view);
        take_pic = (Button) findViewById(R.id.cam_finish_b);
        restart_B = (Button) findViewById(R.id.button_restart);

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

        System.out.println("out of image capure and displaying");

        // on waiting for the picture to show up then we cam
        // once the image is take and displaied for 10 seconds then the survey reverts back to the main page
        // restartApp();

        restart_B.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i_restart = new Intent(Camera_Interaction.this, MainActivity.class);
                startActivity(i_restart);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100){
            Bitmap camImg = (Bitmap) data.getExtras().get("data");
            Bitmap flippedImg = flipImg(camImg);
            imgView.setImageBitmap(flippedImg);
        }
    }

    protected void restartApp(){

       /* try {
            this.wait(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        navigateUpTo(new Intent(this, MainActivity.class));
        startActivity(getIntent());

    }

    protected Bitmap flipImg(Bitmap img) {
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        return Bitmap.createBitmap(
                img, 0, 0, img.getWidth(), img.getHeight(), matrix, true
        );
    }

}