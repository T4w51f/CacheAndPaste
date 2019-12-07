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

    //Copied entry selected
    int copiedEntryIndex;

    //Constants
    private final int MAX_LENGTH = 100;
    private final int RESTRICTED_LENGTH = 5;

    /**
     * Constructor to create listener object and instantiate variables
     */
    public KeyboardListener() {
    }

    //Unused
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        String keyPressed = KeyEvent.getKeyText(keyEvent.getKeyCode());
        int keyCode = keyEvent.getKeyCode();
        String clipboardContent = "";

        if(DEBUG) System.out.println("Key Pressed: " + keyPressed + ", keyCode: " + keyCode);

        if(loadKeyPressed(keyCode)) {
            clipboardContent = CacheAndPaste.clipboard.getContent(copiedEntryIndex);

            if(DEBUG) System.out.println("DEBUGGER: Extracted data ready to be pasted: " + clipboardContent);
            CacheAndPaste.clipboard.setToPaste(clipboardContent);
        }

        if(keyPressed.equals("X")) {
            CacheAndPaste.clipboard.clearAll();
        }
    }

    private boolean loadKeyPressed(int keyCode) {
        copiedEntryIndex = getPasteIndex(keyCode);

        //check if range is correct
        if(copiedEntryIndex > 0 && copiedEntryIndex <= CacheAndPaste.clipboard.size()){
            if(DEBUG) System.out.println("DEBUGGER: Valid key entered: " + String.valueOf(copiedEntryIndex));
            return true;
        }

        if(DEBUG) System.out.println("DEBUGGER: Key entered out of range: " + String.valueOf(copiedEntryIndex));
        return false;
    }


    int getPasteIndex(int keyCode) {
        int idx;

        switch(keyCode) {
            case 49 : {idx = 1; break;}
            case 50: {idx = 2; break;}
            case 51: {idx = 3; break;}
            case 52: {idx = 4; break;}
            case 53: {idx = 5; break;}
            case 97 : {idx = 1; break;}
            case 98 : {idx = 2; break;}
            case 99 : {idx = 3; break;}
            case 100 : {idx = 4; break;}
            case 101 : {idx = 5; break;}
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


//import java.awt.*;
//import java.awt.datatransfer.DataFlavor;
//import java.awt.datatransfer.UnsupportedFlavorException;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//
///**
// * This class listens for keyboard input events.
// * It will allow keyboard shortcuts to be interfaced
// * with the clipboard program.
// */
//public class KeyboardListener implements KeyListener {
//    //Debugger constant to print debug statements
//    private boolean DEBUG = true;
//
//    //Max length of keys to be pressed to use the Clipboard
//    private final int SHORTCUT_KEY_LENGTH = 3;
//
//    //Define the shortcut key combinations
//    private final ArrayList<String> paste1 = new ArrayList<>(Arrays.asList("Ctrl", "V", "NumPad-1"));
//    private final ArrayList<String> paste2 = new ArrayList<>(Arrays.asList("Ctrl", "V", "NumPad-2"));
//    private final ArrayList<String> paste3 = new ArrayList<>(Arrays.asList("Ctrl", "V", "NumPad-3"));
//    private final ArrayList<String> paste4 = new ArrayList<>(Arrays.asList("Ctrl", "V", "NumPad-4"));
//    private final ArrayList<String> paste5 = new ArrayList<>(Arrays.asList("Ctrl", "V", "NumPad-5"));
//
//    private final String copy = "Ctrl, C";
//
//    //An ArrayList to contain all the paste shortcuts to cut down boolean check
//    private ArrayList<String> keyboardPasteShortcut;
//
//    //3 Key Sequence in order of key press
//    public ArrayList<String> keySequence;
//
//    /**
//     * Constructor to create listener object and instantiate variables
//     */
//    public KeyboardListener() {
//        keyboardPasteShortcut = new ArrayList<>();
//        keySequence = new ArrayList<String>();
//
//        initKeyShortcuts();
//    }
//
//    /**
//     * Creates an array of shortcut strings
//     */
//    private void initKeyShortcuts() {
//        keyboardPasteShortcut.add(paste1.toString());
//        keyboardPasteShortcut.add(paste2.toString());
//        keyboardPasteShortcut.add(paste3.toString());
//        keyboardPasteShortcut.add(paste4.toString());
//        keyboardPasteShortcut.add(paste5.toString());
//    }
//
//    /**
//     * Checks if key sequence entered is a shortcut key sequence
//     * @param keySequence a sequence of keys of upto SHORTCUT_KEY_LENGTH keys
//     * @return
//     */
//    private boolean isShortcut(ArrayList<String> keySequence) {
//        return keyboardPasteShortcut.contains(keySequence.toString());
//    }
//
//    //Unused
//    @Override
//    public void keyTyped(KeyEvent keyEvent) {
//    }
//
//    @Override
//    public void keyPressed(KeyEvent keyEvent) {
//        if(DEBUG) System.out.println("Key Pressed: " + KeyEvent.getKeyText(keyEvent.getKeyCode()));
//
//        boolean prereq = keySequence.isEmpty() || (!keySequence.isEmpty() && !keySequence.get(keySequence.size() - 1).equals(KeyEvent.getKeyText(keyEvent.getKeyCode())));
//
//        if(prereq) {
//            //In order to hold only SHORTCUT_KEY_LENGTH elements at a time
//            if(keySequence.size() == SHORTCUT_KEY_LENGTH) {
//                keySequence.remove(0);
//            }
//            keySequence.add(KeyEvent.getKeyText(keyEvent.getKeyCode()));
//        }
//
//        if(DEBUG) System.out.println("DEBUG: KeySequence: " + keySequence);
//        pasteElement();
//    }
//
//    private void pasteElement() {
//        if (isShortcut(keySequence)) {
//            System.out.println("Hit Paste Shortcut: " + keySequence);
//            System.out.println("Pasting item: " + CacheAndPaste.clipboard.getContent(getPasteIndex(keySequence.get(SHORTCUT_KEY_LENGTH - 1))));
//
//            //To prevent conflict between subsequent pastes
//            keySequence.clear();
//        }
//    }
//
//    private int getPasteIndex(String key) {
//        int idx;
//
//        switch(key) {
//            case "NumPad-1" : idx = 1;
//            case "NumPad-2" : idx = 2;
//            case "NumPad-3" : idx = 3;
//            case "NumPad-4" : idx = 4;
//            case "NumPad-5" : idx = 5;
//            default: idx = 1;
//        }
//
//        return idx;
//    }
//
//    //Unused
//    @Override
//    public void keyReleased(KeyEvent keyEvent) {
//        if(DEBUG) System.out.println("Key Released: " + KeyEvent.getKeyText(keyEvent.getKeyCode()));
//    }
//}
