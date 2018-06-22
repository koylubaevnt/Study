package com.koylubaevnt.maps;

import android.content.Intent;
import android.graphics.YuvImage;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickMapsIntent(View view) {
        //geo:latitude,longitude?z=zoom
        //geo:0,0?q=my_street_address&z=zoom

        //String geoUri = String.format("geo:%s,%s?z=15", Double.toString(lat), Double.toString(lng));
        //String geoUriString = "geo:0,0?q=Belgium";
        String geoUriString = "geo:0,10?z=2";
        Uri geoUri = Uri.parse(geoUriString);
        Intent intent = new Intent(Intent.ACTION_VIEW, geoUri);
        startActivity(intent);

    }

    public void onClickStreetView(View view) {
        //google.streetview:cbll=lat,lng&cbp=1,yaw,,pitch,zoom&mz=mapZoom
        //lat - широта
        //lng - долгота
        //yaw - центр панорамы в градусах по часовой стрелке с севера. Обязательно используйте две запятые.
        //pitch - центр обзора панорамы в градусах от -90 (взор вверх) до 90 (взгляд вниз)
        //zoom - масштаб панорамы. 1.0 = нормальный, 2.0 = приближение в 2 раза, 3.0 = в 4 раза и так далее
        //mapZoom - масштабирование места карты, связанное с панорамой. Это значение используется при переходе на Карты.

                String geoUriString = "google.streetview:cbll=59.939448,30.328264&cbp=1,99.56,,1,2.0&mz=19";
        Uri geoUri = Uri.parse(geoUriString);
        Intent intent = new Intent(Intent.ACTION_VIEW, geoUri);
        startActivity(intent);
    }
}
