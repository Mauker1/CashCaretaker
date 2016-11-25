package com.androidessence.cashcaretaker.refresh;

import android.support.v4.app.Fragment;

/**
 * Core fragment class.
 *
 * Created by adam.mcneilly on 11/23/16.
 */
public abstract class CoreFragment extends Fragment {
    protected void showFragment(CoreFragment fragment) {
        ((CoreActivity)getActivity()).showFragment(fragment);
    }

    public abstract String getFragmentTag();
}
