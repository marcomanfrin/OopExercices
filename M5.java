
// M5 - Collezioni, Stream, Generics, Comparator, Optional, Iterator

import java.time.*;
import java.util.*;
import java.util.stream.*;

public class M5 {
    public static void main(String[] args) {
        // 56 - ArrayList vs LinkedList
        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();
        arrayList.add("Alice");
        arrayList.add("Bob");
        linkedList.add("Alice");
        linkedList.add("Bob");
        System.out.println("ArrayList: " + arrayList);
        System.out.println("LinkedList: " + linkedList);
        // LinkedList inserimenti/cancellazioni più veloci in mezzo

        // 57 - Box generico
        Box<Integer> intBox = new Box<>();
        intBox.setContent(42);
        Box<String> strBox = new Box<>();
        strBox.setContent("Hello");
        Box<Player> playerBox = new Box<>();
        playerBox.setContent(new Player("Marco"));
        System.out.println(intBox.getContent());
        System.out.println(strBox.getContent());
        System.out.println(playerBox.getContent());

        // 58 - Lobby multiplayer
        List<Player> room = new ArrayList<>();
        Queue<Player> matchmaking = new LinkedList<>();
        Player p1 = new Player("Luffy"), p2 = new Player("Zoro");
        matchmaking.add(p1);
        matchmaking.add(p2);
        room.add(matchmaking.poll());
        System.out.println("Stanza: " + room);
        //Matchmaking è FIFO -> coda perfetta per accodare i giocatori

        // 59 - Statistiche e achievements
        Map<Player, PlayerData> data = new HashMap<>();
        data.put(p1, new PlayerData());
        data.get(p1).unlock("First Kill");
        data.get(p1).updateStat("Monsters", 3);
        System.out.println(data);

        // 60 - Eventi recenti
        GameEventLog log = new GameEventLog();
        log.log("Enemy defeated");
        for (String e : log)
            System.out.println("Evento recente: " + e);

        // 61 - Comparable vs Comparator
        List<CCharacter> chars = List.of(
                new CCharacter("A", 5, 100),
                new CCharacter("B", 2, 200),
                new CCharacter("C", 5, 50));
        chars.stream().sorted().forEach(System.out::println); // by level
        chars.stream().sorted(Comparator.comparingInt((CCharacter c) -> c.xp).reversed()).forEach(System.out::println);
        chars.stream().sorted(Comparator.comparing(c -> c.name)).forEach(System.out::println);

        // 62 - Operazioni su Item
        List<Item> items = new ArrayList<>(List.of(
                new Item("Spada", 100), new Item("Pozione", 5), new Item("Scudo", 50)));
        items.forEach(System.out::println);
        items.removeIf(i -> i.price < 10);
        double media = items.stream().mapToDouble(Item::getPrice).average().orElse(0);
        System.out.println("Media prezzi: " + media);

        // 63 - Stream su Transaction
        List<Transaction> tx = List.of(
                new Transaction("Luffy", 120, LocalDate.of(2024, 1, 1)),
                new Transaction("Zoro", 300, LocalDate.of(2024, 2, 1)),
                new Transaction("Luffy", 80, LocalDate.of(2024, 3, 1)));
        tx.stream().filter(t -> t.getAmount() > 100).forEach(System.out::println);
        tx.stream().collect(Collectors.groupingBy(Transaction::getUser,
                Collectors.summingDouble(Transaction::getAmount)))
                .entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3).forEach(System.out::println);
        tx.stream().sorted(Comparator.comparing(Transaction::getDate)).forEach(System.out::println);

        // 64 - Raggruppamento prodotti
        List<Product> products = List.of(
                new Product("Katana", "Weapon", 20),
                new Product("Potion", "Consumable", 100),
                new Product("Dagger", "Weapon", 50));
        Map<String, Product> top = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Product::getSold)),
                                Optional::get)));
        System.out.println(top);

        // 65 - Numeri pari trasformati
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> res1 = new ArrayList<>();
        for (int i : nums)
            if (i % 2 == 0)
                res1.add(i * 3);
        System.out.println("For-loop: " + res1);
        List<Integer> res2 = nums.stream().filter(n -> n % 2 == 0).map(n -> n * 3).collect(Collectors.toList());
        System.out.println("Stream: " + res2);

        // 66 - Filtraggio libri in mappa
        List<Book> books = List.of(
                new Book("A", "Author1", 2012, 20),
                new Book("B", "Author2", 2005, 15),
                new Book("A", "Author1", 2013, 25));
        Map<String, Double> bookMap = books.stream()
                .filter(b -> b.getYear() > 2010)
                .collect(Collectors.toMap(Book::getTitle, Book::getPrice,
                        Double::max));
        System.out.println(bookMap);

        // 67 - Optional + Stream
        List<Order> orders = List.of(
                new Order(new Customer("mail@example.com")),
                new Order(null),
                new Order(new Customer(null)));
        List<String> emails = orders.stream()
                .map(Order::getCustomer).flatMap(Optional::stream)
                .map(Customer::getEmail).flatMap(Optional::stream)
                .collect(Collectors.toList());
        System.out.println(emails);
    }
}

// Player class usata in più esercizi
class Player {
    String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Player))
            return false;
        return name.equals(((Player) o).name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

// 57 - Classe generica Box
class Box<T> {
    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}

// 59 - Tracciamento achievements
class PlayerData {
    Set<String> achievements = new HashSet<>();
    Map<String, Integer> stats = new HashMap<>();

    void unlock(String achievement) {
        achievements.add(achievement);
    }

    void updateStat(String key, int value) {
        stats.put(key, stats.getOrDefault(key, 0) + value);
    }
}

// 60 - GameEventLog con Iterator Pattern
/*
 * L’Iterator Pattern separa la logica di attraversamento dalla struttura dati,
 * offrendo un’interfaccia uniforme (hasNext(), next()) per navigare collezioni
 * eterogenee senza conoscere la loro implementazione interna; supporta
 * traversal multipli e lazy evaluation e permette di estendere facilmente il
 * comportamento, ad esempio con un FilterIterator che, decorando un iteratore
 * esistente e accettando un predicato, restituisce solo gli elementi che
 * soddisfano una certa condizione. Grazie a questo approccio modulare e
 * componibile, è possibile riutilizzare la stessa classe FilterIterator con
 * predicati diversi o concatenarne più istanze per applicare filtri complessi,
 * mantenendo il codice pulito e senza modificare le collezioni di base.
 */
class GameEventLog implements Iterable<String> {
    private final List<Event> events = new ArrayList<>();

    public void log(String message) {
        events.add(new Event(message));
    }

    @Override
    public Iterator<String> iterator() {
        return new RecentEventIterator();
    }

    private class Event {
        String message;
        Instant timestamp = Instant.now();

        Event(String msg) {
            this.message = msg;
        }
    }

    private class RecentEventIterator implements Iterator<String> {
        private final Iterator<Event> internal = events.iterator();
        private final Instant cutoff = Instant.now().minus(Duration.ofMinutes(5));
        private Event next;

        public boolean hasNext() {
            while (internal.hasNext()) {
                Event candidate = internal.next();
                if (candidate.timestamp.isAfter(cutoff)) {
                    next = candidate;
                    return true;
                }
            }
            return false;
        }

        public String next() {
            return next.message;
        }
    }
}

// 61 - Classe Character ordinabile
/*
 * L’interfaccia Comparable consente a una classe di definire al suo interno un
 * unico “ordine naturale” tramite il metodo compareTo(), rendendo immediato
 * l’uso di strutture come TreeSet o Arrays.sort() senza parametri; invece
 * Comparator è un’interfaccia esterna, implementabile (anche con lambda) per
 * specificare criteri di ordinamento multipli o ad hoc — ideale quando si
 * vogliono più modalità di confronto, si lavora con classi di terze parti o si
 * desiderano logiche temporanee senza modificare il modello di dominio.
 */
class CCharacter implements Comparable<CCharacter> {
    String name;
    int level;
    int xp;

    CCharacter(String n, int l, int x) {
        name = n;
        level = l;
        xp = x;
    }

    @Override
    public int compareTo(CCharacter o) {
        return Integer.compare(this.level, o.level);
    }

    @Override
    public String toString() {
        return name + ": L" + level + ", XP=" + xp;
    }
}

// 62 - Classe Item
class Item {
    String name;
    double price;

    Item(String n, double p) {
        name = n;
        price = p;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name + ": €" + price;
    }
}

// 63 - Classe Transaction
/*
 * I loop imperativi offrono massimo controllo e prestazioni pure, ma richiedono
 * più codice e sono più soggetti a errori boilerplate. L’API Stream consente
 * trasformazioni** fluide**, dichiarative e parallelizzabili con poche righe,
 * migliorando la manutenibilità, ma introduce un layer di overhead e rende il
 * debug più complesso. La scelta dipende quindi da chiarezza di intenti,
 * complessità della logica e necessità di performance o parallelismo.
 */
class Transaction {
    String user;
    double amount;
    LocalDate date;

    Transaction(String u, double a, LocalDate d) {
        user = u;
        amount = a;
        date = d;
    }

    public String getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
}

// 64 - Classe Product
class Product {
    String name, category;
    int sold;

    Product(String n, String c, int s) {
        name = n;
        category = c;
        sold = s;
    }

    public String getCategory() {
        return category;
    }

    public int getSold() {
        return sold;
    }

    public String toString() {
        return name + " (" + category + "): " + sold;
    }
}

// 66 - Book
class Book {
    String title, author;
    int year;
    double price;

    Book(String t, String a, int y, double p) {
        title = t;
        author = a;
        year = y;
        price = p;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }
}

// 67 - Order e Customer
class Customer {
    Optional<String> email;

    Customer(String email) {
        this.email = Optional.ofNullable(email);
    }

    public Optional<String> getEmail() {
        return email;
    }
}

class Order {
    Optional<Customer> customer;

    Order(Customer c) {
        this.customer = Optional.ofNullable(c);
    }

    public Optional<Customer> getCustomer() {
        return customer;
    }
}
