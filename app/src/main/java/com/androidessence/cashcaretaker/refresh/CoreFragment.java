package com.androidessence.cashcaretaker.refresh;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.androidessence.utility.refresh.User;

/**
 * Core fragment class.
 *
 * Created by adam.mcneilly on 11/23/16.
 */
public abstract class CoreFragment extends Fragment {
    protected void showFragment(CoreFragment fragment) {
        ((CoreActivity)getActivity()).showFragment(fragment);
    }

    protected User getCurrentUser() {
        return ((CoreActivity)getActivity()).currentUser;
    }

    protected void updateUser() {
        ((CoreActivity)getActivity()).updateUser();
    }

    public abstract String getFragmentTag();
}
