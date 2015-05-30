package com.ceosilvajr.app.onlineorderingapp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ceosilvajr.app.onlineorderingapp.CheckoutActivity;
import com.ceosilvajr.app.onlineorderingapp.R;
import com.ceosilvajr.app.onlineorderingapp.adapters.ItemAdapter;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class SelectItemsActivityFragment extends Fragment {

    @InjectView(R.id.swipyrefreshlayout)
    SwipyRefreshLayout mSwipy;

    @InjectView(R.id.lv_items)
    ListView mLVItems;

    private Activity mActivity;

    private ItemAdapter mAdtItems;

    public SelectItemsActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_items, container, false);
        ButterKnife.inject(this, view);

        mActivity = getActivity();

        mAdtItems = new ItemAdapter(mActivity, null);
        mLVItems.setAdapter(mAdtItems);
        mSwipy.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection swipyRefreshLayoutDirection) {
                mSwipy.setRefreshing(false);
            }
        });

        return view;
    }

    @OnClick(R.id.btn_checkout)
    void onCheckoutBtnClicked() {
        Intent intent = new Intent(mActivity, CheckoutActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
