import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Stack;

/**
 * This class will use a Stack to hold
 * recent copied contents. It will have
 * a size of 100 elements and allow the
 * user to access the 5 latest elements.
 */

public class Clipboard {
    //The clipboard is represented by an ArrayList of Strings
    private ArrayList<String> clipboard;

    //DEBUG flag for printing debugger statements on console
    boolean DEBUG = true;

    //Constants
    private final int MAX_LENGTH = 100;
    private final int RESTRICTED_LENGTH = 5;

    /**
     * Creates a new instance of the Clipboard object
     * Creates an empty Stack and instantiates the
     * global variable clipboard
     */
    public Clipboard () {
        this.clipboard = new ArrayList<>();
        if (DEBUG) System.out.println("DEBUGGER: Created new clipboard");
    }

    /**
     * This method will be used to add new copied contents
     * into the clipboard stack
     * @param copiedEntry non-empty string to be added
     */
    public void addToClipboard (String copiedEntry) {
        //Check if the content to be added is not null
        //Check if the content is not an empty string
        //Check if the content is already in the clipboard
        boolean prereqFlag = !copiedEntry.equals("") && copiedEntry != null && !clipboard.contains(copiedEntry);

        if (prereqFlag) {
            if (clipboard.size() < RESTRICTED_LENGTH) {
                clipboard.add(0, copiedEntry);
                if (DEBUG) System.out.println("DEBUGGER: Added copied entry");
            } else {
                clipboard.remove(RESTRICTED_LENGTH - 1);
                clipboard.add(0, copiedEntry);
                if (DEBUG) System.out.println("DEBUGGER: Reached max clipboard capacity");
                if (DEBUG) System.out.println("DEBUGGER: Removing oldest copied entry");
                if (DEBUG) System.out.println("DEBUGGER: Added copied entry");
            }
        }
    }

    /**
     * This method clears the whole clipboard upon
     * the user's request
     */
    public void clearAll () {
        clipboard.clear();
        if (DEBUG) System.out.println("DEBUGGER: Clearing the entire clipboard");
    }

    /**
     * This method returns the copied content at a given base 1 index
     * @param base1_index Ranging from 1 to 5 for this clipboard
     * @return
     */
    public String getContent (int base1_index) {
        if(base1_index > RESTRICTED_LENGTH || base1_index == 0) {
            System.out.println("Index out of clipboard range");
            return "";
        }

        return clipboard.get(base1_index - 1);
    }

    /**
     * Returns the clipboard object to the user call
     * @return global variable clipboard stack object
     */
    public ArrayList<String> getClipboard () {
        return clipboard;
    }

    /**
     * Prints the clipboard
     */
    public void printClipboard() {
        System.out.println(clipboard.toString());
    }

    /**
     * This method loads the copied entry selected onto the paste content
     * such that the user can paste anywhere using ctrl + v
     * @param pasteEntry String to paste
     */
    public void setToPaste(String pasteEntry){
        clipboard.remove(pasteEntry);
        clipboard.add(0, pasteEntry);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(pasteEntry), null);
    }

    /**
     * This method is used to determine the size of the clipboard
     * @return
     */
    public int size() {
        return clipboard.size();
    }
}


//
//import java.awt.*;
//        import java.awt.datatransfer.StringSelection;
//        import java.util.Stack;
//
///**
// * This class will use a Stack to hold
// * recent copied contents. It will have
// * a size of 100 elements and allow the
// * user to access the 5 latest elements.
// */
//
//public class Clipboard {
//    //The clipboard is represented by a stack of Strings
//    private Stack<String> clipboard;
//
//    //Constants
//    private final int MAX_LENGTH = 100;
//    private final int RESTRICTED_LENGTH = 5;
//
//    /**
//     * Creates a new instance of the Clipboard object
//     * Creates an empty Stack and instantiates the
//     * global variable clipboard
//     */
//    public Clipboard () {
//        this.clipboard = new Stack<String>();
//    }
//
//    /**
//     * This method will be used to add new copied contents
//     * into the clipboard stack
//     * @param copiedEntry non-empty string to be added
//     */
//    public void addToClipboard (String copiedEntry) {
//        //Check whether the stack has enough space
//        //Check if the content to be added is not null
//        //Check if the content is not an empty string
//        boolean prereqFlag = (this.clipboard.size() <= MAX_LENGTH) && !copiedEntry.equals("") && copiedEntry != null;
//
//        if (prereqFlag && !this.clipboard.contains(copiedEntry)) {
//            this.clipboard.push(copiedEntry);
//        }
//
//        //Replace this later on with an exception, to avoid overloading system memory
//        //with too many copied elements
//        //else System.out.println("Failed to copy, reached Clipboard limit");
//    }
//
//    /**
//     * This method clears the whole clipboard upon
//     * the user's request
//     */
//    public void clearAll () {
//        this.clipboard.clear();
//    }
//
//    /**
//     * This method returns the copied content at a given base 1 index
//     * @param base1_index Ranging from 1 to 5 for this clipboard
//     * @return
//     */
//    public String getContent (int base1_index) {
//        if(base1_index > 5 || base1_index == 0) {
//            System.out.println("Index out of clipboard range");
//            return "";
//        }
//
//        Stack<String> holder = new Stack<String>();
//        String content = "";
//
//        for (int i = 0; i < base1_index; i++) {
//            holder.push(this.clipboard.pop());
//        }
//
//        //Using pop to retain remove the element from the stack
//        //and place it on the top of the stack so that the user
//        //has the latest entry stored on top - which will be
//        //defaulted to CTRL + V for ease of access
//        content = holder.pop();
//
//        for (int j = 0; j < base1_index - 1; j++) {
//            this.clipboard.push(holder.pop());
//        }
//        this.clipboard.push(content);
//        return content;
//    }
//
//    /**
//     * Returns the clipboard object to the user call
//     * @return global variable clipboard stack object
//     */
//    public Stack<String> getClipboard () {
//        return this.clipboard;
//    }
//
//    /**
//     * Prints the stack
//     */
//    public void printClipboard() {
//        System.out.println(clipboard.toString());
//    }
//
//    /**
//     * This method loads the copied entry selected onto the paste content
//     * such that the user can paste anywhere using ctrl + v
//     * @param pasteEntry String to paste
//     */
//    public void setToPaste(String pasteEntry){
//        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(pasteEntry), null);
//    }
//
//    /**
//     * This method is used to determine the size of the clipboard
//     * @return
//     */
//    public int size() {
//        return clipboard.size();
//    }
//}
