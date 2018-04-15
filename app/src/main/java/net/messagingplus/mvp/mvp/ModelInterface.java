package net.messagingplus.mvp.mvp;



import net.messagingplus.mvp.dataModel.Students;

import java.util.ArrayList;
import java.util.List;

public interface ModelInterface {

    void onStudentsDataLoadSuccess(List<Students> students);
    void onStudentsDataSaveSuccess(String message);
    void onStudentDataLoadFailed(String message);
    void onStudentDataSaveFailed(String message);

}
