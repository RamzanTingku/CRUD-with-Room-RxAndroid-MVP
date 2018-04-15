package net.messagingplus.mvp.mvp;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import net.messagingplus.mvp.MainActivity;
import net.messagingplus.mvp.dataModel.Students;
import net.messagingplus.mvp.database.ModelDB;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Model {

    ModelInterface modelInterface;
    ModelDB modelDB;
    Context context;

    boolean success;



    Model(ModelInterface modelInterface, Context context) {

        this.context=context;
        this.modelInterface=modelInterface;

        modelDB = Room.databaseBuilder(context,ModelDB.class,"students")
                .allowMainThreadQueries()
                .build();

    }

    // TODO: get mathod
    public void getStudentData() {

        fetchDataFromLocalDB();

    }


    // TODO: save mathod
    void saveStudentData(Students students, String type) {

        boolean success = true;

        if(success){

        switch (type){

            case MainActivity.SAVE:

                saveDataToLocalDB(students);
                break;

            case MainActivity.UPDATE:

                updateDataToLocalDB(students);
                break;

                }

            modelInterface.onStudentsDataSaveSuccess("Students data saved");


        }else {

            modelInterface.onStudentDataSaveFailed("Students data save failed");

        }

    }


    public void fetchDataFromLocalDB(){


        modelDB.modelDao().loadStudents()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Students>>() {
                    @Override
                    public void accept(List<Students> students) throws Exception {

                        if(!students.isEmpty()){
                           modelInterface.onStudentsDataLoadSuccess(students);
                            Log.d("MVP","Students data load success");

                            }else {

                            modelInterface.onStudentDataLoadFailed("No Student is found");
                        }

                    }

                });

    }

    public void saveDataToLocalDB(Students students){

        modelDB.modelDao().insertStudent(students);

        fetchDataFromLocalDB();
    }

    public void updateDataToLocalDB(Students students){

        modelDB.modelDao().updateStudent(students);
        //modelDB.modelDao().updateStudentById(students.getStudent_id(),students.getName());
        //modelDB.modelDao().updateStudentById(students.getStudent_id(),students.getName(),students.getSubject());

        fetchDataFromLocalDB();
    }

}
