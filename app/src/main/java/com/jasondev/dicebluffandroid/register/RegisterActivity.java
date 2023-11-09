package com.jasondev.dicebluffandroid.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jasondev.dicebluffandroid.R;
import com.jasondev.dicebluffandroid.classes.WebsocketController;
import com.jasondev.dicebluffandroid.interfaces.WebsocketInterface;

import org.json.JSONException;
import org.json.JSONObject;


public class RegisterActivity extends AppCompatActivity implements WebsocketInterface {

    Button joinButton;
    Button createRoomButton;
    Button publicRoomsButton;
    Button instructionButton;

    EditText username;
    EditText roomCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        joinButton = findViewById(R.id.joinButton);
        createRoomButton = findViewById(R.id.createRoomButton);
        publicRoomsButton = findViewById(R.id.publicRoomsButton);
        instructionButton = findViewById(R.id.instructionButton);
        username = findViewById(R.id.username);
        roomCode = findViewById(R.id.roomCode);

        WebsocketController wc = new WebsocketController(this);
        wc.connectWebSocket();

        onClickEvents();
        setUsername();
    }

    @Override
    public void onConnect() {
        System.out.println("dicebluff: on connect");
        /*JSONObject dat = new JSONObject();
        try {
            dat.put("type", "register");
            dat.put("func", "removePlayer");
            dat.put("player", getSP("username"));
            dat.put("pb", getSP("pb"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }*/

    }

    @Override
    public void onDisconnect() {
        System.out.println("dicebluff: on disconnect");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("dicebluff: on message");
    }

    @Override
    public void onFailure() {
        System.out.println("dicebluff: on failure");
        //Toast.makeText(this, "failure", Toast.LENGTH_SHORT).show();
    }

    public String getSP(String key){
        SharedPreferences sp = getApplicationContext().getSharedPreferences("Data", 0);
        String str = sp.getString(key, "");
        return str;
    }

    public void setSP(String key, String value){
        SharedPreferences sp = getApplicationContext().getSharedPreferences("Data", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void buttonClicked(){
        setSP("username", username.getText().toString());
        setSP("pb", "null");
    }

    public boolean checkUsername(String name){
        if(!name.equals("")){
            return true;
        }else{
            Toast.makeText(RegisterActivity.this, "You have to create a username", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean checkRoomCode(String code){
        if(!code.equals("")){
            return true;
        }else{
            Toast.makeText(RegisterActivity.this, "You have to set a room code", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void setUsername(){
        String name = getSP("username");
        username.setText(name);
    }






    public void onClickEvents(){
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicked();
                if(checkUsername(getSP("username")) && checkRoomCode(roomCode.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "success", Toast.LENGTH_SHORT).show();
                }
            }
        });

        createRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicked();
                if(checkUsername(getSP("username"))){
                    Toast.makeText(RegisterActivity.this, "success", Toast.LENGTH_SHORT).show();
                }
            }
        });

        publicRoomsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicked();
                if(checkUsername(getSP("username"))){
                    Toast.makeText(RegisterActivity.this, "success", Toast.LENGTH_SHORT).show();
                }
            }
        });

        instructionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicked();
            }
        });
    }
}