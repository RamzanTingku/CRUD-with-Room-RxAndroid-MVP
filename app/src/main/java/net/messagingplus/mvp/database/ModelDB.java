package net.messagingplus.mvp.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import net.messagingplus.mvp.dataModel.Students;

@Database(entities = Students.class, version = 1)
public abstract class ModelDB extends RoomDatabase {

    public abstract ModelDao modelDao();

}
