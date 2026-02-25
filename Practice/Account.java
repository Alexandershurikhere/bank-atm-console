package Practice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Account {
    private String cardNumber;
    private int pin;
    private double balance;
    private ArrayList<String> history;

    public Account(String cardNumber, int pin, double balance) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
        this.history=new ArrayList<>();
        addTransaction("Счет создан");
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<String> getHistoty() {
        return history;
    }
    public void withdraw(double amount){
       if(amount>balance){
           throw new IllegalArgumentException("Недостаточно средст на балансе");
       }
       else{
           balance-=amount;
           System.out.println("Снятие: "+ amount);
           System.out.println("Текущий баланс: "+ balance);
       }

    }
    public void deposit(double amount){
        balance+=amount;
        System.out.println("Внесли: "+ amount+" руб");
        System.out.println("Текущий баланс: "+ balance);
    }
    public void addTransaction(String description){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);
        String record = formattedDate + " — " + description;
        history.add(record);
    }

}
