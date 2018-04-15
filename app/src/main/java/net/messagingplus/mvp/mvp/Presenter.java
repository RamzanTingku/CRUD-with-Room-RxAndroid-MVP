package net.messagingplus.mvp.mvp;

import android.content.Context;
import android.util.Log;

import net.messagingplus.mvp.dataModel.Students;

import java.util.ArrayList;
import java.util.List;

public class Presenter implements ModelInterface {

    ViewData view;
    Context context;
    Model model;


    public Presenter(ViewData view,Context context) {
        this.view = view;
        model = new Model(this,context);
        this.context=context;
    }



    public void getStudentDataList() {
        model.getStudentData();
        view.startLoading();
    }

    public  void saveStudentDataList(Students students, String type){
        model.saveStudentData(students, type);

    }



    @Override
    public void onStudentsDataLoadSuccess(List<Students> students) {

        view.stopLoading();
        view.showStudentList(students);

    }

    @Override
    public void onStudentsDataSaveSuccess(String message) {

        Log.d("MVP: ",""+message);

    }

    @Override
    public void onStudentDataLoadFailed(String message) {

        Log.d("MVP: ",""+message);

    }

    @Override
    public void onStudentDataSaveFailed(String message) {

        Log.d("MVP: ",""+message);

    }


}
