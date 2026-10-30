package view;

public class PaymentView {

    public void showMainMenu() {
        System.out.println("== ----- Menu ----- ==");
        System.out.println("1. Bikin Pembayaran");
        System.out.println("2. Tambah Metode Pembayaran");
        System.out.println("0. Keluar");
    }

    public void showAddPaymentMenu() {
        System.out.println("== -- Tambah Pembayaran -- ==");
        System.out.println("1. Cash (Wallet baru)");
        System.out.println("2. Debit Card");
        System.out.println("3. Credit Card");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}