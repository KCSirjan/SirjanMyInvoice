package sirjaninvoice.bignerdranch.android.com.sirjaninvoice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import sirjaninvoice.bignerdranch.android.com.sirjaninvoice.InvoiceDatabase.InvoiceTable;

public class InvoiceBaseHelper  extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "invoiceBase.db";

    public InvoiceBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + InvoiceTable.NAME + "("+" _id integer primary key autoincrement," +
                InvoiceTable.Columns.UUID + "," +
                InvoiceTable.Columns.TITLE + "," +
                InvoiceTable.Columns.DATE + "," +
                InvoiceTable.Columns.COMMENT + "," +
                InvoiceTable.Columns.SHOP + "," +
                InvoiceTable.Columns.SOLVED +
                ")");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
