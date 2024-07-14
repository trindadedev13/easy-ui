package dev.trindadeaquiles.mercadopago;

public interface PaymentListener {
        void onSuccess(String response);
        void onError(String errorMessage);
}