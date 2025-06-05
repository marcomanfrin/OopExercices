

import java.time.LocalDate;

// 1. Class Car
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

// 2. Class BankAccount
class BankAccount {
    private double balance;

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance) balance -= amount;
        else System.out.println("Insufficient funds.");
    }

    public double checkBalance() {
        return balance;
    }
}

// 3. Class MathOperations
class MathOperations {
    public int sum(int a, int b) {
        return a + b;
    }

    public double sum(double a, double b) {
        return a + b;
    }
}

// 4. Class Counter
class Counter {
    static int count = 0;

    public Counter() {
        count++;
    }

    public static int getCount() {
        return count;
    }
}

// 5. Class Circle
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

// 6. Class Student
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

// 7. Class Person and Student subclass
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

// 8. Abstract class Shape
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

// 9. Interface Playable
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

public class M2M3 {
    public static void main(String[] args) {
        // 1. Car
        Car c1 = new Car("Toyota", "Yaris");
        Car c2 = new Car("Mazda", "MX-5", 1998);
        System.out.println(c1);
        System.out.println(c2);

        // 2. BankAccount
        BankAccount ba = new BankAccount();
        ba.deposit(1000);
        ba.withdraw(200);
        System.out.println("Balance: " + ba.checkBalance());

        // 3. MathOperations
        MathOperations mo = new MathOperations();
        System.out.println("Sum int: " + mo.sum(2, 3));
        System.out.println("Sum double: " + mo.sum(2.5, 3.7));

        // 4. Counter
        new Counter();
        new Counter();
        System.out.println("Counter: " + Counter.getCount());

        // 5. Circle
        Circle circle = new Circle(3);
        System.out.println("Area: " + circle.calculateArea());
        System.out.println("Perimeter: " + circle.calculatePerimeter());

        // 6. Student
        Student student = new Student("Alice", new int[]{60, 70, 80});
        System.out.println("Average: " + student.average());
        System.out.println("Passed: " + student.hasPassed());

        // 7. UniStudent
        UniStudent uStudent = new UniStudent("Bob", 21, "S12345");
        uStudent.printDetails();

        // 8. Shape
        Shape rect = new Rectangle(4, 5);
        Shape circ = new CircleShape(2);
        System.out.println("Rectangle Area: " + rect.calculateArea());
        System.out.println("Circle Area: " + circ.calculateArea());

        // 9. Playable
        Playable guitar = new Guitar();
        Playable game = new VideoGame();
        guitar.play();
        game.play();
    }
}
