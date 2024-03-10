package com.suhas.activity.providers

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri


class MyProvider : ContentProvider() {

    private var db: SQLiteDatabase? = null

    companion object {
        //Defining authority so that other application can access it
        const val PROVIDER_NAME = "com.suhas.activity.provider"

        //defining content URI
        const val URL = "content://$PROVIDER_NAME/users"

        //prasing the content uri
        val CONTENT_URI = Uri.parse(URL)
        const val id = "id"
        const val name = "name"
        const val uriCode = 1
        var uriMatcher: UriMatcher? = null
        private val values: HashMap<String, String>? = null

        //declaring name of Database
        const val DATABASE_NAME = "UserDB"

        //declaring table name of the database
        const val TABLE_NAME = "Users"

        //declaring database version
        const val DB_VERSION = 1

        //sql Query to create table
        const val CREATE_DB_TABLE =
            ("CREATE TABLE "
                    + TABLE_NAME
                    + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name TEXT NOT NULL);")

        init {
            //to match the content uri
            //every time user access table undercontent provider
            uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

            //to access whole table
            uriMatcher!!.addURI(
                PROVIDER_NAME,
                "users",
                uriCode
            )

            //to acess a particular row
            //of the table
            uriMatcher!!.addURI(
                PROVIDER_NAME,
                "users/*",
                uriCode
            )
        }
    }

    override fun getType(p0: Uri): String? {
        return when (uriMatcher!!.match(p0)) {
            uriCode -> "vnd.android.cursor.dir/users"
            else -> throw IllegalArgumentException("Unsupported URI: $p0")
        }
    }


    override fun onCreate(): Boolean {
        val context = context
        val dbHelper = DatabseHelper(context)
        db = dbHelper.writableDatabase
        return db != null
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        var sortOrder = p4
        val qb = SQLiteQueryBuilder()
        qb.tables = TABLE_NAME
        when (uriMatcher!!.match(p0)) {
            uriCode -> qb.projectionMap = values
            else -> throw IllegalArgumentException("UNKNOWN URI $p0")

        }
        if (sortOrder == null || sortOrder === "") {
            sortOrder = id
        }
        val c = qb.query(db, p1, p2, p3, null, null, sortOrder)
        c.setNotificationUri(context!!.contentResolver, p0)
        return c
    }


    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        val rowID = db!!.insert(TABLE_NAME, "", p1)
        if (rowID > 0) {
            val uri = ContentUris.withAppendedId(CONTENT_URI, rowID)
            context!!.contentResolver.notifyChange(uri, null)
            return uri
        } else throw SQLiteException("Filed to add a record into $p0")


    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        var count = 0
        count = when (uriMatcher!!.match(p0)) {
            uriCode -> db!!.delete(TABLE_NAME, p1, p2)
            else -> throw IllegalArgumentException("UNKNOWN URI $p0")

        }
        context!!.contentResolver.notifyChange(p0, null)
        return count
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        var count = 0
        count = when (uriMatcher!!.match(p0)) {
            uriCode -> db!!.update(TABLE_NAME, p1, p2, p3)
            else -> throw IllegalArgumentException("UNKNOWN URI $p0")

        }
        context!!.contentResolver.notifyChange(p0, null)
        return count
    }


    //createing the object of database
    //to perform query


    private class DatabseHelper(context: Context?) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DB_VERSION) {

        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(CREATE_DB_TABLE)
        }

        override fun onUpgrade(
            db: SQLiteDatabase,
            oldversion: Int,
            newVersion: Int
        ) {
            //Sql query to drop a table
            //having similar name
            db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        }
    }


}


