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

        if (prereqFlag) this.clipboard.push(copiedEntry);

        //Replace this later on with an exception, to avoid overloading system memory
        //with too many copied elements
        else System.out.println("Failed to copy, reached Clipboard limit");
    }

    /**
     * Returns the clipboard object to the user call
     * @return global variable clipboard stack object
     */
    public Stack<String> getClipboard () {
        return this.clipboard;
    }
}
