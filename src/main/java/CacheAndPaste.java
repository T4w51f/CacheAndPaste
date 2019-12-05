import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyListener;
import java.io.IOException;

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
    //TODO save the copied content from the listener class into clipboard in the main class
    private static JFrame frame = new JFrame("Clipboard");

    private static JLabel label1 = new JLabel();
    private static JLabel label2 = new JLabel();
    private static JLabel label3 = new JLabel();
    private static JLabel label4 = new JLabel();
    private static JLabel label5 = new JLabel();

    private static KeyListener listener;
    public static Clipboard clipboard;

    //This constructor class listens for keyboard shortcuts
    private CacheAndPaste() {
        this.listener = new KeyboardListener();
        addKeyListener(listener);
        setFocusable(true);
    }

    public static void main (String args[]) {

        new SwingBackgroupWorker().execute();

        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run() {

                //Make an instance of the class to print keyboard inputs
                //and a clipboard to hold the data
                CacheAndPaste keyboardInput = new CacheAndPaste();
                clipboard = new Clipboard();

                //Clipboard UI
                frame.getContentPane().add(keyboardInput);
                frame.setSize(200, 800);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.add(label1);
                frame.add(label2);
                frame.add(label3);
                frame.add(label4);
                frame.add(label5);

                frame.setLayout(new GridLayout(7,1));
            }
        });
    }

    public static class SwingBackgroupWorker extends SwingWorker<Object, Object> {
        @Override
        protected Object doInBackground() throws Exception {
            while (true) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        CacheAndPaste.clipboard.addToClipboard(getSystemClipboardContent());
                        //System.out.println("Copied element from system: " + getSystemClipboardContent());
                        CacheAndPaste.clipboard.printClipboard();

                        if(CacheAndPaste.clipboard.size() > 0) label1.setText("1:" + CacheAndPaste.clipboard.getContent(1));
                        if(CacheAndPaste.clipboard.size() > 1) label2.setText("2:" + CacheAndPaste.clipboard.getContent(2));
                        if(CacheAndPaste.clipboard.size() > 2) label3.setText("3:" + CacheAndPaste.clipboard.getContent(3));
                        if(CacheAndPaste.clipboard.size() > 3) label4.setText("4:" + CacheAndPaste.clipboard.getContent(4));
                        if(CacheAndPaste.clipboard.size() > 4) label5.setText("5:" + CacheAndPaste.clipboard.getContent(5));

                        //TODO can enter more than 5
                        //TODO jumps back and forth order
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
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
