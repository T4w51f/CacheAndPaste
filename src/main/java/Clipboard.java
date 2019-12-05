import java.util.Stack;

/**
 * This class will use a Stack to hold
 * recent copied contents. It will have
 * a size of 100 elements and allow the
 * user to access the 5 latest elements.
 */

public class Clipboard {
    //The clipboard is represented by a stack of Strings
    private Stack<String> clipboard;

    //Constants
    private final int MAX_LENGTH = 100;
    private final int RESTRICTED_LENGTH = 5;

    /**
     * Creates a new instance of the Clipboard object
     * Creates an empty Stack and instantiates the
     * global variable clipboard
     */
    public Clipboard () {
        this.clipboard = new Stack<String>();
    }

    /**
     * This method will be used to add new copied contents
     * into the clipboard stack
     * @param copiedEntry non-empty string to be added
     */
    public void addToClipboard (String copiedEntry) {
        //Check whether the stack has enough space
        //Check if the content to be added is not null
        //Check if the content is not an empty string
        boolean prereqFlag = (this.clipboard.size() <= MAX_LENGTH) && !copiedEntry.equals("") && copiedEntry != null;

        if (prereqFlag && !this.clipboard.contains(copiedEntry)) {
            this.clipboard.push(copiedEntry);
        }

        //Replace this later on with an exception, to avoid overloading system memory
        //with too many copied elements
        //else System.out.println("Failed to copy, reached Clipboard limit");
    }

    /**
     * This method clears the whole clipboard upon
     * the user's request
     */
    public void clearAll () {
        this.clipboard.clear();
    }

    public String getContent (int base1_index) {
        Stack<String> holder = new Stack<String>();
        String content = "";

        for (int i = 0; i < base1_index; i++) {
            holder.push(this.clipboard.pop());
        }

        //Using pop to retain remove the element from the stack
        //and place it on the top of the stack so that the user
        //has the latest entry stored on top - which will be
        //defaulted to CTRL + V for ease of access
        content = holder.pop();

        for (int j = 0; j < base1_index - 1; j++) {
            this.clipboard.push(holder.pop());
        }
        this.clipboard.push(content);
        return content;
    }

    /**
     * Returns the clipboard object to the user call
     * @return global variable clipboard stack object
     */
    public Stack<String> getClipboard () {
        return this.clipboard;
    }

    /**
     * Prints the stack
     */
    public void printClipboard() {
        System.out.println(clipboard.toString());
    }

    public int size() {
        return clipboard.size();
    }
}
