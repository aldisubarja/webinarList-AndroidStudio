package com.example.projectbncc;

import android.content.Context;
import android.content.Intent;
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


public class LoginFragment extends Fragment {

    TextView signUpText;
    EditText emailInput,passwordInput;
    Button signIn;
    String email, password;
    SharedPreferences sp;

    private FirebaseAuth auth;

    View.OnClickListener toSignUp = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, new RegisterFragment())
                    .addToBackStack(null)
                    .commit();
        }
    };

    View.OnClickListener login = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SharedPreferences.Editor editor = sp.edit();
            email = emailInput.getText().toString();
            password = passwordInput.getText().toString();
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                editor.putString("login",email);
                                editor.apply();
                                Toast.makeText(requireActivity(), "Login Success", Toast.LENGTH_SHORT).show();
                                Intent toMain = new Intent(requireActivity(),MainActivity.class);
                                startActivity(toMain);
                            } else {
                                Toast.makeText(requireActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signUpText = view.findViewById(R.id.signup_text);
        emailInput = view.findViewById(R.id.email_input);
        passwordInput = view.findViewById(R.id.password_input);
        signIn = view.findViewById(R.id.login_btn);
        sp = getActivity().getSharedPreferences("sp", Context.MODE_PRIVATE);



        auth = FirebaseAuth.getInstance();

        signUpText.setOnClickListener(toSignUp);
        signIn.setOnClickListener(login);
    }
}