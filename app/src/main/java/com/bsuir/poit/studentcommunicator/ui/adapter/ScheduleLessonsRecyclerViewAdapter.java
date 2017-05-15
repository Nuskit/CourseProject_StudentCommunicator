package com.bsuir.poit.studentcommunicator.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bsuir.poit.studentcommunicator.R;
import com.bsuir.poit.studentcommunicator.model.LessonSchedule;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleLessonsRecyclerViewAdapter extends RecyclerView.Adapter<ScheduleLessonsRecyclerViewAdapter.ViewHolder> {

    private final List<LessonSchedule> mValues;

    public ScheduleLessonsRecyclerViewAdapter(List<LessonSchedule> items) {
        mValues = items;
    }

    private Context context;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.fragment_schedule_lessons, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.lessonType.setText(mValues.get(position).getType());
        holder.lessonTime.setText(mValues.get(position).getTime());
        holder.lessonSubject.setText(mValues.get(position).getSubject());
        // TODO: for teacher set field empty
        holder.lessonDescSubject.setText(mValues.get(position).getTeacher());
        holder.lessonPlace.setText(mValues.get(position).getPosition());
        //TODO: change lessonNotification

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show();
                /*if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.schedule_lesson_time)
        TextView lessonTime;
        @BindView(R.id.schedule_lesson_type)
        TextView lessonType;
        @BindView(R.id.schedule_lesson_subject)
        TextView lessonSubject;
        @BindView(R.id.schedule_lesson_desc_subject)
        TextView lessonDescSubject;
        @BindView(R.id.schedule_lesson_place)
        TextView lessonPlace;
        @BindView(R.id.schedule_lesson_notification)
        ImageView lessonNotification;
        View mView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
        }
    }
}
