package com.github.motassemja.overcome.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.github.motassemja.overcome.db.dao.FeelingDao;
import com.github.motassemja.overcome.model.Feeling;

/**
 * Copyright github.com/MotassemJa on 4/1/2018.
 */

@Database(entities = {Feeling.class}, version = 1)
public abstract class FeelingsDatabase extends RoomDatabase {
    public abstract FeelingDao feelingDao();
}
