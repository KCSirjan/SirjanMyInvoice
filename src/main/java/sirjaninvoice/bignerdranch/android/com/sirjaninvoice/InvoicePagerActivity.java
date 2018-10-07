package sirjaninvoice.bignerdranch.android.com.sirjaninvoice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class  InvoicePagerActivity extends AppCompatActivity {
    private static final String EXTRA_INVOICE_ID = "sirjaninvoice.bignerdranch.android.com.sirjaninvoice.invoice_id";
    private ViewPager invoiceViewPager;
    private List<Invoice> invoiceInvoices;
    public static Intent newIntent(Context packageContext, UUID invoiceId) {
        Intent intent = new Intent(packageContext, InvoicePagerActivity.class);
        intent.putExtra(EXTRA_INVOICE_ID, invoiceId);        return intent;
    }
    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_pager);
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_INVOICE_ID);
        invoiceViewPager = (ViewPager) findViewById(R.id.invoice_view_pager);
        invoiceInvoices = InvoiceDetail.get(this).getInvoices();
        FragmentManager fragmentManager = getSupportFragmentManager();
        invoiceViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Invoice invoice = invoiceInvoices.get(position);
                return InvoiceFragment.newInstance(invoice.getId());
            }
            @Override

            public int getCount() {
                return invoiceInvoices.size();
            }
        });

        for (int i = 0; i < invoiceInvoices.size(); i++) {
            if (invoiceInvoices.get(i).getId().equals(crimeId)) {
                invoiceViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
