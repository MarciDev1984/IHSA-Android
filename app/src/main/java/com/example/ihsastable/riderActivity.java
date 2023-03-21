package com.example.ihsastable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;

public class riderActivity extends AppCompatActivity {

    private RecyclerViewAdapter orderRV;
    private long downloadID;
    DownloadManager downloadManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("SUPER AMAZING TEMP", "yeah its rider activity");

        setContentView(R.layout.activity_rider);
        
        String pos = getIntent().getStringExtra("POS");

        TextView schedHead = findViewById(R.id.classTV);
        schedHead.setText("Class " + pos + " Order");

        orderRV = new RecyclerViewAdapter("rider_order_rv");
        RecyclerView riderRecycler = findViewById(R.id.orderRV);
        riderRecycler.setAdapter(orderRV);

        LinearLayoutManager thisManager = new LinearLayoutManager(this);
        riderRecycler.setLayoutManager(thisManager);

        GestureDetectorCompat detect = new GestureDetectorCompat(this, new riderActivity.RecyclerViewOnGestureListener());
        riderRecycler.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(){
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e){
                return detect.onTouchEvent(e);
            }
        });
    }

    public void openSingleRider(int pos){
        Intent opSched = new Intent(this, riderProfile.class);
        opSched.putExtra("POS", String.valueOf(pos + 1));
        startActivity(opSched);
    }

    public void openPDF(View v) {
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri pdfURI = Uri.parse("https://assets.website-files.com/603d0d2db8ec32ba7d44fffe/603d0e327eb2748c8ab1053f_loremipsum.pdf");

        this.registerReceiver(downloadReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        DownloadManager.Request downloadRequest = new DownloadManager.Request(pdfURI);
        downloadRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        downloadID = downloadManager.enqueue(downloadRequest);

        Toast.makeText(getApplicationContext(), "Downloading riding pattern, please wait on this screen until it loads.", Toast.LENGTH_SHORT).show();

        //startActivity(new Intent(Intent.ACTION_VIEW, pdfURI));
    }

    BroadcastReceiver downloadReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                try {
                    ImageView pdfDisplay = findViewById(R.id.ridingPatternIV);

                    PdfRenderer pdfRenderer = new PdfRenderer(downloadManager.openDownloadedFile(downloadID));
                    PdfRenderer.Page patternPage = pdfRenderer.openPage(0);

                    Bitmap pdfBitmap = Bitmap.createBitmap(pdfDisplay.getWidth(), pdfDisplay.getHeight(), Bitmap.Config.ARGB_4444);

                    patternPage.render(pdfBitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

                    pdfDisplay.setImageBitmap(pdfBitmap);
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Oops, Looks like the download didn't complete! Please try again.", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Oops, something went wrong when trying to display the PDF.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    //This is almost directly Jacob Pickman's implementation
    //-----
    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onSingleTapConfirmed(MotionEvent e)
        {
            RecyclerView orderRV = findViewById(R.id.orderRV);
            View view = orderRV.findChildViewUnder(e.getX(), e.getY());
            Log.d("click", "click happened");

            if (view != null)
            {
                RecyclerView.ViewHolder holder = orderRV.getChildViewHolder(view);

                if (holder instanceof RecyclerViewAdapter.RecyclerViewHolder)
                {
                    int position = holder.getAdapterPosition();
                    Log.d("click", "single tap clicked on item " + position);
                    openSingleRider(position);
                    Log.d("click", "Going to show");
                    return true;
                }
            }
            return false;
        }
    }
    //----
}