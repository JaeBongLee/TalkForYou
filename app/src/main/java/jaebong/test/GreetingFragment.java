package jaebong.test;


import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class GreetingFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setContentName("greeting");
        setContentList("Greeting");
        setSoundPool();
        super.onCreateView(inflater, container,savedInstanceState);

        return super.onCreateView(inflater,container,savedInstanceState);
    }

}
