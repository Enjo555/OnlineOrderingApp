package com.ceosilvajr.app.onlineorderingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.ceosilvajr.app.onlineorderingapp.R;
import com.ceosilvajr.app.onlineorderingapp.managers.ShoppingCartManager;
import com.ceosilvajr.app.onlineorderingapp.objects.ShoppingCart;
import com.ceosilvajr.app.onlineorderingapp.objects.ZaloraItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ceosilvajr on 5/30/15.
 */
public class ItemAdapter extends ArrayAdapter<ZaloraItem> {

    private Context mContext;
    private List<ZaloraItem> mMessages;
    private List<ZaloraItem> mCurrentItems;

    private OnAddClicked mOnAddClicked;

    public ItemAdapter(Context context, List<ZaloraItem> objects, OnAddClicked onAddClicked) {
        super(context, 0, objects);
        this.mContext = context;
        this.mMessages = getDummyData();
        this.mCurrentItems = new ArrayList<>();
        this.mOnAddClicked = onAddClicked;
    }

    static class ViewHolder {
        @InjectView(R.id.name)
        TextView name;
        @InjectView(R.id.description)
        TextView description;

        @InjectView(R.id.image_item)
        ImageView imageItem;
        @InjectView(R.id.price)
        TextView price;

        @InjectView(R.id.switch_item)
        Switch mSwItem;

//        @InjectView(R.id.edt_quantity)
//        EditText mEdtQuantity;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;

        View view = convertView;
        final ViewHolder viewHolder;

        if (convertView != null) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            LayoutInflater layoutInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.container_items,
                    parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        final ZaloraItem zaloraItem = mMessages.get(position);
        viewHolder.name.setText(zaloraItem.getName());
        viewHolder.description.setText(zaloraItem.getDescription());
        viewHolder.imageItem.setImageResource(zaloraItem.getDrawableId());
        viewHolder.price.setText("PHP " + zaloraItem.getPrice());

        viewHolder.mSwItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //int quantity = Integer.valueOf(viewHolder.mEdtQuantity.getText().toString());
                    //double totalPrice = quantity * zaloraItem.getPrice();
                    mCurrentItems.add(zaloraItem);
                } else {
                    mCurrentItems.remove(zaloraItem);
                }
                //Add to cart
                ShoppingCart shoppingCart = new ShoppingCart(getmCurrentItems());
                ShoppingCartManager.save(shoppingCart, mContext);
                mOnAddClicked.add();
            }
        });

        return view;
    }

    @Override
    public int getCount() {
        return mMessages.size();
    }

    @Override
    public ZaloraItem getItem(int position) {
        return mMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<ZaloraItem> getmCurrentItems() {
        return mCurrentItems;
    }

    private List<ZaloraItem> getDummyData() {
        List<ZaloraItem> data = new ArrayList<>();
        data.add(new ZaloraItem("Contrast Side", 529.00, "This tee features contrasting tri-panelled body.", R.drawable.zalora_item_1));
        data.add(new ZaloraItem("Foliage Raw", 599.00, "Amp up your casuals with this cool number by 24:01.", R.drawable.zalora_item_2));
        data.add(new ZaloraItem("Foliage Panel", 549.00, "Get beach-ready with this ultra-cool muscle tee by 24:01.", R.drawable.zalora_item_3));
        return data;
    }

    public interface OnAddClicked {
        void add();
    }
}
