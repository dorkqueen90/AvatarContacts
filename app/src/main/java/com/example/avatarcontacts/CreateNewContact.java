package com.example.avatarcontacts;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateNewContact extends Fragment {
    Button submit;
    EditText name, email, phone;
    RadioGroup selected;
    ImageView image;
    ICreateContactFragment mListener;
    ArrayList<Contact> contacts = new ArrayList<>();
    int imgId;

    public CreateNewContact() {
        // Required empty public constructor
    }

    public static CreateNewContact newInstance() {
        CreateNewContact fragment = new CreateNewContact();

        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (ICreateContactFragment) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_new_contact, container, false);
        image = view.findViewById(R.id.avatarImage);
        submit = view.findViewById(R.id.submitButton);
        name = view.findViewById(R.id.editName);
        email = view.findViewById(R.id.editEmail);
        phone = view.findViewById(R.id.editPhone);
        selected = view.findViewById(R.id.radioGroup);

        imgId = mListener.getImage();
        image.setImageResource(imgId);

        contacts.clear();
        contacts.addAll(mListener.getCurrentContacts());

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.showAvatarsFragment();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dept = "";
                if(isNameFilled(name) && isEmailValid(email) && isPhoneFilled(phone)){
                    if(selected.getCheckedRadioButtonId() == R.id.sisButton){
                        dept = "SIS";
                    } else if(selected.getCheckedRadioButtonId() == R.id.csButton){
                        dept = "CS";
                    } else if(selected.getCheckedRadioButtonId() == R.id.bioButton){
                        dept = "BIO";
                    }
                    String stringname = name.getText().toString();
                    String stringemail = email.getText().toString();
                    String stringphone = phone.getText().toString();
                    mListener.addContact(stringname, stringemail, stringphone, dept, imgId);
                }
                getActivity().onBackPressed();
            }
        });
        return view;
    }

    private boolean isNameFilled(EditText name) {
        return name.getText().toString().length() > 0;
    }
    private boolean isEmailValid(EditText email) {
        return email.getText().toString().length() > 0 && email.getText().toString().contains("@") &&
                email.getText().toString().contains(".");
    }
    private boolean isPhoneFilled(EditText phone) {
        return phone.getText().toString().length() > 0;
    }

    interface ICreateContactFragment{
        void showAvatarsFragment();
        ArrayList<Contact> getCurrentContacts();
        void addContact(String name, String email, String phone, String dept, int imgId);
        int getImage();
    }
}