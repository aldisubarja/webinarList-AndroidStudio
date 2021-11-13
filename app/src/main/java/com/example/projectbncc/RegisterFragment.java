package com.example.projectbncc;

import android.app.SharedElementCallback;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class RegisterFragment extends Fragment {

    TextView signInText;
    EditText nameInput,emailInput,passwordInput,confirmPasswordInput;
    Button signUp;
    String name,email,password,confirmPass;
    private FirebaseAuth auth;
    SharedPreferences sp;

    View.OnClickListener toSignIn = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getParentFragmentManager().popBackStack();
        }
    };

    View.OnClickListener register = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            email = emailInput.getText().toString();
            name = nameInput.getText().toString();
            password = passwordInput.getText().toString();
            confirmPass = confirmPasswordInput.getText().toString();
            SharedPreferences.Editor editor = sp.edit();

            if(name.length()==0){
                nameInput.setError("Tidak boleh dikosongkan");
            }
            else if(email.length()==0){
                emailInput.setError("Tidak boleh dikosongkan");
            }
            else if(password.length()==0){
                passwordInput.setError("Tidak boleh dikosongkan");
            }
            else if(confirmPass.length()==0){
                confirmPasswordInput.setError("Tidak boleh dikosongkan");
            }
            else if(name.length()<5){
                nameInput.setError("Nama minimal terdiri dari 5 karakter");
            }
            else if(!email.contains("@") && !email.endsWith(".com")){
                emailInput.setError("Email harus memiliki karakter '@' dan diakhiri dengan \".com\"");
            }
            else if(!password.equals(confirmPass)){
                passwordInput.setError("Password harus sama dengan confirm password");
            }
            else if(!confirmPass.equals(password)){
                confirmPasswordInput.setError("Confirm password harus sama dengan password");
            }else{
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    editor.putString(email+" name",name);
                                    editor.putString(email+" email",email);
                                    editor.apply();
                                    Toast.makeText(requireActivity(), "Register Success", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(requireActivity(), "Register Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signInText = view.findViewById(R.id.signin_text);
        nameInput = view.findViewById(R.id.name_input);
        emailInput = view.findViewById(R.id.email_input);
        passwordInput = view.findViewById(R.id.password_input);
        confirmPasswordInput = view.findViewById(R.id.confirm_password_input);
        signUp = view.findViewById(R.id.register_btn);
        sp = getActivity().getSharedPreferences("sp",Context.MODE_PRIVATE);

        auth = FirebaseAuth.getInstance();

        signInText.setOnClickListener(toSignIn);
        signUp.setOnClickListener(register);
    }
}