package cat.koylubaevnt.com.switchactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.buttonAbout:
                intent = new Intent(MainActivity.this, AboutActivity.class);
                break;
            default:
                intent = new Intent(MainActivity.this, BirthdayActivity.class);
        }
        startActivity(intent);
    }
}
