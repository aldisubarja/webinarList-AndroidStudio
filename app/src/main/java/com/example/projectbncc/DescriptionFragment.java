package com.example.projectbncc;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class DescriptionFragment extends Fragment {

    TextView longDesc;
    ImageView backBtn;
    String desc;

    View.OnClickListener toMainMenu = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getParentFragmentManager().popBackStack();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_description, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        longDesc = view.findViewById(R.id.desc_long);
        backBtn = view.findViewById(R.id.back_btn);

        getParentFragmentManager().setFragmentResultListener("descReq",requireActivity(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                desc = result.getString("description");
            }
        });

        longDesc.setText(desc);
        backBtn.setOnClickListener(toMainMenu);
    }
}