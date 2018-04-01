package com.github.motassemja.overcome.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Copyright github.com/MotassemJa on 4/1/2018.
 */

@Entity
public class Feeling {

    @PrimaryKey
    @NonNull
    private String feelingID;

    private String feelingName;

    public Feeling(String feelingID, String feelingName) {
        this.feelingID = feelingID;
        this.feelingName = feelingName;
    }

    public String getFeelingID() {
        return feelingID;
    }

    public void setFeelingID(String feelingID) {
        this.feelingID = feelingID;
    }

    public String getFeelingName() {
        return feelingName;
    }

    public void setFeelingName(String feelingName) {
        this.feelingName = feelingName;
    }
}
