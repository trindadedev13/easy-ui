package dev.trindadeaquiles.lib.mercadopago;

import org.json.JSONObject;
import java.lang.IllegalArgumentException;

public class MpInfo {

    private static final String POINT_OF_INTERACTION = "point_of_interaction",
    TRANSACTION_DATA = "transaction_data",
    ID = "id",
    QR_CODE = "qr_code",
    QR_CODE_BASE64 = "qr_code_base64";

    private final JSONObject jsonObject;

    public MpInfo(String json) throws Exception{
        if(json == null) throw new IllegalArgumentException("Json cannot be null");
        this.jsonObject = new JSONObject(json);
    }

    public JSONObject getJSONObject(String key){
        try{
            return jsonObject.getJSONObject(key);
        }catch(Exception e){
            return null;
        }
    }

    public String getString(String key){
        try{
            return jsonObject.getString(key);
        }catch(Exception e){
            return null;
        }
    }

    public String getID(){
        return getString(ID);
    }

    public String getQRCode(){
        try{
            return getJSONObject(POINT_OF_INTERACTION)
                .getJSONObject(TRANSACTION_DATA)
                .getString(QR_CODE);
        }catch(Exception e){
            return null;
        }
    }

    public String getBase64(){
        try{
            return getJSONObject(POINT_OF_INTERACTION)
                .getJSONObject(TRANSACTION_DATA)
                .getString(QR_CODE_BASE64);
        }catch(Exception e){
            return null;
        }
    }

    public String getQRCode(final String json){
        try{
            return new JSONObject(json)
                .getJSONObject("point_of_interaction")
                .getJSONObject("transaction_data")
                .getString("qr_code")
                .toString();
        }catch(Exception e){
            return null;
        }
    }
}