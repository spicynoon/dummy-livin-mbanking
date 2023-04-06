import java.util.Scanner;

abstract class BankAccount {
    protected String accountNumber;
    protected String accountHolderName;
    protected int balance;

    public BankAccount(String accountNumber, String accountHolderName, int balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public abstract void printInfo();

    public abstract void transfer(String destinationAccountNumber, int amount);

    public void checkBalance() {
        System.out.println("Saldo Anda saat ini adalah: " + balance);
    }

    public abstract void payment(String referralCode, int amount);
}

class CheckingAccount extends BankAccount {
    private int pin;

    public CheckingAccount(String accountNumber, String accountHolderName, int balance, int pin) {
        super(accountNumber, accountHolderName, balance);
        this.pin = pin;
    }

    public void printInfo() {
        System.out.println("Nomor rekening: " + accountNumber);
        System.out.println("Nama pemilik rekening: " + accountHolderName);
        System.out.println("Saldo: " + balance);
    }

    public void transfer(String destinationAccountNumber, int amount) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan PIN Anda: ");
        int inputPin = scanner.nextInt();
        if (inputPin == pin) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Transfer sebesar " + amount + " ke " + destinationAccountNumber + " berhasil.");
            } else {
                System.out.println("Transfer gagal: saldo tidak mencukupi.");
            }
        } else {
            System.out.println("Transfer gagal: PIN salah.");
        }
    }

    public void payment(String referralCode, int amount) {
        if (referralCode.equals("ABC123")) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println(
                        "Pembayaran sebesar " + amount + " dengan kode referral " + referralCode + " berhasil.");
            } else {
                System.out.println("Pembayaran gagal: saldo tidak mencukupi.");
            }
        } else {
            System.out.println("Pembayaran gagal: kode referral salah.");
        }
    }
}

public class Livin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean mainMenu = true;

        System.out.println("Livin by Mandiri Mobile Banking");
        System.out.println("- Spesial Untuk Anda Yang Spesial -");
        System.out.println("====================================");
        
        String accountNumber = "1234567890";
        int balance = 100000;
        
        System.out.println("- Sign Up Page -");
        System.out.print("Input ur name: ");
        String accountHolderName = scanner.next();
        System.out.print("Input ur secret pin number: ");
        int pin = scanner.nextInt();
        System.out.println("====================================");

        CheckingAccount myAccount = new CheckingAccount(accountNumber, accountHolderName, balance, pin);
        
        while (mainMenu) {
            System.out.println("- Main Menu -");
            myAccount.printInfo();
            myAccount.checkBalance();
            System.out.println("==========================================================");
            System.out.print("1. Transfer\n2. Payment\n3. Check Balance\n4. Log Out\n:");
            int doThis = scanner.nextInt();

            switch (doThis) {
                case 1:
                    System.out.print("Input Account Number Destination: ");
                    String destinationAccountNumber = scanner.next();
                    System.out.print("Input Amount: ");
                    int amountTransfer = scanner.nextInt();
                    myAccount.transfer(destinationAccountNumber, amountTransfer);
                    System.out.println("==========================================================");
                    break;
                case 2:
                    System.out.print("Input ReferralCode: ");
                    String referralCode = scanner.next();
                    System.out.print("Input Amount: ");
                    int amountPayment = scanner.nextInt();
                    myAccount.payment(referralCode, amountPayment);
                    System.out.println("==========================================================");
                    break;
                case 3:
                    myAccount.checkBalance();
                    System.out.println("==========================================================");
                    break;
                case 4:
                    mainMenu = false;
                default:
                    break;
            }
        }
    }
}
