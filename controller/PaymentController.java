package controller;

import model.Wallet;
import model.DebitCard;
import model.CreditCard;
import view.PaymentView;
import util.ScannerUtil;
import java.util.ArrayList;
import java.util.Scanner;

public class PaymentController {
    private PaymentView view;
    private Scanner scanner;
    
    private ArrayList<Wallet> wallets = new ArrayList<>();
    private ArrayList<DebitCard> debitCards = new ArrayList<>();
    private ArrayList<CreditCard> creditCards = new ArrayList<>();

    public PaymentController(PaymentView view) {
        this.view = view;
        this.scanner = new Scanner(System.in);
        
        wallets.add(new Wallet("Utama", 1000.0));
        debitCards.add(new DebitCard("dc-11-2221", 1000.0, "1234"));
        creditCards.add(new CreditCard("cc-11-2221", 1000, "123", "1234"));
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

        view.printMessage("Pilih Kategori Metode Pembayaran:");
        view.printMessage("1. Wallet Cash");
        view.printMessage("2. Debit Card");
        view.printMessage("3. Credit Card");
        int kategori = ScannerUtil.inputInt("Pilih (1/2/3): ", scanner);

        if (kategori == 1) {
            view.printMessage(" Daftar Wallet ");
            for (int i = 0; i < wallets.size(); i++) {
                view.printMessage((i + 1) + ". " + wallets.get(i).getName() + " (Saldo: " + wallets.get(i).getCashValue() + ")");
            }
            int choice = ScannerUtil.inputInt("Pilih Wallet: ", scanner);
            if (choice > 0 && choice <= wallets.size()) {
                wallets.get(choice - 1).pay(amount);
            } else {
                view.printMessage("Pilihan tidak ada");
            }

        } else if (kategori == 2) {
            view.printMessage(" Daftar Debit Card ");
            for (int i = 0; i < debitCards.size(); i++) {
                view.printMessage((i + 1) + ". Card (Saldo: " + debitCards.get(i).getBalance() + ")");
            }
            int choice = ScannerUtil.inputInt("Pilih Debit Card: ", scanner);
            if (choice > 0 && choice <= debitCards.size()) {
                debitCards.get(choice - 1).pay(amount);
            } else {
                view.printMessage("Pilihan tidak ada");
            }

        } else if (kategori == 3) {
            view.printMessage(" Daftar Credit Card ");
            for (int i = 0; i < creditCards.size(); i++) {
                view.printMessage((i + 1) + ". Card (Limit Tersedia: " + creditCards.get(i).getAvailableCredit() + ")");
            }
            int choice = ScannerUtil.inputInt("Pilih Credit Card: ", scanner);
            if (choice > 0 && choice <= creditCards.size()) {
                creditCards.get(choice - 1).pay(amount);
            } else {
                view.printMessage("Pilihan tidak valid.");
            }

        } else {
            view.printMessage("Kategori tidak ada");
        }
    }
    private void addPaymentMethod() {
        view.showAddPaymentMenu();
        int jenis = ScannerUtil.inputInt("Pilih jenis: ", scanner);

        switch (jenis) {
            case 1:
                String nama = ScannerUtil.inputString("Nama Wallet: ", scanner);
                double saldo = ScannerUtil.inputDouble("Saldo Awal: ", scanner);
                wallets.add(new Wallet(nama, saldo));
                view.printMessage("Wallet berhasil ditambahkan.");
                break;
            case 2:
                String cnDebit = ScannerUtil.inputString("Card Number: ", scanner);
                double saldoDebit = ScannerUtil.inputDouble("Saldo Awal: ", scanner);
                String pinDebit = ScannerUtil.inputString("PIN: ", scanner);
                debitCards.add(new DebitCard(cnDebit, saldoDebit, pinDebit));
                view.printMessage("Debit Card berhasil ditambahkan.");
                break;
            case 3:
                String cnCredit = ScannerUtil.inputString("Card Number: ", scanner);
                double limit = ScannerUtil.inputDouble("Limit: ", scanner);
                String ccv = ScannerUtil.inputString("CCV: ", scanner);
                String pinCredit = ScannerUtil.inputString("PIN: ", scanner);
                creditCards.add(new CreditCard(cnCredit, limit, ccv, pinCredit));
                view.printMessage("Credit Card berhasil ditambahkan.");
                break;
            default:
                view.printMessage("Pilihan jenis pembayaran tidak valid.");
                break;
        }
    }
}