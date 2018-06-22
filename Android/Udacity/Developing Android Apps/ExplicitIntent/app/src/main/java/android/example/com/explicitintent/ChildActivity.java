package android.example.com.explicitintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ChildActivity extends AppCompatActivity {

    private TextView mDisplayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        mDisplayText = (TextView) findViewById(R.id.tv_display);

        /*
        * Here is where all the magic happens. The getIntent method will give us the Intent that
        * started this particular Activity.
        */
        Intent intentThatStartedThisActivity = getIntent();

        /*
        * Although there is always an Intent that starts any particular Activity, we can't
        * guarantee that the extra we are looking for was passed as well. Because of that, we need
        * to check to see if the Intent has the extra that we specified when we created the
        * Intent that we use to start this Activity. Note that this extra may not be present in
        * the Intent if this Activity was started by any other method.
        */
        if(intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
            /*
            * Now that we've checked to make sure the extra we are looking for is contained within
            * the Intent, we can extract the extra. To do that, we simply call the getStringExtra
            * method on the Intent. There are various other get*Extra methods you can call for
            * different types of data. Please feel free to explore those yourself.
            */
            String textEntered = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            /*
            * Finally, we can set the text of our TextView (using setText) to the text that was
            * passed to this Activity.
            */
            mDisplayText.setText(textEntered);
        }
    }
}
