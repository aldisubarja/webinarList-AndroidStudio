package com.example.projectbncc;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ProfileFragment extends Fragment {

    TextView emailView,nameView;
    ImageView backBtn;
    SharedPreferences sp;

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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailView = view.findViewById(R.id.email_view);
        nameView = view.findViewById(R.id.name_view);
        backBtn = view.findViewById(R.id.back_btn);
        sp = getActivity().getSharedPreferences("sp", Context.MODE_PRIVATE);

        String loginKey = sp.getString("login",null);

        emailView.setText(sp.getString(loginKey+" email",null));
        nameView.setText(sp.getString(loginKey+" name",null));

        backBtn.setOnClickListener(toMainMenu);
    }
}