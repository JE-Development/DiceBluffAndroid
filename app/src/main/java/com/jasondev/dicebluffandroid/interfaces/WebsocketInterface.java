package com.jasondev.dicebluffandroid.interfaces;

public interface WebsocketInterface {

    public void onConnect();
    public void onDisconnect();
    public void onMessage(String message);
    public void onFailure();

}
