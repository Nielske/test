package fi.jamk.shoppinglist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pasi on 27/09/15.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "JAMK_database";
    private final String DATABASE_TABLE = "products";
    private final String PRODUCT = "product";
    private final String COUNT = "count";
    private final String PRICE = "price";

    public DatabaseOpenHelper(Context context) {
        // Context, database name, optional cursor factory, database version
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create a new table
        db.execSQL("CREATE TABLE "+DATABASE_TABLE+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+PRODUCT+" TEXT, "+COUNT+" INT, " + PRICE +" DECIMAL(3,2));");
        // create sample data
        ContentValues values = new ContentValues();
        values.put(PRODUCT, "Milk");
        values.put(COUNT, 5);
        values.put(PRICE, 1.06);
        // insert data to database, name of table, "Nullcolumnhack", values
        db.insert(DATABASE_TABLE, null, values);
        // a more data...
        values.put(PRODUCT, "Bread");
        values.put(COUNT, 2);
        values.put(PRICE, 2.24);
        db.insert(DATABASE_TABLE, null, values);
        // a more data...
        values.put(PRODUCT, "Butter");
        values.put(COUNT, 1);
        values.put(PRICE, 3.34);
        db.insert(DATABASE_TABLE, null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
        onCreate(db);
    }
}
