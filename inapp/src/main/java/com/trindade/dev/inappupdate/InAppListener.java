package com.trindade.dev.inappupdate;

import org.json.JSONObject;

public interface InAppListener {
   public void onInitialize(JSONObject jObject);
   public void onError(String error);
}