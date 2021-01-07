package chivas.example.com.mylazyviewpager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonRecycleAdapter<E, H extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<H> {

    protected List<E> mList;
    protected Context mContext;
    protected LayoutInflater mInflater;

    public CommonRecycleAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        mList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    /**
     * 添加数据列表到列表头部
     */
    public void addListAtStart(final List<E> list) {
        for (E e : list) {
            if (!mList.contains(e)) {
                mList.add(0, e);
                notifyDataSetChanged();
            }
        }
    }

    public void replaceBean(final int position, final E e) {
        mList.set(position, e);
        notifyDataSetChanged();
    }

    public void replaceBean(E e) {
        int index = -1;
        for (int i = 0; i < mList.size(); i++) {
            if (e.equals(mList.get(i))) {
                index = i;
                break;
            }
        }
        if (index == -1)
            return;
        replaceBean(index, e);
    }

    /**
     * 添加数据列表到列表尾部
     */
    public void addListAtEnd(final List<E> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 添加单个元素到列表头
     */
    public void addListBeanAtStart(final E e) {
        if (e != null && !mList.contains(e)) {
            mList.add(0, e);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加单个元素到列表尾
     */
    public void addListBeanAtEnd(final E e) {
        mList.add(e);
        notifyDataSetChanged();
    }

    /**
     * 替换ListView数据
     */
    public void replaceList(final List<E> list) {
        if (list == null)
            return;
        mList = list;
        notifyDataSetChanged();
    }

    /**
     * 删除ListView所有数据
     */
    public void removeAll() {
        if (mList != null) {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * 删除ListView指定位置的数据
     */
    public void remove(final E e) {
        if (e != null) {
            mList.remove(e);
            notifyDataSetChanged();
        }
    }

    /**
     * 删除ListView指定位置的数据
     */
    public void remove(final int position) {
        if (position >= 0 && position <= mList.size()) {
            mList.remove(position);
            notifyDataSetChanged();
        }
    }

    public void update(final E e) {
        for (int i = 0; i < mList.size(); i++) {
            E o = mList.get(i);
            if (o.equals(e)) {
                mList.set(i, e);
                notifyDataSetChanged();
            }
        }
    }

    /**
     * 在指定位置添加数据
     */
    public void addAtPosition(final E e, final int position) {
        if (e != null) {
            mList.add(position, e);
            notifyDataSetChanged();
        }
    }

    public List<E> getAllItems() {
        return mList;
    }
}
