package chivas.example.com.mylazyviewpager.simple;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2021/1/7.
 */
public abstract class AbstractSimpleLazyLifeCycleFragment extends AbstractSimpleLazyFragment {

    protected static final String INTENT_INT_INDEX = "index";

    protected int tabIndex;

    @Override
    protected void onFragmentFirstVisible() {
        log(tabIndex + " fragment " + "onFragmentFirstVisible");
    }

    @Override
    protected void onFragmentResume() {
        super.onFragmentResume();
        log(tabIndex + " fragment " + "onFragmentResume");
    }

    @Override
    protected void onFragmentPause() {
        super.onFragmentPause();
        log(tabIndex + " fragment " + "onFragmentPause");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Bundle arguments = getArguments();
        if (arguments != null) {
            tabIndex = getArguments().getInt(INTENT_INT_INDEX);
        }
        log(tabIndex + " fragment " + "setUserVisibleHint: " + isVisibleToUser);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        log(tabIndex + " fragment " + "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log(tabIndex + " fragment " + "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        log(tabIndex + " fragment " + "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView(View view) {
        log(tabIndex + " fragment " + "initView");
        Bundle arguments = getArguments();
        if (arguments != null) {
            tabIndex = getArguments().getInt(INTENT_INT_INDEX);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        log(tabIndex + " fragment " + "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        log(tabIndex + " fragment " + "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        log(tabIndex + " fragment " + "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        log(tabIndex + " fragment " + "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        log(tabIndex + " fragment " + "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        log(tabIndex + " fragment " + "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        log(tabIndex + " fragment " + "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        log(tabIndex + " fragment " + "onDetach");
    }
}
