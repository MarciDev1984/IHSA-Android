package com.example.ihsastable;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class scheduleActivity extends AppCompatActivity {

    private showAdapter riderRV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        String pos = getIntent().getStringExtra("POS");

        TextView schedHead = findViewById(R.id.scheduleHeaderTV);
        System.out.println(pos);
        if(pos.equals("1") ){
            schedHead.setText("Butler Equestrian");
        }
        else{
            schedHead.setText("Show " + pos + " Schedule");
        }

        riderRV = new showAdapter(1);
        RecyclerView riderRecycler = findViewById(R.id.riderRV);
        riderRecycler.setAdapter(riderRV);

        LinearLayoutManager thisManager = new LinearLayoutManager(this);
        riderRecycler.setLayoutManager(thisManager);

        GestureDetectorCompat detect = new GestureDetectorCompat(this, new RecyclerViewOnGestureListener());
        riderRecycler.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(){
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e){
                return detect.onTouchEvent(e);
            }
        });
    }

    public void openRider(int pos){
        Intent opSched = new Intent(this, riderActivity.class);
        opSched.putExtra("POS", String.valueOf(pos + 1));
        startActivity(opSched);
    }


    //This is almost directly Jacob Pickman's implementation
    //-----
    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onSingleTapConfirmed(MotionEvent e)
        {
            RecyclerView riderRV = findViewById(R.id.riderRV);
            View view = riderRV.findChildViewUnder(e.getX(), e.getY());
            Log.d("click", "click happened in sa");

            if (view != null)
            {
                RecyclerView.ViewHolder holder = riderRV.getChildViewHolder(view);

                if (holder instanceof showAdapter.showViewHolder)
                {
                    int position = holder.getAdapterPosition();
                    Log.d("click", "single tap clicked on item " + position);
                    openRider(position);
                    Log.d("click", "Going to show");
                    return true;
                }
            }
            return false;
        }
    }
    //----

}