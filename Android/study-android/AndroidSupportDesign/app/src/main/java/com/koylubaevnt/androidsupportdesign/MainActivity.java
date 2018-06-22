package com.koylubaevnt.androidsupportdesign;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Snackbar mSnackbar;

    View.OnClickListener snackbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(), "Молодец!", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSnackbar = Snackbar.make(view, "Покормил кота?", Snackbar.LENGTH_INDEFINITE)
                        .addCallback(new Snackbar.Callback() {
                            @Override
                            public void onDismissed(Snackbar transientBottomBar, int event) {
                                /*
                                    Параметр event у метода onDismissed() позволяет узнать конкретное событие, повлекшее исчезновение, при помощи констант:
                                    DISMISS_EVENT_SWIPE, DISMISS_EVENT_ACTION, DISMISS_EVENT_TIMEOUT, DISMISS_EVENT_MANUAL, DISMISS_EVENT_CONSECUTIVE.
                                 */
                                Toast.makeText(getApplicationContext(), "Dissmiss", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onShown(Snackbar sb) {
                                Toast.makeText(getApplicationContext(), "Snackbar is showed", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setAction("Да", snackbarOnClickListener)
                        .setActionTextColor(Color.GREEN)
                        ;
                View snackbarView = mSnackbar.getView();
                snackbarView.setBackgroundColor(Color.BLUE);
                //TextView snackTextView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                //snackTextView.setTextColor(Color.RED);
                //mSnackbar.setDuration(8000);
                mSnackbar.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        mSnackbar.dismiss();
    }
}
