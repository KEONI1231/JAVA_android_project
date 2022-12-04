package com.example.final_java_project.backend;

import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class HttpWebSocket {
    public static String WEB_SOCKET_URL = "ws://192.168.0.10:8080/homepage/websocket.message";

    public static WebSocketListener listener = new WebSocketListener() {
        @Override
        public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            super.onClosed(webSocket, code, reason);
        }

        @Override
        public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            super.onClosing(webSocket, code, reason);
            Log.d("TLOG","소켓 onClosing");
            webSocket.close(1000, null);
            webSocket.cancel();

        }

        @Override
        public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable okhttp3.Response response) {
            super.onFailure(webSocket, t, response);
            Log.d("TLOG","소켓 onFailure : " + t.toString());
        }

        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
            super.onMessage(webSocket, text);
            Log.d("TLOG", "text 데이터 확인 : " + text.toString());
        }

        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
            super.onMessage(webSocket, bytes);
            Log.d("TLOG", "ByteString 데이터 확인 : " + bytes.toString());
        }

        @Override
        public void onOpen(@NotNull WebSocket webSocket, @NotNull okhttp3.Response response) {
            super.onOpen(webSocket, response);
            Log.d("TLOG", "전송 데이터 확인 : " + webSocket + " : " + response);
            webSocket.close(1000, null);
        }
    };

}