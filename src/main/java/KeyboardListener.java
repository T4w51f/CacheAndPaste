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
    private boolean DEBUG = true;

    //Max length of keys to be pressed to use the Clipboard
    private final int SHORTCUT_KEY_LENGTH = 3;

    //Define the shortcut key combinations
    private final ArrayList<String> paste1 = new ArrayList<>(Arrays.asList("Ctrl", "V", "NumPad-1"));
    private final ArrayList<String> paste2 = new ArrayList<>(Arrays.asList("Ctrl", "V", "NumPad-2"));
    private final ArrayList<String> paste3 = new ArrayList<>(Arrays.asList("Ctrl", "V", "NumPad-3"));
    private final ArrayList<String> paste4 = new ArrayList<>(Arrays.asList("Ctrl", "V", "NumPad-4"));
    private final ArrayList<String> paste5 = new ArrayList<>(Arrays.asList("Ctrl", "V", "NumPad-5"));

//    private final String paste1 = "Ctrl , V, NumPad-1";
//    private final String paste2 = "Ctrl , V, NumPad-2";
//    private final String paste3 = "Ctrl , V, NumPad-3";
//    private final String paste4 = "Ctrl , V, NumPad-4";
//    private final String paste5 = "Ctrl , V, NumPad-5";

    private final String copy = "Ctrl, C";

    //An ArrayList to contain all the paste shortcuts to cut down boolean check
    private ArrayList<String> keyboardPasteShortcut;

    //3 Key Sequence in order of key press
    private ArrayList<String> keySequence;

    /**
     * Constructor to create listener object and instantiate variables
     */
    public KeyboardListener() {
        keyboardPasteShortcut = new ArrayList<>();
        keySequence = new ArrayList<String>();

        initKeyShortcuts();
    }

    /**
     * Creates an array of shortcut strings
     */
    private void initKeyShortcuts() {
        keyboardPasteShortcut.add(paste1.toString());
        keyboardPasteShortcut.add(paste2.toString());
        keyboardPasteShortcut.add(paste3.toString());
        keyboardPasteShortcut.add(paste4.toString());
        keyboardPasteShortcut.add(paste5.toString());
    }

    /**
     * Checks if key sequence entered is a shortcut key sequence
     * @param keySequence a sequence of keys of upto SHORTCUT_KEY_LENGTH keys
     * @return
     */
    private boolean isShortcut(ArrayList<String> keySequence) {
        return keyboardPasteShortcut.contains(keySequence.toString());
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

    //Unused
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
        addCopiedContentToClipboard();
        pasteElement();
    }

    private void pasteElement() {
        if (isShortcut(keySequence)) {
            System.out.println("Hit Paste Shortcut: " + keySequence);
            System.out.println("Pasting item: " + CacheAndPaste.clipboard.getContent(getPasteIndex(keySequence.get(SHORTCUT_KEY_LENGTH - 1))));

            //To prevent conflict between subsequent pastes
            keySequence.clear();
        }
    }

    private void addCopiedContentToClipboard() {
        if(keySequence.toString().contains(copy)) {
            CacheAndPaste.clipboard.addToClipboard(getSystemClipboardContent());
            System.out.println("Copied element from system: " + getSystemClipboardContent());
            CacheAndPaste.clipboard.printClipboard();

            //To prevent conflict between subsequent copies
            keySequence.clear();
        }
    }

    private int getPasteIndex(String key) {
        int idx;

        switch(key) {
            case "NumPad-1" : idx = 1;
            case "NumPad-2" : idx = 2;
            case "NumPad-3" : idx = 3;
            case "NumPad-4" : idx = 4;
            case "NumPad-5" : idx = 5;
            default: idx = 1;
        }

        return idx;
    }

    //Unused
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(DEBUG) System.out.println("Key Released: " + KeyEvent.getKeyText(keyEvent.getKeyCode()));
    }
}
