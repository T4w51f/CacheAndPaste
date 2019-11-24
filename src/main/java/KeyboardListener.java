import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class listens for keyboard input events.
 * It will allow keyboard shortcuts to be interfaced
 * with the clipboard program.
 */
public class KeyboardListener implements KeyListener {
    //TODO Clear the keySequence if it gets longer than 10 elements for optimization
    //Define the shortcut key combinations
    private final ArrayList<String> copy1 = new ArrayList<>(Arrays.asList("Ctrl", "V", "NumPad-1"));

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        System.out.println("Key Pressed: " + KeyEvent.getKeyText(keyEvent.getKeyCode()));

        ArrayList<String> keySequence = CacheAndPaste.keySequence;

        boolean prereq = keySequence.isEmpty() || (!keySequence.isEmpty() && !keySequence.get(keySequence.size() - 1).equals(KeyEvent.getKeyText(keyEvent.getKeyCode())));
        if (prereq) keySequence.add(KeyEvent.getKeyText(keyEvent.getKeyCode()));
        System.out.println("DEBUG: KeySequence: " + keySequence);
        if (keySequence.toString().contains(copy1.toString())) System.out.println("Hit Shortcut to copy 1");
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        System.out.println("Key Released: " + KeyEvent.getKeyText(keyEvent.getKeyCode()));
    }
}
