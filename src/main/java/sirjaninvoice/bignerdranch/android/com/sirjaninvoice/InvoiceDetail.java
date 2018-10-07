package sirjaninvoice.bignerdranch.android.com.sirjaninvoice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InvoiceDetail {

    private static InvoiceDetail sInvoiceDetail;

    private Context invoiceContext;
    private SQLiteDatabase invoiceDatabase;
    public static InvoiceDetail get(Context context) {
        if (sInvoiceDetail == null) {
            sInvoiceDetail = new InvoiceDetail(context);
        }        return sInvoiceDetail;
    }
    private InvoiceDetail(Context context) {
        invoiceContext = context.getApplicationContext();
        invoiceDatabase = new InvoiceBaseHelper(invoiceContext).getWritableDatabase();

        for (int i = 0; i < 4; i++) {
            Invoice invoice = new Invoice();
            invoice.setTitle("home  " + i);
            invoice.setSolved(i % 2 == 0);
        }

    }
    public void addInvoice(Invoice i) {
        ContentValues values = getContentValues(i);
        invoiceDatabase.insert(InvoiceDatabase.InvoiceTable.NAME, null, values);

    }
    public void helpInvoice(Help h)
    {

    }
    public List<Invoice> getInvoices() {

        List<Invoice> invoices = new ArrayList<>();
        InvoiceCursorWrapper cursor = queryInvoices(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                invoices.add(cursor.getInvoice());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return invoices;
    }

    public Invoice getInvoices(UUID id) {

        InvoiceCursorWrapper cursor = queryInvoices(
                InvoiceDatabase.InvoiceTable.Columns.UUID + " = ?",
                new String[] { id.toString() }
        );  try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getInvoice();
        } finally {
            cursor.close();    }
    }
    public File getPhotoFile(Invoice invoice) {
        File filesDir = invoiceContext.getFilesDir();
        return new File(filesDir, invoice.getImageFilename());
    }


    public void updateInvoice(Invoice invoice) {
        String uuidString = invoice.getId().toString();
        ContentValues values = getContentValues(invoice);
        invoiceDatabase.update(InvoiceDatabase.InvoiceTable.NAME, values,
                InvoiceDatabase.InvoiceTable.Columns.UUID + " = ?",
                new String[] { uuidString });
    }
    private InvoiceCursorWrapper queryInvoices(String whereClause, String[] whereArgs) {
        Cursor cursor = invoiceDatabase.query(            InvoiceDatabase.InvoiceTable.NAME,            null,
                // columns - null selects all columns
                whereClause,
                whereArgs,            null,
                // groupBy
                null,
                // having
                null
                // orderBy
        );
        return new InvoiceCursorWrapper(cursor);
    }
    private static ContentValues getContentValues(Invoice invoice) {
        ContentValues values = new ContentValues();
        values.put(InvoiceDatabase.InvoiceTable.Columns.UUID, invoice.getId().toString());
        values.put(InvoiceDatabase.InvoiceTable.Columns.TITLE, invoice.getTitle());
        values.put(InvoiceDatabase.InvoiceTable.Columns.DATE, invoice.getDate().getTime());
        values.put(InvoiceDatabase.InvoiceTable.Columns.COMMENT, invoice.getComment());
        values.put(InvoiceDatabase.InvoiceTable.Columns.SHOP, invoice.getShopName());
        values.put(InvoiceDatabase.InvoiceTable.Columns.SOLVED, invoice.isSolved() ? 1 : 0);
        return values;    }

}

