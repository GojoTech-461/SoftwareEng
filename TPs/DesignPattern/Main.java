class Database {
    private static Database instance;
    private final String name;

    private Database(String name) {
        this.name = name;
    }

    public static synchronized Database getInstance(String name) {
        if(instance == null) {
            instance = new Database(name);
        }
        return instance;
    }

    public String getName(){
        return name;
    }

    public void getConnection(){
        System.out.println("You are connected to the database " + name);
    }
}

public class Main {
    public static void main(String[] args) {
        Database b1 = Database.getInstance("Hospital Database");
        Database b2 = Database.getInstance("Market Database");

        
        System.out.println("Database 1: " + b1.getName());
        System.out.println("Database 2: " + b2.getName());

        b1.getConnection();
        b2.getConnection();

    }
}