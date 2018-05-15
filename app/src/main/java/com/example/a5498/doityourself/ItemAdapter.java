package com.example.a5498.doityourself;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemAdapter extends BaseAdapter {


    LayoutInflater lay;
    Map ma;
    ArrayList<String> id;
    ArrayList<Integer> pwd;


     public ItemAdapter(Context c,Map m){
     lay =(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
     ma = m;
     id = new ArrayList<>(m.keySet());
     pwd = new ArrayList<>(m.values());

     }


    @Override
    public int getCount() {
       return ma.size();
    }

    @Override
    public Object getItem(int position) {
       return pwd.get(position);
    }

    @Override
    public long getItemId(int position) {
       return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         View v = lay.inflate(R.layout.newtable,null);

         TextView t1 = v.findViewById(R.id.Usernametextview);
        TextView t2 = v.findViewById(R.id.PasswordTextview);

        t1.setText(id.get(position));
        t2.setText(pwd.get(position));

        return  v;
    }
}
