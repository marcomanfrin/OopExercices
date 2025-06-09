
import java.util.*;
import java.util.concurrent.*;

// 82. NumberPrinter Thread
class NumberPrinter extends Thread {
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(getName() + ": " + i);
            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        }
    }
}

// 83. FileDownloader Runnable
class FileDownloader implements Runnable {
    private final String fileName;

    public FileDownloader(String fileName) {
        this.fileName = fileName;
    }

    public void run() {
        for (int i = 0; i <= 100; i += 20) {
            System.out.println("Downloading " + fileName + ": " + i + "%");
            try { Thread.sleep(300); } catch (InterruptedException ignored) {}
        }
    }
}

// 84. ExecutorService
class SimpleTask implements Runnable {
    private final int id;

    public SimpleTask(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Task " + id + " running on thread " + Thread.currentThread().getName());
    }
}

// 85. Synchronized Counter
class SharedCounter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

// 86. Producer-Consumer
class Store {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 5;

    public synchronized void produce(int value) throws InterruptedException {
        while (queue.size() == CAPACITY)
            wait();
        queue.add(value);
        System.out.println("Produced: " + value);
        notify();
    }

    public synchronized void consume() throws InterruptedException {
        while (queue.isEmpty())
            wait();
        int val = queue.poll();
        System.out.println("Consumed: " + val);
        notify();
    }
}

// 87. Deadlock simulation
class MyPrinter {}
class MyScanner {}

class DeadlockSimulator {
    public static void simulate() {
        final MyPrinter printer = new MyPrinter();
        final MyScanner scanner = new MyScanner();

        Thread t1 = new Thread(() -> {
            synchronized (printer) {
                System.out.println("Thread1 locked printer");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (scanner) {
                    System.out.println("Thread1 locked scanner");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (scanner) {
                System.out.println("Thread2 locked scanner");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (printer) {
                    System.out.println("Thread2 locked printer");
                }
            }
        });

        t1.start();
        t2.start();
    }
}

// 88. Observer pattern with Stock
interface StockObserver {
    void update(String stock, double price);
}

class Stock {
    private final List<StockObserver> observers = new ArrayList<>();
    private String name;
    private double price;

    public Stock(String name) { this.name = name; }

    public void addObserver(StockObserver obs) {
        observers.add(obs);
    }

    public void removeObserver(StockObserver obs) {
        observers.remove(obs);
    }

    public void setPrice(double price) {
        this.price = price;
        for (StockObserver obs : observers)
            obs.update(name, price);
    }
}

class EmailAlert implements StockObserver {
    public void update(String stock, double price) {
        System.out.println("EmailAlert: " + stock + " changed to $" + price);
    }
}

class MobileApp implements StockObserver {
    public void update(String stock, double price) {
        System.out.println("MobileApp: " + stock + " changed to $" + price);
    }
}

// 89. Singleton AppConfig
class AppConfig {
    private static volatile AppConfig instance;
    private AppConfig() {}

    public static AppConfig getInstance() {
        if (instance == null) {
            synchronized (AppConfig.class) {
                if (instance == null)
                    instance = new AppConfig();
            }
        }
        return instance;
    }

    public String getSetting() {
        return "Config value";
    }
}

// 90. Chatroom with Observer
interface ChatUser {
    void receive(String message);
}

class ChatRoom {
    private final List<ChatUser> users = new ArrayList<>();

    public void register(ChatUser user) {
        users.add(user);
    }

    public void unregister(ChatUser user) {
        users.remove(user);
    }

    public void postMessage(String message) {
        for (ChatUser user : users)
            user.receive(message);
    }
}

class User implements ChatUser {
    private final String name;

    public User(String name) { this.name = name; }

    public void receive(String message) {
        System.out.println(name + " received: " + message);
    }
}

// MAIN
public class M7 {
    public static void main(String[] args) {
        // 82
        NumberPrinter np1 = new NumberPrinter();
        NumberPrinter np2 = new NumberPrinter();
        np1.start(); np2.start();
        try { np1.join(); np2.join(); } catch (InterruptedException ignored) {}

        // 83
        Thread d1 = new Thread(new FileDownloader("file1.zip"));
        Thread d2 = new Thread(new FileDownloader("file2.zip"));
        d1.start(); d2.start();
        try { d1.join(); d2.join(); } catch (InterruptedException ignored) {}

        // 84
        ExecutorService service = Executors.newFixedThreadPool(2);
        for (int i = 1; i <= 5; i++) {
            service.submit(new SimpleTask(i));
        }
        service.shutdown();

        // 85
        SharedCounter counter = new SharedCounter();
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) counter.increment();
        };
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) threads[i] = new Thread(task);
        for (Thread t : threads) t.start();
        for (Thread t : threads)
            try { t.join(); } catch (InterruptedException ignored) {}
        System.out.println("Final count (synchronized): " + counter.getCount());

        // 86
        Store store = new Store();
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    store.produce(i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ignored) {}
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    store.consume();
                    Thread.sleep(1200);
                }
            } catch (InterruptedException ignored) {}
        });
        producer.start(); consumer.start();
        try { producer.join(); consumer.join(); } catch (InterruptedException ignored) {}

        // 87
        DeadlockSimulator.simulate();

        // 88
        Stock s = new Stock("GOOG");
        EmailAlert e = new EmailAlert();
        MobileApp m = new MobileApp();
        s.addObserver(e); s.addObserver(m);
        s.setPrice(1450);
        s.removeObserver(e);
        s.setPrice(1480);

        // 89
        AppConfig config = AppConfig.getInstance();
        System.out.println("AppConfig: " + config.getSetting());

        // 90
        ChatRoom room = new ChatRoom();
        User u1 = new User("UserA");
        User u2 = new User("UserB");
        User u3 = new User("UserC");
        room.register(u1); room.register(u2); room.register(u3);
        room.postMessage("Welcome");
        room.unregister(u2);
        room.postMessage("UserB has left");
    }
}
