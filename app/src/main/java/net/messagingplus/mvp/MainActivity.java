package net.messagingplus.mvp;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import net.messagingplus.mvp.adapter.StudentAdapter;
import net.messagingplus.mvp.adapter.StudentOnClickListener;
import net.messagingplus.mvp.database.ModelDB;
import net.messagingplus.mvp.mvp.Presenter;
import net.messagingplus.mvp.dataModel.Students;
import net.messagingplus.mvp.mvp.ViewData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements ViewData, View.OnClickListener{

    Context context;
    Presenter presenter;
    StudentAdapter studentAdapter;
    ModelDB modelDB;

    public static final String UPDATE = "update";
    public static final String SAVE = "save";
    public static final String DELETE = "delete";

    @BindView(R.id.list_recyclerview)
    RecyclerView listRV;
    @BindView(R.id.add_item_fab)
    FloatingActionButton addFAB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        context = MainActivity.this;
        presenter=new Presenter(this,context);
        presenter.getStudentDataList();

        addFAB.setOnClickListener(this);

    }

    @Override
    public void startLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showStudentList(List<Students> studentsList) {

        Log.d("MVP",""+studentsList.size());

        setRecyclerView(studentsList);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.add_item_fab:

                addStudents(new Students(),MainActivity.SAVE);

                break;
        }

    }

    private void addStudents(final Students students, final String type) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_add_student, null);
        dialogBuilder.setView(dialogView);

        final EditText nameET = (EditText) dialogView.findViewById(R.id.std_name_et);
        final EditText subjectET = (EditText) dialogView.findViewById(R.id.std_subject_et);

        final long id = students.getStudent_id();
        final String name = students.getName();
        final String subject = students.getSubject();

        nameET.setText(name);
        subjectET.setText(subject);


        String title = "Add New Student";

        dialogBuilder.setTitle(title);
        dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (type.equals(MainActivity.SAVE)){

                    String name = nameET.getText().toString();
                    String subject = subjectET.getText().toString();

                    if(TextUtils.isEmpty(name)){
                        nameET.setError("Input Name");
                    }else if(TextUtils.isEmpty(subject)){
                        subjectET.setError("Input Subject");
                    }else {

                        Students students = new Students(name, subject);
                        presenter.saveStudentDataList(students, type);
                    }
                }

                else if(type.equals(MainActivity.UPDATE)){

                    String name = nameET.getText().toString();
                    String subject = subjectET.getText().toString();

                    if(TextUtils.isEmpty(name)){
                        nameET.setError("Input Name");
                    }else if(TextUtils.isEmpty(subject)){
                        subjectET.setError("Input Subject");
                    }else {

                        Students students = new Students(id,name,subject);
                        presenter.saveStudentDataList(students, type);
                    }


                }

            }
        });


        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        }



        public void setRecyclerView(List<Students> students){

            studentAdapter = new StudentAdapter(this,students);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setReverseLayout(true);
            llm.setStackFromEnd(true);
            listRV.setHasFixedSize(true);
            listRV.setLayoutManager(llm);
            listRV.setAdapter(studentAdapter);

            studentAdapter.setOnItemClickListener(new StudentOnClickListener() {
                @Override
                public void onItemClick(Students item, View view) {

                    addStudents(item,MainActivity.UPDATE);

                }
            });

        }


}
