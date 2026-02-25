package Practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
//It is class with private fields

public class ATM {
    //this is encapsulation
    private HashMap<String,Account> accounts;
    private Account currentAccount;

    public ATM() {
        accounts = new HashMap<>();
        currentAccount = null;
    }
//this method create an account
    public  void addAccount(Account account){
        if(accounts.containsKey(account.getCardNumber())){
            System.out.println("Счет с таким номером уже существует");
        }
        else{
            accounts.put(account.getCardNumber(),account);
            System.out.println("Счет успешно добавлен");
        }
    }
    // this method check a number card and pin
    public boolean authorize(String cardNumber, int pin) {
        Account account = accounts.get(cardNumber);
        if (account != null && account.getPin() == pin) {
            currentAccount = account;
            return true;
        }
        return false;
    }
    //this method delete an account of user
    public void logout(){
        currentAccount = null;

    }
    //this method show menu
    public void showMenu() {
        System.out.println("\n=== МЕНЮ ===");
        System.out.println("1. Проверить баланс");
        System.out.println("2. Снять наличные");
        System.out.println("3. Пополнить счёт");

        System.out.println("4. История операций");
        System.out.println("5. Выйти");
        System.out.print("Выберите пункт: ");
    }
    public void checkBalance(){
        if(currentAccount==null){
            System.out.println("Сначала авторизуйтесь");
            return;
        }
        System.out.printf("Ваш баланс: %.2f руб.\n", currentAccount.getBalance());
    }
    //this method withdraw money with the account
    public void withdraw(){
        if(currentAccount==null){
            System.out.println("Сначала авторизуйтесь");
            return;
        }
        Scanner scanner=new Scanner(System.in);
        System.out.println("Введите сумму снятия: ");
        double amount=scanner.nextDouble();
        scanner.nextLine();
        try {
            currentAccount.withdraw(amount);
            System.out.println("Операция успешно выполненна ");
        }catch (IllegalArgumentException e){
            System.out.println("Ошибка: "+ e.getMessage());
        }

    }
    //this method put money on the account
    public void deposit(){
        if(currentAccount==null){
            System.out.println("Сначала авторизуйтесь");
            return;
        }
        Scanner scanner=new Scanner(System.in);
        System.out.println("Введите сумму для пополнения: ");
        double amount=scanner.nextDouble();
        scanner.nextLine();
        try {
            currentAccount.deposit(amount);
            System.out.println("Операция успешно выполненна ");
        }catch (IllegalArgumentException e){
            System.out.println("Ошибка: "+ e.getMessage());
        }

    }
    //this method show the history the  user
    public void showHistory() {
        if (currentAccount == null) {
            System.out.println("Сначала авторизуйтесь!");
            return;
        }
        ArrayList<String> history = currentAccount.getHistoty();
        if (history.isEmpty()) {
            System.out.println("История операций пуста.");
        } else {
            System.out.println("Последние операции:");
            // Покажем последние 5 записей
            int start = Math.max(0, history.size() - 5);
            for (int i = start; i < history.size(); i++) {
                System.out.println(history.get(i));
            }
        }
    }
    // this method work with the user
    public void run() {
       Scanner scanner=new Scanner(System.in);
        System.out.println("Добро пожаловать в банкомат");
        System.out.print("Введите карту: ");
        String card=scanner.nextLine();
        System.out.print("Введите пин-код: ");
        int pin=scanner.nextInt();
        scanner.nextLine();
        if(authorize(card,pin)){
            System.out.println("Успешная авторизация");
            showMenu();
            int choice=scanner.nextInt();
            scanner.nextLine();
            boolean sesion=true;
            scanner.nextLine();
            while (sesion){
                switch (choice){
                    case 1 -> checkBalance();
                    case 2 -> withdraw();
                    case 3 -> deposit();
                    case 4 -> showHistory();
                    case 5 ->{
                        sesion=false;
                        logout();
                        System.out.println("До свидания");
                    }
                    default -> System.out.println("Некоректный ввод");
                }
            }

        }else {
            System.out.println("Неверна указана карта или пин-код");
        }
    }
}
