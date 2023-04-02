package com.ldm.a6_2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText4);
        textView = findViewById(R.id.textView);

        editText.addTextChangedListener(new TextWatcher() {
            // TextWathher 인터페이스는 아래 3개의 메서드를 필수로 구현하여아 한다.
            // 이 문제에서는 변화가 있을 경우에 대한 처리 관련 메서드만 필요하다.

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력하기 전에 동작하는 메서드
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textView.setText((start + count) + " / " + 80 + " 바이트");
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 입력이 끝났을 때 호출 된다
            }
        });
    }

    // 전송버튼 클릭 시 동작
    public void onButton1Clicked(View v) {
        sendToast();
    }

    // Toast 메시지를 출력한다.
    private void sendToast() {
        Toast.makeText(this, editText.getText(), Toast.LENGTH_LONG).show();
    }
}