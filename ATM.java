package dow;
import java.util.*;
class Transaction {
	    private int transactionId;
	    private Date date;
	    private double amount;

	    public Transaction(int transactionId, double amount) {
	        this.transactionId = transactionId;
	        this.date = new Date();
	        this.amount = amount;
	    }

	    public double getAmount() {
	        return amount;
	    }

	    public Date getDate() {
	        return date;
	    }
	}

	class Withdrawal extends Transaction {
	    public Withdrawal(int transactionId, double amount) {
	        super(transactionId, amount);
	    }
	}

	class Deposit extends Transaction {
	    public Deposit(int transactionId, double amount) {
	        super(transactionId, amount);
	    }
	}

	class User {
	    private String userId;
	    private String pin;
	    private double balance;
	    private List<Transaction> transactionHistory;

	    public User(String userId, String pin) {
	        this.userId = userId;
	        this.pin = pin;
	        this.balance = 0.0;
	        this.transactionHistory = new ArrayList<>();
	    }

	    public String getUserId() {
	        return userId;
	    }

	    public boolean authenticate(String enteredPin) {
	        return pin.equals(enteredPin);
	    }

	    public double getBalance() {
	        return balance;
	    }

	    public List<Transaction> getTransactionHistory() {
	        return transactionHistory;
	    }

	    public void deposit(double amount, int transactionId) {
	        balance += amount;
	        transactionHistory.add(new Deposit(transactionId, amount));
	    }

	    public boolean withdraw(double amount, int transactionId) {
	        if (balance >= amount) {
	            balance -= amount;
	            transactionHistory.add(new Withdrawal(transactionId, amount));
	            return true;
	        }
	        return false;
	    }
	}

	public class ATM {
	    private Map<String, User> users;
	    private int transactionIdCounter;

	    public ATM() {
	        users = new HashMap<>();
	        transactionIdCounter = 1;
	    }

	    public void addUser(String userId, String pin) {
	        User newUser = new User(userId, pin);
	        users.put(userId, newUser);
	    }

	    public boolean loginUser(String userId, String enteredPin) {
	        User user = users.get(userId);
	        return user != null && user.authenticate(enteredPin);
	    }

	    public void showTransactionHistory(String userId) {
	        User user = users.get(userId);
	        if (user != null) {
	            List<Transaction> history = user.getTransactionHistory();
	            System.out.println("Transaction History for User: " + userId);
	            for (Transaction transaction : history) {
	                System.out.println("Transaction ID: " + transactionIdCounter++);
	                System.out.println("Date: " + transaction.getDate());
	                System.out.println("Amount: \u20B9" + transaction.getAmount());
	            }
	        }
	    }

	    public static void main(String[] args) {
	        ATM atm = new ATM();
	        atm.addUser("user1", "1234");
	        atm.addUser("user2", "5678");

	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Enter User ID: ");
	        String userId = scanner.nextLine();
	        System.out.print("Enter PIN: ");
	        String pin = scanner.nextLine();

	        if (atm.loginUser(userId, pin)) {
	            System.out.println("Login Successful!");

	            while (true) {
	                System.out.println("\nATM Menu:");
	                System.out.println("1. View Transaction History");
	                System.out.println("2. Withdraw");
	                System.out.println("3. Deposit");
	                System.out.println("4. Quit");
	                System.out.print("Enter your choice (1/2/3/4): ");

	                int choice = scanner.nextInt();
	                scanner.nextLine();  // Consume newline

	                switch (choice) {
	                    case 1:
	                        atm.showTransactionHistory(userId);
	                        break;
	                    case 2:
	                        System.out.print("Enter withdrawal amount: \u20B9");
	                        double withdrawalAmount = scanner.nextDouble();
	                        boolean withdrawalSuccess = atm.users.get(userId).withdraw(withdrawalAmount, atm.transactionIdCounter);
	                        if (withdrawalSuccess) {
	                            System.out.println("Withdrawal successful!");
	                        } else {
	                            System.out.println("Insufficient funds.");
	                        }
	                        break;
	                    case 3:
	                        System.out.print("Enter deposit amount: " + "\u20B9");
	                        double depositAmount = scanner.nextDouble();
	                        atm.users.get(userId).deposit(depositAmount, atm.transactionIdCounter);
	                        System.out.println("Deposit successful!");
	                        break;
	                    case 4:
	                        System.out.println("Thank you for using the ATM. Goodbye!");
	                        System.exit(0);
	                    default:
	                        System.out.println("Invalid choice. Please try again.");
	                }
	            }
	        } else {
	            System.out.println("Login Failed. Invalid User ID or PIN.");
	        }
	    scanner.close();	    }
	}
