import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class listens for keyboard input events.
 * It will allow keyboard shortcuts to be interfaced
 * with the clipboard program.
 */
public class KeyboardListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        System.out.println("Key Pressed: " + KeyEvent.getKeyText(keyEvent.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        System.out.println("Key Released: " + KeyEvent.getKeyText(keyEvent.getKeyCode()));
    }

    public interface OnInputListener {
        void sendInput(String key);
    }
}
