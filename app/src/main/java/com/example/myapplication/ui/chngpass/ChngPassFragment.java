package com.example.myapplication.ui.chngpass;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.DrawerNavig;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChngPassFragment extends Fragment {

    private Button botonchangePass;
    private Button botonchangeCancelar;
    private TextInputEditText current;
    private TextInputEditText newPass;
    private TextInputEditText confPass;
    private FirebaseAuth auth;
    private ProgressDialog progress;
    private View.OnClickListener onClickListener;
    private String email;
    private FirebaseUser user;
    private AuthCredential cred;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_chngpass, container, false);

        current = (TextInputEditText) viewRoot.findViewById(R.id.campoCurrent);
        newPass = (TextInputEditText) viewRoot.findViewById(R.id.campoContraNew);
        confPass = (TextInputEditText) viewRoot.findViewById(R.id.campoContraConf);

        progress = new ProgressDialog(getActivity());

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        email = user.getEmail();

        botonchangePass = viewRoot.findViewById(R.id.chngpassSucces);
        onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(current.getText().toString()) || TextUtils.isEmpty(newPass.getText().toString())
                        || TextUtils.isEmpty(confPass.getText().toString()) ) {
                    Toast.makeText(getActivity(), "Error: Debe llenar todos los campos",Toast.LENGTH_LONG).show();
                }
                else{
                    if(newPass.getText().toString().equals(confPass.getText().toString())) {
                        cred = EmailAuthProvider
                                .getCredential(email, current.getText().toString());
                        progress.setMessage("Cambiando contraseña...");
                        progress.setCancelable(false);
                        progress.show();
                        reauthChange(cred);
                    }
                    else{
                        Toast.makeText(getActivity(), "Error: Las contraseñas no coinciden!",Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
        botonchangePass.setOnClickListener(onClickListener);

        botonchangeCancelar = viewRoot.findViewById(R.id.chngpassCancel);
        botonchangeCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        });

        return viewRoot;
    }

    private void reauthChange(AuthCredential cred) {
        user.reauthenticate(cred)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.v("ReAuth attempt : " , "Completed");
                        if(task.isSuccessful()){
                            Log.d("ReAuth Successful", "User re-authenticated.");
                            processChange();
                        }
                        else {
                            Log.d("ReAuth Unsuccessful", "User not re-authenticated.");
                            Log.v("  Failure reason ", task.getException().toString());
                            String[] errorString = task.getException().toString().split(":");
                            progress.dismiss();
                            Toast.makeText(getActivity(),"Error: "+errorString[1],Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void processChange(){
        //Update User Password
        user.updatePassword(newPass.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("UPDATE PASSWORD", "User password updated.");
                            progress.dismiss();
                            getActivity().finish();
                        }
                        else{
                            Log.d("UPDATE PASSWORD", "User password not updated.");
                            Log.v("  Failure reason ", task.getException().toString());
                            String[] errorString = task.getException().toString().split(":");
                            progress.dismiss();
                            Toast.makeText(getActivity(),"Error: "+errorString[1],Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }

    private void cancelar(){
        Intent intent = new Intent(getActivity(), DrawerNavig.class);
        startActivity(intent);
    }


}