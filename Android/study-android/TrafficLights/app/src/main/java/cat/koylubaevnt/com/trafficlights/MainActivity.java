package cat.koylubaevnt.com.trafficlights;

import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout mConstraintLayout;
    private TextView mInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mConstraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);
        mInfoTextView = (TextView) findViewById(R.id.textViewInfo);

//        Button yellowButton = (Button) findViewById(R.id.buttonYellow);
//        yellowButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setInfoTextViewText(R.string.yellow);
//                setConstraintLayoutBacgroundColor(R.color.yellowColor);
//            }
//        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonRed:
                setInfoTextViewText(R.string.red);
                setConstraintLayoutBacgroundColor(R.color.redColor);
                break;
            case R.id.buttonYellow:
                setInfoTextViewText(R.string.yellow);
                setConstraintLayoutBacgroundColor(R.color.yellowColor);
                break;
            default:
                setInfoTextViewText(R.string.green);
                setConstraintLayoutBacgroundColor(R.color.greenColor);
        }

    }

    private void setConstraintLayoutBacgroundColor(int color) {
        mConstraintLayout.setBackgroundColor(ContextCompat.getColor(this, color));
    }

    private void setInfoTextViewText(int text) {
        mInfoTextView.setText(text);
    }
}
