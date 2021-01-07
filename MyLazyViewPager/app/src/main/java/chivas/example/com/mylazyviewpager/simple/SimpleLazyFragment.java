package chivas.example.com.mylazyviewpager.simple;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import chivas.example.com.mylazyviewpager.R;
import chivas.example.com.mylazyviewpager.ThreadPoolManager;

public class SimpleLazyFragment extends AbstractSimpleLazyLifeCycleFragment {

    private ImageView imageView;
    private TextView textView;
//    private CountDownTimer countDownTimer;
    private Handler handler = new Handler();

    public static SimpleLazyFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(INTENT_INT_INDEX, position);
        SimpleLazyFragment fragment = new SimpleLazyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fra_simple_lazy;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        imageView = view.findViewById(R.id.iv_content);
        textView = view.findViewById(R.id.tv_loading);
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
            int id = R.mipmap.a;
            switch (tabIndex - 1) {
                case 1:
                    id = R.mipmap.a;
                    break;
                case 2:
                    id = R.mipmap.b;
                    break;
                case 3:
                    id = R.mipmap.c;
                    break;
                case 4:
                    id = R.mipmap.d;
                    break;
            }
            imageView.setImageResource(id);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setVisibility(View.VISIBLE);
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
