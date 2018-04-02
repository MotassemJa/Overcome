package com.github.motassemja.overcome.db;

import com.github.motassemja.overcome.model.Feeling;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright github.com/MotassemJa on 4/1/2018.
 */
public class DataGenerator {

    private static final String[] ID = new String[]{
            "1", "2", "3"
    };

    private static final String[] NAME = new String[]{
            "SADNESS", "JOY", "CRYING"
    };

    public static List<Feeling> generateFeelings() {
        List<Feeling> feelings = new ArrayList<>();
        for (int i = 0; i < ID.length; i++) {
            feelings.add(new Feeling(ID[i], NAME[i]));
        }
        return feelings;
    }
}
