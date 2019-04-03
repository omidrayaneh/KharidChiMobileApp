package com.omidrayaneh.omid.kharidchi.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.omidrayaneh.omid.kharidchi.model.product;

import java.util.LinkedList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "ProductDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_PRODUCT_TABLE = "CREATE TABLE product ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name_product TEXT, "+
                "price TEXT,"+
                "image_url TEXT," +
                "discount TEXT,"+
                "all_qty TEXT,"+
                "qty TEXT )";

        // create books table
        db.execSQL(CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS product");

        // create fresh books table
        this.onCreate(db);
    }

    /**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */

    // Books table name
    private static final String TABLE_PRODUCT = "product";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name_product";
    private static final String KEY_PRICE = "price";
    private static final String KEY_IMAGE = "image_url";
    private static final String KEY_ALL_QTY = "all_qty";
    private static final String KEY_QTY = "qty";
    private static final String KEY_DISCOUNT = "discount";

    private static final String[] COLUMNS = {KEY_ID,KEY_NAME,KEY_PRICE,KEY_IMAGE,KEY_DISCOUNT,KEY_ALL_QTY,KEY_QTY};

    public void addProduct(product products){
        Log.d("addBook", products.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, products.getName_product()); // get name
        values.put(KEY_PRICE, products.getPrice()); // get price
        values.put(KEY_IMAGE, products.getImage_url()); // get image
        values.put(KEY_DISCOUNT, products.getDiscont());
        values.put(KEY_ALL_QTY, products.getAll_qty());
        values.put(KEY_QTY, products.getQty());

        // 3. insert
        db.insert(TABLE_PRODUCT, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public product getProduct(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_PRODUCT, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        product products = new product();
        products.setId(Integer.parseInt(cursor.getString(0)));
        products.setname_product(cursor.getString(1));
        products.setPrice(cursor.getString(2));
        products.setImage_url(cursor.getString(3));
        products.setDiscont(cursor.getString(4));
        products.setAll_qty(cursor.getString(5));
        products.setQty(cursor.getString(6));

        Log.d("getproduct("+id+")", products.toString());

        // 5. return book
        return products;
    }

    /////////////////////////////
    public product selectProduct(String name){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();



        // 2. build query
        Cursor cursor =
                db.query(TABLE_PRODUCT, // a. table
                        COLUMNS, // b. column names
                        " name_product = ?", // c. selections
                        new String[] { String.valueOf(name) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        product products = new product();
        products.setId(Integer.parseInt(cursor.getString(0)));
        products.setname_product(cursor.getString(1));
        products.setPrice(cursor.getString(2));
        products.setImage_url(cursor.getString(3));
        products.setDiscont(cursor.getString(4));
        products.setAll_qty(cursor.getString(5));
        products.setQty(cursor.getString(6));

        Log.d("getproduct("+name+")", products.toString());

        // 5. return book
        return products;
    }

    // Get All Books
    public List<product> getAllProduct() {
        List<product> products = new LinkedList<product>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_PRODUCT;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        product productses = null;
        if (cursor.moveToFirst()) {
            do {
                productses = new product();
                productses.setId(Integer.parseInt(cursor.getString(0)));
                productses.setname_product(cursor.getString(1));
                productses.setPrice(cursor.getString(2));
                productses.setImage_url(cursor.getString(3));
                productses.setDiscont(cursor.getString(4));
                productses.setAll_qty(cursor.getString(5));
                productses.setQty(cursor.getString(6));

                // Add book to books
                products.add(productses);
            } while (cursor.moveToNext());
        }

        Log.d("getAllproduct()", products.toString());

        // return books
        return products;
    }

    // Updating single book
    public int updateProduct(product products) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("name_product", products.getName_product()); // get title
        values.put("price", products.getPrice()); // get author
        values.put("image_url", products.getImage_url()); // get author
        values.put("discount", products.getDiscont());
        values.put("all_qty", products.getAll_qty()); // get author
        values.put("qty", products.getQty()); // get author

        // 3. updating row
        int i = db.update(TABLE_PRODUCT, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(products.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single book
    public void deleteProduct(product products) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_PRODUCT,
                KEY_ID+" = ?",
                new String[] { String.valueOf(products.getId()) });

        // 3. close
        db.close();

        Log.d("deleteproduct", products.toString());

    }

}
