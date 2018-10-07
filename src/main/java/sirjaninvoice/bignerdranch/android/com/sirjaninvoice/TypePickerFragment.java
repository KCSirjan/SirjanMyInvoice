package sirjaninvoice.bignerdranch.android.com.sirjaninvoice;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;
import java.lang.reflect.Type;

public class TypePickerFragment  extends DialogFragment {
    public static final String EXTRA_TYPE =
            "sirjaninvoice.bignerdranch.android.com.sirjaninvoice.type";
    private static final String ARG_TYPE = "type";
    private RadioGroup invoiceRadioGroup;
    public static TypePickerFragment newInstance(RadioButton type) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TYPE, (Serializable) type);
        TypePickerFragment fragment = new TypePickerFragment();
        fragment.setArguments(args);
        return fragment;    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final RadioButton type = (RadioButton) getArguments().getSerializable(ARG_TYPE);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_type, null);
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.type_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sendResult(Activity.RESULT_OK, type);
                    }
                })
                .create();    }
    private void sendResult(int resultCode, RadioButton type) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TYPE, (Serializable) type);
        getTargetFragment()                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}