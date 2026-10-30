// import java.util.Scanner;

// public class Main {
    
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         Wallet myWallet = new Wallet("Nama", 1000.0);
//         DebitCard myDebitCard = new DebitCard("dc-11-2221", 1000.0, "1234");
//         CreditCard myCreditCard = new CreditCard("cc-11-2221", 1000, "123","1234");

//         while (true) {
//             System.out.println("== ----- SALDO ----- ==");
//             System.out.println("1. Wallet Cash: " + myWallet.getCashValue());
//             System.out.println("2. Debit Card Balance: " +  myDebitCard.getBalance());
//             System.out.println("3. Credit Card Limit Tersedia: " + myCreditCard.getAvailableCredit());

//             System.out.print("Masukkan jumlah yang mau dibayar : ");
//             double amount = scanner.nextDouble();

//             if (amount <= 0) {
//                 System.out.println("End");
//                 break;
//             }

//             System.out.println("Pilih metode pembayaran:");
//             System.out.print("Cash /Debit/Credit = 1/2/3: ");
//             int method = scanner.nextInt();

//             switch (method) {
//                 case 1:
//                     myWallet.pay(amount);
//                     break;
//                 case 2:
//                     myDebitCard.pay(amount);
//                     break;
//                 case 3:
//                     myCreditCard.pay(amount);
//                     break;
//                 default:
//                     System.out.println("input payment method yang ada");
//                     break;
//             }

//             if (myWallet.getCashValue() <= 0 && myDebitCard.getBalance() <= 0) {
//                 System.out.println("Semua saldo habis");
//                 break;
//             }
//         }

//         scanner.close();
//     }

// }
