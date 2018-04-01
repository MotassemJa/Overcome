package com.github.motassemja.overcome.utils;

import com.github.motassemja.overcome.model.Feeling;

/**
 * Copyright github.com/MotassemJa on 4/1/2018.
 */
public class FeelingUtils {

    public static Feeling createFeeling(String id, String name) {
        return new Feeling(id, name);
    }
}
