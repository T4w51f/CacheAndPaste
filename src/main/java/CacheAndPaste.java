import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.sql.SQLOutput;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

/**
 * Date: 24/11/2019
 * Author: Ahnaf Ahsan, Tawsif Hasan
 *
 * Java Main Class:
 * The methods in this class will be used to call
 * those from the Clipboard class that stores copied
 * content by the user, and process the data into a
 * UI that allows the user to paste desired content
 * with simple keyboard shortcuts
 */

public class CacheAndPaste extends JPanel {
    private static KeyListener listener;

    //This constructor class listens for keyboard shortcuts
    public CacheAndPaste() {
        this.listener = new KeyboardListener();
        addKeyListener(listener);
        setFocusable(true);
    }

    public static void main (String args[]) {
        JFrame frame = new JFrame("Clipboard");

        //Make an instance of the class to print keyboard inputs
        //and a clipboard to hold the data
        CacheAndPaste keyboardInput = new CacheAndPaste();
        Clipboard clipboard = new Clipboard();

        //Clipboard UI
        frame.add(keyboardInput);
        frame.setSize(50, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void sendInput(String key) {
        System.out.println("Test: " + key);
    }
}
