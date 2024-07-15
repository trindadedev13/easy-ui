package dev.trindadeaquiles.lib.mercadopago;

public interface PaymentListener {
        void onSuccess(String response);
        void onError(String errorMessage);
}