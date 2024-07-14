package dev.trindadeaquiles.mercadopago;

import android.content.Context;
import android.content.DialogInterface;
import android.app.Activity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dev.trindadeaquiles.RequestNetwork;
import dev.trindadeaquiles.RequestNetworkController;
import java.util.HashMap;
import java.util.Map;

public class MercadoPago {

    public String firstName, lastName, email;
    public double price;
    public Context context;
    public Activity act;

    public MercadoPago(Context ctx, Activity act) {
        this.context = ctx;
        this.act = act;
    }

    public void createPayment(final PaymentListener listener) {
        try {
            // header
            HashMap<String, Object> mapHeaders = new HashMap<>();
            mapHeaders.put("Authorization", "Bearer " + MercadoPagoConfig.API_KEY);
            mapHeaders.put("Content-Type", "application/json; charset=utf-8");

            // payment info
            HashMap<String, Object> map = new HashMap<>();
            map.put("description", "Sample Description");

            // payer info
            HashMap<String, Object> payer = new HashMap<>();
            payer.put("first_name", firstName);
            payer.put("last_name", lastName);
            payer.put("email", email);

            // more payment info
            map.put("payer", payer);
            map.put("payment_method_id", "pix");
            map.put("transaction_amount", price);

            // initialize RequestNetwork
            RequestNetwork createPaymentReq = new RequestNetwork(act);

            // set header of RequestNetwork
            createPaymentReq.setHeaders(mapHeaders);
            // set parameters of RequestNetwork (Req.Body)
            createPaymentReq.setParams(map, RequestNetworkController.REQUEST_BODY);
            // start request (Req.Post)
            createPaymentReq.startRequestNetwork(RequestNetworkController.POST, "https://api.mercadopago.com/v1/payments", "createPayment", new RequestNetwork.RequestListener() {
                @Override
                public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {
                    onResponseCreate(response, listener);
                }

                @Override
                public void onErrorResponse(String tag, String msg) {
                    listener.onError(msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verifyPayment(String operation, final PaymentListener listener) {
        try {
            // header
            HashMap<String, Object> mapHeaders = new HashMap<>();
            mapHeaders.put("Authorization", "Bearer " + MercadoPagoConfig.API_KEY);
            mapHeaders.put("Content-Type", "application/json");

            // initialize RequestNetwork
            RequestNetwork verifyPaymentReq = new RequestNetwork(act);

            // set header of RequestNetwork
            verifyPaymentReq.setHeaders(mapHeaders);
            // set parameters of RequestNetwork (Req.Param)
            verifyPaymentReq.setParams(mapHeaders, RequestNetworkController.REQUEST_PARAM);
            // start request (Req.Get)
            verifyPaymentReq.startRequestNetwork(RequestNetworkController.GET, "https://api.mercadopago.com/v1/payments/" + operation, "", new RequestNetwork.RequestListener() {
                @Override
                public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {
                    onResponseVerify(response, listener);
                }

                @Override
                public void onErrorResponse(String tag, String msg) {
                    listener.onError(msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onResponseCreate(String response, PaymentListener listener) {
        try {
            if (response.contains("error")) {
                listener.onError(response);
            } else {
                listener.onSuccess(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onResponseVerify(String response, PaymentListener listener) {
        try {
            HashMap<String, Object> map = new Gson().fromJson(response, new TypeToken<HashMap<String, Object>>() {}.getType());
            String statusPayment = map.get("status").toString();
            listener.onSuccess("Status: " + statusPayment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}