import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

    //TODO Clear the keySequence if it gets longer than 10 elements for optimization
    //Define the shortcut key combinations
    private final ArrayList<String> paste1 = new ArrayList<>(Arrays.asList("Ctrl", "V", "NumPad-1"));
    private final ArrayList<String> paste2 = new ArrayList<>(Arrays.asList("Ctrl", "V", "NumPad-2"));
    private final ArrayList<String> paste3 = new ArrayList<>(Arrays.asList("Ctrl", "V", "NumPad-3"));
    private final ArrayList<String> paste4 = new ArrayList<>(Arrays.asList("Ctrl", "V", "NumPad-4"));
    private final ArrayList<String> paste5 = new ArrayList<>(Arrays.asList("Ctrl", "V", "NumPad-5"));

    //An ArrayList to contain all the shortcuts to cut down boolean check
    private ArrayList<String> keyboardShortcut;

    public KeyboardListener() {
        keyboardShortcut = new ArrayList<>();
        initKeyShortcuts();
    }

    private void initKeyShortcuts() {
        keyboardShortcut.add(paste1.toString());
        keyboardShortcut.add(paste2.toString());
        keyboardShortcut.add(paste3.toString());
        keyboardShortcut.add(paste4.toString());
        keyboardShortcut.add(paste5.toString());
    }

    private boolean isShortcut(ArrayList<String> keySequence) {
        return keyboardShortcut.contains(keySequence.toString());
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(DEBUG) System.out.println("Key Pressed: " + KeyEvent.getKeyText(keyEvent.getKeyCode()));

        ArrayList<String> keySequence = CacheAndPaste.keySequence;

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
