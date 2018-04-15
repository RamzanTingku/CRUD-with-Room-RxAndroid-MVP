package net.messagingplus.mvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.messagingplus.mvp.R;
import net.messagingplus.mvp.dataModel.Students;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {


    Context context;
    private List<Students> studentsList;
    private StudentOnClickListener onItemClickListener;

    public StudentAdapter(Context context, List<Students> studentsList) {
        this.context = context;
        this.studentsList = studentsList;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_student, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {

        final Students students =  studentsList.get(position);


        holder.textViews[0].setText(""+students.getName());
        holder.textViews[1].setText(""+students.getSubject());
        holder.textViews[2].setText(""+students.getRoll());
        holder.textViews[3].setText(""+students.getSession());
        holder.textViews[4].setText(""+students.getSession());

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(students, v);
                }
        };

        holder.itemLayout.setOnClickListener(listener);

    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        @BindViews({R.id.std_name_tv,
                R.id.std_subject_tv,
                R.id.std_roll_tv,
                R.id.std_session_tv,
                R.id.std_institute_tv,
               })
        TextView[] textViews;

        @BindView(R.id.std_image_iv)
        ImageView imageView;

        @BindView(R.id.item_layout)
        CardView itemLayout;


        public StudentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


    public StudentOnClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(StudentOnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
