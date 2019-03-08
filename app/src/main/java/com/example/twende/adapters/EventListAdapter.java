package com.example.twende.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.twende.R;
import com.example.twende.models.Event;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder> {

    private ArrayList<Event> mEvents = new ArrayList<>();
    private Context mContext;

    public EventListAdapter(Context context, ArrayList<Event> events) {
        mContext = context;
        mEvents = events;
    }

    @Override
    public EventListAdapter.EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
        EventViewHolder viewHolder = new EventViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventListAdapter.EventViewHolder holder, int position) {
        holder.bindEvent(mEvents.get(position));
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.eventImageView)
        ImageView mEventImageView;
        @BindView(R.id.eventNameTextView)
        TextView mEventNameTextView;
        @BindView(R.id.statusTextView)
        TextView mStatusTextView;
        @BindView(R.id.currencyTextView)
        TextView mCurrencyTextView;

        private Context mContext;

        public EventViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindEvent(Event event) {
            mEventNameTextView.setText(event.getName());
            mStatusTextView.setText(event.getStatus());
            mCurrencyTextView.setText(event.getCurrency());
        }

    }
}



