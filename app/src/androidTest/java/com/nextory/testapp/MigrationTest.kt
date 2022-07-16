package com.nextory.testapp

import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.nextory.testapp.data.AppDatabase
import com.nextory.testapp.data.MIGRATION_1_2
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MigrationTest {
    private val TEST_DB = "migration-test"

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        AppDatabase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    @Throws(IOException::class)
    fun migrate1To2() {
        var db = helper.createDatabase(TEST_DB, 1).apply {
            execSQL("INSERT INTO book Values (1,'title','author', 'description', 'imageUrl');")
            close()
        }

        db = helper.runMigrationsAndValidate(TEST_DB, 2, true, MIGRATION_1_2)
        val cursor = db.query("SELECT favorite FROM book")
        cursor.moveToNext()
        val favoriteState = cursor.getInt(0)
        cursor.close()
        Assert.assertEquals(0, favoriteState)
    }
}