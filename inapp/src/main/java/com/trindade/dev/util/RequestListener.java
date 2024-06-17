package com.trindade.dev.util;

public interface RequestListener {
    public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders);
    public void onErrorResponse(String tag, String message);
}