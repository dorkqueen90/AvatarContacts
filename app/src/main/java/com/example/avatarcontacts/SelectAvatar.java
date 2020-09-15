package com.example.avatarcontacts;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SelectAvatar extends Fragment {

    public SelectAvatar() {
        // Required empty public constructor
    }

    ISelectAvatar mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (ISelectAvatar) context;
    }

    public static SelectAvatar newInstance() {
        SelectAvatar fragment = new SelectAvatar();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_select_avatar, container, false);

        view.findViewById(R.id.redFemale).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.imageSelected(R.drawable.avatar_f_2);
                getActivity().onBackPressed();
            }
        });
        view.findViewById(R.id.brownFemale).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.imageSelected(R.drawable.avatar_f_1);
                getActivity().onBackPressed();
            }
        });
        view.findViewById(R.id.darkFemale).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.imageSelected(R.drawable.avatar_f_3);
                getActivity().onBackPressed();
            }
        });
        view.findViewById(R.id.blondeMale).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.imageSelected(R.drawable.avatar_m_3);
                getActivity().onBackPressed();
            }
        });
        view.findViewById(R.id.brownMale).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.imageSelected(R.drawable.avatar_m_2);
                getActivity().onBackPressed();
            }
        });
        view.findViewById(R.id.darkMale).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.imageSelected(R.drawable.avatar_m_1);
                getActivity().onBackPressed();
            }
        });

        return view;
    }

    interface ISelectAvatar{
        void imageSelected(int imgId);
    }
}