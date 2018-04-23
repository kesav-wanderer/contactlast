package com.example.kedu.contact;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Michael Eierman on 11/14/2016.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {

    private ArrayList<Contact> items;
    private Context adapterContext;

    public ContactAdapter(Context context, ArrayList<Contact> items) {
        super(context, R.layout.list_item, items);
        adapterContext = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        try {
            Contact contact = items.get(position);

            if (v == null) {
                LayoutInflater vi = (LayoutInflater) adapterContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list_item, null);
            }
            ImageView img=(ImageView)v.findViewById(R.id.imageView2);
            TextView contactName = (TextView) v.findViewById(R.id.textContactName);
            TextView contactNumber = (TextView) v.findViewById(R.id.textPhoneNumber);
            TextView cellnumber = (TextView) v.findViewById(R.id.Cellno);
            Button b = (Button) v.findViewById(R.id.buttonDeleteContact);
            TextView addr=(TextView)v.findViewById(R.id.Address);
            TextView st=(TextView)v.findViewById(R.id.State);
            TextView city=(TextView)v.findViewById(R.id.City);
            TextView zip=(TextView)v.findViewById(R.id.Zip);

            contactName.setText(contact.getContactName());
            contactNumber.setText("Home:"+contact.getPhoneNumber());
            cellnumber.setText("Cell:"+contact.getCellNumber());
            addr.setText(contact.getStreetAddress()+",");
            st.setText(contact.getState()+",");
            city.setText(contact.getCity()+",");
            zip.setText(contact.getZipCode());
            b.setVisibility(View.INVISIBLE);
            contactName.setTextColor((position%2==0)? Color.RED:Color.BLUE);
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return v;
    }

    public void showDelete(final int position, final View convertView,final Context context, final Contact contact) {
        View v = convertView;
        final Button b = (Button) v.findViewById(R.id.buttonDeleteContact);
        //2
        if (b.getVisibility()==View.INVISIBLE) {
            b.setVisibility(View.VISIBLE);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideDelete(position, convertView, context);
                    items.remove(contact);
                    deleteOption(contact.getContactID(), context);
                }
            });
        }
        else {
            hideDelete(position, convertView, context);
        }
    }
    //3
    private void deleteOption(int contactToDelete, Context context) {
        ContactDataSource db = new ContactDataSource(context);
        try {
            db.open();
            db.deleteContact(contactToDelete);
            db.close();
        }
        catch (Exception e) {
            Toast.makeText(adapterContext, "Delete Contact Failed", Toast.LENGTH_LONG).show();
        }
        this.notifyDataSetChanged();
    }
    //4
    public void hideDelete(int position, View convertView, Context context) {
        View v = convertView;
        final Button b = (Button) v.findViewById(R.id.buttonDeleteContact);
        b.setVisibility(View.INVISIBLE);
        b.setOnClickListener(null);
    }

}
