package dev.trindadedev.lib.inappupdate;

import org.json.JSONObject;

public interface InAppListener {
   public void onInitialize(JSONObject jObject);
   public void onError(String error);
}