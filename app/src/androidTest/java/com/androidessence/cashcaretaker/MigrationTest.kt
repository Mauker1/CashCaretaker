package com.androidessence.cashcaretaker

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory
import android.arch.persistence.room.migration.Migration
import android.arch.persistence.room.testing.MigrationTestHelper
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.androidessence.cashcaretaker.core.CCDatabaseR
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tests database migrations.
 */
@RunWith(AndroidJUnit4::class)
class MigrationTest {
    @JvmField @Rule var helper: MigrationTestHelper = MigrationTestHelper(
            InstrumentationRegistry.getInstrumentation(),
            CCDatabaseR::class.java.canonicalName,
            FrameworkSQLiteOpenHelperFactory())

    // @Test
    fun migrate4To5() {
        var db = helper.createDatabase(TEST_DB, 4)

        // Db has schema version 4. Insert some data using SQL queries.
        // You cannot use DAO classes because they expect the latest schema.
        db.execSQL("INSERT INTO accountTable VALUES (1, 'Checking', '5.00')")

        // Prepare for next version
        db.close()

        // Re-open the database with version 5
        // and provide MIGRATION_4_5 as the migration process
        db = helper.runMigrationsAndValidate(TEST_DB, 5, true, MIGRATION_4_5)

        // MigrationTestHelper automatically verifies the schema changes
        // but you need to validate that the data was migrated properly.
    }

    companion object {
        private val TEST_DB = "cashcaretaker.db"

        // No-op migration because we're just converting existing schema to Room
        val MIGRATION_4_5: Migration = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // No-op
            }
        }
    }
}

//
//        // Re-open the database with version 2 and provide
//        // MIGRATION_1_2 as the migration process.
//        db = helper.runMigrationsAndValidate(TEST_DB, 2, true, MIGRATION_1_2);
//
//        // MigrationTestHelper automatically verifies the schema changes,
//        // but you need to validate that the data was migrated properly.
//    }
//}
