import java.util.Scanner;

public class Wallet {
    private String name;
    private double cashValue;

    public String getName() {
        return name;
    }

    public double getCashValue() {
        return cashValue;
    }

    public Wallet(String name, double cashValue) {
        this.name = name;
        this.cashValue = cashValue;
    }

    public boolean validate(double amount) {
        return amount > 0 && this.cashValue >= amount;
    }

    public void pay(double amount) {
        if (validate(amount)) {
            this.cashValue -= amount;
            System.out.println("Pemabayaran cash beharsil , sisa wallet : " + cashValue);
        } else {
            System.out.println("saldo : " + cashValue + " -> tidak cukup");
        }
    }

    // public static void main(String[] args) {
    // Scanner scanner = new Scanner(System.in);
    // Wallet myWallet = new Wallet("Nama", 10000.0);

    // System.out.println(myWallet.getName() + "|| saldo :" +
    // myWallet.getCashValue());
    // while (true) {
    // System.out.print("Masukkan jumlah yang dibayar : ");
    // double amount = scanner.nextDouble();
    // myWallet.pay(amount);
    // if (myWallet.getCashValue() == 0) {
    // break;
    // }
    // }

    // }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Wallet myWallet = new Wallet("Nama", 1000.0);
        DebitCard myDebitCard = new DebitCard("xx-11-2221", 1000.0, "1234");

        while (true) {
            System.out.println("== ----- SALDO ----- ==");
            System.out.println("1. Wallet Cash: " + myWallet.getCashValue());
            System.out.println("2. Debit Card Balance: " +  myDebitCard.getBalance());

            System.out.print("Masukkan jumlah yang mau dibayar : ");
            double amount = scanner.nextDouble();

            if (amount <= 0) {
                System.out.println("End");
                break;
            }

            System.out.println("Pilih metode pembayaran:");
            System.out.print("1. Cash/ 2. Debit : ");
            int method = scanner.nextInt();

            switch (method) {
                case 1:
                    myWallet.pay(amount);
                    break;
                case 2:
                    myDebitCard.pay(amount);
                    break;

                default:
                    System.out.println("input payment method yang ada");
                    break;
            }

            if (myWallet.getCashValue() <= 0 && myDebitCard.getBalance() <= 0) {
                System.out.println("Semua saldo habis");
                break;
            }
        }

        scanner.close();
    }
}