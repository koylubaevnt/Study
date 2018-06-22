package cat.koylubaevnt.com.transferdataactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    public final static String USER = "com.koylubaevnt.cat.USER";
    public final static String GIFT = "com.koylubaevnt.cat.GIFT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String user = getIntent().getExtras().getString(USER);
        String gift = getIntent().getStringExtra(GIFT);
        TextView infoTextView = (TextView) findViewById(R.id.textViewInfo);
        if(user.length() > 0 && gift.length() > 0) {
            infoTextView.setText(user + ", вам передали " + gift);
        } else {
            infoTextView.setText("анные не были переданы");
        }
    }
}
