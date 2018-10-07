package sirjaninvoice.bignerdranch.android.com.sirjaninvoice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class InvoiceListFragment extends Fragment {


    private Button invoiceHelp;
    private RecyclerView invoiceInvoiceRecyclerView;
    private InvoiceAdapter invoiceAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invoice_list, container, false);

        invoiceInvoiceRecyclerView = (RecyclerView) view.findViewById(R.id.invoice_recycler_view);
        invoiceInvoiceRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;    }
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_invoice_list, menu);
        MenuItem help = menu.findItem(R.id.show_help);


    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_invoice:
                Invoice invoice = new Invoice();
                InvoiceDetail.get(getActivity()).addInvoice(invoice);
                Intent intent = InvoicePagerActivity.newIntent(getActivity(), invoice.getId());
                startActivity(intent);
                return true;
            case R.id.show_help:
                invoiceHelp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        } }

    private void updateUI() {
        InvoiceDetail invoiceDetail = InvoiceDetail.get(getActivity());
        List<Invoice> invoices = invoiceDetail.getInvoices();
        if (invoiceAdapter == null) {
            invoiceAdapter = new InvoiceAdapter(invoices);
            invoiceInvoiceRecyclerView.setAdapter(invoiceAdapter);
        } else {
            invoiceAdapter.setInvoices(invoices);
            invoiceAdapter.notifyDataSetChanged();
        }

    }

    /**
     * A view holder class for recycler View
     */
    private class CrimeHolder extends RecyclerView.ViewHolder  implements View.OnClickListener  {
        private TextView invoiceTitleTextView;
        private TextView invoiceDateTextView;
        private TextView invoiceShopTextView;
        private Invoice invoiceInvoice;
        public CrimeHolder(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_invoice, parent, false));
            itemView.setOnClickListener(this);
            invoiceTitleTextView = (TextView) itemView.findViewById(R.id.invoice_title);
            invoiceDateTextView = (TextView) itemView.findViewById(R.id.invoice_date);
            invoiceShopTextView = (TextView) itemView.findViewById(R.id.invoice_shop);
        }
        @Override    public void onClick(View view) {
            Intent intent = InvoicePagerActivity.newIntent(getActivity(), invoiceInvoice.getId());
            startActivity(intent);    }
        public void bind(Invoice invoice) {
            invoiceInvoice = invoice;
            invoiceTitleTextView.setText(invoiceInvoice.getTitle());
            invoiceDateTextView.setText(invoiceInvoice.getDate().toString());
            invoiceShopTextView.setText(invoiceInvoice.getShopName());
        }
    }

    /**
     * end of View Holder
     */

    /**
     *
     * An Adapter for RecyclerView
     */
    private class InvoiceAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Invoice> invoiceInvoices;
        public InvoiceAdapter(List<Invoice> invoices) {
            invoiceInvoices = invoices;        }
        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Invoice invoice = invoiceInvoices.get(position);
            holder.bind(invoice);

        }
        @Override
        public int getItemCount() {
            return
                    invoiceInvoices.size();
        }
        public void setInvoices(List<Invoice> invoices) {
            invoiceInvoices = invoices;    }

    }
    /**
     * End of Adapter
     */

}


