package com.hm.greencity.customermanagement.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.JsonObject;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.adapters.AdapterAssociateDueInstallment;
import com.hm.greencity.customermanagement.common.NetworkUtils;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.constants.BaseFragment;
import com.hm.greencity.customermanagement.models.AssociateDashboard.ResponseAssociateDashboard;
import com.hm.greencity.customermanagement.models.AssociateDueInstallment.Lstdue;
import com.hm.greencity.customermanagement.models.AssociateDueInstallment.ResponseAssociateDueInstallment;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AssosiateDashboardOld extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.tv_total_downline)
    TextView tvTotalDownline;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.cv_total_dwln)
    CardView cvTotalDwln;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.tv_total_direct)
    TextView tvTotalDirect;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.cv_total_direct)
    CardView cvTotalDirect;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.tv_total_active)
    TextView tvTotalActive;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.cv_my_ledger)
    CardView cvTotalActive;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.tv_total_inactive)
    TextView tvTotalInactive;



    @BindView(R.id.tv_total_payout)
    TextView tvTotalPayout;

    @BindView(R.id.tv_total_advance)
    TextView tvTotalAdvance;

    @BindView(R.id.tv_total_deduct)
    TextView tvTotalDeduct;

    @BindView(R.id.tv_total_balance)
    TextView tvTotalBalance;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @BindView(R.id.tvlegType)
    TextView tvlegType;

    @BindView(R.id.showAll)
    TextView showAll;

    @BindView(R.id.tvlegActive)
    TextView tvlegActive;
    @BindView(R.id.tvLegInactive)
    TextView tvLegInactive;
    @BindView(R.id.totalmembers)
    TextView totalmembers;
    @BindView(R.id.totalbusiness)
    TextView totalbusiness;
    @BindView(R.id.constrOne)
    ConstraintLayout constrOne;
    @BindView(R.id.tvLeft)
    TextView tvLeft;
    @BindView(R.id.total_left)
    TextView totalLeft;
    @BindView(R.id.paid_left)
    TextView paidLeft;
    @BindView(R.id.cf_left)
    TextView cfLeft;
    @BindView(R.id.totalbuis_left)
    TextView totalbuisLeft;
    @BindView(R.id.constrTwo)
    ConstraintLayout constrTwo;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.total_right)
    TextView totalRight;
    @BindView(R.id.paid_right)
    TextView paidRight;
    @BindView(R.id.cf_right)
    TextView cfRight;
    @BindView(R.id.totalbuis_right)
    TextView totalbuisRight;
    @BindView(R.id.constrThree)
    ConstraintLayout constrThree;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.active_total)
    TextView activeTotal;
    @BindView(R.id.inactive_total)
    TextView inactiveTotal;
    @BindView(R.id.totalmembers_total)
    TextView totalmembersTotal;
    @BindView(R.id.totalbuis_total)
    TextView totalbuisTotal;
    @BindView(R.id.text_dueInstallment)
    TextView text_dueInstallment;


    List<Lstdue> lstduesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.associate_dashboard, container, false);
        unbinder = ButterKnife.bind(this, view);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        lstduesAdapter=new ArrayList();
        getData();
        if (NetworkUtils.getConnectivityStatus(context) != 0) {

            AssociateDashBoardTotals();

        } else showMessage(R.string.alert_internet);

        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showAll.getText().toString().equalsIgnoreCase("Show All"))
                {
                    setDueInstallmentAdapter(lstduesAdapter,true);
                    showAll.setText("Show Less");
                }
                else
                {
                    setDueInstallmentAdapter(lstduesAdapter,false);
                    showAll.setText("Show All");
                }
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    private void AssociateDashBoardTotals() {
       // showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("CustomerID", PreferencesManager.getInstance(context).getCustomerID());
//        object.addProperty("CustomerID","24");


        Call<ResponseAssociateDashboard> call = apiServices.AssociateDashBoard(object);
        call.enqueue(new Callback<ResponseAssociateDashboard>() {
            @Override
            public void onResponse(Call<ResponseAssociateDashboard> call, Response<ResponseAssociateDashboard> response) {
                hideLoading();
                try {

                        if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                            tvTotalDownline.setText(response.body().getTotalDownline());
                            tvTotalDirect.setText(response.body().getTotalDirects());
                            tvTotalActive.setText(response.body().getTotalActive());
                            tvTotalInactive.setText(response.body().getTotalInActive());
                            tvTotalPayout.setText(response.body().getTotalPayout());
                            tvTotalAdvance.setText(response.body().getTotalPaidPayout());
                            tvTotalDeduct.setText(response.body().getTotalDeduction());
                            tvTotalBalance.setText(response.body().getPayoutWalletBalance());
                            totalLeft.setText(response.body().getTotalBusinessLeft());
                            totalRight.setText(response.body().getTotalBusinessRight());
                            paidLeft.setText(response.body().getPaidBusinessLeft());
                            paidRight.setText(response.body().getPaidBusinessRight());
                            totalbuisLeft.setText(response.body().getPaidBusinessLeft());
                            totalbuisRight.setText(response.body().getPaidBusinessRight());



                    }else showMessage(response.body().getMessage());
                } catch (Exception e) {
                    showMessage("Server Issue");
                }
            }

            @Override
            public void onFailure(Call<ResponseAssociateDashboard> call, Throwable t) {
                hideLoading();
            }
        });
    }


    public void getData() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("CustomerID", PreferencesManager.getInstance(context).getCustomerID());
//        object.addProperty("CustomerID","24");
        Call<ResponseAssociateDueInstallment> call = apiServices.AssociateDueInstallement(object);
        call.enqueue(new Callback<ResponseAssociateDueInstallment>() {
            @Override
            public void onResponse(Call<ResponseAssociateDueInstallment> call, Response<ResponseAssociateDueInstallment> response) {

                if (response.isSuccessful()) {
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        hideLoading();
                        lstduesAdapter =response.body().getLstduelist();
                        text_dueInstallment.setVisibility(View.VISIBLE);
                        setDueInstallmentAdapter(lstduesAdapter,false);

                    } else showMessage("Record Not Found!");
                    text_dueInstallment.setVisibility(View.VISIBLE);
                    hideLoading();
                }
                else{
                    showMessage(response.body().getMessage());
                    text_dueInstallment.setVisibility(View.GONE);
                }
                hideLoading();

            }

            @Override
            public void onFailure(Call<ResponseAssociateDueInstallment> call, Throwable t) {
                hideLoading();
            }

        });
    }

    private void setDueInstallmentAdapter(List<Lstdue> lstduesAdapter, boolean showAll) {

        AdapterAssociateDueInstallment adapter = new AdapterAssociateDueInstallment(lstduesAdapter, getContext(),showAll);
        recyclerview.setAdapter(adapter);
        text_dueInstallment.setVisibility(View.VISIBLE);

    }
}
