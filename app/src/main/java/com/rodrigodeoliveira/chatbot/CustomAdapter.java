package com.rodrigodeoliveira.chatbot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {

    TextView nome, email;

    Context context;

    ArrayList<Data> data;

    LayoutInflater inflater;

    public CustomAdapter(Context context, ArrayList<Data> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        nome = (TextView) view.findViewById(R.id.txt_nome);
        email = (TextView) view.findViewById(R.id.txt_email);


        nome.setText(nome.getText()+data.get(i).getNome());
        email.setText(email.getText()+""+ data.get(i).getEmail());



        return view;
    }
}
