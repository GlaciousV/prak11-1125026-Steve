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

    public static void main(String[ ] args) {
        Scanner scanner = new Scanner(System.in);
        Wallet Wallet1 = new Wallet("Steve", 200.0);

        System.out.println(Wallet1.getName()+"|| saldo :"+Wallet1.getCashValue());
        while (true) {
            System.out.print("Masukkan jumlah yang dibayar : ");
            double amount = scanner.nextDouble();
            Wallet1.pay(amount);  
            if (Wallet1.getCashValue() == 0){
                break;
            }
        }

    }
}