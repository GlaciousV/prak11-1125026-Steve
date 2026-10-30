package controller;

import model.PaymentStrategy;
import model.AuthorizationMethod;
import model.Wallet;
import model.DebitCard;
import model.CreditCard;
import view.PaymentView;
import util.ScannerUtil;
import java.util.*;

public class PaymentController {
    private PaymentView view;
    private Scanner scanner;

    private List<PaymentStrategy> paymentMethods = new ArrayList<>();

    public PaymentController(PaymentView view) {
        this.view = view;
        this.scanner = new Scanner(System.in);

        paymentMethods.add(new Wallet("Utama", 1000.0));
        paymentMethods.add(new DebitCard("dc-11-2221", 1000.0, "1234"));
        paymentMethods.add(new CreditCard("cc-11-2221", 1000, "123", "1234"));
    }

    public void start() {
        boolean running = true;
        while (running) {
            view.showMainMenu();
            int menu = ScannerUtil.inputInt("Pilih menu: ", scanner);

            switch (menu) {
                case 0:
                    view.printMessage("End");
                    running = false;
                    break;
                case 1:
                    processPayment();
                    break;
                case 2:
                    addPaymentMethod();
                    break;
                default:
                    view.printMessage("Menu tidak valid.");
                    break;
            }
        }
    }

    private void processPayment() {
        double amount = ScannerUtil.inputDouble("Masukkan jumlah yang mau dibayar: ", scanner);
        if (amount <= 0) {
            view.printMessage("Nominal tidak valid, dibatalkan.");
            return;
        }

        if (paymentMethods.isEmpty()) {
            view.printMessage("Belum ada metode pembayaran yang terdaftar.");
            return;
        }

        view.printMessage("Daftar Metode Pembayaran Tersedia:");
        for (int i = 0; i < paymentMethods.size(); i++) {
            PaymentStrategy ps = paymentMethods.get(i);
            view.printMessage((i + 1) + ". " + ps.getDetails());
        }

        int choice = ScannerUtil.inputInt("Pilih metode pembayaran (1 - " + paymentMethods.size() + "): ", scanner);

        if (choice > 0 && choice <= paymentMethods.size()) {
            PaymentStrategy selectedStrategy = paymentMethods.get(choice - 1);
            try {
                processPayment(selectedStrategy, amount);
            } catch (Exception e) {
                view.printMessage("Pembayaran Gagal: " + e.getMessage());
            }
        } else {
            view.printMessage("Pilihan tidak ada.");
        }
    }

    private void processPayment(PaymentStrategy paymentStrategy, double amount) throws Exception {
        if (!paymentStrategy.validate(amount)) {
            throw new Exception("Saldo atau limit tidak mencukupi.");
        }

        List<AuthorizationMethod> requiredAuthorizations = paymentStrategy.getRequiredAuthorizations();

        for (int i = 0; i < requiredAuthorizations.size(); i++) {
            AuthorizationMethod auth = requiredAuthorizations.get(i);
            // belum jalan pin dan ccv nya
            switch (auth) {
                case CCV:
                    String inputCcv = ScannerUtil.inputString("Masukkan CCV: ", scanner);
                    break;

                case PIN:
                    String inputPin = ScannerUtil.inputString("Masukkan PIN: ", scanner);
                    break;
            }
        }

        paymentStrategy.pay(amount);
    }

    private void addPaymentMethod() {
        view.showAddPaymentMenu();
        int jenis = ScannerUtil.inputInt("Pilih jenis: ", scanner);

        switch (jenis) {
            case 1:
                String nama = ScannerUtil.inputString("Nama Wallet: ", scanner);
                double saldo = ScannerUtil.inputDouble("Saldo Awal: ", scanner);
                paymentMethods.add(new Wallet(nama, saldo));
                view.printMessage("Wallet berhasil ditambahkan.");
                break;
            case 2:
                String cnDebit = ScannerUtil.inputString("Card Number: ", scanner);
                double saldoDebit = ScannerUtil.inputDouble("Saldo Awal: ", scanner);
                String pinDebit = ScannerUtil.inputString("PIN: ", scanner);
                paymentMethods.add(new DebitCard(cnDebit, saldoDebit, pinDebit));
                view.printMessage("Debit Card berhasil ditambahkan.");
                break;
            case 3:
                String cnCredit = ScannerUtil.inputString("Card Number: ", scanner);
                double limit = ScannerUtil.inputDouble("Limit: ", scanner);
                String ccv = ScannerUtil.inputString("CCV: ", scanner);
                String pinCredit = ScannerUtil.inputString("PIN: ", scanner);
                paymentMethods.add(new CreditCard(cnCredit, limit, ccv, pinCredit));
                view.printMessage("Credit Card berhasil ditambahkan.");
                break;
            default:
                view.printMessage("Pilihan jenis pembayaran tidak valid.");
                break;
        }
    }
}