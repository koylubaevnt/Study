package com.koylubaevnt.camera;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String JPEG_FILE_PREFIX = "JPEG";
    private static final String JPEG_FILE_SUFFIX = "jpg";

    private final int CAMERA_RESULT = 0;
    private final int TAKE_PICTURE = 1;
    private final int CAMERA_CAPTURE = 2;
    private final int PIC_CROP = 3;

    private Uri picUri;

    private ImageView mImageView;
    private Uri mOutputFileUri;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Съемка и кадрирование");

        mImageView = (ImageView) findViewById(R.id.imageView);
    }


    public void onClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAMERA_RESULT) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            mImageView.setImageBitmap(bitmap);
        } else if (requestCode == TAKE_PICTURE) {
            if(data != null) {
                if(data.hasExtra("data")) {
                    Bitmap bitmap = data.getParcelableExtra("data");
                    mImageView.setImageBitmap(bitmap);
                }
            } else {
                mImageView.setImageURI(mOutputFileUri);
            }
        } else  {
            if (resultCode == RESULT_OK) {
                if(requestCode == CAMERA_CAPTURE) {
                    picUri = data.getData();
                    performCrop();
                } else if (requestCode == PIC_CROP) {
                    Bundle extras = data.getExtras();

                    Bitmap bitmap = extras.getParcelable("data");
                    ImageView picView = (ImageView) findViewById(R.id.imageView);
                    picView.setImageBitmap(bitmap);
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    private void performCrop() {
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(picUri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 256);
            intent.putExtra("outputY", 256);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, PIC_CROP);
        } catch (ActivityNotFoundException e) {
            String errorMessage = "Извините, но ваше устройство не поддерживает кадрирование";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onClickSaveFoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(), "test.jpg");
        mOutputFileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutputFileUri);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    private void dispatchTakePictureIntent(int actionCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, actionCode);
    }

    public static boolean isIntentAvailable(Context context, String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    private void handleSmallCameraPhoto(Intent intent) {
        Bundle extras = intent.getExtras();
        mImageBitmap = (Bitmap) extras.get("data");
        mImageView.setImageBitmap(mImageBitmap);
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = JPEG_FILE_PREFIX + timeStamp + "_";
        File image = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX, getAlbumDir());
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private File getAlbumDir() {
        return null;
    }

    //Visible for Galery
    private void galleryAddPic() {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri uri = Uri.fromFile(f);
        intent.setData(uri);
        this.sendBroadcast(intent);
    }

    private void setPic() {
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        mImageView.setImageBitmap(bitmap);
    }

    public void onClickCadrirovanie(View view) {
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_CAPTURE);
        } catch (ActivityNotFoundException e) {
            String errorMessage = "Ваше устройство не поддерживает съемку";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
