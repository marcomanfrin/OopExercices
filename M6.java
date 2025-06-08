import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class M6 {
    public static void main(String[] args) throws Exception {
        // Esercizi precedenti...

        // M6.68 - Lettura file
        readTxtFile("example.txt");

        // M6.69 - Scrittura file
        writeMessagesToFile("output.txt", List.of("Hello", "World"));

        // M6.70 - Conteggio parole
        Map<String, Integer> wordCount = countWordsFromFile("example.txt");
        System.out.println(wordCount);

        // M6.71 - Copia file binario
        copyBinaryFile("image.jpg", "copy_image.jpg");

        // M6.72 - Scrittura/Lettura dati binari
        writeData("data.bin", 42, 3.14, "Hello");
        readData("data.bin");

        // M6.73 - Serializzazione oggetto
        User user = new User("Alice", "alice@mail.com", 30);
        serializeUser("user.ser", user);
        System.out.println(deserializeUser("user.ser"));

        // M6.74 - Serializzazione lista
        List<Book> books = List.of(new Book("Java", "Author A"), new Book("Python", "Author B"));
        serializeBooks("books.ser", books);
        deserializeBooks("books.ser").forEach(System.out::println);

        // M6.76 - File .txt ordinati
        printSortedTxtFiles(".");

        // M6.77 - FileChannel read
        readWithFileChannel("example.txt");

        // M6.78 - Filtraggio e salvataggio
        filterAndSaveLines("example.txt", "filtered.txt", "keyword");

        // M6.79 - Input utente validato
        validateAndSaveInput("user_input.txt", "prohibited");

        // M6.80 - Memento Pattern
        TextEditor editor = new TextEditor();
        editor.write("Hello");
        editor.write(" World");
        editor.save();
        editor.write("!!!");
        System.out.println("Before undo: " + editor.getText());
        editor.undo();
        System.out.println("After undo: " + editor.getText());

        // M6.81 - Log filtering
        List<String> filtered = filterLogs("log.txt", "2024-01-01", "2025-01-01", "ERROR", "NullPointer");
        filtered.forEach(System.out::println);
    }

    static void readTxtFile(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null)
                System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void writeMessagesToFile(String path, List<String> messages) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            for (String msg : messages)
                writer.write(msg + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Map<String, Integer> countWordsFromFile(String path) throws IOException {
        Map<String, Integer> map = new HashMap<>();
        Pattern pattern = Pattern.compile("\\W+");
        Files.lines(Path.of(path))
                .map(String::toLowerCase)
                .flatMap(line -> Arrays.stream(pattern.split(line)))
                .filter(word -> !word.isBlank())
                .forEach(word -> map.put(word, map.getOrDefault(word, 0) + 1));
        return map;
    }

    static void copyBinaryFile(String src, String dest) throws IOException {
        try (InputStream in = new FileInputStream(src); OutputStream out = new FileOutputStream(dest)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0)
                out.write(buffer, 0, length);
        }
    }

    static void writeData(String path, int i, double d, String s) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(path))) {
            dos.writeInt(i);
            dos.writeDouble(d);
            dos.writeUTF(s);
        }
    }

    static void readData(String path) throws IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(path))) {
            System.out.println("int: " + dis.readInt());
            System.out.println("double: " + dis.readDouble());
            System.out.println("String: " + dis.readUTF());
        }
    }

    static void serializeUser(String path, User user) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(user);
        }
    }

    static User deserializeUser(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (User) ois.readObject();
        }
    }

    static void serializeBooks(String path, List<Book> books) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(books);
        }
    }

    static List<Book> deserializeBooks(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                return ((List<?>) obj).stream()
                        .filter(Book.class::isInstance)
                        .map(Book.class::cast)
                        .collect(Collectors.toList());
            } else {
                throw new IOException("Deserialized object is not a List");
            }
        }
    }

    static void printSortedTxtFiles(String dir) throws IOException {
        Files.list(Path.of(dir))
                .filter(p -> p.toString().endsWith(".txt"))
                .sorted()
                .forEach(System.out::println);
    }

    // TODO: review FileChannel usage, this is a basic example
    static void readWithFileChannel(String path) throws IOException {
        try (FileChannel channel = FileChannel.open(Path.of(path), StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (channel.read(buffer) > 0) {
                buffer.flip();
                while (buffer.hasRemaining())
                    System.out.print((char) buffer.get());
                buffer.clear();
            }
        }
    }

    static void filterAndSaveLines(String inputPath, String outputPath, String keyword) throws IOException {
        try (Stream<String> lines = Files.lines(Path.of(inputPath))) {
            List<String> result = lines.filter(l -> l.contains(keyword))
                                       .map(String::toUpperCase)
                                       .collect(Collectors.toList());
            Files.write(Path.of(outputPath), result);
        }
    }

    static void validateAndSaveInput(String path, String forbidden) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter input: ");
        String input = scanner.nextLine().trim().replaceAll("\\s+", " ");
        if (input.contains(forbidden)) System.out.println("Error: contains forbidden words");
        else Files.writeString(Path.of(path), input + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        scanner.close();
    }

    // --- Serializable classes ---

    // TODO: review SerialVersionUID
    
    static class User implements Serializable {
        private static final long serialVersionUID = 1L;
        String name, email;
        int age;

        public User(String name, String email, int age) {
            this.name = name; this.email = email; this.age = age;
        }

        public String toString() {
            return name + ", " + email + ", " + age;
        }
    }
    
    static class Book implements Serializable {
        String title, author;
        public Book(String title, String author) {
            this.title = title; this.author = author;
        }
        public String toString() {
            return title + " by " + author;
        }
    }

    // --- Memento Pattern ---

    static class EditorState {
        private final String content;
        public EditorState(String content) {
            this.content = content;
        }
        public String getContent() {
            return content;
        }
    }

    static class TextEditor {
        private StringBuilder content = new StringBuilder();
        private final Stack<EditorState> history = new Stack<>();

        public void write(String text) {
            content.append(text);
        }

        public void save() {
            history.push(new EditorState(content.toString()));
        }

        public void undo() {
            if (!history.isEmpty()) {
                content = new StringBuilder(history.pop().getContent());
            }
        }

        public String getText() {
            return content.toString();
        }
    }

    // --- Log filtering CLI ---

    static List<String> filterLogs(String logPath, String fromDate, String toDate, String level, String keyword) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate from = LocalDate.parse(fromDate, formatter);
        LocalDate to = LocalDate.parse(toDate, formatter);

        try (Stream<String> lines = Files.lines(Path.of(logPath))) {
            return lines.filter(line -> {
                try {
                    String[] parts = line.split(" ", 3);
                    LocalDate date = LocalDate.parse(parts[0], formatter);
                    String logLevel = parts[1];
                    String msg = parts[2];
                    return (date.isAfter(from.minusDays(1)) && date.isBefore(to.plusDays(1))) &&
                           logLevel.equalsIgnoreCase(level) &&
                           msg.contains(keyword);
                } catch (Exception e) {
                    return false;
                }
            }).collect(Collectors.toList());
        }
    }
}
