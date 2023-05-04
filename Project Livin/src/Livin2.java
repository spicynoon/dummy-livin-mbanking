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

    public WayToSign(String accountNumber, String accountHolderName, int balance, int pin, String username,
            String password) {
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

    public static void register(String accountNumber, String accountHolderName, int balance, int pin, String username,
            String password) {
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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WayToSign currentUser = null;

        while (true) {
            System.out.println("Silakan pilih opsi:");
            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("3. Keluar");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("- Sign Up Page -");
                System.out.print("Masukkan nama lengkap: ");
                String accountHolderName = scanner.nextLine();
                System.out.print("Masukkan username: ");
                String username = scanner.nextLine();
                System.out.print("Masukkan password: ");
                String password = scanner.nextLine();

                CheckingAccount newAccount = new CheckingAccount(generateAccountNumber(), accountHolderName, 0, 1234);
                WayToSign.register(newAccount.accountNumber, accountHolderName, 0, 1234, username, password);
                System.out.println("Akun Anda berhasil dibuat.");
                System.out.println("Nomor rekening Anda: " + newAccount.accountNumber);
                System.out.println("Silakan login untuk mengakses akun Anda.");
            } else if (choice == 2) {
                System.out.println("- Login Page -");
                System.out.print("Masukkan username: ");
                String username = scanner.nextLine();
                System.out.print("Masukkan password: ");
                String password = scanner.nextLine();

                currentUser = WayToSign.login(username, password);
                if (currentUser != null) {
                    System.out.println("Selamat datang, " + currentUser.accountHolderName + "!");
                    break;
                } else {
                    System.out.println("Login gagal: username atau password salah.");
                }
            } else if (choice == 3) {
                System.out.println("Terima kasih telah menggunakan Livin. Sampai jumpa!");
                System.exit(0);
            } else {
                System.out.println("Opsi tidak valid. Silakan pilih opsi yang tersedia.");
            }
        }

        CheckingAccount currentAccount = currentUser;
        System.out.println("Nomor rekening Anda: " + currentAccount.accountNumber);
        System.out.println("Saldo Anda saat ini: " + currentAccount.balance);
        while (true) {
            System.out.println("Silakan pilih opsi:");
            System.out.println("1. Transfer");
            System.out.println("2. Cek saldo");
            System.out.println("3. Bayar");
            System.out.println("4. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Masukkan nomor rekening tujuan: ");
                String destinationAccountNumber = scanner.nextLine();
                System.out.print("Masukkan jumlah yang akan ditransfer: ");
                int amount = scanner.nextInt();
                scanner.nextLine();

                currentAccount.transfer(destinationAccountNumber, amount);
            } else if (choice == 2) {
                currentAccount.checkBalance();
            } else if (choice == 3) {
                System.out.print("Masukkan kode referral: ");
                String referralCode = scanner.nextLine();
                System.out.print("Masukkan jumlah yang akan dibayarkan: ");
                int amount = scanner.nextInt();
                scanner.nextLine();

                currentAccount.payment(referralCode, amount);
            } else if (choice == 4) {
                System.out.println("Anda telah keluar, terima kasih telah menggunakan Livin");
            }
        }
    }
}
