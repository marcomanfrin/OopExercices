

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class M2M3 {
    public static void main(String[] args) {
        // 25. Car
        Car c1 = new Car("Toyota", "Yaris");
        Car c2 = new Car("Mazda", "MX-5", 1998);
        System.out.println(c1);
        System.out.println(c2);

        // 26. BankAccount
        BankAccount ba = new BankAccount();
        ba.deposit(1000);
        ba.withdraw(200);
        System.out.println("Balance: " + ba.checkBalance());

        // 27. MathOperations
        MathOperations mo = new MathOperations();
        System.out.println("Sum int: " + mo.sum(2, 3));
        System.out.println("Sum double: " + mo.sum(2.5, 3.7));

        // 28. Counter
        new Counter();
        new Counter();
        System.out.println("Counter: " + Counter.getCount());

        // 29. Circle
        Circle circle = new Circle(3);
        System.out.println("Area: " + circle.calculateArea());
        System.out.println("Perimeter: " + circle.calculatePerimeter());

        // 30. Student
        Student student = new Student("Alice", new int[]{60, 70, 80});
        System.out.println("Average: " + student.average());
        System.out.println("Passed: " + student.hasPassed());

        // 31. UniStudent
        UniStudent uStudent = new UniStudent("Bob", 21, "S12345");
        uStudent.printDetails();

        // 32. Shape
        Shape rect = new Rectangle(4, 5);
        Shape circ = new CircleShape(2);
        System.out.println("Rectangle Area: " + rect.calculateArea());
        System.out.println("Circle Area: " + circ.calculateArea());

        // 33. Playable
        Playable guitar = new Guitar();
        Playable game = new VideoGame();
        guitar.play();
        game.play();
        
        // 34. Chargeable array
        Chargeable[] devices = { new Smartphone(), new Laptop(), new ElectricCar() };
        for (Chargeable d : devices) d.charge();

        // 35. Vehicle structure
        CarVehicle cv = new CarVehicle();
        Truck tr = new Truck();
        Bicycle bi = new Bicycle();
        cv.startEngine(); cv.move();
        tr.startEngine(); tr.loadCargo(); tr.move();
        bi.move();

        // 36-37. Wildlife conservation
        Animal[] animals = { new Lion(), new Penguin(), new Fish(), new Eagle() };
        for (Animal a : animals) WildlifeConservationSystem.printAnimalDetails(a);
        WildlifeConservationSystem.performAction((Flyable) animals[3]); // Eagle
        WildlifeConservationSystem.performAction((Swimmable) animals[1]); // Penguin

        // 38. SIM system
        SIM sim = new SIM("1234567890", 10.0);
        sim.addCall(new Call(2, "111"));
        sim.addCall(new Call(3, "222"));
        sim.addCall(new Call(1, "333"));
        sim.addCall(new Call(4, "444"));
        sim.addCall(new Call(5, "555"));
        sim.addCall(new Call(6, "666")); // Overwrites first
        sim.printCalls();

        // 39. E-commerce system
        Customer cust = new Customer("C1", "Mario", "mario@mail.com");
        Cart cart = new Cart(cust);
        Product p1 = new Product("P1", "Laptop", 1000, 2);
        Product p2 = new Product("P2", "Mouse", 20, 5);
        cart.addProduct(p1); cart.addProduct(p2);
        cart.printDetails();

        // 40. CarBuilder
        Car myCar = new CarBuilder().setBrand("Toyota").setModel("Supra").setEngine("3.0L").addFeature("Turbo").build();
        System.out.println("Built Car: " + myCar);

        // 41. Document Factory
        Document doc = DocumentFactory.createDocument("pdf");
        doc.generate();

        // 42. Proxy Image
        Image img = new ProxyImage("test.jpg");
        img.display(); // loads and displays
        img.display(); // only displays

        // 43. Home Theater Facade
        HomeTheaterFacade ht = new HomeTheaterFacade();
        ht.startMovie();
        ht.endMovie();

        // 44-45. Bridge Pattern
        RemoteControl basic = new BasicRemote(new TV());
        basic.powerOn();
        basic.powerOff();

        RemoteControl advanced = new AdvancedRemote(new Radio());
        advanced.powerOn();
        advanced.powerOff();
    }
}

// 25. Class Car
class Car {
    String brand, model;
    int year;

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.year = LocalDate.now().getYear();
    }

    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public String toString() {
        return brand + " " + model + " (" + year + ")";
    }
}

// 26. Class BankAccount
class BankAccount {
    private double balance;

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public double withdraw(double amount) {
        if (amount <= balance) {balance -= amount; return amount;}
        else {System.out.println("Insufficient funds."); return 0;}
    }

    public double checkBalance() {
        return balance;
    }
}

// 27. Class MathOperations
class MathOperations {
    public int sum(int a, int b) {
        return a + b;
    }

    public double sum(double a, double b) {
        return a + b;
    }
}

// 28. Class Counter
class Counter {
    static int count = 0;

    public Counter() {
        count++;
    }

    public static int getCount() {
        return count;
    }
}

// 29. Class Circle
class Circle {
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

// 30. Class Student
class Student {
    String name;
    int[] grades;

    public Student(String name, int[] grades) {
        this.name = name;
        this.grades = grades;
    }

    public double average() {
        int sum = 0;
        for (int grade : grades) sum += grade;
        return grades.length > 0 ? (double) sum / grades.length : 0;
    }

    public boolean hasPassed() {
        return average() >= 50;
    }
}

// 31. Class Person and Student subclass
class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class UniStudent extends Person {
    String studentId;

    public UniStudent(String name, int age, String studentId) {
        super(name, age);
        this.studentId = studentId;
    }

    public void printDetails() {
        System.out.println("Name: " + name + ", Age: " + age + ", ID: " + studentId);
    }
}

// 32. Abstract class Shape
abstract class Shape {
    abstract double calculateArea();
}

class Rectangle extends Shape {
    double length, width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    double calculateArea() {
        return length * width;
    }
}

class CircleShape extends Shape {
    double radius;

    public CircleShape(double radius) {
        this.radius = radius;
    }

    double calculateArea() {
        return Math.PI * radius * radius;
    }
}

// 33. Interface Playable
interface Playable {
    void play();
}

class Guitar implements Playable {
    public void play() {
        System.out.println("Strumming the guitar...");
    }
}

class VideoGame implements Playable {
    public void play() {
        System.out.println("Playing the video game...");
    }
}

// 34. Chargeable Interface and Device Hierarchy
interface Chargeable {
    void charge();
}

abstract class Device {
    abstract void connectToWifi();
}

class Smartphone extends Device implements Chargeable {
    public void connectToWifi() {
        System.out.println("Smartphone connected to WiFi.");
    }
    public void charge() {
        System.out.println("Charging Smartphone.");
    }
}

class Laptop extends Device implements Chargeable {
    public void connectToWifi() {
        System.out.println("Laptop connected to WiFi.");
    }
    public void charge() {
        System.out.println("Charging Laptop.");
    }
}

class ElectricCar implements Chargeable {
    public void charge() {
        System.out.println("Charging Electric Car.");
    }
}

// 35. Vehicle Structure
interface Movable {
    void move();
}

interface Loadable {
    void loadCargo();
}

interface EnginePowered {
    void startEngine();
}

abstract class AbstractVehicle {
    // common properties or methods (if any)
}

class CarVehicle extends AbstractVehicle implements Movable, EnginePowered {
    public void startEngine() {
        System.out.println("Car engine started.");
    }
    public void move() {
        System.out.println("Car is moving.");
    }
}

class Truck extends AbstractVehicle implements Loadable, Movable, EnginePowered {
    public void startEngine() {
        System.out.println("Truck engine started.");
    }
    public void loadCargo() {
        System.out.println("Truck loading cargo.");
    }
    public void move() {
        System.out.println("Truck is moving.");
    }
}

class Bicycle extends AbstractVehicle implements Movable {
    public void move() {
        System.out.println("Bicycle is moving.");
    }
}

// 36. Wildlife Conservation
abstract class Animal {
    abstract void eat();
    abstract void sleep();
}

interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

class Lion extends Animal {
    public void eat() { System.out.println("Lion eating."); }
    public void sleep() { System.out.println("Lion sleeping."); }
}

class Penguin extends Animal implements Swimmable {
    public void eat() { System.out.println("Penguin eating."); }
    public void sleep() { System.out.println("Penguin sleeping."); }
    public void swim() { System.out.println("Penguin swimming."); }
}

class Fish extends Animal implements Swimmable {
    public void eat() { System.out.println("Fish eating."); }
    public void sleep() { System.out.println("Fish sleeping."); }
    public void swim() { System.out.println("Fish swimming."); }
}

class Eagle extends Animal implements Flyable {
    public void eat() { System.out.println("Eagle eating."); }
    public void sleep() { System.out.println("Eagle sleeping."); }
    public void fly() { System.out.println("Eagle flying."); }
}

// 37. WildlifeConservationSystem
class WildlifeConservationSystem {
    static void printAnimalDetails(Animal animal) {
        animal.eat();
        animal.sleep();
    }

    static void performAction(Flyable f) {
        f.fly();
    }

    static void performAction(Swimmable s) {
        s.swim();
    }
}

// 38. SIM Card system
class Call {
    int duration;
    String number;

    public Call(int duration, String number) {
        this.duration = duration;
        this.number = number;
    }

    public String toString() {
        return "Call to " + number + " lasted " + duration + " minutes.";
    }
}

class SIM {
    String number;
    double credit;
    Call[] lastCalls = new Call[5];
    int index = 0;

    public SIM(String number, double credit) {
        this.number = number;
        this.credit = credit;
    }

    public void addCall(Call call) {
        lastCalls[index % 5] = call;
        index++;
    }

    public void printCalls() {
        for (Call call : lastCalls) {
            if (call != null)
                System.out.println(call);
        }
    }
}

// 39. E-commerce system
class Product {
    String code, description;
    double price;
    int quantity;

    public Product(String code, String description, double price, int quantity) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
}

class Customer {
    String id, name, email;
    LocalDate registrationDate;

    public Customer(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.registrationDate = LocalDate.now();
    }
}

class Cart {
    Customer customer;
    List<Product> products = new ArrayList<>();
    double totalCost = 0.0;

    public Cart(Customer customer) {
        this.customer = customer;
    }

    public void addProduct(Product p) {
        if (p.quantity > 0) {
            products.add(p);
            totalCost += p.price;
        }
    }

    public void removeProduct(Product p) {
        if (products.remove(p)) {
            totalCost -= p.price;
        }
    }

    public void printDetails() {
        System.out.println("Cart of " + customer.name);
        for (Product p : products) {
            System.out.println(p.description + " - " + p.price);
        }
        System.out.println("Total: " + totalCost);
    }
}

// 40. Builder Pattern for Car
class CarBuilder {
    private String brand, model;
    private List<String> features = new ArrayList<>();

    public CarBuilder setBrand(String brand) { this.brand = brand; return this; }
    public CarBuilder setModel(String model) { this.model = model; return this; }
    public CarBuilder setEngine(String engine) { return this; }
    public CarBuilder addFeature(String feature) { features.add(feature); return this; }

    public Car build() {
        Car car = new Car(brand, model);
        return car;
    }
}

// 41. Factory Pattern for documents
abstract class Document {
    abstract void generate();
}

class PDFDocument extends Document {
    void generate() { System.out.println("Generating PDF Document"); }
}

class WordDocument extends Document {
    void generate() { System.out.println("Generating Word Document"); }
}

class TextDocument extends Document {
    void generate() { System.out.println("Generating Text Document"); }
}

class DocumentFactory {
    public static Document createDocument(String type) {
        return switch (type.toLowerCase()) {
            case "pdf" -> new PDFDocument();
            case "word" -> new WordDocument();
            case "text" -> new TextDocument();
            default -> throw new IllegalArgumentException("Unknown type");
        };
    }
}

// 42. Proxy Pattern for images
interface Image {
    void display();
}

class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading " + filename);
    }

    public void display() {
        System.out.println("Displaying " + filename);
    }
}

class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    public void display() {
        if (realImage == null)
            realImage = new RealImage(filename);
        realImage.display();
    }
}

// 43. Home Theater Facade
class DVDPlayer { 
    void on() { System.out.println("DVD Player On"); } 
    void off() { System.out.println("DVD Player Off"); }
}
class Projector { 
    void on() { System.out.println("Projector On"); } 
    void off() { System.out.println("Projector Off"); }
}
class SoundSystem { 
    void on() { System.out.println("Sound System On"); } 
    void off() { System.out.println("Sound System Off"); }
}
class Lights { 
    void dim() { System.out.println("Lights Dimmed"); } 
    void on() { System.out.println("Lights On"); }
}

class HomeTheaterFacade {
    DVDPlayer dvd = new DVDPlayer();
    Projector proj = new Projector();
    SoundSystem sound = new SoundSystem();
    Lights lights = new Lights();

    void startMovie() {
        lights.dim();
        dvd.on();
        proj.on();
        sound.on();
        System.out.println("Movie Started");
    }

    void endMovie() {
        lights.on();
        dvd.off();
        proj.off();
        sound.off();
        System.out.println("Movie Ended");
    }
}

// 44-45. Bridge Pattern for remotes and devices
interface DeviceBridge {
    void turnOn();
    void turnOff();
}

class TV implements DeviceBridge {
    public void turnOn() { System.out.println("TV On"); }
    public void turnOff() { System.out.println("TV Off"); }
}

class Radio implements DeviceBridge {
    public void turnOn() { System.out.println("Radio On"); }
    public void turnOff() { System.out.println("Radio Off"); }
}

abstract class RemoteControl {
    protected DeviceBridge device;

    public RemoteControl(DeviceBridge device) {
        this.device = device;
    }

    abstract void powerOn();
    abstract void powerOff();
}

class BasicRemote extends RemoteControl {
    public BasicRemote(DeviceBridge device) { super(device); }

    void powerOn() { device.turnOn(); }
    void powerOff() { device.turnOff(); }
}

class AdvancedRemote extends RemoteControl {
    public AdvancedRemote(DeviceBridge device) { super(device); }

    void powerOn() {
        device.turnOn();
    }

    void powerOff() {
        device.turnOff();
    }
}
