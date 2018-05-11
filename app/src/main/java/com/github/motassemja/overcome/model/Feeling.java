package com.github.motassemja.overcome.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Copyright github.com/MotassemJa on 4/1/2018.
 */

@Entity
public class Feeling {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long feelingID;

    private String feelingName;

    public Feeling(String feelingName) {
        this.feelingName = feelingName;
    }

    public long getFeelingID() {
        return feelingID;
    }

    public void setFeelingID(long feelingID) {
        this.feelingID = feelingID;
    }

    public String getFeelingName() {
        return feelingName;
    }

    public void setFeelingName(String feelingName) {
        this.feelingName = feelingName;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null &&
                obj instanceof Feeling &&
                ((Feeling) obj).getFeelingID() == this.feelingID;
    }
}
