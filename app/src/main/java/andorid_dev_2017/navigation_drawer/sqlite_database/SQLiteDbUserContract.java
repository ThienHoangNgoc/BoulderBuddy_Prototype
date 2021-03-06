package andorid_dev_2017.navigation_drawer.sqlite_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

public class SQLiteDbUserContract {

    SQLiteDbHelper sqLiteDbHelper;

    public SQLiteDbUserContract(Context context) {
        sqLiteDbHelper = new SQLiteDbHelper(context);
    }

    private int getNumberOfEntries(String tableName) {
        String countQuery = "SELECT *FROM " + tableName;
        SQLiteDatabase db = sqLiteDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public void insertEntry(String name, String password, String lastLogin, String exp,
                            String rank, String rankPoints, String imagePath, String loginStatus) {
        SQLiteDatabase db = sqLiteDbHelper.getWritableDatabase();
        int id = getNumberOfEntries(UserEntry.TABLE_NAME) + 1;
        ContentValues values = new ContentValues();
        values.put(UserEntry.COLUMN_NAME_USER_ID, id);
        values.put(UserEntry.COLUMN_NAME_USERNAME, name);
        values.put(UserEntry.COLUMN_NAME_PASSWORD, password);
        values.put(UserEntry.COLUMN_NAME_LAST_LOGIN, lastLogin);
        values.put(UserEntry.COLUMN_NAME_EXP, exp);
        values.put(UserEntry.COLUMN_NAME_RANK, rank);
        values.put(UserEntry.COLUMN_NAME_RANK_POINTS, rankPoints);
        values.put(UserEntry.COLUMN_NAME_PROFILE_PICTURE, imagePath);
        values.put(UserEntry.COLUMN_NAME_LOGIN_STATUS, loginStatus);


        long newRowID;
        newRowID = db.insert(
                UserEntry.TABLE_NAME,
                null,
                values);

    }

    public void updateOneColumn(String columnName, String newValue, String rowID) {
        SQLiteDatabase db = sqLiteDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(columnName, newValue);
        db.update(UserEntry.TABLE_NAME, values, "Id=" + rowID, null);

    }


    public void deleteRow(String rowID) {
        SQLiteDatabase db = sqLiteDbHelper.getWritableDatabase();
        db.delete(UserEntry.TABLE_NAME, "Id=" + rowID, null);

    }

    public void deleteAllEntries() {
        SQLiteDatabase db = sqLiteDbHelper.getWritableDatabase();
        db.execSQL(SQLiteDbHelper.SQL_DELETE_ENTRIES_USER);
        db.execSQL(SQLiteDbHelper.SQL_CREATE_ENTRIES_USER);
    }

    public Cursor readEntry() {
        SQLiteDatabase db = sqLiteDbHelper.getReadableDatabase();
        String[] projection = {
                UserEntry.COLUMN_NAME_USER_ID,
                UserEntry.COLUMN_NAME_USERNAME,
                UserEntry.COLUMN_NAME_PASSWORD,
                UserEntry.COLUMN_NAME_LAST_LOGIN,
                UserEntry.COLUMN_NAME_EXP,
                UserEntry.COLUMN_NAME_RANK,
                UserEntry.COLUMN_NAME_RANK_POINTS,
                UserEntry.COLUMN_NAME_PROFILE_PICTURE,
                UserEntry.COLUMN_NAME_LOGIN_STATUS,
        };

        String sortOrder = UserEntry.COLUMN_NAME_USER_ID + " ASC";
        Cursor c = db.query(
                UserEntry.TABLE_NAME,
                projection,
                null,               //columns for the WHERE clause
                null,               //values for the WHERE clause
                null,               //don't group the rows
                null,               //don't filter by row groups
                sortOrder
        );
        return c;
    }


}
