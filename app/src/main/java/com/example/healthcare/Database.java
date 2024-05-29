package com.example.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "create table users(username text,email text, password text)";
        sqLiteDatabase.execSQL(qry1);

        String qry2 = "create table cart(username text,product text,price float,otype text)";
        sqLiteDatabase.execSQL(qry2);

        String qry3 = "create table orderplace(username text,fullname text,address text,contactno text,pincode int,date text,time text,amount float,otype text)";
        sqLiteDatabase.execSQL(qry3);

        String qry4 = "create table orderdetails(ordernumber TEXT PRIMARY KEY, payment_status TEXT)";
        sqLiteDatabase.execSQL(qry4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int ii) {

    }

    public void register(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

    public int login(String username, String password) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username=? and password=?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        return result;
    }

    public void addCart(String username, String product, float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("product", product);
        cv.put("price", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart", null, cv);
        db.close();

    }

    public int checkCart(String username, String product) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where username = ? and product = ?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        db.close();
        return result;
    }

    public void removeCart(String username, String otype) {
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart", "username=? and otype=?", str);
        db.close();
    }

    public ArrayList getCartData(String username, String otype) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        Cursor c = db.rawQuery("select * from cart where username = ? and otype = ?", str);
        if (c.moveToFirst()) {
            do {
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product + "$" + price);
            } while (c.moveToNext());
        }
        db.close();
        return arr;
    }

    public void addOrder(String username, String fullname, String address, String contact, int pincode, String date, String time, float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("fullname", fullname);
        cv.put("address", address);
        cv.put("contactno", contact);
        cv.put("pincode", pincode);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("amount", price);
        cv.put("otype", otype);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace", null, cv);
        db.close();
    }


    public void insertOrderDetails(String orderNumber, String paymentStatus) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ordernumber", orderNumber);
        cv.put("payment_status", paymentStatus);
        db.insert("orderdetails", null, cv);
        db.close();
    }

    public void updateOrderPaymentStatus(String orderNumber, String paymentStatus) {
        ContentValues cv = new ContentValues();
        cv.put("payment_status", paymentStatus);
        SQLiteDatabase db = getWritableDatabase();

        String[] whereArgs = new String[]{orderNumber};
        Log.d("od number", orderNumber);
        int update;

        try {
            update = db.update("orderdetails", cv, "ordernumber = ?", whereArgs);
            Log.e("update done", "Paid update done");
        } catch (Exception e) {
            Log.e("Error", "Error updating payment status: " + e.getMessage());
            update = -1; // Set update to a negative value to indicate an error
        } finally {
            db.close();
        }

        if (update > 0) {
            Log.d("Debug", "Yes update");
        } else {
            Log.d("Debug", "Not update");
        }
    }

    public ArrayList getOrderData(String username) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = username;
        Cursor c = db.rawQuery("select * from orderplace where username = ?", str);
        if (c.moveToFirst()) {
            do {
                arr.add(c.getString(1) + "$" + c.getString(2) + "$" + c.getString(3) + "$" + c.getString(4) + "$" + c.getString(5) + "$" + c.getString(6) + "$" + c.getString(7) + "$" + c.getString(8));
            } while (c.moveToNext());
        }
        db.close();
        return arr;
    }

    public String getOrderPaymentStatus(String orderNumber) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {"payment_status"};
        String selection = "ordernumber = ?";
        String[] selectionArgs = {orderNumber};

        Cursor cursor = db.query("orderdetails", columns, selection, selectionArgs, null, null, null);

        String paymentStatus = "";
        if (cursor.moveToFirst()) {
            int paymentStatusColumnIndex = cursor.getColumnIndex("payment_status");
            if (paymentStatusColumnIndex != -1) {
                paymentStatus = cursor.getString(paymentStatusColumnIndex);
            }
        }
        cursor.close();
        db.close();

        return paymentStatus;
    }


    public int checkAppointmentExists(String username, String fullname, String address, String contact, String date, String time) {
        int result = 0;
        String str[] = new String[6];
        str[0] = username;
        str[1] = fullname;
        str[2] = address;
        str[3] = contact;
        str[4] = date;
        str[5] = time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from orderplace where username = ? and fullname = ? and address = ? and contactno = ? and date = ? and time = ?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        db.close();
        return result;
    }
}