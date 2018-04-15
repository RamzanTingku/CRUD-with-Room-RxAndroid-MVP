package net.messagingplus.mvp.dataModel;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "students")
public class Students {

    @PrimaryKey(autoGenerate = true)
    public long student_id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "institute")
    private String institute;

    @ColumnInfo(name = "subject")
    private String subject;

    @ColumnInfo(name = "roll")
    private String roll;

    @ColumnInfo(name = "session")
    private String session;

    @ColumnInfo(name = "image")
    private String image;


    public Students() {
    }

    public Students(long student_id, String name, String subject) {
        this.student_id = student_id;
        this.name = name;
        this.subject = subject;
    }

    @Ignore
    public Students(long student_id, String name, String institute, String subject, String roll, String session, String image) {
        this.student_id = student_id;
        this.name = name;
        this.institute = institute;
        this.subject = subject;
        this.roll = roll;
        this.session = session;
        this.image = image;
    }

    public Students(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    public long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Students{" +
                "student_id=" + student_id +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
