package wizrole.materialdesign.title;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wizrole.materialdesign.R;

/**
 * Created by liushengping on 2018/3/2.
 * 何人执笔？
 */

public class Fragment_one extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one,null);
    }
}
