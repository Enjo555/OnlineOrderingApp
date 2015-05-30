package com.ceosilvajr.app.onlineorderingapp.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ceosilvajr.app.onlineorderingapp.R;
import com.ceosilvajr.app.onlineorderingapp.SelectItemsActivity;
import com.ceosilvajr.app.onlineorderingapp.managers.ShoppingCartManager;
import com.ceosilvajr.app.onlineorderingapp.objects.ShoppingCart;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class CheckoutActivityFragment extends Fragment {

    @InjectView(R.id.tv_final_price)
    TextView mTVPrice;

    @InjectView(R.id.rb_credit_debit)
    RadioButton mRBCreditDebit;
    @InjectView(R.id.rb_paypal)
    RadioButton mRBPaypal;
    @InjectView(R.id.rb_credit_card)
    RadioButton mRBCreditCard;
    @InjectView(R.id.rb_cash)
    RadioButton mRBCash;

    private Activity mContext;

    public CheckoutActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);
        ButterKnife.inject(this, view);

        mContext = getActivity();
        ShoppingCart shoppingCart = ShoppingCartManager.get(mContext);
        mTVPrice.setText("PHP " + shoppingCart.getTotalPrice());

        return view;
    }

    @OnClick({R.id.rb_cash, R.id.rb_credit_card, R.id.rb_credit_debit, R.id.rb_paypal})
    void radbuttonClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_cash:
                mRBCash.setChecked(true);
                mRBCreditCard.setChecked(false);
                mRBCreditDebit.setChecked(false);
                mRBPaypal.setChecked(false);
                break;
            case R.id.rb_credit_debit:
                mRBCash.setChecked(false);
                mRBCreditCard.setChecked(false);
                mRBCreditDebit.setChecked(true);
                mRBPaypal.setChecked(false);
                break;
            case R.id.rb_credit_card:
                mRBCash.setChecked(false);
                mRBCreditCard.setChecked(true);
                mRBCreditDebit.setChecked(false);
                mRBPaypal.setChecked(false);
                break;
            case R.id.rb_paypal:
                mRBCash.setChecked(false);
                mRBCreditCard.setChecked(false);
                mRBCreditDebit.setChecked(false);
                mRBPaypal.setChecked(true);
                break;
            default:
                mRBCash.setChecked(true);
                mRBCreditCard.setChecked(false);
                mRBCreditDebit.setChecked(false);
                mRBPaypal.setChecked(false);
                break;
        }
    }

    @OnClick(R.id.btn_continue)
    void continueBtnClicked() {
        final AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
        alertDialog.setTitle(getString(R.string.app_name));
        alertDialog.setMessage(mContext.getString(R.string.dialog_message));
        alertDialog.setCancelable(false);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, mContext.getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO
                alertDialog.dismiss();
                new LoadingTask().execute();
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, mContext.getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private class LoadingTask extends AsyncTask {

        private ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(mContext);
            pd.setCancelable(false);
            pd.setMessage(mContext.getString(R.string.payment_message));
            pd.show();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            pd.dismiss();
            successAlert();
            ShoppingCartManager.delete(mContext);
        }
    }

    private void successAlert() {
        final AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
        alertDialog.setTitle(getString(R.string.app_name));
        alertDialog.setMessage(mContext.getString(R.string.thank_you));
        alertDialog.setCancelable(false);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, mContext.getString(R.string.done), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
                Intent intent = new Intent(mContext, SelectItemsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        alertDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
