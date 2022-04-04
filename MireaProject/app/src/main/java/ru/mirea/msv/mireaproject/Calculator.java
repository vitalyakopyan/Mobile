package ru.mirea.msv.mireaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Calculator extends Fragment {

    private TextView outText;
    private TextView inputText;
    private StringBuilder sb;

    public Calculator() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sb = new StringBuilder();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calculator, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        outText = (TextView)view.findViewById(R.id.calculatorOutText);
        inputText = (TextView)view.findViewById(R.id.calculatorInputText);

        view.findViewById(R.id.buttonClear).setOnClickListener((view2)->{
            inputText.setText("");
        });

        view.findViewById(R.id.buttonDel).setOnClickListener((view2)->{
            sb.setLength(0);
            sb.append(inputText.getText());
            if (sb.length() > 0)
                sb.setLength(sb.length() - 1);
            inputText.setText(sb.toString());
        });

        view.findViewById(R.id.buttonEquals).setOnClickListener((view2)->{
            sb.setLength(0);

            try{
                sb.append(eval(inputText.getText().toString()));
                outText.setText(sb.toString());
            }catch (Exception e){
                sb.append(e.getMessage());
                outText.setText(sb.toString());
            }
        });

        view.findViewById(R.id.buttonDot).setOnClickListener((view2)->{
            sb.setLength(0);
            sb.append(inputText.getText());
            sb.append('.');
            inputText.setText(sb.toString());
        });

        view.findViewById(R.id.buttonZero).setOnClickListener((view2)->{
            sb.setLength(0);
            sb.append(inputText.getText());
            sb.append(0);
            inputText.setText(sb.toString());
        });

        view.findViewById(R.id.buttonOne).setOnClickListener((view2)->{
            sb.setLength(0);
            sb.append(inputText.getText());
            sb.append(1);
            inputText.setText(sb.toString());
        });

        view.findViewById(R.id.buttonTwo).setOnClickListener((view2)->{
            sb.setLength(0);
            sb.append(inputText.getText());
            sb.append(2);
            inputText.setText(sb.toString());
        });

        view.findViewById(R.id.buttonThree).setOnClickListener((view2)->{
            sb.setLength(0);
            sb.append(inputText.getText());
            sb.append(3);
            inputText.setText(sb.toString());
        });

        view.findViewById(R.id.buttonFour).setOnClickListener((view2)->{
            sb.setLength(0);
            sb.append(inputText.getText());
            sb.append(4);
            inputText.setText(sb.toString());
        });

        view.findViewById(R.id.buttonFive).setOnClickListener((view2)->{
            sb.setLength(0);
            sb.append(inputText.getText());
            sb.append(5);
            inputText.setText(sb.toString());
        });

        view.findViewById(R.id.buttonSix).setOnClickListener((view2)->{
            sb.setLength(0);
            sb.append(inputText.getText());
            sb.append(6);
            inputText.setText(sb.toString());
        });

        view.findViewById(R.id.buttonSeven).setOnClickListener((view2)->{
            sb.setLength(0);
            sb.append(inputText.getText());
            sb.append(7);
            inputText.setText(sb.toString());
        });

        view.findViewById(R.id.buttonEight).setOnClickListener((view2)->{
            sb.setLength(0);
            sb.append(inputText.getText());
            sb.append(8);
            inputText.setText(sb.toString());
        });

        view.findViewById(R.id.buttonNine).setOnClickListener((view2)->{
            sb.setLength(0);
            sb.append(inputText.getText());
            sb.append(9);
            inputText.setText(sb.toString());
        });

        view.findViewById(R.id.buttonPlus).setOnClickListener((view2)->{
            sb.setLength(0);
            sb.append(inputText.getText());
            sb.append('+');
            inputText.setText(sb.toString());
        });

        view.findViewById(R.id.buttonMinus).setOnClickListener((view2)->{
            sb.setLength(0);
            sb.append(inputText.getText());
            sb.append('-');
            inputText.setText(sb.toString());
        });

        view.findViewById(R.id.buttonDivide).setOnClickListener((view2)->{
            sb.setLength(0);
            sb.append(inputText.getText());
            sb.append('/');
            inputText.setText(sb.toString());
        });

        view.findViewById(R.id.buttonMultiply).setOnClickListener((view2)->{
            sb.setLength(0);
            sb.append(inputText.getText());
            sb.append('*');
            inputText.setText(sb.toString());
        });

        view.findViewById(R.id.buttonLeftParenthesis).setOnClickListener((view2)->{
            sb.setLength(0);
            sb.append(inputText.getText());
            sb.append('(');
            inputText.setText(sb.toString());
        });

        view.findViewById(R.id.buttonRightParenthesis).setOnClickListener((view2)->{
            sb.setLength(0);
            sb.append(inputText.getText());
            sb.append(')');
            inputText.setText(sb.toString());
        });
    }

    public double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                return x;
            }
        }.parse();
    }
}