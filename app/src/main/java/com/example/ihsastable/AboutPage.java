package com.example.ihsastable;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class AboutPage extends AppCompatActivity {

    TextView link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        link = findViewById(R.id.textView3);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link.setLinkTextColor(Color.BLUE);

//        link = findViewById(R.id.link);
//        link.setText("Click here to open IHSA General information");
//        String WEB_URLS = "https://www.ihsainc.com/about-us/general-information";
//        Linkify.addLinks(link, Linkify.WEB_URLS);
    }
}