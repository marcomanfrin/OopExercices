package other;

import java.util.*;

public class textEditor {
    
public static void main(String[] args) throws Exception {
    TextEditor editor = new TextEditor();
        editor.write("Hello");
        editor.write(" World");
        editor.save();
        editor.write("!!!");
        System.out.println("Before undo: " + editor.getText());
        editor.undo();
        System.out.println("After undo: " + editor.getText());
}

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

}
