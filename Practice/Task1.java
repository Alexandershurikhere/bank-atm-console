package Practice;

public class Task1 {
    public static void main(String[] args){
        ATM atm = new ATM();
        // add the account
        atm.addAccount(new Account("1111", 1234, 1000));
        atm.addAccount(new Account("2222", 5678, 500));
        atm.run();



    }

}

