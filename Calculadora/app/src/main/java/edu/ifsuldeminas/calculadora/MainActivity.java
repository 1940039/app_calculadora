package edu.ifsuldeminas.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ifsuldeminas.mch.calc";
    private Button nZero, nOne, nTwo, nThree, nFour, nFive, nSix, nSeven, nEight, nNine, point, plus, less, mult, div, percent, equal, clean, delete;
    private TextView textViewExpression, textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        IniciateID();

        nZero.setOnClickListener(this);
        nOne.setOnClickListener(this);
        nTwo.setOnClickListener(this);
        nThree.setOnClickListener(this);
        nFour.setOnClickListener(this);
        nFive.setOnClickListener(this);
        nSix.setOnClickListener(this);
        nSeven.setOnClickListener(this);
        nEight.setOnClickListener(this);
        nNine.setOnClickListener(this);
        point.setOnClickListener(this);
        plus.setOnClickListener(this);
        less.setOnClickListener(this);
        mult.setOnClickListener(this);
        div.setOnClickListener(this);
        percent.setOnClickListener(this);
        delete.setOnClickListener(this);

        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewExpression.setText("");
                textViewResult.setText("0");
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView expression = findViewById(R.id.textViewExpression);
                String s = expression.getText().toString();

                if(!TextUtils.isEmpty(s)) {
                    int length = s.length();
                    StringBuilder sb = new StringBuilder(s.substring(0, length - 1));
                    expression.setText(sb.toString());
                }
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String s = textViewExpression.getText().toString();
                    Expression e = new ExpressionBuilder(s).build();

                    double result = e.evaluate();
                    long longResult = (long) result;

                    String resultString = (result == (double) longResult) ? String.valueOf(longResult) : String.valueOf(result);

                    if (result == (double) longResult) {
                        textViewResult.setText((CharSequence) String.valueOf(longResult));

                    } else {
                        textViewResult.setText((CharSequence) String.valueOf(result));
                    }
                } catch (ArithmeticException e) {
                    Log.e(TAG, "Arithmetic exception: " + e.getMessage());
                    textViewResult.setText("Error");
                } catch (Exception e) {
                    Log.e(TAG, "Exception: " + e.getMessage());
                    textViewResult.setText("Error");
                }
            }
        });
    }

    private void IniciateID() {
        nZero = findViewById(R.id.nbr_zero);
        nOne = findViewById(R.id.nbr_one);
        nTwo = findViewById(R.id.nbr_two);
        nThree = findViewById(R.id.nbr_three);
        nFour = findViewById(R.id.nbr_four);
        nFive = findViewById(R.id.nbr_five);
        nSix = findViewById(R.id.nbr_six);
        nSeven = findViewById(R.id.nbr_seven);
        nEight = findViewById(R.id.nbr_eight);
        nNine = findViewById(R.id.nbr_nine);
        point = findViewById(R.id.point);
        plus = findViewById(R.id.plus);
        less = findViewById(R.id.less);
        mult = findViewById(R.id.mult);
        div = findViewById(R.id.div);
        percent = findViewById(R.id.percent);
        equal = findViewById(R.id.equal);
        clean = findViewById(R.id.clean);
        textViewExpression = findViewById(R.id.textViewExpression);
        textViewResult = findViewById(R.id.textViewResult);
        delete = findViewById(R.id.delete);
    }

    public void ExpressionLine(String s) {
        String result = textViewExpression.getText().toString();

        if (TextUtils.isEmpty(result)) {
            textViewExpression.setText(" ");
        }

        String lastChar = "";
        int length = textViewExpression.length();
        if (length > 0) {
            lastChar = textViewExpression.getText().toString().substring(length - 1);
        }
        if (lastChar.matches("[+\\-*/%]") && s.matches("[+\\-*/%]")) {
            textViewExpression.setText(textViewExpression.getText().toString().substring(0, length - 1));
        }

        textViewExpression.setText(result + s);
        textViewResult.setText(" ");
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.nbr_zero:
                ExpressionLine("0");
                break;

            case R.id.nbr_one:
                ExpressionLine("1");
                break;

            case R.id.nbr_two:
                ExpressionLine("2");
                break;

            case R.id.nbr_three:
                ExpressionLine("3");
                break;

            case R.id.nbr_four:
                ExpressionLine("4");
                break;

            case R.id.nbr_five:
                ExpressionLine("5");
                break;

            case R.id.nbr_six:
                ExpressionLine("6");
                break;

            case R.id.nbr_seven:
                ExpressionLine("7");
                break;

            case R.id.nbr_eight:
                ExpressionLine("8");
                break;

            case R.id.nbr_nine:
                ExpressionLine("9");
                break;

            case R.id.point:
                ExpressionLine(".");
                break;

            case R.id.plus:
                ExpressionLine("+");
                break;

            case R.id.less:
                ExpressionLine("-");
                break;

            case R.id.mult:
                ExpressionLine("*");
                break;

            case R.id.div:
                ExpressionLine("/");
                break;

            case R.id.percent:
                ExpressionLine("%");
                break;
        }
    }
}