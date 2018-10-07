package sirjaninvoice.bignerdranch.android.com.sirjaninvoice;

public class InvoiceDatabase {
    public static final class InvoiceTable {
        public static final String NAME = "invoices";
        public static final class Columns {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
            public static final String COMMENT = "comment";
            public static final String SHOP = "shopName";
        }
    }
}
