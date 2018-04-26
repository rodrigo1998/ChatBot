package com.rodrigodeoliveira.chatbot;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends Activity {

    LayoutInflater inflater1;

    int count =0;
    String email;
    String nome;

    EditText txtnome, txtemail,txtsearch;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;

    Data data;



    ArrayList<Data> dataArrayList;

    CustomAdapter customAdapter;

    String key;

    int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference().child("Usuario");
        key=databaseReference.push().getKey();

        txtnome = (EditText) findViewById(R.id.txt_nome);
        txtemail = (EditText)findViewById(R.id.txt_email);


          findViewById(R.id.btn_enviar).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    try {

                        nome = txtnome.getText().toString().trim();


                        if (TextUtils.isEmpty(nome) ) {

                            Toast.makeText(getApplicationContext(), "Por favor insira seu nome", Toast.LENGTH_SHORT).show();

                        } else {

                            email = txtemail.getText().toString().trim();

                            data = new Data(databaseReference.push().getKey(), nome, email);

                            databaseReference.child(data.getKey()).setValue(data);


                            txtnome.setText("");
                            txtemail.setText("");

                        }
                    } catch (Exception e) {

                        Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();

                    }


                }
        });

        dataArrayList = new ArrayList<>();

        customAdapter = new CustomAdapter(LoginActivity.this, dataArrayList);




        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Data datam = dataSnapshot.getValue(Data.class);

                dataArrayList.add(datam);

                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        realtimeUpdate();
    }

    public void realtimeUpdate(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataArrayList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    data = dataSnapshot1.getValue(Data.class);

                    dataArrayList.add(data);

                }


               customAdapter.notifyDataSetChanged();


            }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
}

    public void func(){

        if(count!=0){

            customAdapter = new CustomAdapter(getApplicationContext(),dataArrayList);



        }else {

            Toast.makeText(getApplicationContext(), "There is no data to show", Toast.LENGTH_SHORT).show();
        }


    }
}
