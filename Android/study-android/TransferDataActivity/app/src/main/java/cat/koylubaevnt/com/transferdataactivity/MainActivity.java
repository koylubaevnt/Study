package cat.koylubaevnt.com.transferdataactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        EditText userEditText = (EditText) findViewById(R.id.editTextWhome);
        EditText giftEditText = (EditText) findViewById(R.id.editTextDescription);

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);

        intent.putExtra(SecondActivity.USER, userEditText.getText().toString());
        intent.putExtra(SecondActivity.GIFT, giftEditText.getText().toString());

        startActivity(intent);
    }
}
