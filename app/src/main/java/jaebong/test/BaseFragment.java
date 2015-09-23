package jaebong.test;

import android.app.Activity;
import android.app.Fragment;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by JaeBong on 15. 9. 2..
 */
public class BaseFragment extends Fragment implements View.OnClickListener{
    private LayoutInflater layoutInflater;

    private TextView textViewKorean;
    private TextView textViewJapanese;
    private TextView textViewChinese;
    private TextView textViewEnglish;

    private ImageView imageViewNext;
    private ImageView imageViewBefore;
    private ImageView imageViewSound;

    private RelativeLayout relativeLayout;

    private Dao dao;

    private int nowContentIdx;
    private ArrayList<Content> contentList;

    private SoundPool soundPool;
    private int soundCorrect;
    private String contentName;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        dao = new Dao(getActivity());
        nowContentIdx = 0;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutInflater = inflater;
        createViewStart(inflater);
        return relativeLayout;
    }

    public void createViewStart(LayoutInflater inflater) {

        setRelativeLayout(inflater);
        setImageViewItems();
        setTextViewItems();
        setNowContent(nowContentIdx);
        this.setSoundPool();
        setOnClickListenerOnViewItems();
    }

    public void setNowContent(int nowContentIdx){
        Content nowContent = contentList.get(nowContentIdx);
        textViewKorean.setText(nowContent.getKorean());
        textViewJapanese.setText(nowContent.getJapanese());
        textViewChinese.setText(nowContent.getChinese());
        textViewEnglish.setText(nowContent.getEnglish());
        setNowSoundResource(nowContent);

    }

    public void setSoundPool(){
        Log.e("test","SoundPoolFunctionStart");
        soundPool = new SoundPool(0, AudioManager.STREAM_MUSIC,0);
        soundCorrect = soundPool.load(getActivity(),getResources().getIdentifier(contentName + "_1", "raw", getActivity().getPackageName()),1);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void setContentName(String contentName){
        this.contentName = contentName;
    }

    public void setTextViewItems(){
        this.textViewKorean = (TextView)relativeLayout.findViewById(getResources().getIdentifier("textView_"+contentName+"_korean","id",getActivity().getPackageName()));
        this.textViewJapanese = (TextView)relativeLayout.findViewById(getResources().getIdentifier("textView_"+contentName+"_japanese","id",getActivity().getPackageName()));
        this.textViewChinese = (TextView)relativeLayout.findViewById(getResources().getIdentifier("textView_"+contentName+"_chinese","id",getActivity().getPackageName()));
        this.textViewEnglish = (TextView)relativeLayout.findViewById(getResources().getIdentifier("textView_"+contentName+"_english","id",getActivity().getPackageName()));


    }

    public void setImageViewItems(){
        this.imageViewBefore = (ImageView)relativeLayout.findViewById(getResources().getIdentifier("imageView_"+contentName+"_left","id",getActivity().getPackageName()));
        this.imageViewNext = (ImageView)relativeLayout.findViewById(getResources().getIdentifier("imageView_"+contentName+"_right","id",getActivity().getPackageName()));
        this.imageViewSound = (ImageView)relativeLayout.findViewById(getResources().getIdentifier("imageView_" + contentName + "_sound", "id", getActivity().getPackageName()));

    }

    public void setRelativeLayout (LayoutInflater inflater){
        Log.e("test","fragment_" + contentName);
        this.relativeLayout = (RelativeLayout)inflater.inflate(getResources().getIdentifier("fragment_" + contentName, "layout", getActivity().getPackageName()),null);
    }

    public void setContentList (String contentName){
        contentList = new ArrayList<Content>();
        contentList = dao.getContentFromTable(contentName);
    }

    public void setOnClickListenerOnViewItems(){
        Log.e("test","setOnClickListenerOnViewItems");
        imageViewSound.setOnClickListener(this);
        imageViewNext.setOnClickListener(this);
        imageViewBefore.setOnClickListener(this);
    }

    public void moveToNextContent(){
        int LAST_IDX_OF_LIST = contentList.size() - 1;

        if(nowContentIdx == (LAST_IDX_OF_LIST)){
            nowContentIdx = -1;
        }

        setNowContent(++nowContentIdx);


    }

    public void moveToPreviousContent(){
        int FIRST_IDX_OF_LIST = 0;

        if(nowContentIdx  == FIRST_IDX_OF_LIST ){
            nowContentIdx = contentList.size();
        }
        setNowContent(--nowContentIdx);



    }

    public void setNowSoundResource(Content nowContent){
        int soundResourceId = getResources().getIdentifier("raw/"+nowContent.getSound(), "raw", getActivity().getPackageName());
        soundCorrect = soundPool.load(getActivity(),soundResourceId,1);
    }


    public void onClick(View v) {

        if(v.getId() == getResources().getIdentifier("imageView_"+contentName+"_right", "id", getActivity().getPackageName())){
            moveToNextContent();
        }else if (v.getId() ==  getResources().getIdentifier("imageView_"+contentName+"_left", "id", getActivity().getPackageName())){
            moveToPreviousContent();
        }else if (v.getId() == getResources().getIdentifier("imageView_"+contentName+"_sound","id",getActivity().getPackageName())){
            soundPool.play(soundCorrect,1.0f,1.0f,0,0,1.0f);
        }else {
            Toast toast = Toast.makeText(getActivity(),"OnClickError",Toast.LENGTH_SHORT);
            toast.show();
        }

    }

}
