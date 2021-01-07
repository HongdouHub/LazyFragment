package chivas.example.com.mylazyviewpager.simple;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import chivas.example.com.mylazyviewpager.R;
import chivas.example.com.mylazyviewpager.ThreadPoolManager;
import chivas.example.com.mylazyviewpager.adapter.TestViewPagerPopulateLazyAdapter;

/**
 * Created by Administrator on 2021/1/7.
 */
public class TestViewPagerPopulateLazyFragment extends AbstractSimpleLazyLifeCycleFragment
        implements TestViewPagerPopulateLazyAdapter.OnItemClickListener {

    private TextView textView;
    private RecyclerView rvMain;
    private TestViewPagerPopulateLazyAdapter mAdapter;

//    private CountDownTimer countDownTimer;
    private Handler handler = new Handler();

    public static TestViewPagerPopulateLazyFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(INTENT_INT_INDEX, position);
        TestViewPagerPopulateLazyFragment fragment = new TestViewPagerPopulateLazyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fra_view_pager_populate_lazy;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        rvMain = (RecyclerView) view.findViewById(R.id.rvMain);
        textView = (TextView) view.findViewById(R.id.tv_loading);
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getActivity(), "position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        mAdapter = new TestViewPagerPopulateLazyAdapter(getActivity());
        mAdapter.setOnItemClickListener(this);

        rvMain.setAdapter(mAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //禁用滑动
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        rvMain.setLayoutManager(linearLayoutManager);
        rvMain.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void onFragmentResume() {
        super.onFragmentResume();

        ThreadPoolManager.getInstance().scheduleDelay(new Runnable() {
            @Override
            public void run() {
                handler.post(updateRunnable);
            }
        }, String.valueOf(tabIndex), 50, TimeUnit.MILLISECONDS);

        // millisInFuture: 倒计时的总时长
        // countDownInterval：每次的间隔时间  单位都是毫秒
//        countDownTimer = new CountDownTimer(1000, 100) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//
//            }
//
//            @Override
//            public void onFinish() {
//                handler.post(updateRunnable);
//            }
//        };
//        countDownTimer.start();
    }

    @Override
    protected void onFragmentPause() {
        super.onFragmentPause();
        handler.removeMessages(0);
//        countDownTimer.cancel();
        ThreadPoolManager.getInstance().cancelTask(String.valueOf(tabIndex), true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        if (countDownTimer != null) {
//            countDownTimer.cancel();
//        }
        ThreadPoolManager.getInstance().cancelTask(String.valueOf(tabIndex), true);
    }

    private Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
            textView.setVisibility(View.GONE);

            List<String> data = new ArrayList<>(200);
            for (int i = 0; i < 200; i++) {
                data.add("联系人:" + i);
            }
            mAdapter.replaceList(data);

            log(tabIndex + " fragment " + "update");

            //模拟耗时工作
//            try {
//                Thread.sleep(40);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    };
}
