package net.messagingplus.mvp.database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import net.messagingplus.mvp.dataModel.Students;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ModelDao {

    @Insert
    void insertStudent(Students students);

    @Update
    void updateStudent(Students students);

    /*@Query("update students set name=:name " +
            "and institute=:institute " +
            "and subject=:subject " +
            "and roll=:roll " +
            "and session=:session " +
            "and image=:image " +
            "where student_id in(:student_id)")
    int updateStudentById(long student_id, String name, String institute, String subject, String roll, String session, String image);
*/
   /* @Query("update students set name=:name " +
            "and subject=:subject " +
            "where student_id in(:student_id)")
    int updateStudentById(long student_id, String name,String subject);*/


    @Query("update students set name=:name where student_id in(:student_id)")
    int updateStudentById(long student_id, String name);

    @Query("delete from students where student_id = :student_id")
    int deleteStudentById(long student_id);

    @Query("SELECT * FROM students")
    Flowable<List<Students>> loadStudents();
}
