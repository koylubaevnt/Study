package cat.koylubaevnt.com.ravencounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mInfoTextView;
    private Button mRavenCounterButton;
    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInfoTextView = (TextView) findViewById(R.id.textView);
        mRavenCounterButton = (Button) findViewById(R.id.buttonCrowsCounter);

        mRavenCounterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInfoTextView.setText("Я насчитал " + ++mCount + " ворон");
            }
        });
    }

    public void onClick(View view) {
        mInfoTextView.setText("Hello Kitty!");
    }
}
