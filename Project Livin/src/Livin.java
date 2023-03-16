import java.util.Scanner;

// class UserAccount {
//     String nama;
//     private int id;
//     private float password;
//     int norek;
//     double balance;
//     private int pin;

// }
public class Livin {
    // CLASS USERACCOUNT
    static class UserAccount {
        private String username;
        private String password;
        private int pin;
        private double balance;

        public UserAccount(String username, String password) {
            this.username = username;
            this.password = password;
            this.balance = 0.0;
            this.pin = pin;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public int getPin() {
            return pin;
        }

        public void setPin() {
            this.pin = pin;
        }

        public void deposit(double amount) {
            balance += amount;
        }

        public void withdraw(double amount) {
            if (balance < amount) {
                System.out.println("Saldo tidak cukup");
            } else {
                balance -= amount;
            }
        }

        public void transfer(UserAccount receiver, double amount) {
            if (balance < amount) {
                System.out.println("Saldo tidak cukup");
            } else {
                withdraw(amount);
                receiver.deposit(amount);
            }
        }

        // MAIN SCOPE
        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Ënter Username: ");
            String username = scanner.nextLine();
            System.out.println("Enter Password: ");
            String password = scanner.nextLine();

            UserAccount userAccount = new UserAccount(username, password);

            System.out.println("Welcome, " + userAccount.getUsername());

            while (true) {
                System.out.println("Choose an option: ");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Transfer");
                System.out.println("4. Check balance");
                System.out.println("5. Exit");

                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Ënter amount: ");
                        double depositAmount = scanner.nextDouble();
                        userAccount.deposit(depositAmount);
                        System.out.println("Deposit successful");
                        break;
                
                    default:
                        break;
                }
            }


            // switch (option) {
            // case 1:
            // System.out.print("Enter amount: ");
            // double depositAmount = scanner.nextDouble();
            // userAccount.deposit(depositAmount);
            // System.out.println("Deposit successful");
            // break;
            // case 2:
            // System.out.print("Enter amount: ");
            // double withdrawAmount = scanner.nextDouble();
            // userAccount.withdraw(withdrawAmount);
            // System.out.println("Withdrawal successful");
            // break;
            // case 3:
            // System.out.print("Enter receiver's username: ");
            // String receiverUsername = scanner.next();
            // UserAccount receiver = getReceiverAccount(receiverUsername);
            // if (receiver != null) {
            // System.out.print("Enter amount: ");
            // double transferAmount = scanner.nextDouble();
            // userAccount.transfer(receiver, transferAmount);
            // System.out.println("Transfer successful");
            // } else {
            // System.out.println("Receiver not found");
            // }
            // break;
            // case 4:
            // System.out.println("Current balance: " + userAccount.getBalance());
            // break;
            // case 5:
            // System.out.println("Thank you for using our service");
            // System.exit(0);
            // default:
            // System.out.println("Invalid option");
            // }
            // }
            // }
        }
    }
}
