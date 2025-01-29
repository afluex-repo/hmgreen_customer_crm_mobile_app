package com.hm.greencity.customermanagement.Fragment;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hm.greencity.customermanagement.databinding.FragmentKeyTrabsportBinding;



public class KeyTrabsportFragment extends Fragment {
    FragmentKeyTrabsportBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding =FragmentKeyTrabsportBinding.inflate(getLayoutInflater());
       initview();
       onclicklistener();
        return binding.getRoot();

    }

    private void initview() {

    }

    private void onclicklistener() {

    }


}