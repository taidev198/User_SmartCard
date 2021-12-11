package com.example.user.ui.base;

import android.view.View;

public interface OnRecyclerItemClickListener<T> extends BaseRecyclerListener {

    void onItemClicked(View view, long pos, T item);
}
