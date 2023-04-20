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
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_riding_pattern_display);

        this.getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                try {
                    final DownloadManager downloadManager = (DownloadManager) RidingPatternDisplay.this.getSystemService(Context.DOWNLOAD_SERVICE);
                    final long downloadID = RidingPatternDisplay.this.getIntent().getLongExtra("downloadID", -1);
                    if (downloadID == -1) throw new InputMismatchException("Did not collect downloadID from previous activity, something has seriously broken!");
                    final ImageView pdfDisplay = RidingPatternDisplay.this.findViewById(R.id.pdfDisplayLarge);

                    final PdfRenderer pdfRenderer = new PdfRenderer(downloadManager.openDownloadedFile(downloadID));
                    final PdfRenderer.Page patternPage = pdfRenderer.openPage(0);

                    final Bitmap pdfBitmap = Bitmap.createBitmap(pdfDisplay.getWidth(), pdfDisplay.getHeight(), Bitmap.Config.ARGB_4444);

                    patternPage.render(pdfBitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

                    pdfDisplay.setImageBitmap(pdfBitmap);
                } catch (final FileNotFoundException e) {
                    Toast.makeText(RidingPatternDisplay.this.getApplicationContext(), "Oops, Looks like the download didn't complete! Please try again.", Toast.LENGTH_SHORT).show();
                } catch (final IOException e) {
                    Toast.makeText(RidingPatternDisplay.this.getApplicationContext(), "Oops, something went wrong when trying to display the PDF.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}