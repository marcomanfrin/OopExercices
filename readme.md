# Object Oriented Programming - Practice

These exercises support learning and final exam preparation. Some are designed to deepen understanding beyond the basic material.

## 📚 Module Index

- [M1 - Basic exercises](#m1)
- [M2 & M3 - Classes, inheritance, interfaces, patterns](#m2--m3)
- [M4 - Exceptions, logging, chain of responsibility](#m4)
- [M5 - Collections, Stream API, Lambda](#m5)
- [M6 - File I/O, serialization, Memento](#m6)
- [M7 - Threading, Executor, Deadlock, Observer](#m7)

## M1
### Basic exercises

1. Write a program that declares variables of different types (`int`, `double`, `String`, `boolean`) and prints their values. Try type conversion.
2. Take two `float` numbers and print their sum, difference, product, and quotient (formatted to 2 decimal places).
3. Write a method that takes three numbers and determines the largest using `if-else`.
4. Write a program that takes coefficients `a`, `b`, `c` and calculates the real roots of a quadratic equation. If no real roots exist, print a message.
5. Write a program that checks if an integer is prime.
6. Method `perimeterRectangle(double length, double width)` that returns the perimeter of a rectangle.
7. Method `isOdd(int num)` that returns `1` if the number is odd, `0` otherwise.
8. Method `triangleArea(double a, double b, double c)` that calculates the area using Heron’s formula.
9. Given three strings, print their concatenation in normal and reverse order.
10. Static method that counts and prints how many words are in a sentence.
11. Method that extracts and prints a substring from a given index.
12. Method that checks if a string is a palindrome (ignoring spaces and case).
13. Method that counts the occurrences of a character in a string.
14. Method that counts how many vowels are in a string (case-insensitive).
15. Method that finds the index of the first occurrence of a character or prints a message if not present.
16. Method that calculates and prints the factorial of a number.
17. Method that prints the first `n` numbers in the Fibonacci series.
18. Method that calculates and prints the GCD of two numbers.
19. Method that prints the multiplication table of `n`.
20. Method that prints a numeric pattern with `n` rows.
21. Method that returns the `n`th prime number.
22. Method that finds the maximum sum of a contiguous subarray.
23. Method that rotates an array to the right by `k` positions.
24. Method that checks if a string of brackets `()[]{}` is balanced.

## M2 & M3
### Classes, inheritance, interfaces, pattern

1. **Class `Car`**
    - Attributes: `brand`, `model`, `year`
    - Two constructors:
        - One that accepts `brand` and `model`, sets `year` to current
        - One that initializes all attributes
    - Method `toString()`
    - Instantiate two objects and print their details
2. **Class `BankAccount`**
    - Private attribute `balance`
    - Methods: `deposit()`, `withdraw()`, `checkBalance()`
    - Prevent withdrawals exceeding the balance
3. **Class `MathOperations`**
    - Two overloaded `sum()` methods: one for `int`, one for `double`
4. **Class `Counter`**
    - Static variable `count`
    - Incremented on each instantiation
    - Method to retrieve `count`
5. **Class `Circle`**
    - Attribute `radius`
    - Methods: `calculateArea()`, `calculatePerimeter()`
6. **Class `Student`**
    - Student info and array of grades
    - Method to compute average and check pass (`≥ 50`)
7. **Class `Person` and subclass `Student`**
    - `Person`: `name`, `age`
    - `Student`: `studentId`, method to print all details
8. **Abstract class `Shape`**
    - Abstract method `calculateArea()`
    - Subclasses: `Rectangle`, `Circle` with area implementation
9. **Interface `Playable`**
    - Method `play()`
    - Classes `Guitar` and `VideoGame` implement `Playable`
10. **Interface and device hierarchy**
    - `Chargeable` with `charge()`
    - Abstract `Device` with `connectToWifi()`
    - `Smartphone` and `Laptop`: extend `Device`, implement `Chargeable`
    - `ElectricCar`: only implements `Chargeable`
    - Create an array of `Chargeable`, call `charge()`
11. **Vehicle structure**
    - Appropriate interface and abstract class
    - Common method `startEngine()`
    - Only some: `loadCargo()`, others `move()`
    - Implement `Car`, `Truck`, `Bicycle`
12. **Wildlife conservation**
    - Abstract class `Animal`: `eat()`, `sleep()`
    - Interfaces: `Flyable`, `Swimmable`
    - Classes: `Lion`, `Penguin`, `Fish`, `Eagle`, etc.
13. **Class `WildlifeConservationSystem`**
    - Array of `Animal`
    - Static method `printAnimalDetails(Animal animal)`
    - Overloaded methods `performAction(Flyable)` and `performAction(Swimmable)`
14. **SIM Card system**
    - Class `Call`: duration and called number
    - Class `SIM`: number, credit, last 5 calls (fixed array)
    - Add at least 6 calls, print details
15. **E-commerce system**
    - `Product`: code, description, price, quantity
    - `Customer`: ID, name, email, registration date
    - `Cart`: associated customer, list of products, total cost
    - Methods: add/remove, stock check, print details
16. **Builder Pattern for `Car`**
    - `Car`: `brand`, `model`, `engine`, `features`
    - `CarBuilder` class: chained methods
    - Use builder to create different configurations
17. **Factory Pattern for documents**
    - Abstract class `Document`: method `generate()`
    - Subclasses: `PDFDocument`, `WordDocument`, `TextDocument`
    - `DocumentFactory` returns correct instance by type
18. **Proxy Pattern for images**
    - Interface `Image` with `display()`
    - `RealImage`: loads and shows
    - `ProxyImage`: loads only on first `display()`
19. **Home Theater Facade**
    - Components: `DVDPlayer`, `Projector`, `SoundSystem`, `Lights`
    - `HomeTheaterFacade`: methods `startMovie()`, `endMovie()`
20. **Device/Remote hierarchy**
    - Devices: `TV`, `Radio`
    - Remotes: `BasicRemote`, `AdvancedRemote`
    - Tightly coupled: e.g., `TVBasicRemote`, `TVAdvancedRemote`
21. **Bridge Pattern for remotes and devices**
    - `RemoteControl` (abstraction): `powerOn()`, `powerOff()`
    - `Device` (interface): `TV`, `Radio`
    - `BasicRemote`, `AdvancedRemote` independent of device type

## M4
### Exceptions, logging, chain of responsibility

1. **Division and input handling**
    - Method that reads an integer and divides 100 by it
    - Handles `ArithmeticException` and `InputMismatchException` with friendly messages
2. **Square root and logging**
    - Calculates square root of a number
    - Uses `Logger` to log input, result, invalid inputs (e.g., negative numbers)
    - Configure logger to write to file
3. **Safe number parsing**
    - Method takes a list of strings and tries converting to numbers
    - Skips and logs unparseable ones
4. **Login system with attempts**
    - Simulate login
    - If username/password incorrect, throw custom exception
    - Block after 3 failed attempts
5. **Exception propagation**
    - Three methods: `readFile()`, `processData()`, `main()`
    - `readFile()` throws `IOException`
    - Don’t catch until `main()` to show propagation
6. **Custom Exception Wrapper**
    - Library method calls third-party code that may throw unchecked exceptions
    - Internally catches and rethrows as custom `LibraryException`
7. **Chain of Responsibility Logger**
    - Loggers: `ConsoleLogger`, `FileLogger`, `EmailLogger`
    - Each handles one log level (INFO, WARNING, ERROR)
    - Passes to next logger if level unhandled
8. **Enum and loggers**
    - Define `enum LogLevel { INFO, WARNING, ERROR }`
    - Abstract class `Logger` with:
        - `setNext(Logger)`
        - `log(LogLevel, String message)`
9. **Concrete logger implementations**
    - `ConsoleLogger`: prints to console if `INFO`
    - `FileLogger`: simulates writing to file for `WARNING`
    - `EmailLogger`: simulates email for `ERROR`
10. **Generic catch and safety**
    - Class that catches all exceptions with `catch(Exception e)`
    - Logs messages
    - Explain why it’s bad practice (e.g., Log4Shell), and risks of logging untrusted input

## M5
### Collections, Stream API, Lambda

1. **Player list**
    - Store player names in a `List`
    - Show difference between `ArrayList` and `LinkedList`
    - Explain performance impact for insertions/removals
2. **Generic class `Box<T>`**
    - Methods: `getContent()`, `setContent()`
    - Test with `Integer`, `String`, and a custom `Player` class
3. **Multiplayer lobby**
    - Use `List<Player>` for game room
    - Use `Queue<Player>` for matchmaking queue
    - Implement: join/leave room, generate sessions
    - Explain why `Queue` is better for matchmaking
4. **Achievement tracking**
    - `Set<String>` for unique achievements
    - `Map<String, Integer>` for stats like "monsters defeated"
    - Add methods to update stats and unlock achievements
    - Extend to multiple players using `Map<Player, PlayerData>`
5. **GameEventLog and Iterator Pattern**
    - Custom collection with game events and timestamps
    - Inner class `RecentEventIterator` returns only events from last 5 minutes
    - Explain `Iterator Pattern` benefits and reuse for other filters
6. **Class `Character` with ordering**
    - Attributes: `name`, `level`, `XP`
    - Implements `Comparable<Character>` for natural ordering by `level`
    - Create two `Comparator`s: one for descending `XP`, one for alphabetical `name`
    - Compare `Comparable` vs `Comparator` and when to use each
7. **List of `Item` objects**
    - Each `Item` has `name` and `price`
    - Print using `forEach()` and lambda
    - Use `removeIf()` to remove items below a threshold
    - Compute average price with `Stream`
    - Compare with traditional `for-loop` version
8. **Stream on `Transaction`**
    - Filter transactions above a threshold
    - Group by user and compute total spend
    - Find top 3 spenders
    - Sort by date
    - Explain data transformations with Stream vs loops
9. **Stream on `Product`**
    - Group products by `category`
    - Find the best-selling product for each category
    - Output: `Map<String, Product>`, key is `category`
10. **Even numbers transformation (two versions)**
    - List of integers
    - Filter evens
    - Multiply ×3
    - Collect in new list
    - Write in both `for-loop` and `Stream` form
    - Compare clarity and readability
11. **Books in filtered map**
    - List of `Book(title, author, yearPublished, price)`
    - Filter books after 2010
    - Collect in `Map<String, Double>` with `title` as key
    - Handle duplicate titles: keep the most expensive
12. **Optional and Stream**
    - List of `Order`, each has `Optional<Customer>`, and `Customer` has `Optional<String> email`
    - Extract only present emails
    - Collect into `List<String>`

## M6
### File I/O, serialization, Memento

1. **Read `.txt` file**
    - Use `BufferedReader` to read and print each line
    - Handle exceptions and close resources properly
2. **Write file with messages**
    - Method receives a list of `String`
    - Writes each line using `BufferedWriter` in append mode
3. **Word count from file**
    - Read a `.txt` file
    - Count how often each word appears (ignore case and punctuation)
    - Save in `Map<String, Integer>`
4. **Copy binary files**
    - Copy `.jpg` or similar with `FileInputStream` and `FileOutputStream`
    - Use buffer for performance
5. **Write and read binary data**
    - Use `DataOutputStream` to write `int`, `double`, `String`
    - Read with `DataInputStream` and print values
6. **Object serialization**
    - `User(name, email, age)` implements `Serializable`
    - Write an object to file and read to verify integrity
7. **Serialization of object list**
    - Serialize a list of `Book` objects to file
    - Deserialize and print contents
8. **Handling `serialVersionUID`**
    - Add a new field to `User`
    - Define `serialVersionUID`
    - Verify compatibility with an older version
9. **Print `.txt` files in directory**
    - Use `Files.list()` and `Stream` to print all `.txt` files in a directory
    - Sort alphabetically
10. **Read file with `FileChannel`**
    - Use `FileChannel` and `ByteBuffer` to read file to memory and print it
    - Avoid legacy streams
11. **Filter lines with Stream**
    - Use `Files.lines()`:
        - Filter lines with a keyword
        - Convert to uppercase
        - Save in new file
12. **User input with validation**
    - Takes user input and writes to file
    - Verifies:
        - No forbidden words
        - Cleans extra spaces
        - Is valid, else prints error
13. **Text editor with undo (Memento Pattern)**
    - `EditorState`: represents current content (memento)
    - `TextEditor`: modifies and saves/restores state (originator)
    - `History`: manages previous versions using a stack (caretaker)
    - Implement `undo()` to return to previous state
14. **CLI for log filtering**
    - App loads a large log file
    - Allows filters for:
        - Date range
        - Log level (`INFO`, `WARN`, `ERROR`)
        - Substring
    - Use `Stream<String>` from `Files.lines()` for efficient processing

## M7
### Threading, Executor, Deadlock, Observer

1. **Thread with `NumberPrinter`**
    - Class `NumberPrinter` extends `Thread`
    - Each thread prints from 1 to 10 with 500ms delay
    - Start two instances and observe parallel execution
    - Explain difference between calling `run()` vs `start()`
2. **Simulated file download**
    - Class `FileDownloader` implements `Runnable`
    - Simulates download by printing progress and sleeping
    - Start multiple threads with different filenames
3. **Execution with `ExecutorService`**
    - Create 5 `Callable` or `Runnable`
    - Use with fixed thread pool of 2 threads
    - Print which thread runs each task
    - Shutdown executor properly
4. **Synchronized counter**
    - Class `Counter` with `increment()` method
    - 100 threads call it 1000 times
    - Use `synchronized` to ensure correctness
    - Compare result without `synchronized`
5. **Producer-Consumer**
    - Producer adds objects every second
    - Consumer removes if present
    - Use `wait()` and `notify()` for synchronization
    - Log interactions
6. **Deadlock simulation**
    - Two resources: `Printer` and `Scanner`
    - Two threads lock resources in opposite order
    - Show how deadlock occurs
    - Prevent with timeout or lock ordering
7. **Observer pattern with stock actions**
    - `Stock` is the observed subject
    - Observers: `EmailAlert`, `MobileApp`
    - Notify all observers on price change
    - Demonstrate registration, deregistration, and notification
8. **Singleton `AppConfig`**
    - Thread-safe Singleton class that loads settings
    - Use lazy initialization with: `synchronized`, static holder, or `volatile`
    - Verify behavior from multiple threads
9. **Chatroom with Observer**
    - `ChatRoom`: subject managing subscribed users
    - Method `postMessage(String)` notifies all
    - Users (`User`) implement `receive(String message)`
    - Demonstration:
        - Create 3 users: `UserA`, `UserB`, `UserC`
        - Post message "Welcome"
        - Unsubscribe `UserB`
        - Post "UserB has left" and show only `UserA` and `UserC` receive it