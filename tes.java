import java.util.ArrayList;
import java.util.List;
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

class WayToSign extends CheckingAccount {
    private static List<WayToSign> registeredUsers = new ArrayList<>();
    private String username;
    private String password;

    public WayToSign(String accountNumber, String accountHolderName, int balance, int pin, String username, String password) {
        super(accountNumber, accountHolderName, balance, pin);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public static List<WayToSign> getRegisteredUsers() {
        return registeredUsers;
    }

    public static void register(String accountNumber, String accountHolderName, int balance, int pin, String username, String password) {
        WayToSign newUser = new WayToSign(accountNumber, accountHolderName, balance, pin, username, password);
        registeredUsers.add(newUser);
    }

    public static WayToSign login(String username, String password) {
        for (WayToSign user : registeredUsers) {
            if (user.getUsername().equals(username) && user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }
}

public class Livin {
    private static List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner
