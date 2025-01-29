package com.hm.greencity.customermanagement.Fragment;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hm.greencity.customermanagement.databinding.FragmentCompanyProfileBinding;



public class CompanyProfileFragment extends Fragment {
   FragmentCompanyProfileBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     binding =FragmentCompanyProfileBinding.inflate(getLayoutInflater());
     initview();
     onclicklistener();
        return binding.getRoot();


    }

    private void initview() {

    }

    private void onclicklistener() {
     binding.owner.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
       if (binding.ownerll.getVisibility() == View.GONE) {
        binding.ownerll.setVisibility(View.VISIBLE);
       } else {
        binding.ownerll.setVisibility(View.GONE);
       }
      }
     });


     binding.ourteam.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
       if (binding.outteamll.getVisibility() == View.GONE) {
        binding.outteamll.setVisibility(View.VISIBLE);
       } else {
        binding.outteamll.setVisibility(View.GONE);
       }
      }
     });

     binding.realestate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
       if (binding.realestatell.getVisibility() == View.GONE) {
        binding.realestatell.setVisibility(View.VISIBLE);
       } else {
        binding.realestatell.setVisibility(View.GONE);
       }
      }
     });
    }


}