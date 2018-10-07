package sirjaninvoice.bignerdranch.android.com.sirjaninvoice;

import android.support.v4.app.Fragment;

public class InvoiceListActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new InvoiceListFragment();
    }
}
