package com.example.twende.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.twende.Constants;
import com.example.twende.R;
import com.example.twende.models.Event;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.eventImageView) ImageView mImageLabel;
    @BindView(R.id.eventNameTextView) TextView mNameLabel;
    @BindView(R.id.statusTextView) TextView mStatusLabel;
    @BindView(R.id.currencyTextView) TextView mCurrencyLabel;
    @BindView(R.id.websiteTextView) TextView mWebsiteLabel;
    @BindView(R.id.saveEventButton) Button mSaveRestaurantButton;


    private Event mEvent;





    public static EventDetailFragment newInstance (Event event) {
        EventDetailFragment eventDetailFragment = new EventDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("event", Parcels.wrap(event));
        eventDetailFragment.setArguments(args);
        return eventDetailFragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEvent = Parcels.unwrap(getArguments().getParcelable("event"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
        ButterKnife.bind(this, view);

        mNameLabel.setText(mEvent.getName());
        mStatusLabel.setText(mEvent.getStatus());
        mCurrencyLabel.setText(mEvent.getCurrency());

        mWebsiteLabel.setOnClickListener(this);

        mSaveRestaurantButton.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mEvent.getUrl()));
            startActivity(webIntent);
        }

        if (v == mSaveRestaurantButton) {
            DatabaseReference eventRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_EVENTS);
            eventRef.push().setValue(mEvent);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }

    //implicit intent


}
}
