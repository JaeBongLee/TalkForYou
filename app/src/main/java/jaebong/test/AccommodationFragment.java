package jaebong.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AccommodationFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setContentName("accommodation");
        setContentList("Accommodation");
        setSoundPool();
        super.onCreateView(inflater, container,savedInstanceState);

        return super.onCreateView(inflater,container,savedInstanceState);
    }
}
