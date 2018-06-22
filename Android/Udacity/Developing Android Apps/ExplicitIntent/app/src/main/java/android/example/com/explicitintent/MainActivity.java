package android.example.com.explicitintent;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    /* Fields that will store our EditText and Button */
    private EditText mNameEntry;
    private Button mDoSomethingCollButton;

    private Button mOpenWebPageButton;
    private Button mOpenMapButton;
    private Button mShareTextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Using findViewById, we get a reference to our Button from xml. This allows us to
         * do things like set the onClickListener which determines what happens when the button
         * is clicked.
         */
        mNameEntry = (EditText) findViewById(R.id.et_text_entry);
        mDoSomethingCollButton = (Button) findViewById(R.id.b_do_something);
        mOpenWebPageButton = (Button) findViewById(R.id.b_open_web_page);
        mOpenMapButton = (Button) findViewById(R.id.b_open_map);
        mShareTextButton = (Button) findViewById(R.id.b_share_text);

        /* Setting an OnClickListener allows us to do something when this button is clicked. */
        mDoSomethingCollButton.setOnClickListener(new View.OnClickListener() {

            /**
             * The onClick method is triggered when this button (mDoSomethingCoolButton) is clicked.
             *
             * @param view The view that is clicked. In this case, it's mDoSomethingCoolButton.
             */
            @Override
            public void onClick(View view) {
                /* We'll first get the text entered by the user in the EditText */
                String textEntered = mNameEntry.getText().toString();

                /*
                 * Storing the Context in a variable in this case is redundant since we could have
                 * just used "this" or "MainActivity.this" in the method call below. However, we
                 * wanted to demonstrate what parameter we were using "MainActivity.this" for as
                 * clear as possible.
                 */
                Context context = MainActivity.this;
                /* This is the class that we want to start (and open) when the button is clicked. */
                Class destinationClass = ChildActivity.class;

                /*
                * Here, we create the Intent that will start the Activity we specified above in
                * the destinationActivity variable. The constructor for an Intent also requires a
                * context, which we stored in the variable named "context".
                */
                Intent startChildActivityIntent = new Intent(context, destinationClass);
                /*
                * We use the putExtra method of the Intent class to pass some extra stuff to the
                * Activity that we are starting. Generally, this data is quite simple, such as
                * a String or a number. However, there are ways to pass more complex objects.
                */
                startChildActivityIntent.putExtra(Intent.EXTRA_TEXT, textEntered);
                /**
                 * Once the Intent has been created, we can use Activity's method, "startActivity"
                 * to start the ChildActivity.
                 */
                startActivity(startChildActivityIntent);
            }
        });

        /**
         * ****************** IMPLICIT ACTIVITY ****************
         * */
        mOpenWebPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickWebpageButton(view);
            }
        });

        mOpenMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickOpenMapButton(view);
            }
        });

        mShareTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 /* Create the String that you want to share */
                String textThatYouWantToShare =
                    "Sharing the coolest thing I've learned so far. You should " +
                    "check out Udacity and Google's Android Nanodegree!";

                /* Send that text to our method that will share it. */
                shareText(textThatYouWantToShare);
            }
        });
    }

    /**
     * This method shares text and allows the user to select which app they would like to use to
     * share the text. Using ShareCompat's IntentBuilder, we get some really cool functionality for
     * free. The chooser that is started using the {@link IntentBuilder#startChooser()} method will
     * create a chooser when more than one app on the device can handle the Intent. This happens
     * when the user has, for example, both a texting app and an email app. If only one Activity
     * on the phone can handle the Intent, it will automatically be launched.
     *
     * @param textToShare Text that will be shared
     */
    public void shareText(String textToShare) {
        /*
        * You can think of MIME types similarly to file extensions. They aren't the exact same,
        * but MIME types help a computer determine which applications can open which content. For
        * example, if you double click on a .pdf file, you will be presented with a list of
        * programs that can open PDFs. Specifying the MIME type as text/plain has a similar affect
        * on our implicit Intent. With text/plain specified, all apps that can handle text content
        * in some way will be offered when we call startActivity on this particular Intent.
        */
        String mimeType = "text/plain";
        /* This is just the title of the window that will pop up when we call startActivity */
        String title= "Learning How To Share";
        /* ShareCompat.IntentBuilder provides a fluent API for creating Intents */
        ShareCompat.IntentBuilder.from(this)
                .setChooserTitle(title)
                .setType(mimeType)
                .setText(textToShare)
                .startChooser();
    }

    public void onClickOpenMapButton(View v) {
        String addressString = "1600 Amphitheatre Parkway, CA";
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("geo")
                .path("0,0")
                .query(addressString);
        showMap(builder.build());
        //Toast.makeText(this, "TODO: open a web page when this button is clicked", Toast.LENGTH_LONG).show();
    }

    public void onClickWebpageButton(View v) {
        String urlAsString = "http://www.udacity.com";
        openWebPage(urlAsString);
        //Toast.makeText(this, "TODO: open a web page when this button is clicked", Toast.LENGTH_LONG).show();
    }

    /**
     * This method will fire off an implicit Intent to view a location on a map.
     *
     * When constructing implicit Intents, you can use either the setData method or specify the
     * URI as the second parameter of the Intent's constructor,
     * as I do in {@link #openWebPage(String)}
     *
     * @param geoLocation The Uri representing the location that will be opened in the map
     */
    public void showMap(Uri geoLocation) {
        /*
        * Again, we create an Intent with the action, ACTION_VIEW because we want to VIEW the
        * contents of this Uri.
        */
        Intent intent = new Intent(Intent.ACTION_VIEW);
        /*
        * Using setData to set the Uri of this Intent has the exact same affect as passing it in
        * the Intent's constructor. This is simply an alternate way of doing this.
        */
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method fires off an implicit Intent to open a webpage.
     *
     * @param url Url of webpage to open. Should start with http:// or https:// as that is the
     *            scheme of the URI expected with this Intent according to the Common Intents page
     */
    private void openWebPage(String url) {
        /*
        * We wanted to demonstrate the Uri.parse method because its usage occurs frequently. You
        * could have just as easily passed in a Uri as the parameter of this method.
        */
        Uri webpage = Uri.parse(url);
        /*
        * Here, we create the Intent with the action of ACTION_VIEW. This action allows the user
        * to view particular content. In this case, our webpage URL.
        */
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        /*
        * This is a check we perform with every implicit Intent that we launch. In some cases,
        * the device where this code is running might not have an Activity to perform the action
        * with the data we've specified. Without this check, in those cases your app would crash.
        */
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
