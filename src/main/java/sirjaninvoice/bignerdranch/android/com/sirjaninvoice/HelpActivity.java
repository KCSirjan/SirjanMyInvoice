package sirjaninvoice.bignerdranch.android.com.sirjaninvoice;

import android.support.v4.app.Fragment;

public class HelpActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new Help();
    }
}
