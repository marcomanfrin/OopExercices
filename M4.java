import java.io.*;
import java.util.*;
import java.util.logging.*;

public class M4 {
    public static void main(String[] args) {
        // 46. Division
        DivisionHandler.divide100ByUserInput();

        // 47. Square root with logging
        SqrtLogger.calculateSqrt(16);
        SqrtLogger.calculateSqrt(-9);

        // 48. Safe parsing
        SafeParser.parseNumbers(Arrays.asList("42", "abc", "100"));

        // 49. Login system
        try {
            LoginSystem.login();
        } catch (LoginException e) {
            System.out.println(e.getMessage());
        }

        // 50. Exception propagation
        FileProcessor.run();

        // 51. Library wrapper
        try {
            LibraryMethod.callThirdParty();
        } catch (LibraryException e) {
            System.out.println("Caught LibraryException: " + e.getMessage());
        }

        // 52-53. Chain of responsibility logger
        LoggerHandler loggerChain = new ConsoleLogger();
        LoggerHandler fileLogger = new FileLogger();
        LoggerHandler emailLogger = new EmailLogger();
        loggerChain.setNext(fileLogger);
        fileLogger.setNext(emailLogger);

        loggerChain.log(LogLevel.INFO, "This is an info message.");
        loggerChain.log(LogLevel.WARNING, "This is a warning.");
        loggerChain.log(LogLevel.ERROR, "This is an error.");

        // 55. Catching all exceptions and explaining risks
        UnsafeCatcher.riskyOperation();
    }
}

// Exercise 46
class DivisionHandler {
    public static void divide100ByUserInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter an integer: ");
            int number = scanner.nextInt();
            System.out.println("Result: " + (100 / number));
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter an integer.");
        }
        finally {
            scanner.close();
        }
    }
}

// Exercise 47
class SqrtLogger {
    private static final Logger logger = Logger.getLogger("SqrtLogger");

    static {
        try {
            FileHandler fh = new FileHandler("sqrt.log", true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void calculateSqrt(double number) {
        logger.info("Input: " + number);
        if (number < 0) {
            logger.warning("Invalid input: negative number");
            System.out.println("Cannot calculate square root of negative number.");
        } else {
            double result = Math.sqrt(number);
            logger.info("Result: " + result);
            System.out.println("Square root: " + result);
        }
    }
}

// Exercise 48
class SafeParser {
    private static final Logger logger = Logger.getLogger("SafeParser");

    public static void parseNumbers(List<String> list) {
        for (String s : list) {
            try {
                int num = Integer.parseInt(s);
                System.out.println("Parsed: " + num);
            } catch (NumberFormatException e) {
                logger.warning("Unparseable string: " + s);
            }
        }
    }
}

// Exercise 49
class LoginException extends Exception {
    public LoginException(String message) {
        super(message);
    }
}

class LoginSystem {
    private static final String USER = "admin";
    private static final String PASS = "1234";

    public static void login() throws LoginException {
        try (Scanner scanner = new Scanner(System.in)) {
            int attempts = 0;
            while (attempts < 3) {
                System.out.print("Username: ");
                String user = scanner.next();
                System.out.print("Password: ");
                String pass = scanner.next();
                if (USER.equals(user) && PASS.equals(pass)) {
                    System.out.println("Login successful!");
                    return;
                } else {
                    attempts++;
                    System.out.println("Invalid credentials.");
                }
            }
            throw new LoginException("Too many failed attempts. Access blocked.");
        }
    }
}

// Exercise 50
class FileProcessor {
    public static void readFile() throws IOException {
        throw new IOException("File not found");
    }

    public static void processData() throws IOException {
        readFile();
    }

    public static void run() {
        try {
            processData();
        } catch (IOException e) {
            System.out.println("Caught exception in main: " + e.getMessage());
        }
    }
}

// Exercise 51
class LibraryException extends Exception {
    public LibraryException(String message, Throwable cause) {
        super(message, cause);
    }
}

class LibraryMethod {
    public static void callThirdParty() throws LibraryException {
        try {
            throw new RuntimeException("Third-party failure");
        } catch (RuntimeException e) {
            throw new LibraryException("Wrapped exception", e);
        }
    }
}

// Exercises 52, 53, 54
enum LogLevel { INFO, WARNING, ERROR }

abstract class LoggerHandler {
    protected LoggerHandler next;

    public void setNext(LoggerHandler next) {
        this.next = next;
    }

    public void log(LogLevel level, String message) {
        if (!handle(level, message) && next != null) {
            next.log(level, message);
        }
    }

    protected abstract boolean handle(LogLevel level, String message);
}

class ConsoleLogger extends LoggerHandler {
    protected boolean handle(LogLevel level, String message) {
        if (level == LogLevel.INFO) {
            System.out.println("Console: " + message);
            return true;
        }
        return false;
    }
}

class FileLogger extends LoggerHandler {
    protected boolean handle(LogLevel level, String message) {
        if (level == LogLevel.WARNING) {
            System.out.println("File: " + message);
            return true;
        }
        return false;
    }
}

class EmailLogger extends LoggerHandler {
    protected boolean handle(LogLevel level, String message) {
        if (level == LogLevel.ERROR) {
            System.out.println("Email: " + message);
            return true;
        }
        return false;
    }
}

// Exercise 55
class UnsafeCatcher {
    public static void riskyOperation() {
        try {
            throw new RuntimeException("Unknown failure");
        } catch (Exception e) {
            System.out.println("Caught an exception: " + e.getMessage());
            // Logging untrusted input can cause issues if not sanitized
            // Avoid exposing sensitive info to logs (e.g., stack traces or input that may be malicious)
        }
    }
    // Catching Exception is risky because:
    //  - It hides bugs or critical errors.
    //  - It may log sensitive info (e.g., Log4Shell).
    //  - It violates the principle of least privilege in error handling.
}