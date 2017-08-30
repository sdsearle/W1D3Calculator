package com.example.spenc.w1d3calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvAnswer;
    String input = "";
    float answer;
    Button op;
    boolean recentslv, decimalpressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAnswer = (TextView) findViewById(R.id.tvAnswer);
        Log.d("tag","tag");

    }


    public void updateTextAnswer(View view) {
        switch (view.getId()){

            case R.id.bin0:
            case R.id.bin1:
            case R.id.bin2:
            case R.id.bin3:
            case R.id.bin4:
            case R.id.bin5:
            case R.id.bin6:
            case R.id.bin7:
            case R.id.bin8:
            case R.id.bin9:
                if(recentslv){
                    input = "";
                    recentslv = false;
                }
                //Log.d("test",""+((Button)view).getText());
                updateInput(((Button)view).getText().toString());
                updateTextView(input);
                break;

            case R.id.binDecimal:
                if(!decimalpressed) {
                    if(recentslv){
                        input = "";
                        recentslv = false;
                    }
                    updateInput(((Button) view).getText().toString());
                    updateTextView(input);
                    decimalpressed = true;
                }
                break;

            case R.id.binMultiply:
            case R.id.binMinus:
            case R.id.binPlus:
            case R.id.binDivide:
                operate((Button)view);
                decimalpressed = false;
                break;

            case R.id.binClear:
                clear();
                decimalpressed = false;
                break;

            case R.id.binEnter:
                solve();
                decimalpressed = false;
                break;

            case R.id.binSign:
                signChange();
                updateTextView(input);
                break;

        }
    }

    private void signChange() {
        float temp = Float.parseFloat(input);
        temp = -temp;
        input = temp+"";
    }

    private void solve() {
        operate(null);
        input = ""+answer;
        recentslv = true;
    }

    private void clear() {
        input = "";
        answer = 0;
        op = null;
        updateTextView("0");
    }

    private void operate(Button b) {
        //Log.d("operate","op");
        if(op != null){
            if(input.compareTo("") != 0){
                switch (op.getId()){
                    case R.id.binPlus:
                        answer = answer + Float.parseFloat(input);
                        break;
                    case R.id.binMinus:
                        answer = answer - Float.parseFloat(input);
                        break;
                    case R.id.binDivide:
                        answer = answer / Float.parseFloat(input);
                        break;
                    case R.id.binMultiply:
                        answer = answer * Float.parseFloat(input);
                        break;
                }

                //op = null;

            }
            else{
                input = ""+answer;

            }

        }
        else{
            //Log.d("Tag", "operate: " + input);
            answer = Float.parseFloat(input);
        }
        op = b;
        input = "";
        updateTextView(""+answer);
    }

    private void updateTextView(String input) {
        tvAnswer.setText(input);
    }

    private void updateInput(String s) {
        input += s;
    }

    private void updateAnswer(CharSequence text) {
        Float.parseFloat(text.toString());
    }
}
