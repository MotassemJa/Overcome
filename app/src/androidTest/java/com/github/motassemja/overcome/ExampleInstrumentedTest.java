package com.github.motassemja.overcome;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.github.motassemja.overcome.db.FeelingsDatabase;
import com.github.motassemja.overcome.db.dao.FeelingDao;
import com.github.motassemja.overcome.model.Feeling;
import com.github.motassemja.overcome.utils.FeelingUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private FeelingDao mFeelingDao;
    private FeelingsDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, FeelingsDatabase.class).build();
        mFeelingDao = mDb.feelingDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void writeAndReadFeeling() throws Exception {
        Feeling feeling = FeelingUtils.createFeeling("1", "Test");
        mFeelingDao.insertFeeling(feeling);
        Feeling feeling1 = mFeelingDao.findFeeling("1");
        assertEquals(feeling, feeling1);
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.github.motassemja.overcome", appContext.getPackageName());
    }
}
