# Design patterns

# Creational Design Patterns

---

## Builder

The Builder Pattern is a **Creational Design Pattern** focused on constructing complex objects step-by-step, making it especially useful when dealing with objects that **require several fields or configurations**.

It separates the construction process from the final representation, allowing developers to create different types of representations using the same construction code.

This pattern is particularly valuable when the object construction process **requires many parameters or involves optional fields**.

- **Separates Construction from Representation:** The Builder pattern isolates the step-by-step object creation process from the final object itself.
- **Fluent Interface:** Builders often employ a "fluent interface," allowing method chaining to enhance readability and streamline object creation.
- **Optional Parameters:** The Builder pattern is excellent for scenarios where only some parameters are mandatory, and others are optional.

### Architecture

![Screenshot 2025-04-05 alle 17.25.04.png](Design%20patterns%201ee75be0d2f280ccba3ec4bb068cefe5/Screenshot_2025-04-05_alle_17.25.04.png)

- **Director**: Orchestrates the building process using a given Builder. It doesn't know about the specific details of the Product, allowing flexible product construction.
- **Builder** **Interface**: Defines the construction steps to be implemented by concrete builders, with methods like setAttribute1, setAttribute2, etc.
- **Concrete Builder**: Implements the Builder interface and provides details for the actual construction of the product. This builder holds the state for the product being built.
- **Product**: The final object created by the Builder. Itʼs usually made immutable and only accessible through the builder.

<aside>

### An example

Suppose we are creating a House object with several optional features. A house might have a pool, garage, garden, or even multiple floors, but not all houses will have every feature.

```jsx
public class House {
	private String foundation;
	private String structure;
	private String roof;
	private boolean hasGarage;
	private boolean hasSwimmingPool;
	private boolean hasGarden;
	
	@Override
	public String toString() {
		return "House with " + foundation + ", " + structure + " and " + roof +
					 (hasGarage ? ", Garage" : "") +
					 (hasSwimmingPool ? ", Swimming Pool" : "") +
					 (hasGarden ? ", Garden" : "");
	}
	
	private House(HouseBuilder builder) {
		this.foundation = builder.foundation;
		this.structure = builder.structure;
		this.roof = builder.roof;
		this.hasGarage = builder.hasGarage;
		this.hasSwimmingPool = builder.hasSwimmingPool;
		this.hasGarden = builder.hasGarden;
	}
	//...
}
```

```jsx
public static class HouseBuilder {
	// Required parameters
	private String foundation;
	private String structure;
	private String roof;
	
	private boolean hasGarage = false;
	private boolean hasSwimmingPool = false;
	private boolean hasGarden = false;
	
	public HouseBuilder(String foundation, String structure, String roof) {
		this.foundation = foundation;
		this.structure = structure;
		this.roof = roof;
	}
	
	public House build() {
		return new House(this);
	}
	//...
	// Optional parameters with default value
	
	public HouseBuilder setGarage(boolean hasGarage) {
		this.hasGarage = hasGarage;
		return this;
	}
	
	public HouseBuilder setSwimmingPool(boolean hasSwimmingPool) {
		this.hasSwimmingPool = hasSwimmingPool;
		return this;
	}
	
	public HouseBuilder setGarden(boolean hasGarden) {
		this.hasGarden = hasGarden;
		return this;
	}
}
```

```jsx
public static void main(String[] args) {
	
	// Building a simple house
	House simpleHouse = new House.HouseBuilder("Concrete", "Wood", "Tiles")
	.build();
	System.out.println(simpleHouse);
	
	// Building a luxurious house with all features
	House luxuryHouse = new House.HouseBuilder("Concrete", "Brick", "Shingles")
	.setGarage(true)
	.setSwimmingPool(true)
	.setGarden(true)
	.build();
	System.out.println(luxuryHouse);
}
```

</aside>

### Benefits

- **Readable Code →** The fluent interface created by chaining builder methods enhances code readability.
- **Reduced Constructor Overload →** It prevents the need for multiple constructors with various parameters.
- **Immutable Objects →** Once built, the object is generally immutable, which is ideal for creating thread-safe objects.

### Best practice

- **Use Inner Classes for Simple Builders →** If only a single class will use the builder, consider making the builder a static inner class.
- **Enforce Immutability →** Ensure that the final object does not allow modifications after creation.
- **Apply Builder for Complex Objects →** The pattern is most beneficial when the object has numerous optional attributes.

## Singleton

<aside>

**Ensures only one instance of a class, globally accessible.**

</aside>

The Singleton Pattern is a Creational Design Pattern that **restricts the instantiation of a class to a single instance**.

This is particularly useful when **exactly one object is needed** to coordinate actions across the system, such as a configuration manager, a logging utility, or a database connection pool.

The Singleton pattern **ensures a single instance**, provides global access, and controls instantiation by lazy-loading the instance when first needed.

### Key concepts

- **Single Instance Guarantee:** The Singleton pattern ensures only one instance of the class exists throughout the application lifecycle.
- **Global Access Point:** It provides a globally accessible instance, which can be useful for resources that need to be shared across multiple parts of an application.
- **Lazy Initialization:** The instance is created only when itʼs needed, reducing memory usage and initialization time, especially in complex systems.

### General Architecture

![Screenshot 2025-05-04 alle 15.54.38.png](Design%20patterns%201ee75be0d2f280ccba3ec4bb068cefe5/Screenshot_2025-05-04_alle_15.54.38.png)

**-instance: Singleton →** A private static field that holds the single instance of the class.

**-Singleton() →** A private constructor to prevent instantiation fromoutside the class.

**+getInstance(): Singleton →** A public static method to return the single instance of the class, ensuring only one instance is created.

**+operation(): void →** An example public method that can be called on the Singleton instance.

### Eager Initialization

In the eager initialization approach, the Singleton instance is created at the time of class loading. This is the simplest approach, but it doesnʼt support lazy initialization.

```java
public class BasicSingleton {
	// Eagerly created instance
	private static final BasicSingleton INSTANCE = new BasicSingleton();
	
	// Private constructor to prevent instantiation from outside
	private BasicSingleton() {
	}
	
	// Public method to provide access to the instance
	public static BasicSingleton getInstance() {
		return INSTANCE;
	}
	
	// Example method to demonstrate functionality
	public void showMessage() {
		System.out.println("Hello from Basic Singleton!");
	}
}
```

### Lazy Initialization Singleton (Thread-Unsafe)

In this approach, the Singleton instance is created only when it's needed. However, this implementation is not thread-safe and should not be used in multithreaded applications.

```java
public class LazySingletonUnsafe {
	// Lazily created instance
	private static LazySingletonUnsafe instance;
	
	private LazySingletonUnsafe() {
	}
	
	public static LazySingletonUnsafe getInstance() {
		if (instance == null) {
			instance = new LazySingletonUnsafe();
		}
		return instance;
	}
	
	public void showMessage() {
		System.out.println("Hello from Lazy Singleton!");
	}
}
```

### Lazy Initialization Singleton (Thread-Safe)

In this approach, the Singleton instance is created only when it's needed.
To make the Singleton thread-safe, we can add synchronized to the getInstance method.

```java
public class LazySingletonSafe {
	// Lazily created instance
	private static LazySingletonSafe instance;
	
	private LazySingletonSafe() {
	}
	
	public static synchronized LazySingletonSafe getInstance() {
		if (instance == null) {
			instance = new LazySingletonSafe();
		}
		return instance;
	}
	
	public void showMessage() {
		System.out.println("Hello from Lazy Singleton!");
	}
}
```

### Lazy Initialization Singleton (Double-Checked Locking)

Double-checked locking minimizes the performance hit of synchronized access by performing the null check twice. It only synchronizes the getInstance method the first time itʼs called, making subsequent calls faster.

```java
public class LazySingletonDCLocking {
	// Lazily created instance
	private static volatile LazySingletonDCLocking instance;
	
	private LazySingletonDCLocking() {
	}
	
	public static LazySingletonDCLocking getInstance() {
		if (instance == null) { // First Check
			synchronized (LazySingletonDCLocking.class) {
				if (instance == null) { // Second check
					instance = new LazySingletonDCLocking();
				}
			}
		}
		return instance;
	}
		
	public void showMessage() {
		System.out.println("Hello from Lazy Singleton!");
	}
}
```

### Bill Pugh Singleton (Initialization-on-Demand Holder Idiom)

This is one of the most efficient ways to implement a Singleton in Java. It relies on the Java language's guarantee that
static inner classes are not loaded until they are referenced, achieving lazy initialization without explicit synchronization.

```java
public class BillPughSingleton {
	private BillPughSingleton() {
	}
	
	// Inner static helper class, loaded only when getInstance() is called
	private static class SingletonHelper {
		private static final BillPughSingleton INSTANCE = new BillPughSingleton();
	}
	
	public static BillPughSingleton getInstance() {
		return SingletonHelper.INSTANCE;
	}
	
	public void showMessage() {
		System.out.println("Hello from Bill Pugh Singleton!");
	}
}
```

### Enum Singleton

In Java, an enum provides a built-in mechanism to implement Singleton pattern. It is inherently thread-safe and handles serialization by default, making it a good choice for Singletons.

```java
public enum EnumSingleton {
	INSTANCE;
	
	public void showMessage() {
		System.out.println("Hello from Enum Singleton!");
	}
}
```

### Pros and cons

|  | Pros | Cons |
| --- | --- | --- |
| Basic Singleton (Eager Initialization) | - Simple to implement
- Thread-safe by default | No lazy loading; instance is created t class loading, even if it's never used Inefficient for resource-intensive Singletons |
| Lazy Initialization Singleton (Thread-Unsafe) | - Supports lazy initialization, creating the instance only when needed | - Not thread-safe; multiple threads can create multiple instances if accessed concurrently |
| Lazy InitializationSingleton (Thread-Safe) | - Thread-safe due to synchronized access
- Simple to implement | - Synchronized method incurs performance overhead
- Inefficient under high concurrency, as every access requires locking |
| Lazy Initialization Singleton (Double-Checked Locking) | - Thread-safe and avoids the synchronization performance hit after the first access
- Supports lazy initialization
- Efficient under high concurrency | - Complex implementation
- Requires volatile keyword, which can be confusing for some developers |
| Bill Pugh Singleton (Initialization-on-Demand Holder Idiom) | - Thread-safe and efficient without requiring synchronization
- Supports lazy initialization
- Simple and intuitive for experienced Java developers | - Less intuitive for beginners, as it relies on inner static class behavior
- May seem unconventional compared to more straightforward approaches |
| Enum Singleton | - Simplest approach with thread safety by default
- Handles serialization automatically
- Resistant to reflection attacks | - Limited flexibility (can't extend)
- Not suitable if you need to implement Singleton with more complex initializations or extendability |

### Best practices

- **Use Enum Singleton if Simple:** For most scenarios where you need a simple Singleton, use the enum Itʼs concise, thread-safe, and serializable. approach.
- **Avoid Global State When Possible:** Ensure your Singleton doesnʼt become a global state or mutable object, as this can lead to hard-to-find bugs in larger applications.
- **Lazy Initialization for Resource-Heavy Singletons:** If the Singleton involves costly resource initialization, prefer lazy initialization.
- **Thread Safety:** Ensure that the Singleton is implemented in a thread-safe manner if it will be accessed across multiple threads.

## Factory method

<aside>

**Defines method for creating objects, allowing subclasses to customize.**

</aside>

The Factory Method pattern provides an interface for creating an object, but lets subclasses decide which class to instantiate.

The Factory Method pattern delegates the object creation process to subclasses, offering flexibility for the application to create different types of objects without modifying the core code.

- **Creator Class:** Declares the factory method, often as an abstract method.
- **Concrete Creator:** Implements the factory method to produce a specific type of product.
- **Product Interface:** Defines the interface for objects created by the factory method.
- **Concrete Product:** Implements the Product interface.

### Architecture

![Screenshot 2025-04-05 alle 17.39.00.png](Design%20patterns%201ee75be0d2f280ccba3ec4bb068cefe5/Screenshot_2025-04-05_alle_17.39.00.png)

- **Creator**: Declares the factoryMethod that returns a Product. It also contains some core operation method that relies on factoryMethod to get a product instance.
- **ConcreteCreator**: Implements the factoryMethod, returning an instance of ConcreteProduct.
- **Concrete Builder**: Implements the Product interface, providing a specific implementation.
- **Product**: Defines the interface for objects created by the factory.

<aside>

### Example

Suppose we are creating a Dialog class that has a createButton factory method to instantiate different types of buttons (e.g., WindowsButton and HTMLButton), depending on the specific dialog.

```jsx
public interface Button {
	void render();
	void onClick();
}

public class WindowsButton implements Button {
	@Override
	public void render() {
		System.out.println("Render a button in Windows style.");
	}
	
	@Override
	public void onClick() {
		System.out.println("Click event for Windows button.");
	}
}

public class HTMLButton implements Button {
	@Override
	public void render() {
		System.out.println("Render a button in HTML style.");
	}
	@Override
	public void onClick() {
		System.out.println("Click event for HTML button.");
	}
}
```

```jsx
public abstract class Dialog {
	// Factory method
	abstract Button createButton();
	
	// Operation that uses the product
	public void renderWindow() {
		// Create a button using the factory method
		Button okButton = createButton();
		okButton.render();
		okButton.onClick();
	}
}

public class WindowsDialog extends Dialog {
	@Override
	Button createButton() {
		return new WindowsButton();
	}
}
public class HTMLDialog extends Dialog {
	@Override
	Button createButton() {
		return new HTMLButton();
	}
}
```

```jsx
public class FactoryMethodDemo {
	public static void main(String[] args) {
		Dialog dialog;
		
		// Configure to create a Windows button
		dialog = new WindowsDialog();
		dialog.renderWindow();
		
		// Configure to create an HTML button
		dialog = new HTMLDialog();
		dialog.renderWindow();
	}
}
```

</aside>

### Benefits

- **Encapsulation of Object Creation →** The Factory Method pattern encapsulates the object creation process, allowing the client code to work with higher-level abstractions without needing to know about concrete implementations.
- **Single Responsibility Principle →** By delegating object creation to subclasses, the Factory Method pattern allows the creator class to focus on higher-level operations, while specific instantiation logic is handled separately.
- **Flexibility and Scalability →** Itʼs easy to extend the code to support new types of products by creating additional subclasses, without modifying existing code, which adheres to the Open/Closed Principle.
- **Decoupling →** It promotes loose coupling between the client code and the specific classes it depends on, making the code easier to change and maintain.

### Best practices

- **Use When Variants Are Required →** Apply the Factory Method when you foresee the need to create different variations of an object and want to
manage this creation logic in one place.
- **Ensure the Product Interface is Stable →** Since the client relies on the product interface, it should be well-designed and unlikely to change.
- **Name Factories Based on Product Context →** Naming the factory methods and concrete classes based on their context makes the code self-explanatory.
- **Minimize Dependencies in Concrete Creators →** Ensure that each concrete creator only depends on the necessary dependencies and avoids coupling to
specific client implementations.
- **Consider Using Anonymous Inner Classes or Lambdas →** If the variations are simple and few, consider using anonymous classes or lambdas to create concise factory methods without defining additional subclasses.

## Abstract Factory

<aside>

**Provides interface for creating families of related objects, independently.**

</aside>

The Abstract Factory pattern provides an interface for creating families of related or dependent objects without specifying their concrete classes.

It allows you to create objects that are designed to work together and encapsulates object creation logic for multiple, related products.

- **Abstract Factory:** Declares methods for creating abstract products.
- **Concrete Factory:** Implements creation methods for specific product families.
- **Abstract Product:** Declares an interface for a product object.
- **Concrete Product:** Implements the Abstract Product interface for a specific variant.
- **Client:** Uses only the Abstract Factory and Abstract Product interfaces.

### Architecture

![Screenshot 2025-04-05 alle 17.46.15.png](Design%20patterns%201ee75be0d2f280ccba3ec4bb068cefe5/Screenshot_2025-04-05_alle_17.46.15.png)

- **AbstractFactory**: Defines an interface for creating abstract products, typically with methods like createProductA and createProductB.
- **Concrete Products (e.g., ConcreteProductA1, ConcreteProductB1)**: Implementations of the abstract products for a specific family.
- **ConcreteFactory1 and ConcreteFactory2**: These are specific implementations of AbstractFactory, creating related concrete products (e.g., ConcreteProductA1 and ConcreteProductB1 for ConcreteFactory1).
- **AbstractProductA and AbstractProductB**: Interfaces for products that will be created by the factories.

<aside>

### Example

Suppose we are creating a UI factory that can produce Button and Checkbox elements for either Windows or MacOS.

```jsx
public interface Checkbox {
	void check();
}

public class MacOSCheckbox implements Checkbox {
	@Override
	public void check() {
		System.out.println("Checking MacOS checkbox.");
	}
}

public class WindowsCheckbox implements Checkbox {
	@Override
	public void check() {
		System.out.println("Checking Windows checkbox.");
	}
}
```

```jsx
public interface UIFactory {
	Button createButton();
	Checkbox createCheckbox();
}

public class MacOSFactory implements UIFactory {
	@Override
	public Button createButton() {
		return new MacOSButton();
	}
	
	@Override
	public Checkbox createCheckbox() {
		return new MacOSCheckbox();
	}
}

public class WindowsFactory implements UIFactory {
	@Override
	public Button createButton() {
		return new WindowsButton();
	}
	
	@Override
	public Checkbox createCheckbox() {
		return new WindowsCheckbox();
	}
}
```

```jsx
public class AbstractFactoryDemo {
	public static void main(String[] args) {
		UIFactory factory;
		
		// Configure to use WindowsFactory
		factory = new WindowsFactory();
		Button windowsButton = factory.createButton();
		Checkbox windowsCheckbox = factory.createCheckbox();
		windowsButton.render();
		windowsCheckbox.check();
		
		// Configure to use MacOSFactory
		factory = new MacOSFactory();
		Button macButton = factory.createButton();
		Checkbox macCheckbox = factory.createCheckbox();
		macButton.render();
		macCheckbox.check();
	}
}
```

</aside>

### Benefits

- **Enforces Consistent Product Families →** The Abstract Factory pattern is especially useful when products are designed to work together as a family, ensuring that the client receives related objects that are compatible
- **Flexibility to Switch Product Families →** It allows the client code to remain unchanged while switching between different product families.
- **Encapsulation of Object Creation for Families →** Similar to the Factory Method pattern, the Abstract Factory encapsulates the instantiation logic, making the code easier to maintain and extend.
- **Decoupling of Client Code →** It removes dependencies on concrete classes, as the client only interacts with interfaces. This decoupling makes the code more adaptable and testable.

### Best practices

- **Use Descriptive Factory Names →** Clearly name each factory based on the family of products it creates to clarify the family of objects.
- **Ensure Families Are Cohesive and Consistent →** Ensure that all products created by a factory are meant to work together. The factory should provide a full set of compatible products to avoid runtime issues.
- **Define Product Interfaces Carefully →** Since all concrete products implement the same interfaces, design these interfaces carefully to ensure they fulfill the needs of all product variants.
- **Use Dependency Injection for Flexibility →** Pass the desired factory to the client using dependency injection. This allows easy switching of product families at runtime.
- **Favor Composition over Inheritance →** Rather than inheriting from the factory classes, use composition to manage dependencies, as it often leads to more flexible and maintainable code.

## Factory vs Abstract factory

| Feature | Factory Method | Abstract Factory |
| --- | --- | --- |
| Primary Goal | Create a single product variant | Create a family of related products |
| Naming | Name methods based on product functionality | Name factories based on product families |
| Client Code Coupling | Decoupled from specific implementations | Decoupled from product family implementations |
| Use Case | For creating one-off variants | For creating cohesive product families |
| Flexibility | Easy to extend new product variants | Easy to switch between different product sets |

## Prototype

<aside>

**Creates new objects by copying an existing object (prototype).**

</aside>

# Structural Design Patterns

---

## Proxy

<aside>

**Controls access to an object, adding functionalities like logging or caching.**

</aside>

The Proxy Pattern is a Structural Design Pattern that provides a surrogate or placeholder for another object to control access to it.

In essence, a proxy acts as an intermediary that forwards requests to the real object, sometimes adding extra functionality such as access control, lazy initialization, logging, caching, or monitoring.

> The Proxy pattern is useful when you want to:
> 
> 
> Control access to a resource that is costly to create or needs restricted access.
> 
> Add additional behavior to the real object without modifying its code.
> 
> Enable lazy loading of the actual object, creating it only when it is required.
> 

### Key concepts

- **Subject (Interface):** Defines the common interface for both the Proxy and the Real Subject. This interface allows the Proxy to represent the Real Subject.
- **Real Subject :**The actual object that performs the real work.
- **Proxy:** Controls access to the Real Subject. It implements the same interface as the Real Subject and forwards requests to it, sometimes adding additional functionality.

### Aarchitecture

![Screenshot 2025-04-05 alle 18.02.45.png](Design%20patterns%201ee75be0d2f280ccba3ec4bb068cefe5/Screenshot_2025-04-05_alle_18.02.45.png)

- **Subject:** Declares the interface request() that both the RealSubject and Proxy implement.
- **RealSubject:** The actual object that performs the task when request() is called.
- **Proxy:** Holds a reference to RealSubject and adds an extra layer by controlling or modifying access to RealSubject.

<aside>

### Example

Imagine a scenario where we have a large file that needs to be read and displayed. Loading this file can be time-consuming, so weʼll use a Virtual Proxy to load it only when necessary.

```jsx
public interface FileDisplay {
	void displayFileContent();
}

public class RealFile implements FileDisplay {
	private String filePath;
	
	public RealFile(String filePath) {
		this.filePath = filePath;
		loadFile();
	}
	
	// Simulate loading of a large file
	private void loadFile() {
		System.out.println("Loading file from " + filePath + "...");
	}
	
	@Override
	public void displayFileContent() {
	System.out.println("Displaying content of " + filePath);
	}
}
```

```jsx
public class FileProxy implements FileDisplay {
	private RealFile realFile;
	private String filePath;
	
	public FileProxy(String filePath) {
	this.filePath = filePath;
	}
	
	@Override
	public void displayFileContent() {
		if (realFile == null) {
			realFile = new RealFile(filePath); // Load the file only when needed
		}
		realFile.displayFileContent();
		}
}
```

```jsx
public class ProxyPatternDemo {
	public static void main(String[] args) {
		// Client uses FileProxy instead of RealFile directly
		FileDisplay fileProxy = new FileProxy("large_file.txt");
		
		// File loading is delayed until the first call to displayFileContent
		System.out.println("Calling displayFileContent() first time:");
		fileProxy.displayFileContent();
		
		System.out.println("\nCalling displayFileContent() second time:");
		fileProxy.displayFileContent();
	}
}
```

</aside>

### Best practices

- **Use for Lazy Loading →** Employ the Proxy pattern when an object is resource-intensive to create, but you want to defer its instantiation until necessary.
- **Implement Caching When Possible →** For repeated access to data, a Cache Proxy can improve performance by reducing the need for repeated expensive operations.
- **Keep Proxy Lightweight →** Ensure the Proxy itself is lightweight and does not perform complex operations, as it should primarily be an intermediary.
- **Use Logging Proxies for Monitoring →** In cases where tracking or auditing is needed, a Logging Proxy can intercept and log calls without modifying the core code of the Real Subject.

## Façade

<aside>

**Simplifies complex subsystem interface, providing an easier, unified interface.**

</aside>

The Façade Pattern is a Structural Design Pattern that provides a **simplified interface to a complex subsystem.**

Instead of exposing the intricacies of the subsystem to the client, the Façade pattern offers a single, unified interface.

This pattern is especially useful for making a library or complex system easier to use by **hiding its details** and providing higher-level methods.

- **Façade**: The primary interface that provides simple methods to the client, delegating complex tasks to appropriate subsystems.
- **Subsystem Classes:** Classes that perform detailed work. The Façade uses these classes internally but hides them from the client.
- **Client:** The code that interacts with the subsystem only through the Façade interface, unaware of the underlying complexities.

### Architecture

![Screenshot 2025-04-05 alle 18.11.02.png](Design%20patterns%201ee75be0d2f280ccba3ec4bb068cefe5/Screenshot_2025-04-05_alle_18.11.02.png)

- **Client:** Calls the simplified interface provided by the Façade.
- **Façade:** Provides the simplified interface, coordinating and delegating work to the subsystem classes.
- **Subsystem Classes:** Perform detailed operations. They are accessed by the Façade, but their complexity is hidden from the client.

<aside>

### Example

Consider a complex Home Theater system with subsystems like Amplifier, DVDPlayer, Projector, and Screen. The Façade provides a single interface to operate these components together, making it easy for clients to start and stop the system.

```jsx
public class Amplifier {
	public void on() {
		System.out.println("Amplifier is on");
	}
	public void setVolume(int level) {
		System.out.println("Setting volume to " + level);
	}
	public void off() {
		System.out.println("Amplifier is off");
	}
}

public class Projector {
	public void on() {
		System.out.println("Projector is on");
	}
	public void wideScreenMode() {
		System.out.println("Projector in widescreen mode");
	}
	public void off() {
		System.out.println("Projector is off");
	}
}

public class DVDPlayer {
	public void on() {
		System.out.println("DVD player is on");
	}
	public void play(String movie) {
		System.out.println("Playing movie: " + movie);
	}
	public void stop() {
		System.out.println("Stopping DVD player");
	}
	public void off() {
		System.out.println("DVD player is off");
	}
}

public class Screen {
	public void down() {
		System.out.println("Lowering the screen");
	}
	public void up() {
		System.out.println("Raising the screen");
	}
}
```

```jsx
public class HomeTheaterFacade {
	private Amplifier amp;
	private DVDPlayer dvd;
	private Projector projector;
	private Screen screen;
	public HomeTheaterFacade(Amplifier amp, DVDPlayer dvd, Projector projector, Screen screen) {
		this.amp = amp;
		this.dvd = dvd;
		this.projector = projector;
		this.screen = screen;
	}
	
	public void watchMovie(String movie) {
		System.out.println("Get ready to watch a movie...");
		screen.down();
		projector.on();
		projector.wideScreenMode();
		amp.on();
		amp.setVolume(5);
		dvd.on();
		dvd.play(movie);
	}
	public void endMovie() {
		System.out.println("Shutting down the home theater...");
		dvd.stop();
		dvd.off();
		amp.off();
		projector.off();
		screen.up();
	}
}
```

</aside>

### Best practices

- **Use for Simplifying Complex Systems →** Implement a Façade to hide the complexity of subsystems when they require multiple steps or calls to complete a task. A Façade can decouple client code from complex subsystems, reducing dependency and making changes to subsystems easier.
- **Combine with Other Patterns →** The Façade pattern often works well with other patterns. For example, itʼs common to use a Façade with the Singleton pattern to ensure only one instance of the Façade exists.
- **Avoid Overloading the Façade →** The Façade should not become overly complex. If it has too many methods, consider creating separate Façades for different subsystem functionalities.
- **Focus on Key Operations →** The Façade should only expose the key operations that the client is interested in, keeping unnecessary subsystem details hidden.

## Bridge

<aside>

**Separates abstraction from implementation, allowing independent variation and flexibility.**

</aside>

The Bridge Pattern is a Structural Design Pattern that **separates an abstraction from its implementation**, allowing them to vary independently.

This pattern is particularly useful when both the abstraction and its implementation are **expected to change frequently**.

Instead of binding the two at compile time, the Bridge pattern decouples them by **introducing an interface**, which enables flexibility in extending either without modifying the other.

- **Abstraction:** Defines the high-level interface that will use the Implementor.
- **Implementor Interface:** Defines the interface for the implementation classes.
- **Concrete Implementor:** Implements the Implementor interface. It contains the specific implementation that the Abstraction uses.
- **Refined Abstraction:** Extends the Abstraction, adding additional features if necessary.

### Architecture

![Screenshot 2025-04-05 alle 18.20.52.png](Design%20patterns%201ee75be0d2f280ccba3ec4bb068cefe5/Screenshot_2025-04-05_alle_18.20.52.png)

- **Abstraction:** Declares an operation method that uses the Implementor.
- **Implementor:** Defines the interface for implementation classes.
- **Concrete Implementors (A and B):** Specific implementations of the Implementor interface.
- **Refined Abstraction:** Extends the Abstraction, optionally adding new functionality.

<aside>

### Example

Imagine a scenario with two device types (TV and Radio) and two control types (BasicRemote and AdvancedRemote). Using the Bridge pattern, we separate device functionalities from control types, allowing flexibility in combining them.

```jsx
public interface Device {
	void powerOn();
	void powerOff();
	void setVolume(int level);
	boolean isPoweredOn();
}

public class TV implements Device {
	private boolean on = false;
	private int volume = 10;
	@Override
	public void powerOn() {
		on = true;
		System.out.println("TV is now ON.");
	}
	@Override
	public void powerOff() {
		on = false;
		System.out.println("TV is now OFF.");
	}
	@Override
	public void setVolume(int level) {
		volume = level;
		System.out.println("TV volume set to " + volume);
	}
	@Override
	public boolean isPoweredOn() {
		return on;
	}
}

public class Radio implements Device {
	private boolean on = false;
	private int volume = 10;
	@Override
	public void powerOn() {
		on = true;
		System.out.println("Radio is now ON.");
	}
	@Override
	public void powerOff() {
		on = false;
		System.out.println("Radio is now OFF.");
	}
	@Override
	public void setVolume(int level) {
		volume = level;
		System.out.println("Radio volume set to " + volume}
	@Override
	public boolean isPoweredOn() {
		return on;
	}
}
```

```jsx
public class RemoteControl {
	protected Device device;
	
	public RemoteControl(Device device) {
		this device = device;
	}
	
	public void togglePower() {
		if (device. isPoweredOn)) {
			device.powerOff();
		}
		else {
			device.powerOn() ;
		}
	}
	
	public void volumeDown(){
	device.setVolume (5):
	}

	public void volumeUp() {
		device.setVolume(15);
	}
}

public class AdvancedRemoteControl extends RemoteControl {

	public AdvancedRemoteControl(Device device) {
		super (device) ;
	}
	
	public void mute () {
		System.out.println("Muting the device."); 
		device.setVolume (0);
	}
}
```

```jsx
public class BridgePatternDemo {
	public static void main(String[] args) {
		Device tv = new TV();
		Device radio = new Radio();
		
		RemoteControl basicRemoteForTV = new RemoteControl(tv);
		AdvancedRemoteControl advancedRemoteForRadio = new AdvancedRemoteControl(radio);
		
		System.out.println("Using Basic Remote for TV:");
		basicRemoteForTV.togglePower();
		basicRemoteForTV.volumeUp();
		
		System.out.println("\nUsing Advanced Remote for Radio:");
		advancedRemoteForRadio.togglePower();
		advancedRemoteForRadio.volumeUp();
		advancedRemoteForRadio.mute();
	}
}
```

</aside>

### Best Practices

- **Use When Abstraction and Implementation Vary →** The Bridge pattern is ideal when both the abstraction and the implementation vary independently and relies on composition (Abstraction holds a reference to Implementor) rather than deep inheritance, which avoids class explosion in cases of multiple abstractions and implementations.
- **Hide Complexity from the Client →** Use interfaces effectively to keep the client code decoupled from both the abstraction and the implementation details.
- **Consider Performance Impact →** Since the pattern introduces an additional layer of abstraction, it could slightly impact performance. Assess if the flexibility gained is worth the overhead.
- **Use in Layered Architecture →** Bridge is commonly used in applications that require multiple layers, such as UI frameworks, where an abstraction layer must be separated from platform-specific implementations.

## Adapter

<aside>

**Converts one interface into another, making incompatible interfaces compatible.**

</aside>

The Adapter Pattern **allows incompatible interfaces to work together** by wrapping an existing class with a new interface.

This pattern is beneficial when you want to **integrate a class into a system** with an interface that doesn't match the one expected by the client.

The adapter acts as a bridge, converting the original interface into one that the client can use.

This pattern is useful when you need to use an existing class, but its **interface is incompatible** with the rest of the system, or when a legacy class needs to operate within a new framework.

### Key concepts

- **Target Interface:** Defines the interface that the client expects.
- **Adaptee:** The existing interface that is incompatible with the Target.
- **Adapter Class:** Wraps the Adaptee and converts its interface to the Target.
- **Client**: Uses the Target interface and interacts seamlessly with the Adaptee via the Adapter

### General architecture

![Screenshot 2025-04-19 alle 10.27.34.png](Design%20patterns%201ee75be0d2f280ccba3ec4bb068cefe5/Screenshot_2025-04-19_alle_10.27.34.png)

- **Target**: Interface the client expects.
- **Adaptee**: Existing class with an incompatible interface.
- **Adapter**: Converts the Adaptee's interface to the Target, allowing seamless integration.

### An example

Consider a scenario where a modern data processing application works with JSON data but needs to integrate a legacy CVS-based data source. The Adapter Pattern bridges this gap by adapting the CSV parser for use with the JSON-based interface.

```java
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVRecord;
import org.json.JSONArray;
import org.json.JSONObject;

public class CSVtoJSONAdapter {
	public static JSONObject getJSONfromCSV(List<CSVRecord> records) throws IOException {
		JSONArray result = new JSONArray();
		for (CSVRecord csvRecord : records) {
			JSONObject tempObject = new JSONObject();
			Map<String, String> map = csvRecord.toMap();
			map.forEach(tempObject::put);
			result.put(tempObject);
		}
		return new JSONObject().put("data", result);
	}
}
```

```java
import org.apache.commons.csv.CSVRecord;
import org.json.JSONObject;

public class App {
	public static void main(String[] args) throws Exception 
	{
		List<CSVRecord> records = LegacySource.getCSVData();
		
		JSONObject json = CSVtoJSONAdapter.getJSONfromCSV(records);
		
		TargetSystem.receiveData(json);
	}
}
```

### Best Practices

- **Favor Composition over Inheritance:** Use composition to wrap the Adaptee, providing flexibility for adapter variations.
- **Minimize Changes to Adaptee:** Adapt the Adaptee's interface without modifying its code, preserving existing functionality.
- **Single Responsibility:** Keep adapters simple, focusing on interface conversion rather than complex logic.
- **Use When Necessary:** Apply only when interfaces are incompatible; avoid unnecessary adapters if redesign is feasible.
- **Encapsulate Legacy Code:** Use adapters to encapsulate legacy systems, enabling integration without altering old code.

## Decorator

<aside>

**Dynamically adds behavior to objects without altering their structure.**

</aside>

The Decorator Pattern provides a **flexible alternative to subclassing for extending functionality**. Instead of creating subclasses for each possible combination of features, **you can "wrap" objects** with decorators at runtime to add or override behavior.

Decorators implement the same interface as the objects they wrap, allowing them to appear as the original type to clients. 

This pattern is especially useful when you want to **add responsibilities to individual objects**, rather than to a whole class, and when combinations of functionalities are dynamic.

### Key concepts

- **Component Interface:** Defines the interface for objects that can be decorated.
- **Concrete Component:** The primary object that can be decorated with additional behavior.
- **Decorator Class:** Implements the Component interface and wraps a Component instance, adding new behavior.
- **Concrete Decorators:** Extend the functionality of the wrapped object by adding specific behaviors or responsibilities.

### General architecture

![Screenshot 2025-04-19 alle 11.02.21.png](Design%20patterns%201ee75be0d2f280ccba3ec4bb068cefe5/Screenshot_2025-04-19_alle_11.02.21.png)

- **Component:** Interface defining the base operations.
- **ConcreteComponent:** Primary object that can be decorated.
- **Decorator:** Wraps a Component and provides an interface for decorators.
- **ConcreteDecorators:** Extend the functionality of Decorator by adding specific behaviors.

### An example

Imagine a coffee shop where customers can order coffee with optional extras (milk, sugar, etc.). Using the Decorator pattern, we can add these extras dynamically without creating numerous subclasses.

```java
interface Coffee {
	String getDescription();
	double cost();
}

// Concrete Component
class SimpleCoffee implements Coffee {
	@Override
	public String getDescription() {
		return "Simple Coffee";
	}
	@Override
	public double cost() {
		return 5.00;
	}
}

// Decorator Class
abstract class CoffeeDecorator implements Coffee {
	protected Coffee coffee;
	public CoffeeDecorator(Coffee coffee) {
		this.coffee = coffee;
	}
	@Override
	public String getDescription() {
		return coffee.getDescription();
	}
	@Override
	public double cost() {
		return coffee.cost();
	}
}

class MilkDecorator extends CoffeeDecorator {
	public MilkDecorator(Coffee coffee) {
		super(coffee);
	}
	@Override
	public String getDescription() {
		return coffee.getDescription() + ", Milk";
	}
	@Override
	public double cost() {
		return coffee.cost() + 1.50;
	}
}

class SugarDecorator extends CoffeeDecorator {
	public SugarDecorator(Coffee coffee) {
		super(coffee);
	}
	@Override
	public String getDescription() {
		return coffee.getDescription() + ", Sugar";
	}
	@Override
	public double cost() {
		return coffee.cost() + 0.50;
	}
}

Coffee coffee = new SimpleCoffee();
System.out.println(coffee.getDescription() + " $" + coffee.cost());

coffee = new MilkDecorator(coffee);
System.out.println(coffee.getDescription() + " $" + coffee.cost());

coffee = new SugarDecorator(coffee);
System.out.println(coffee.getDescription() + " $" + coffee.cost());
```

### Best practices

- **Favor Composition over Inheritance:** Use composition to add functionalities without creating numerous subclasses.
- **Single Responsibility:** Each decorator should add one behavior, keeping the classes simple and focused.
- **Chain Decorators:** Decorators can be chained for flexible, layered behaviors.
- **Keep Component Interface Simple:** The Component interface should be as lean as possible to ease decoration.
- **Use Decorators When Needed:** Avoid overuse to prevent complexity from excessive layers of decoration.

## Composite

<aside>

Treats individual objects and compositions uniformly within tree structures.

</aside>

The Composite Pattern is a structural design pattern that allows you to **compose objects into tree-like structures** to represent part-whole hierarchies. It enables clients to treat individual objects and compositions of objects uniformly.

This pattern is beneficial for **creating hierarchies,** such as file systems or organizational structures, where each element can be either a standalone item or a collection of items.

The Composite pattern simplifies client code by allowing uniform treatment of single objects and composite objects, making it ideal when **dealing with recursive structures.**

### Key concepts

- **Component Interface:** Defines the common interface for all elements, allowing individual and composite elements to be treated uniformly.
- **Leaf:** Represents individual, end-unit objects in the composition that do not contain other objects.
- **Composite:** Holds child components, implementing methods to manage child elements.
- **Uniformity:** Ensures clients interact with individual and composite elements through the same interface.

### General Architecture

![Screenshot 2025-04-19 alle 11.14.18.png](Design%20patterns%201ee75be0d2f280ccba3ec4bb068cefe5/Screenshot_2025-04-19_alle_11.14.18.png)

- **Component:** The interface that declares the operations for both leaves and composites.
- **Leaf:** A basic element with no children, implements Component operations independently.
- **Composite:** Stores and manages child components, implementing add, remove, and getChild methods.

### An example

Imagine a company structure with departments and employees. Departments may contain employees or sub-departments. The Composite pattern lets us represent this hierarchy so that clients can uniformly access departments and employees.

```java
interface OrganizationComponent {
	void showDetails();
}

class Department implements OrganizationComponent {
	private String name;
	private List<OrganizationComponent> components = new ArrayList<>();
	public Department(String name) {
		this.name = name;
	}
	
	public void addComponent(OrganizationComponent component) {
		components.add(component);
	}
	
	public void removeComponent(OrganizationComponent component) {
		components.remove(component);
	}
	
	@Override
	public void showDetails() {
		System.out.println("Department: " + name);
		for (OrganizationComponent component : components) {
			component.showDetails();
		}
	}
}

class Employee implements OrganizationComponent {
	private String name;
	private String position;
	public Employee(String name, String position) {
		this.name = name;
		this.position = position;
	}
	
	@Override
	public void showDetails() {
		System.out.println(name + " (" + position + ")");
	}
}

```

```java
public static void main(String[] args) throws Exception {
	// Employees
	Employee emp1 = new Employee("Alice", "Developer");
	Employee emp2 = new Employee("Bob", "Designer");
	
	// Department with employees
	Department itDepartment = new Department("IT Department");
	itDepartment.addComponent(emp1);
	itDepartment.addComponent(emp2);
	
	// Larger department with a sub-department
	Department headOffice = new Department("Head Office");
	headOffice.addComponent(itDepartment);
	headOffice.addComponent(new Employee("Eve", "Manager"));
	headOffice.showDetails();
}
```

### Best Practices

- **Use Uniform Interfaces:** Define a single interface for both composite and leaf objects to simplify client interactions.
- **Add Child Management in Composite Only:** Include add and remove methods only in the composite to avoid redundant methods in the leaf.
- **Recursive Operations:** Design operations in the composite to work recursively through all children, making the hierarchy flexible and extensible.
- **Balance Complexity:** Avoid over-complicating the composite structure; keep it simple and manageable.
- **Handle Null Cases:** Safeguard against null components to prevent unexpected errors in recursive operations.

## Flyweight

<aside>

**Shares objects to minimize memory use, useful for numerous similar objects.**

</aside>

# Behavioural Design Patterns

---

## Exception shielding

The Exception Shielding Pattern is a behavioral design pattern used to **prevent sensitive details from being exposed** to end-users or external systems when an exception occurs. This pattern ensures that only necessary information is conveyed while hiding internal implementation details.

Exception shielding **typically involves catching exceptions** at critical points, logging the error details internally, and **providing a generalized, user-friendly message to the client.**

Use this pattern when dealing with external APIs, user interfaces, or sensitive data processes where information leakage could lead to security risks or usability issues.

### Key concepts

- **Error Wrapping:** Wrap low-level exceptions in higher-level, user-friendly messages without revealing implementation details.
- **Logging:** Log detailed information about exceptions for internal diagnostics, but avoid exposing it to the client.
- **Error Messages:** Replace technical error messages with user-friendly or generic messages.
- **Exception Handling Layer:** Create a specific layer to catch and handle exceptions for consistent behavior across an application.

### General Architecture

![Screenshot 2025-04-25 alle 17.37.28.png](Design%20patterns%201ee75be0d2f280ccba3ec4bb068cefe5/Screenshot_2025-04-25_alle_17.37.28.png)

**Client:** Requests a service that may encounter an exception.

**Service:** The component performing operations that may throw exceptions.

**ExceptionShieldingHandler:** Intercepts exceptions, logs details internally, and provides safe error messages to the client.

### an Example

Imagine an application querying a database. If a connection fails, the Exception Shielding pattern prevents exposure of low-level database errors to the end user, logging details internally and returning a generic error message.

```java
// Service class where exceptions may occur
class DatabaseService {
	public void fetchData() throws Exception {
		throw new Exception("Database connection timeout"); // Simulated database error
	}
}

// Exception shielding handler
class ExceptionShieldingHandler {
	public void handleException(Exception e) {
		logException(e); // Log detailed exception for internal tracking
		throw new RuntimeException("An error occurred while fetching data. Please try again later.");
		// Shielded error message
	}
	private void logException(Exception e) {
		System.err.println("Internal error logged: " + e.getMessage());
	}
}

// Client code
public class ExceptionShieldingExample {
	public static void main(String[] args) {
		DatabaseService dbService = new DatabaseService();
		ExceptionShieldingHandler shieldingHandler = new ExceptionShieldingHandler();

		try {
			dbService.fetchData();
		} 
		catch (Exception e) {
			shieldingHandler.handleException(e); // Shield the exception
		}
	}
}
```

### Best Practices

- **Log Internally:** Capture detailed errors for debugging without exposing them to clients.
- **Use Generic Messages:** Replace technical details with simple, non-specific messages for user-facing exceptions.
- **Centralize Exception Handling:** Use a consistent handler to catch and shield exceptions across services.
- **Differentiate Critical Errors:** Shield only those errors that could lead to information leakage or confusion.
- **Test Exception Paths:** Regularly test exception shielding to ensure only intended messages are exposed.

## Chain of responsibility

<aside>

**Passes requests along a chain of handlers for processing.**

</aside>

The Chain of Responsibility Pattern is a behavioral design pattern that lets you pass requests along a chain of handlers. When a request is sent, each handler decides to either *process it or pass it to the next handler in the chain*.

This pattern promotes loose coupling by allowing handlers to be structured dynamically and by keeping client code separate from the handling logic.

Use this pattern when **multiple objects can handle a request**, but the specific handler isnʼt known until runtime, or when you want to control the sequence of processing operations.

### Key concepts

- **Handler Interface:** Defines a method for handling requests and a reference to the next handler.
- **Concrete Handler:** Each handler in the chain decides if it can process the request; if not, it forwards it.
- **Chain Structure:** Allows the creation of a flexible chain of handlers where each one can process or pass the request.
- **Decoupling:** Separates request processing logic from the client, improving modularity.

### General Architecture

![Screenshot 2025-04-25 alle 18.18.20.png](Design%20patterns%201ee75be0d2f280ccba3ec4bb068cefe5/Screenshot_2025-04-25_alle_18.18.20.png)

- **Handler:** Interface with methods for setting the next handler and handling requests.
- **ConcreteHandlerA and ConcreteHandlerB:** Specific implementations that process the request or pass it to the next handler.
- **nextHandler:** Reference that connects handlers in the chain.

### an Example

Imagine a customer support system that handles requests at different levels (e.g., general inquiries, technical support, and more). Each handler in the chain will determine if it can process the request or pass it along to the next handler.

```java
public enum Difficulty {
	LEVEL1(1),
	LEVEL2(2),
	LEVEL3(3),
	LEVEL4(4);
	
	private final int level;
	
	private Difficulty(int level) {
		this.level = level;
	}
	public int getLevel() {
		return level;
	}
}

//actual req been processed
public class SupportRequest { 
	private Difficulty difficulty;
	private String message;
	
	public SupportRequest(Difficulty difficulty, String message) {
		this.difficulty = difficulty;
		this.message = message;
	}
	public Difficulty getDifficulty() {
		return difficulty;
	}
	public String getMessage() {
		return message;
	}
}

public abstract class SupportHandler {
	protected SupportHandler nextHandler;
	
	public void setNextHandler(SupportHandler nextHandler) {
		this.nextHandler = nextHandler;
	}
	public abstract void handleRequest(SupportRequest request);
}

public class L1SupportHandler extends SupportHandler {
	@Override
	public void handleRequest(SupportRequest request) {
		if (request.getDifficulty().getLevel() <= Difficulty.LEVEL1.getLevel()) {
			System.out.println("L1 support handling request: " + request.getMessage());
		} 
		else if (nextHandler != null) {
			System.out.println("L1 cannot handle request. Escalating to L2...");
			nextHandler.handleRequest(request);
		}
	}
}

public class L2SupportHandler extends SupportHandler {
	@Override
	public void handleRequest(SupportRequest request) {
		if (request.getDifficulty().getLevel() <= Difficulty.LEVEL2.getLevel()) {
			System.out.println("L2 support handling request: " + request.getMessage());
		} 
		else if (nextHandler != null) {
			System.out.println("L2 cannot handle request. Escalating to L3...");
			nextHandler.handleRequest(request);
		}
	}
}

public static void main(String[] args) {
	// Create handlers
	SupportHandler l1 = new L1SupportHandler();
	SupportHandler l2 = new L2SupportHandler();
	SupportHandler l3 = new L3SupportHandler();
	
	// Set up the chain of responsibility: L1 -> L2 -> L3
	l1.setNextHandler(l2);
	l2.setNextHandler(l3);
	
	SupportRequest request1 = new SupportRequest(Difficulty.LEVEL1, "Password reset request.");
	SupportRequest request2 = new SupportRequest(Difficulty.LEVEL2, "System installation issue.");
	SupportRequest request3 = new SupportRequest(Difficulty.LEVEL3, "Database connectivity issue.");
	SupportRequest request4 = new SupportRequest(Difficulty.LEVEL4, "Complex system outage.");
	
	// Process each request starting at the first handler (L1)
	System.out.println("Processing request 1:");
	l1.handleRequest(request1);
	
	System.out.println("\nProcessing request 2:");
	l1.handleRequest(request2);
	
	System.out.println("\nProcessing request 3:");
	l1.handleRequest(request3);
	
	System.out.println("\nProcessing request 4:");
	l1.handleRequest(request4);
}
```

### Best Practices

- **Keep Handlers Simple:**
Ensure each handler has a single responsibility and does minimal work before passing the request.
- **Terminate the Chain:**
End the chain with a handler that handles or logs unhandled requests to prevent failures.
- **Dynamic Configuration:**
Allow the chain to be configured at runtime for flexible handling.
- **Avoid Circular Chains:**
Make sure handlers do not form circular references, which could lead to infinite loops.
- **Use When Multiple Handlers Are Possible:**
Apply this pattern if multiple handlers can handle a request, and the order of handling matters.

## Command

<aside>

**Encapsulates requests as objects, allowing parameterization and undo functionality.**

</aside>

## Iterator

<aside>

**Provides a way to sequentially access elements of a collection.**

</aside>

The Iterator Pattern is a behavioral design pattern that allows **sequential traversal through complex data structures** without exposing the underlying details. It provides a standard interface for **iterating over elements**, enabling easy access to collections while keeping the collection's implementation hidden.

This pattern is especially useful when you need to **access elements of a collection without knowing its structure**, or when you need to implement different ways of accessing collection elements, such as forward or backward traversal.

### Key concepts

- **Iterator Interface:** Defines the methods (hasNext(), next()) to iterate through a collection.
- **Concrete Iterator:** Implements the iterator interface for specific types of collections.
- **Aggregate Interface:** Defines a method for creating an iterator for the collection.
- **Concrete Aggregate:** Implements the aggregate interface to create instances of the concrete iterator.

### General Architecture

![Screenshot 2025-04-28 alle 18.34.29.png](Design%20patterns%201ee75be0d2f280ccba3ec4bb068cefe5/Screenshot_2025-04-28_alle_18.34.29.png)

- **Iterator:** Interface with methods for traversal, such hasNext() and next().
- **ConcreteIterator:** Implements the Iterator interface for specific data structures.
- **Aggregate:** Interface defining createIterator(), allowing collections to create iterators.
- **ConcreteAggregate:** Concrete implementation of the collection, returning a ConcreteIterator.

### an Example

Consider a library with a collection of books that needs to be accessed sequentially, regardless of the internal data structure. The Iterator pattern enables sequential access without exposing internal details.

```java
class Book {
	private String title;
	
	public Book(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
}

interface BookIterator {
	boolean hasNext();
	Book next();
}

interface BookCollection {
	BookIterator createIterator();
	int getSize();
	Book getBookAt(int index);
}

class Library implements BookCollection {
	private List<Book> books = new ArrayList<>();
	public void addBook(Book book) {
		books.add(book);
	}
	
	@Override
	public BookIterator createIterator() {
		return new LibraryBookIterator(this);
	}
	
	@Override
	public int getSize() {
		return books.size();
	}
	
	@Override
	public Book getBookAt(int index) {
		return books.get(index);
	}
}
```

### Best Practices

- **Encapsulate Collection Details:**
Use iterators to access elements without exposing the underlying structure.
- **Single Responsibility:**
Keep iterator logic separate from collection, enhancing modularity.
- **Avoid Modifying Collections:**
Iterators should primarily focus on traversal, not modifying elements.
- **Null Safety:**
Handle cases where elements might be missing to prevent NullPointerExceptions.
- **Reusable Iterators:**
Ensure iterators can be reused or reset for better efficiency and flexibility.

## Mediator

<aside>

**Centralizes communication between objects, promoting loose coupling among them.**

</aside>

## Memento

<aside>

**Captures and restores an object's internal state without breaking encapsulation.**

</aside>

The Memento Pattern is a behavioural design pattern that enables an **object to save and restore its state** without exposing its internal details.

The Memento Pattern involves three main components: the originator, which creates and restores its states; the memento, which stores the state; and the caretaker, which manages the mementos.

This pattern is particularly useful **for implementing undo/redo** functionality or **saving checkpoints** in an application's workflow.

Use this pattern when you need to capture an object's state periodically and restore it when needed.

### Key concepts

- **Originator:** The object whose state needs to be saved and restored.
- **Memento:** Stores the state of the Originator at a particular time without exposing its details.
- **Caretaker:** Manages mementos by storing and restoring them as required.
- **Encapsulation:** Ensures that state details are hidden within the memento, maintaining the integrity of the Originator.

### General Architecture

- **Originator →** The object whose state is stored and restored through mementos.
- **Memento →** A snapshot of the originator's state at a specific moment.
- **Caretaker →** Manages the mementos, allowing retrieval and restoration of previous states.

![Screenshot 2025-05-03 alle 15.52.30.png](Design%20patterns%201ee75be0d2f280ccba3ec4bb068cefe5/Screenshot_2025-05-03_alle_15.52.30.png)

### an Example

Imagine a text editor that allows users to type text and undo changes. Using the Memento pattern, we can save the editorʼs state at each change and restore it as needed for the undo functionality.

```java
class TextEditor {
private StringBuilder content = new StringBuilder);
public void type(String words) {
content. append (words) ;
}
public String getContent() {
return content.toString();
public TextEditorMemento save() {
return new TextEditorMemento (content. toString()):
}
}
public void restore TextEditorMemento memento) 1
content = new StringBuilder (memento.getState());
}
}

// Memento class
class TextEditorMemento {
	private final String state;

	public TextEditorMemento (String state) {
		this.state = state;
	}
	public String getState(){
		return state;
	}
}

class History {
	private List<TextEditorMemento> mementos = new ArrayList<>();

	public void addMemento(TextEditorMemento memento) {
		mementos. add (memento) ;
	}
	public TextEditorMemento getMemento (int index) {
		return mementos.get (index);
	}
}

public class App {
	public static void main(String[] args) {
		TextEditor editor = new TextEditor();
		History history = new History();
		
		editor.type("Hello, ");
		history.addMemento(editor.save());
		
		editor.type("world!");
		history.addMemento(editor.save());
		
		editor.type(" More text.");
		System.out.println("Current content: " + editor.getContent());
		
		// Undo to previous states
		editor.restore(history.getMemento(1));
		System.out.println("After undo: " + editor.getContent());
		
		editor.restore(history.getMemento(0));
		System.out.println("After second undo: " + editor.getContent());
	}
}
```

### Best practices

- **Maintain Encapsulation**
Keep state details hidden within the memento to protect originator integrity.
- **Avoid Excessive Mementos**
Limit memento creation to essential points to avoid memory issues.
- **Store Only Necessary State**
Capture only the minimal state needed to restore the object correctly.
- **Use Immutable Mementos**
Make mementos immutable to prevent accidental modifications to saved states.
- **Test Restoration Logic**
Ensure that restoring from mementos consistently returns the originator to a valid state.

## Observer

<aside>

**Automatically notifies dependents when an object's state changes.**

</aside>

The Observer Pattern is a behavioral design pattern that allows one object (the Subject) to **notify multiple dependent objects** (Observers) about changes in its state. This pattern establishes a one-to-many relationship between objects, where updates to the Subject automatically propagate to all registered Observers.

This decoupling of the Subject from its Observers promotes flexibility and helps manage complex dependencies.

The Observer Pattern is useful for implementing event handling, real-time notifications, and scenarios where multiple parts of a system must be synchronized with a data source.

### Key concepts

- **Subject:** The main object that holds state and notifies registered observers of any changes.
- **Observer Interface:** Defines the update method that Observers implement to receive state updates.
- **Concrete Observer:** Implements the Observer interface and reacts to state changes in the Subject.
- **Loose Coupling:** Allows Observers to register or unregister dynamically, decoupling dependencies.

### General Architecture

![Screenshot 2025-05-04 alle 15.47.05.png](Design%20patterns%201ee75be0d2f280ccba3ec4bb068cefe5/Screenshot_2025-05-04_alle_15.47.05.png)

- **Subject:** Maintains a list of observers and provides methods to attach, detach, and notify them of changes.
- **Observer:** Interface with an update method that Observers must implement.
- **Concrete Subject:** A specific implementation of Subject, which stores state and notifies observers upon changes.
- **Concrete Observer:** Implements the Observer interface and responds to state changes.

### an Example

Imagine a news application that sends updates to users who subscribe to specific topics. Using the Observer Pattern, the application can notify all subscribers when new content is available.

```java
class NewsTopic implements Subject {
	private String topic;
	private List<Observer> observers;
	
	public NewsTopic(String topic) {
		this.topic = topic;
		this.observers = new ArrayList<>();
	}
	
	@Override
	public void registerObserver(Observer observer) {
		if (observer != null && !observers.contains(observer)) {
			observers.add(observer);
		}
	}
	
	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	
	@Override
	public void notifyObservers(String news) {
		for (Observer observer : observers) {
			observer.update(topic, news);
		}
	}
	
	public void publishNews(String news) {
		System.out.println("Publishing news on topic: " + topic);
		notifyObservers(news);
	}
}
```

```java
public class NewsApplication {
	public static void main(String[] args) {
		// Create a news topic
		NewsTopic techNews = new NewsTopic("Technology");
		
		// Create subscribers
		Observer alice = new Subscriber("Alice");
		Observer bob = new Subscriber("Bob");
		Observer charlie = new Subscriber("Charlie");
		
		// Subscribers register to the news topic
		techNews.registerObserver(alice);
		techNews.registerObserver(bob);
		techNews.registerObserver(charlie);
		
		// Publish news update
		techNews.publishNews("New smartphone released!");
		
		// Remove one subscriber
		techNews.removeObserver(bob);
		
		// Publish another news update
		techNews.publishNews("Breakthrough in AI!");
	}
}
```

### Best practices

- **Minimize Coupling:** Use an Observer interface to keep the Subject and Observers loosely coupled.
- **Handle Unsubscriptions:** Allow Observers to easily detach to prevent memory leaks.
- **Notify Efficiently:** Optimize notifications to avoid performance issues with large numbers of Observers.
- **Consider Observer Registration:** Track Observers properly to manage relationships effectively.
- **Avoid Cyclic Dependencies:** Prevent cyclical notifications among Observers to avoid infinite update loops.

## State

<aside>

**Alters object behavior based on its internal state changes.**

</aside>

## Interpreter

<aside>

**Evaluates sentences in a language by defining grammar and interpreting.**

</aside>

## Strategy

<aside>

**Encapsulates interchangeable algorithms, allowing them to vary independently.**

</aside>

The Strategy Pattern is a behavioral design pattern that enables **selecting an algorithm's behavior at runtime.** It works by defining a family of algorithms, encapsulating each one, and **making them interchangeable.**

This pattern is ideal when **multiple algorithms** are required to accomplish a task, and we need **flexibility** in choosing between them dynamically.

Commonly used in scenarios such as sorting methods, payment options, or dynamic behaviors in gaming, it promotes code modularity and minimizes conditional logic.

### Key concepts

- **Strategy Interface:** Defines the common method that each algorithm (strategy) must implement.
- **Concrete Strategies:** Implement different versions of the algorithm.
- **Context Class:** Holds a reference to a strategy and delegates task execution to it.
- **Dynamic Switching:** Enables changing the strategy at runtime, enhancing flexibility.

### General Architecture

![Screenshot 2025-04-19 alle 09.56.59.png](Design%20patterns%201ee75be0d2f280ccba3ec4bb068cefe5/ddfacd41-ac47-4baf-8326-7ed10c77f582.png)

- **Context:** Contains a reference to a Strategy and uses it to execute behavior.
- **Strategy Interface:** Defines an execute() method.
- **Concrete Strategies (A & B):** Implement specific behaviors of the execute() method.

### An example

Imagine an online store offering multiple payment options. Using the Strategy pattern, we can add new payment methods without modifying the core processing logic.

```java
//strategy interface
interface PaymentStrategy {
	void pay(double amount);
}

//concrete strategy A
class CreditCardPayment implements PaymentStrategy {
	@Override
	public void pay(double amount) {
		System.out.println("Paid $" + amount + " with Credit Card.");
	}
}

//cocrete strategy B
class PayPalPayment implements PaymentStrategy {
	@Override
	public void pay(double amount) {
		System.out.println("Paid $" + amount + " using PayPal.");
	}
}
```

```java
class Order {
	private PaymentStrategy paymentStrategy;
	
	public Order(PaymentStrategy paymentStrategy) {
		this.paymentStrategy = paymentStrategy;
	}
	
	public void processPayment(double amount) {
		paymentStrategy.pay(amount);
	}
}
```

```java
public class App {
	public static void main(String[] args) throws Exception {
		Order order1 = new Order(new CreditCardPayment());
		order1.processPayment(100);
		
		Order order2 = new Order(new PayPalPayment());
		order2.processPayment(200);
	}
}
```

### Best Practices

- **Use Composition:** Compose behavior using strategy classes, improving modularity.
- **Favor Interfaces:** Define strategies with interfaces to simplify adding or changing algorithms.
- **Limit Context Knowledge:** Ensure Context only knows Strategy interfaces, not implementations.
- **Avoid Excessive Strategies:** Use the pattern only when there are multiple significant behaviors.
- **Leverage Dependency Injection:** Inject strategy into Context for more flexible configuration.

## Template

<aside>

**Defines an algorithm's skeleton, allowing subclasses to modify specific steps.**

</aside>

The Template Pattern is a Behavioral Design Pattern that defines the skeleton of an algorithm in a base class, allowing subclasses to implement specific steps.

This pattern is useful when you have an overarching process (or template) with steps that can be customized in different ways by subclasses.

The Template pattern enables code reuse by allowing subclasses to change certain parts of the algorithm without altering its structure.

### Key concepts

- **Template Method:** A method in the base class that defines the skeleton of the operation. It often contains calls to abstract or hook methods, which subclasses can override.
- **Abstract Methods:** These are steps in the template that are left unimplemented in the base class, forcing subclasses to provide their specific implementation.
- **Hook Methods (Optional):** Concrete methods in the base class that can be overridden by subclasses but arenʼt mandatory to do so. They provide optional extension points.

### Architecture

![Screenshot 2025-04-16 alle 17.35.21.png](Design%20patterns%201ee75be0d2f280ccba3ec4bb068cefe5/Screenshot_2025-04-16_alle_17.35.21.png)

**AbstractClass**

Contains the `templateMethod()` which defines the algorithm structure.

It includes abstract methods (e.g., `primitiveOperation1`, `primitiveOperation2`) that subclasses must implement.

**ConcreteClassA** and **ConcreteClassB**

Implement specific steps of the algorithm defined by `templateMethod()` in AbstractClass.

### Example

Consider a scenario where you want to generate documents in different formats (e.g., HTML and PDF).
The overall steps for generating a document are similar, but the way the document content is formatted differs based on the type.
Weʼll use the Template pattern to define a general structure for generating a document and let subclasses provide specific implementations for each format.

```java
public abstract class DocumentGenerator {
	// Template method defining the steps to generate a document
	public final void generateDocument() {
		openDocument();
		addTitle();
		addContent();
		closeDocument();
}

	// Abstract methods that subclasses must implement
	protected abstract void openDocument();
	protected abstract void addTitle();
	protected abstract void addContent();
	protected abstract void closeDocument();
}

public class App {
	public static void main(String[] args) {
		DocumentGenerator htmlDocument = new HTMLDocument();
		DocumentGenerator pdfDocument = new PDFDocument();
		System.out.println("Generating HTML Document:");
		htmlDocument.generateDocument();
		System.out.println("\nGenerating PDF Document:");
		pdfDocument.generateDocument();
	}
}
```

```java
public class HTMLDocument extends DocumentGenerator ‹
	@Override
	protected void openDocument) {
		System.out.println ("Opening HTML document...");
	}
	@Override
	protected void addTitle) {
		System.out.println("<h1>Document Title</h1>");
	}
	@Override
	protected void addContent) {
		System.out. println("<p>This is the content of the HTML document. </p>");
	}
	@Override
	protected void closeDocument) {
		System.out.println("Closing HTML document.");
	}
}
```

```java
public class PDFDocument extends DocumentGenerator {
	@Override
	protected void openDocument) {
		System.out.println("Opening PDF document...");
	}
	@Override
	protected void addTitle) {
		System.out.println("[PDF Title: Document Title]");
	}
	@Override
	protected void addContent) {
		System.out .println("[PDF Content: This is the content of the PDF document.]");
	}
	@Override
	protected void closeDocument ( {
		System.out.println("Closing PDF document.");
	}
}
```

### Best practices

- **Use Final Modifier on Template Method:** Mark the template method as final to prevent subclasses from altering the algorithm structure.
- **Define Required Steps as Abstract Methods:** Steps that every subclass must implement should be defined as abstract methods in the base class.
- **Use Hook Methods for Optional Steps:** If some steps are optional, consider using concrete (non-abstract) methods in the base class that subclasses can override if needed. This provides flexibility without enforcing implementation.
- **Keep the Template Method Simple:** Avoid complex logic in the template method. It should merely define the sequence of operations.
- **Favor Composition Over Inheritance When Possible:** In situations where inheritance may lead to rigid class structures, consider using strategy or delegate patterns instead.

## Visitor

<aside>

**Separates operations from objects, allowing new operations without modifying classes.**

</aside>