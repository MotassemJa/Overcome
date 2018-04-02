package com.github.motassemja.overcome.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.motassemja.overcome.model.Feeling;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Copyright github.com/MotassemJa on 4/1/2018.
 */

@Dao
public interface FeelingDao {

    @Query("SELECT * FROM FEELING WHERE feelingID = :id")
    LiveData<Feeling> findFeelingById(String id);

    @Query("SELECT * FROM Feeling WHERE feelingID like :id")
    Feeling findFeeling(String id);

    @Insert(onConflict = REPLACE)
    void insertFeeling(Feeling... feeling);

    @Insert(onConflict = REPLACE)
    void insertAllFeelings(List<Feeling> feelingList);

    @Update
    void updateFeeling(Feeling... feeling);

    @Delete
    void deleteFeeling(Feeling... feeling);

}
