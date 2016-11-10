package com.zejian.emotionkeyboard.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zejian.emotionkeyboard.R;
import com.zejian.emotionkeyboard.fragment.EmotionMainFragment;

/**
 * Created by zejian
 * Time  16/1/11 下午4:18
 * Email shinezejian@163.com
 * Description:主体内容为ListView
 */
public class ListViewBarEditActivity extends AppCompatActivity implements EmotionMainFragment.EmotionShowInterface{
    private TextView contentView;
    private LinearLayout linearLayout;
//    private ListView listView;
    private EmotionMainFragment emotionMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_listview_bar_edit);
        initView();
        initListentener();
        initDatas();
    }

    /**
     * 初始化控件
     */
    private void initView()
    {
//        listView= (ListView) findViewById(R.id.listview);
        linearLayout= (LinearLayout) findViewById(R.id.contentLay);
        contentView= (TextView) findViewById(R.id.view_content);
    }

    /**
     * 初始化监听器
     */
    public void initListentener(){
        EmotionMainFragment.setEmotionShowInterface(this);

    }

    /**
     * 初始化布局数据
     */
    private void initDatas(){
        initEmotionMainFragment();
    }

    /**
     * 初始化表情面板
     */
    public void initEmotionMainFragment(){
        //构建传递参数
        Bundle bundle = new Bundle();
        //绑定主内容编辑框
        bundle.putBoolean(EmotionMainFragment.BIND_TO_EDITTEXT,true);
        //隐藏控件
        bundle.putBoolean(EmotionMainFragment.HIDE_BAR_EDITTEXT_AND_BTN,false);
        //替换fragment
        //创建修改实例
        emotionMainFragment =EmotionMainFragment.newInstance(EmotionMainFragment.class,bundle);
        emotionMainFragment.bindToContentView(linearLayout);
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        // Replace whatever is in thefragment_container view with this fragment,
        // and add the transaction to the backstack
        transaction.replace(R.id.fl_emotionview_main,emotionMainFragment);
        transaction.addToBackStack(null);
        //提交修改
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        /**
         * 判断是否拦截返回键操作
         */
        if (!emotionMainFragment.isInterceptBackPress()) {
            super.onBackPressed();
        }
    }

    @Override
    public void setEmotionShow(Editable emotionShow) {
        contentView.setText(emotionShow);
    }
}
