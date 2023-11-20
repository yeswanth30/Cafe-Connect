package com.coffeeshop.DBHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.coffeeshop.models.history;
import com.coffeeshop.models.home;
import com.coffeeshop.models.payment;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "coffeeshop";
    private static final int DB_VERSION = 5;
    private static final String TABLE_SIGNUP = "signup";
    private static final String SIGNUP_CID_COL = "cid";
    private static final String PHONE_COL = "phone";
    private static final String DOB_COL = "dob";
    private static final String NAME_COL = "name";
    private static final String ADDRESS_COL = "address";
    private static final String EMAIL_COL = "email";
    private static final String PASSWORD_COL = "password";




    private static final String TABLE_HOME = "home";
    private static final String ITEMNAME_COL = "itemname";
    private static final String PRICE_COL = "price";
    private static final String QUANTITY_COL = "quantity";
    private static final String HOMEID_COL = "homeid";
    private static final String PHOTO_COL = "photo";





    private static final String TABLE_PAYMENT = "payment";
    private static final String PID_COL = "pid";
    private static final String PAYMENT_CID_COL = "cid";
    private static final String PAYMENT_HOMEID_COL = "homeid";
    private static final String AMOUNT_COL = "amount";
    private static final String MODE_COL = "mode";
    private static final String DATE_COL = "date";
    private static final String STATUS_COL = "status";


    private static final String TABLE_ADDRESS = "address";
    private static final String ADDID_COL = "addid";
    private static final String LINE1_COL = "line1";
    private static final String LINE2_COL = "line2";
    private static final String COUNTRY_COL = "country";
    private static final String CODE_COL = "code";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_SIGNUP + " ("
                + SIGNUP_CID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + PHONE_COL + " TEXT,"
                + DOB_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + PASSWORD_COL + " TEXT,"
                + ADDRESS_COL + " TEXT)";
        db.execSQL(query);

        String query1 = "CREATE TABLE " + TABLE_HOME + " ("
                + HOMEID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ITEMNAME_COL + " TEXT,"
                + PHOTO_COL + " BLOB, "
                + PRICE_COL + " TEXT,"
                + QUANTITY_COL + " TEXT)";
        db.execSQL(query1);

        String query2 = "CREATE TABLE " + TABLE_PAYMENT + " ("
                + PID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PAYMENT_CID_COL + " INTEGER,"
                + PAYMENT_HOMEID_COL + " INTEGER,"
                + AMOUNT_COL + " TEXT,"
                + MODE_COL + " TEXT,"
                + DATE_COL + " TEXT,"
                + STATUS_COL + " TEXT)";
        db.execSQL(query2);

        String query3 = "CREATE TABLE " + TABLE_ADDRESS + " ("
                + ADDID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LINE1_COL + " TEXT,"
                + LINE2_COL + " TEXT,"
                + COUNTRY_COL + " TEXT,"
                + CODE_COL + " TEXT)";
        db.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SIGNUP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDRESS);
        onCreate(db);
    }

    public void addNewUser(String CID_COL, String NAME_COL, String PHONE_COL, String DOB_COL, String EMAIL_COL, String PASSWORD_COL,
                           String ADDRESS_COL) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(CID_COL, CID_COL);
        values.put(NAME_COL, NAME_COL);
        values.put(PHONE_COL, PHONE_COL);
        values.put(DOB_COL, DOB_COL);
        values.put(EMAIL_COL, EMAIL_COL);
        values.put(PASSWORD_COL, PASSWORD_COL);
        values.put(ADDRESS_COL, ADDRESS_COL);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_SIGNUP, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    public void signup(String name, String phone, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();
        values.put(NAME_COL, name);
        values.put(PHONE_COL, phone);
        values.put(EMAIL_COL, email);
        values.put(PASSWORD_COL, password);
        db.insert(TABLE_SIGNUP, null, values);
        db.close();
    }

    public String  login(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String id = null;
        String sql = " SELECT * FROM signup WHERE email = '" + email + "'  AND password = '" + password + "' ;";
        Cursor cursor = db.rawQuery(sql, null);
        //  if(cursor.getCount() > 0) {
        if(cursor.moveToFirst()) {
            id = cursor.getString(0);
        }
      return id;
    }



//    public Cursor home(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = " SELECT * FROM home " ;
//        Cursor res = db.rawQuery(query,null);
//        return res;
//    }

    public List<home> gethomeList() {
        String sql = "select * from " + TABLE_HOME;
        SQLiteDatabase SQLiteDatabase = this.getReadableDatabase();
        List<home> storeproduct = new ArrayList<>();
        Cursor cursor = SQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("homeid"));
                String itemname = cursor.getString(1);
                String price = cursor.getString(3);
                String photo = cursor.getString(2);
                storeproduct.add(new home(id,itemname, price, photo));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeproduct;
    }


    public void home(home homes) {
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        values.put(ITEMNAME_COL, homes.getItemname());
        values.put(PRICE_COL, homes.getPrice());
        values.put(PHOTO_COL, homes.getPhoto());
        db.insert(TABLE_HOME, null, values);
        db.close();
    }

    public void payment(String cid,Integer homeid,double amt, String mode, String date, String status) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(PID_COL, homes.getId());
//        values.put(PID_COL, id);
        //values.put(PAYMENT_CID_COL, cid);
       // values.put(PAYMENT_HOMEID_COL, homeid);
        Log.e("cid",cid);
        values.put(MODE_COL, mode);
        values.put(DATE_COL, date);
        values.put(STATUS_COL, status);
        values.put(AMOUNT_COL, amt);

        String whereClause = PAYMENT_CID_COL + " = ? ";
        String[] whereArgs = new String[]{cid};
        db.update(TABLE_PAYMENT, values, whereClause, whereArgs);
        db.close();
//        db.insert(TABLE_PAYMENT, null, values);
//        db.close();
    }

//    public List<payment> getpaymentList() {
//        String sql = "SELECT * FROM home INNER JOIN payment ON payment.homeid = home.homeid ";
//        SQLiteDatabase SQLiteDatabase = this.getReadableDatabase();
//        List<payment> storepayment = new ArrayList<>();
//        Cursor cursor = SQLiteDatabase.rawQuery(sql, null);
//        if (cursor.moveToFirst()) {
//            do {
//                int pid = cursor.getColumnIndexOrThrow("pid");
//                int cid = cursor.getColumnIndexOrThrow("cid");
//                int homeid = cursor.getColumnIndexOrThrow("homeid");
//                int amount = cursor.getInt(3);
//                String mode = cursor.getString(4);
//                String date = cursor.getString(5);
//                String status = cursor.getString(6);
//
//                storepayment.add(new payment(pid, cid, homeid, amount, mode, date, status));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return storepayment;
//    }

    public List<home> getallcart(String cid) {
        SQLiteDatabase SQLiteDatabase = this.getReadableDatabase();
        String sql = " SELECT home.* FROM home INNER JOIN payment ON payment.homeid = home.homeid " + "WHERE cid = '"+cid+"'; ";       //   String sql = "select * from " + TABLE_PAYMENT;

        List<home> returnproducts = new ArrayList<>();
        Cursor cursor = SQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
//                int nameid = cursor.getColumnIndexOrThrow("itemname");
//                int priceid = cursor.getColumnIndexOrThrow("price");
                String itemname = cursor.getString(1);
                String price = cursor.getString(3);
                Log.e("database data",itemname+price);
                //  String imageurl = cursor.getBlob(4);
                returnproducts.add(new home(itemname, price));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return returnproducts;
    }

    public List<history> getallhistory(String cid) {
        SQLiteDatabase SQLiteDatabase = this.getReadableDatabase();
        String sql = " SELECT home.* FROM home INNER JOIN payment ON payment.homeid = home.homeid WHERE payment.cid = '"+ cid +"'; " ;        //   String sql = "select * from " + TABLE_PAYMENT;

        List<history> returnhistory = new ArrayList<>();
        Cursor cursor = SQLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
//                int nameid = cursor.getColumnIndexOrThrow("itemname");
//                int priceid = cursor.getColumnIndexOrThrow("price");
//                String itemname = cursor.getString(1);
                Integer price = cursor.getInt(3);
             //   Log.e("database data",price);
                //  String imageurl = cursor.getBlob(4);
                returnhistory.add(new history(price));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return returnhistory;
    }
        public void pay(Integer ids,String cid) {
            SQLiteDatabase db = this.getWritableDatabase();

            // on below line we are creating a
            // variable for content values.
            ContentValues values = new ContentValues();

            values.put(PAYMENT_CID_COL, cid);
            values.put(HOMEID_COL, ids);
            db.insert(TABLE_PAYMENT, null, values);
            db.close();
        }

    public void pays(Integer Amount,String cid) {
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        values.put(PAYMENT_CID_COL, cid);
        values.put(AMOUNT_COL, Amount);
        db.insert(TABLE_PAYMENT, null, values);
        db.close();
    }
}
