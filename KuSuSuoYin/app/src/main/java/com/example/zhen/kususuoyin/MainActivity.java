package com.example.zhen.kususuoyin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity
        extends AppCompatActivity implements QuickIndexBar.OnLetterUpdateListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QuickIndexBar qib = (QuickIndexBar) findViewById(R.id.ksy);
        qib.setOnLetterUpdateListener(this);
    }
    /**
     * 显示当前字母
     * @param letter
     */
    @Override
    public void updateLetter(String letter){
        Utils.showToast(this, letter);
    }


}
