package com.trindade.mercadopago;

public interface PaymentListener {
        void onSuccess(String response);
        void onError(String errorMessage);
}