package net.messagingplus.mvp.mvp;

import net.messagingplus.mvp.dataModel.Students;

import java.util.ArrayList;
import java.util.List;

public interface ViewData {

    void startLoading();
    void stopLoading();
    void showError(String message);
    void showStudentList(List<Students> studentsList);

}
