package com.jasondev.dicebluffandroid.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.jasondev.dicebluffandroid.R;
import com.jasondev.dicebluffandroid.classes.WebsocketController;
import com.jasondev.dicebluffandroid.interfaces.WebsocketInterface;

public class RegisterActivity extends AppCompatActivity implements WebsocketInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        WebsocketController wc = new WebsocketController(this);
        wc.connectWebSocket();
    }

    @Override
    public void onConnect() {
        Toast.makeText(this, "connect", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisconnect() {
        Toast.makeText(this, "disconnect", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMessage(String message) {
        Toast.makeText(this, "message: " + message, Toast.LENGTH_SHORT).show();
    }
}