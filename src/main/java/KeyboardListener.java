import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class listens for keyboard input events.
 * It will allow keyboard shortcuts to be interfaced
 * with the clipboard program.
 */
public class KeyboardListener implements KeyListener {
    //Debugger constant to print debug statements
    private boolean DEBUG = false;

    //Max length of keys to be pressed to use the Clipboard
    private final int SHORTCUT_KEY_LENGTH = 3;

    //Define the shortcut key combinations
    private final ArrayList<String> paste1 = new ArrayList<>(Arrays.asList("Ctrl", "V", "NumPad-1"));
    private final ArrayList<String> paste2 = new ArrayList<>(Arrays.asList("Ctrl", "V", "NumPad-2"));
    private final ArrayList<String> paste3 = new ArrayList<>(Arrays.asList("Ctrl", "V", "NumPad-3"));
    private final ArrayList<String> paste4 = new ArrayList<>(Arrays.asList("Ctrl", "V", "NumPad-4"));
    private final ArrayList<String> paste5 = new ArrayList<>(Arrays.asList("Ctrl", "V", "NumPad-5"));

    //An ArrayList to contain all the shortcuts to cut down boolean check
    private ArrayList<String> keyboardShortcut;

    //3 Key Sequence in order of key press
    private ArrayList<String> keySequence;

    /**
     * Constructor to create listener object and instantiate variables
     */
    public KeyboardListener() {
        keyboardShortcut = new ArrayList<>();
        keySequence = new ArrayList<String>();

        initKeyShortcuts();
    }

    /**
     * Creates an array of shortcut strings
     */
    private void initKeyShortcuts() {
        keyboardShortcut.add(paste1.toString());
        keyboardShortcut.add(paste2.toString());
        keyboardShortcut.add(paste3.toString());
        keyboardShortcut.add(paste4.toString());
        keyboardShortcut.add(paste5.toString());
    }

    /**
     * Checks if key sequence entered is a shortcut key sequence
     * @param keySequence a sequence of keys of upto SHORTCUT_KEY_LENGTH keys
     * @return
     */
    private boolean isShortcut(ArrayList<String> keySequence) {
        return keyboardShortcut.contains(keySequence.toString());
    }

    /**
     * Fetches the latest copied content from the system clipboard
     * @return A string of the copied content from the system, and
     * an empty string if nothing is copied
     */
    private String getSystemClipboardContent() {
        String systemContent = "";
        try {
            systemContent = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return systemContent;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(DEBUG) System.out.println("Key Pressed: " + KeyEvent.getKeyText(keyEvent.getKeyCode()));

        boolean prereq = keySequence.isEmpty() || (!keySequence.isEmpty() && !keySequence.get(keySequence.size() - 1).equals(KeyEvent.getKeyText(keyEvent.getKeyCode())));

        if(prereq) {
            //In order to hold only SHORTCUT_KEY_LENGTH elements at a time
            if(keySequence.size() == SHORTCUT_KEY_LENGTH) {
                keySequence.remove(0);
            }
            keySequence.add(KeyEvent.getKeyText(keyEvent.getKeyCode()));
        }

        if(DEBUG) System.out.println("DEBUG: KeySequence: " + keySequence);
        if(isShortcut(keySequence)) System.out.println("Hit Shortcut: " + keySequence);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(DEBUG) System.out.println("Key Released: " + KeyEvent.getKeyText(keyEvent.getKeyCode()));
    }
}
