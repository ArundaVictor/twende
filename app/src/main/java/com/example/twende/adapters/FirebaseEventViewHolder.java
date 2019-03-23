package com.example.twende.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.twende.Constants;
import com.example.twende.R;
import com.example.twende.models.Event;
import com.example.twende.ui.EventDetailActivity;
import com.example.twende.util.ItemTouchHelperViewHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseEventViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

    View mView;
    Context mContext;
    public ImageView mEventImageView;

    public FirebaseEventViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ArrayList<Event> events = new ArrayList<>();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                DatabaseReference ref = FirebaseDatabase.getInstance()
                        .getReference(Constants.FIREBASE_CHILD_EVENTS)
                        .child(uid);

                ref.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            events.add(snapshot.getValue(Event.class));
                        }

                        int itemPosition = getLayoutPosition();

                        Intent intent = new Intent(mContext, EventDetailActivity.class);
                        intent.putExtra("position", itemPosition + "");
                        intent.putExtra("events", Parcels.wrap(events));

                        mContext.startActivity(intent);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

            }
        });
    }

    public void bindEvent(Event event) {
        mEventImageView = (ImageView) mView.findViewById(R.id.eventImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.eventNameTextView);
        TextView currencyTextView = (TextView) mView.findViewById(R.id.currencyTextView);
        TextView statusTextView = (TextView) mView.findViewById(R.id.statusTextView);

        nameTextView.setText(event.getName());
        currencyTextView.setText(event.getCurrency());
        statusTextView.setText(event.getStatus());
    }

    @Override
    public void onItemSelected() {
        Log.d("Animation", "onItemSelected");
        // we will add animations here
    }

    @Override
    public void onItemClear() {
        Log.d("Animation", "onItemClear");
        // we will add animations here
    }


}
