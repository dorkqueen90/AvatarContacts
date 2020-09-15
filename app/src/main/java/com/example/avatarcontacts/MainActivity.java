package com.example.avatarcontacts;
/*
I didn't implement the edit feature b/c in class you told us not to worry about it.
 */
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactsList.IContactsFragment, CreateNewContact.ICreateContactFragment,
SelectAvatar.ISelectAvatar{
    ArrayList<Contact> contacts = new ArrayList<>();
    int imgId = R.drawable.select_avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Contacts");

        contacts.add(new Contact("Sophy", "Priest@draenei.com", "123-234-4567", "CS", R.drawable.avatar_f_1));
        contacts.add(new Contact("Forestdawn", "Druid@nightelf.com", "235-341-9834", "SIS", R.drawable.avatar_f_3));
        contacts.add(new Contact("Cassie", "Scholar@aura.com", "970-114-8776", "CS", R.drawable.avatar_f_2));

        getSupportFragmentManager().beginTransaction()
                .add(R.id.contentView, ContactsList.newInstance())
                .commit();
    }

    @Override
    public ArrayList<Contact> getCurrentContacts() {
        return this.contacts;
    }

    @Override
    public void addContact(String name, String email, String phone, String dept, int imgId) {
        contacts.add(new Contact(name, email, phone, dept, imgId));
    }

    @Override
    public int getImage() {
        return this.imgId;
    }

    @Override
    public void showNewContactFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentView, CreateNewContact.newInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void deleteContact(Contact contact) {
        contacts.remove(contact);
    }

    @Override
    public void showAvatarsFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentView, SelectAvatar.newInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void imageSelected(int imgId) {
        this.imgId = imgId;
    }

}