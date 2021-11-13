package com.example.projectbncc;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MenuFragment extends Fragment {

    ImageView profileBtn;
    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<Webinar> webinarList;

    View.OnClickListener toProfile = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new ProfileFragment())
                    .addToBackStack(null)
                    .commit();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileBtn = view.findViewById(R.id.profile_btn);
        recyclerView = view.findViewById(R.id.webinar_list);
        database = FirebaseDatabase.getInstance().getReference("webinar");
        webinarList = new ArrayList<>();


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Webinar webinar = dataSnapshot.getValue(Webinar.class);
                    webinarList.add(webinar);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        myAdapter = new MyAdapter(view.getContext(),webinarList);
        recyclerView.setAdapter(myAdapter);

        profileBtn.setOnClickListener(toProfile);
    }
}