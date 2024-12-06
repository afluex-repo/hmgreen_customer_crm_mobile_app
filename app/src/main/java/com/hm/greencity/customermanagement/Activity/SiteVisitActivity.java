package com.hm.greencity.customermanagement.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;
import com.hm.greencity.customermanagement.Network.ChatService;
import com.hm.greencity.customermanagement.Network.RetrofitClient;
import com.hm.greencity.customermanagement.R;
import com.hm.greencity.customermanagement.common.BaseActivity;
import com.hm.greencity.customermanagement.common.PreferencesManager;
import com.hm.greencity.customermanagement.databinding.ActivitySiteVisitBinding;
import com.hm.greencity.customermanagement.models.Notes.GetNote.LstNotepad;
import com.hm.greencity.customermanagement.models.ResponseList.LstSite;
import com.hm.greencity.customermanagement.models.Site.ResSite;
import com.hm.greencity.customermanagement.models.SiteVisit.ReqSiteVisit;
import com.hm.greencity.customermanagement.models.SiteVisit.ResSiteVisit;
import com.hm.greencity.customermanagement.models.Team.LstTeam;
import com.hm.greencity.customermanagement.models.Team.ResTeam;
import com.hm.greencity.customermanagement.retrofit.ApiServices;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class SiteVisitActivity extends BaseActivity {

    ActivitySiteVisitBinding binding;
    public ApiServices apiServices;
    private ArrayAdapter<String> adapter;
    private ChatService chatService;
    private List<LstSite> messageList = new ArrayList<>();
    private String selectedSiteId = "";
    private String selectedTeamId = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivitySiteVisitBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        apiServices = RetrofitClient.getApiServices();
        initview();
        onclicklistener();

    }

    @Override
    public void onNoteDelete(LstNotepad note) {

    }

    private void initview() {
        fetchSiteList();
        fetchTeamList();

    }

    private void onclicklistener() {
        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SiteVisitActivity.this,AssociateContaner.class);
                startActivity(intent);
                finish();

            }
        });
        binding.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        SiteVisitActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                binding.date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });
        binding.visitlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SiteVisitActivity.this,SiteVisitorListActivity.class);
                startActivity(intent);
                finish();
            }
        });
        binding.submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }


    private void getData() {
        int userId = Integer.parseInt(PreferencesManager.getInstance(this).getUserId());
        String siteId = selectedSiteId;
        String teamId = selectedTeamId;
        String customerName = binding.customername.getText().toString().trim();
        String date = binding.date.getText().toString().trim();
        String mobileNo = binding.mobilenumber.getText().toString().trim();
        String customerNo = binding.numberofcumtomer.getText().toString().trim();
        if (siteId.isEmpty() || customerName.isEmpty() || date.isEmpty() || mobileNo.isEmpty() || customerNo.isEmpty()) {
            Toast.makeText(SiteVisitActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        ReqSiteVisit reqSiteVisit = new ReqSiteVisit(
                userId,
                Integer.parseInt(siteId),
                customerName,
                date,
                Long.parseLong(mobileNo),
                Integer.parseInt(customerNo),
                teamId
        );
        Call<ResSiteVisit> call = apiServices.savesitevisit(reqSiteVisit);
        call.enqueue(new Callback<ResSiteVisit>() {
            @Override
            public void onResponse(Call<ResSiteVisit> call, Response<ResSiteVisit> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResSiteVisit resSiteVisit = response.body();
                    if (resSiteVisit.getStatus().equals("200")) {
                        Toast.makeText(SiteVisitActivity.this, resSiteVisit.getMessage(), Toast.LENGTH_SHORT).show();
                        binding.submitbtn.setEnabled(false);
                    } else {
                        Toast.makeText(SiteVisitActivity.this, "Error: " + resSiteVisit.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SiteVisitActivity.this, "Response unsuccessful: " + response.message(), Toast.LENGTH_SHORT).show();
                    Log.e("Response Error", response.message());
                }
            }
            @Override
            public void onFailure(Call<ResSiteVisit> call, Throwable t) {
                Toast.makeText(SiteVisitActivity.this, "Request Failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("API Error", t.toString());
            }
        });
    }

 //site drop down
    private void fetchSiteList() {
        ChatService chatService = RetrofitClient.getClient().create(ChatService.class);
        Call<ResSite> call = chatService.site();
        call.enqueue(new Callback<ResSite>() {
            @Override
            public void onResponse(Call<ResSite> call, Response<ResSite> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResSite resSite = response.body();
                    if ("200".equals(resSite.getStatusCode())) {
                        List<LstSite> siteList = resSite.getLstSite();
                        bindSiteListToSpinner(siteList);
                    } else {
                        Toast.makeText(SiteVisitActivity.this, resSite.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SiteVisitActivity.this, "Failed to fetch sites", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResSite> call, Throwable t) {
                Toast.makeText(SiteVisitActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void bindSiteListToSpinner(List<LstSite> siteList) {
        List<String> siteNames = new ArrayList<>();
        final List<LstSite> finalSiteList = siteList;
        for (LstSite site : siteList) {
            if (site != null && site.getSiteName() != null) {
                siteNames.add(site.getSiteName());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, siteNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = findViewById(R.id.Site);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedSiteName = parentView.getItemAtPosition(position).toString();
                for (LstSite site : finalSiteList) {
                    if (site.getSiteName().equals(selectedSiteName)) {
                        selectedSiteId = site.getSiteID();
                        break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

//team
    private void fetchTeamList() {
        ChatService chatService = RetrofitClient.getClient().create(ChatService.class);
        Call<ResTeam> call = chatService.team();
        call.enqueue(new Callback<ResTeam>() {
            @Override
            public void onResponse(Call<ResTeam> call, Response<ResTeam> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResTeam resSite = response.body();
                    if ("200".equals(resSite.getStatus())) {
                        List<LstTeam> siteList = resSite.getLstTeam();
                        bindTeamListToSpinner(siteList);
                    } else {
                        Toast.makeText(SiteVisitActivity.this, resSite.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SiteVisitActivity.this, "Failed to fetch sites", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResTeam> call, Throwable t) {
                Toast.makeText(SiteVisitActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void bindTeamListToSpinner(List<LstTeam> siteList) {
        List<String> siteNames = new ArrayList<>();
        final List<LstTeam> finalSiteList = siteList;
        for (LstTeam site : siteList) {
            if (site != null && site.getTeamName() != null) {
                siteNames.add(site.getTeamName());
            }
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, siteNames);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner1 = findViewById(R.id.team);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedSiteName = parentView.getItemAtPosition(position).toString();
                for (LstTeam site : finalSiteList) {
                    if (site.getTeamName().equals(selectedSiteName)) {
                        selectedTeamId = site.getpK_TeamId();
                        break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }
}
