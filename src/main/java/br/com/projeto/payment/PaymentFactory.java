package br.com.projeto.payment;

public final class PaymentFactory {

    public static Payments get() {
        return new Payment();
    }

    private PaymentFactory() {
    }

}
