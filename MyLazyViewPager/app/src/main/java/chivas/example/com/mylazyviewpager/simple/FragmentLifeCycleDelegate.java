package chivas.example.com.mylazyviewpager.simple;

import android.support.v4.app.Fragment;
import android.util.Log;

public class FragmentLifeCycleDelegate {
    
    private String fragmentName;

    public FragmentLifeCycleDelegate(Fragment fragment) {
        this.fragmentName = fragment.getClass().getSimpleName();
    }

    public void dumpLifeCycle(String method) {
        Log.d("FragmentLifeCycle", "name: " + fragmentName + " -> " + method);
    }
}
