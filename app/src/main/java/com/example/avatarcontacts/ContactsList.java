package com.example.avatarcontacts;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactsList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactsList extends Fragment {
    ListView listView;
    ArrayList<Contact> contacts = new ArrayList<>();
    ContactAdapter adapter;
    Button newContact;

    public ContactsList() {
        // Required empty public constructor
    }

    public static ContactsList newInstance() {
        ContactsList fragment = new ContactsList();

        return fragment;
    }

    IContactsFragment mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mListener = (IContactsFragment) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts_list, container, false);

        newContact = view.findViewById(R.id.newContactButton);

        contacts.clear();
        contacts.addAll(mListener.getCurrentContacts());

        listView = view.findViewById(R.id.listView);
        adapter = new ContactAdapter(getActivity(), R.layout.fragment_contact_layout, contacts);
        listView.setAdapter(adapter);

        return view;
    }

    class ContactAdapter extends ArrayAdapter<Contact> {

        public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.fragment_contact_layout, parent, false);
            }
            final Contact contact = getItem(position);

            ((TextView)convertView.findViewById(R.id.displayName)).setText(contact.name);
            ((TextView)convertView.findViewById(R.id.displayEmail)).setText(contact.email);
            ((TextView)convertView.findViewById(R.id.displayPhone)).setText(contact.phone);
            ((TextView)convertView.findViewById(R.id.displayDep)).setText(contact.dept);
            ((ImageView)convertView.findViewById(R.id.displayImage)).setImageResource(contact.imgId);

            newContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.showNewContactFragment();
                }
            });

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    mListener.deleteContact(contacts.get(position));

                    Fragment frg = ContactsList.this;
                    final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.detach(frg);
                    ft.attach(frg);
                    ft.commit();

                    return true;
                }
            });

            return convertView;
        }
    }
    interface IContactsFragment{
        ArrayList<Contact> getCurrentContacts();
        void showNewContactFragment();
        void deleteContact(Contact contact);
    }
}