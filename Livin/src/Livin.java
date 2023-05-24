import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

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

    public abstract void transfer(String destinationAccount, int amount);

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
                System.out.println("Transfer gagal: PIN salah.");
            }
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
    private static List<WayToSign> registeredUsersList = new ArrayList<>();
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

    public static List<WayToSign> getRegisteredUsersList() {
        return registeredUsersList;
    }

    public static void register(String accountNumber, String accountHolderName, int balance, int pin,
            String username, String password) {
        WayToSign newUser = new WayToSign(accountNumber, accountHolderName, balance, pin, username, password);
        registeredUsersList.add(newUser);
    }

    public static WayToSign login(String username, String password) {
        for (WayToSign user : registeredUsersList) {
            if (user.getUsername().equals(username) && user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }
}

public class Livin {
    private static Set<String> usedUsernames = new HashSet<>();
    private static Set<String> usedFullNames = new HashSet<>();
    private static Set<String> usedAccountNumbers = new HashSet<>();

    public static String generateAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();

        while (accountNumber.length() < 10) {
            int digit = random.nextInt(10);
            accountNumber.append(digit);
        }

        return accountNumber.toString();
    }

    public static List<String> generateUniqueAccountNumbers() {
        List<String> uniqueAccountNumbers = new ArrayList<>();

        while (uniqueAccountNumbers.size() < 12) {
            String accountNumber = generateAccountNumber();
            if (!usedAccountNumbers.contains(accountNumber)) {
                uniqueAccountNumbers.add(accountNumber);
                usedAccountNumbers.add(accountNumber);
            }
        }

        return uniqueAccountNumbers;
    }
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
                if (usedFullNames.contains(accountHolderName)){
                    System.out.println("Nama lengkap sudah digunakan. Silakan gunakan nama lengkap yang berbeda.");
                    continue;
                }

                System.out.print("Masukkan username: ");
                String username = scanner.nextLine();
                if (usedUsernames.contains(username)){
                    System.out.println("Username sudah digunakan. Silakan gunakan username yang berbeda.");
                    continue;
                }

                System.out.print("Masukkan password: ");
                String password = scanner.nextLine();
                System.out.print("Masukkan pin: ");
                int pin = scanner.nextInt();
                
                List<String> accountNumbers = generateUniqueAccountNumbers();
                String accountNumber = accountNumbers.get(accountNumbers.size() - 1);

                WayToSign.register(accountNumber, accountHolderName, 0, pin, username, password);
                usedUsernames.add(username);
                usedFullNames.add(accountHolderName);
                usedAccountNumbers.add(accountNumber);
                
                System.out.println("Akun Anda berhasil dibuat.");
                System.out.println("Nomor rekening Anda: " + accountNumber);
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
                break; // Keluar dari loop while
            } else {
                System.out.println("Opsi tidak valid. Silakan pilih opsi yang tersedia.");
            }
        }
    }
}