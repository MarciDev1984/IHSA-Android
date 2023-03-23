package com.example.ihsastable;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;

public class RidingPatternDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riding_pattern_display);

        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                try {
                    DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                    long downloadID = getIntent().getLongExtra("downloadID", -1);
                    if (downloadID == -1) throw new InputMismatchException("Did not collect downloadID from previous activity, something has seriously broken!");
                    ImageView pdfDisplay = findViewById(R.id.pdfDisplayLarge);

                    PdfRenderer pdfRenderer = new PdfRenderer(downloadManager.openDownloadedFile(downloadID));
                    PdfRenderer.Page patternPage = pdfRenderer.openPage(0);

                    Bitmap pdfBitmap = Bitmap.createBitmap(pdfDisplay.getMaxWidth(), pdfDisplay.getMaxHeight(), Bitmap.Config.ARGB_4444);

                    patternPage.render(pdfBitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

                    pdfDisplay.setImageBitmap(pdfBitmap);
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Oops, Looks like the download didn't complete! Please try again.", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Oops, something went wrong when trying to display the PDF.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}