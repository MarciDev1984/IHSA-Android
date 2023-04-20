package com.example.ihsastable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.lifecycle.Observer;
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

import com.example.ihsastable.data.model.EventClass;
import com.example.ihsastable.data.model.Rider;
import com.example.ihsastable.data.repository.RiderRepository;
import com.example.ihsastable.viewmodel.EventClassesViewModel;
import com.example.ihsastable.viewmodel.RidersViewModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Activity_Class_Order extends AppCompatActivity {

    private RecyclerViewAdapter orderRV;
    private long downloadID;
    DownloadManager downloadManager;
    EventClass eventClass;
    RiderRepository riderRepository;
    int pos;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("SUPER AMAZING TEMP", "yeah its rider activity");
        this.setContentView(R.layout.activity_class_order);
        this.pos = this.getIntent().getIntExtra("pos", 0);

        this.orderRV = new RecyclerViewAdapter("rider_order_rv");
        final RecyclerView riderRecycler = this.findViewById(R.id.orderRV);
        riderRecycler.setAdapter(this.orderRV);

        final LinearLayoutManager thisManager = new LinearLayoutManager(this);
        riderRecycler.setLayoutManager(thisManager);

        final GestureDetectorCompat detect = new GestureDetectorCompat(this, new Activity_Class_Order.RecyclerViewOnGestureListener());
        riderRecycler.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(){
            @Override
            public boolean onInterceptTouchEvent(final RecyclerView rv, final MotionEvent e){
                return detect.onTouchEvent(e);
            }
        });
        this.riderRepository = new RiderRepository();
        this.downloadManager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        final Uri pdfURI = Uri.parse("https://drive.google.com/uc?export=download&id=1GG6jGkVEKIK0nZNZGZ1mrH2ToK2egc_J");

        registerReceiver(this.downloadReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        final DownloadManager.Request downloadRequest = new DownloadManager.Request(pdfURI);
        downloadRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        this.downloadID = this.downloadManager.enqueue(downloadRequest);

    }

    Observer<ArrayList<Rider>> riderUpdateObserver = new Observer<ArrayList<Rider>>() {
        @Override
        public void onChanged(final ArrayList<Rider> riders) {
            Activity_Class_Order.this.orderRV.updateRiders();
        }
    };
    public void onStart() {
        super.onStart();
        final TextView schedHead = this.findViewById(R.id.classTV);
        this.eventClass = EventClassesViewModel.getModel().eventClasses.getValue().get(this.pos);
        this.riderRepository.fetchRidersFromRiderIds(this.eventClass.getRiders());
        schedHead.setText(this.eventClass.getClassName());
        RidersViewModel.getModel().riders.observe(this, this.riderUpdateObserver);
    }
    public void onStop(){
        super.onStop();
        this.riderRepository.unsubFirebase();
    }
    public void openSingleRider(final int pos){
        final Intent opSched = new Intent(this, Activity_Rider_Profile.class);
        opSched.putExtra("pos", String.valueOf(pos + 1));
        this.startActivity(opSched);
    }

    public void openPDF(final View v) {
        final Intent pdfView = new Intent(this, RidingPatternDisplay.class);
        pdfView.putExtra("downloadID", this.downloadID);
        this.startActivity(pdfView);
    }

    protected BroadcastReceiver downloadReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context, final Intent intent) {
            final String action = intent.getAction();
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                try {
                    final ImageView pdfDisplay = Activity_Class_Order.this.findViewById(R.id.ridingPatternIV);

                    final PdfRenderer pdfRenderer = new PdfRenderer(Activity_Class_Order.this.downloadManager.openDownloadedFile(Activity_Class_Order.this.downloadID));
                    final PdfRenderer.Page patternPage = pdfRenderer.openPage(0);

                    final Bitmap pdfBitmap = Bitmap.createBitmap(pdfDisplay.getWidth(), pdfDisplay.getHeight(), Bitmap.Config.ARGB_4444);

                    patternPage.render(pdfBitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

                    pdfDisplay.setImageBitmap(pdfBitmap);
                } catch (final FileNotFoundException e) {
                    Toast.makeText(Activity_Class_Order.this.getApplicationContext(), "Oops, Looks like the download didn't complete! Please try again.", Toast.LENGTH_SHORT).show();
                } catch (final IOException e) {
                    Toast.makeText(Activity_Class_Order.this.getApplicationContext(), "Oops, something went wrong when trying to display the PDF.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    //This is almost directly Jacob Pickman's implementation
    //-----
    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onSingleTapConfirmed(final MotionEvent e)
        {
            final RecyclerView orderRV = Activity_Class_Order.this.findViewById(R.id.orderRV);
            final View view = orderRV.findChildViewUnder(e.getX(), e.getY());
            Log.d("click", "click happened");

            if (view != null)
            {
                final RecyclerView.ViewHolder holder = orderRV.getChildViewHolder(view);

                if (holder instanceof RecyclerViewAdapter.RecyclerViewHolder)
                {
                    final int position = holder.getAdapterPosition();
                    Log.d("click", "single tap clicked on item " + position);
                    Activity_Class_Order.this.openSingleRider(position);
                    Log.d("click", "Going to show");
                    return true;
                }
            }
            return false;
        }
    }
    //----
}