package other;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

// Memento: stato dell'editor
class EditorState implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String content;

    public EditorState(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

// Caretaker: gestisce la cronologia degli stati
class History {
    private final Deque<EditorState> states = new ArrayDeque<>();

    public void push(EditorState state) {
        states.push(state);
    }

    public EditorState pop() {
        return states.pop();
    }

    public boolean isEmpty() {
        return states.isEmpty();
    }
}

// Originator: l'editor di testo
public class mementoTextEditor {
    private String content = "";
    private final History history = new History();

    // Digita nuovo testo, salvando lo stato precedente
    public void type(String text) {
        history.push(save());
        content += text;
    }

    // Salva lo stato corrente in un memento
    public EditorState save() {
        return new EditorState(content);
    }

    // Ripristina uno stato precedente
    private void restore(EditorState state) {
        this.content = state.getContent();
    }

    // Annulla l'ultima modifica
    public void undo() {
        if (!history.isEmpty()) {
            EditorState prev = history.pop();
            restore(prev);
        }
    }

    public String getContent() {
        return content;
    }

    // Demo
    public static void main(String[] args) {
        mementoTextEditor editor = new mementoTextEditor();
        editor.type("Version 1. ");
        editor.type("Version 2. ");
        editor.type("Version 3.");

        System.out.println("Contenuto corrente: " + editor.getContent());
        editor.undo();
        System.out.println("Dopo undo: " + editor.getContent());
        editor.undo();
        System.out.println("Dopo secondo undo: " + editor.getContent());
    }
}
