package com.example.lattitude.w2d4e3;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentB extends Fragment {
    Button btSend;
    EditText etMessage;
    TextView tvResultFirst;
    public FragmentB() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_b, container, false);
        etMessage = v.findViewById(R.id.etMessage);
        btSend = v.findViewById(R.id.btSend);
        tvResultFirst = v.findViewById(R.id.tvResultFirst);
        btSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("message-receiver-B");
                intent.putExtra("message", etMessage.getText().toString());
                LocalBroadcastManager.getInstance(v.getContext()).sendBroadcast(intent);
            }
        });
        LocalBroadcastManager.getInstance(v.getContext()).registerReceiver(messageReceiverB,
                new IntentFilter("message-receiver-A"));
        return v;
    }

    private BroadcastReceiver messageReceiverB = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message");
            tvResultFirst.setText(message);
        }
    };

}
