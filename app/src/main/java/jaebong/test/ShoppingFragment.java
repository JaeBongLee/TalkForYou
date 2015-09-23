package jaebong.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ShoppingFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setContentName("shopping");
        setContentList("Shopping");
        setSoundPool();
        super.onCreateView(inflater, container,savedInstanceState);

        return super.onCreateView(inflater,container,savedInstanceState);
    }
}
