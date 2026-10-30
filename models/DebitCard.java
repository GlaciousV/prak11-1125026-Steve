public class DebitCard {
    private String cardNumber;
    private double balance;
    private String pin;

    public double getBalance() {
        return balance;
    }

    public DebitCard(String cardNumber, double balance, String pin) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.pin = pin;
    }

    public boolean validate(double amount) {
        return amount > 0 && this.balance >= amount;
    }

    public void pay(double amount) {
        if (validate(amount)) {
            this.balance -= amount;
            System.out.println("Pemabayaran cash beharsil , sisa wallet : " + balance);
        } else {
            System.out.println("saldo : " + balance + " -> tidak cukup");
        }
    }

}
