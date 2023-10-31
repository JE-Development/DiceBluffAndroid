package com.jasondev.dicebluffandroid.classes;

import com.jasondev.dicebluffandroid.interfaces.WebsocketInterface;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WebsocketController {

    WebsocketInterface wsi;
    WebSocket ws;

    public WebsocketController(WebsocketInterface wsi){
        this.wsi = wsi;
    }


    public void connectWebSocket() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("ws://212.227.183.160:3000")
                .build();

        WebSocketListener listener = new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                wsi.onConnect();
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                wsi.onMessage(text);
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                // Nachricht als ByteString empfangen.
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                wsi.onDisconnect();
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                // Fehler beim WebSocket.
            }
        };

        ws = client.newWebSocket(request, listener);
    }

    public void sendMessage(String message) {
        if (ws != null) {
            ws.send(message);
        }
    }

    public void closeWebSocket() {
        if (ws != null) {
            ws.close(1000, "Goodbye, WebSocket!");
        }
    }

}
