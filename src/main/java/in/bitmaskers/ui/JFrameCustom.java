package in.bitmaskers.ui;

import javax.swing.*;
import java.awt.*;

public class JFrameCustom extends JFrame {
    String title;

    public JFrameCustom(String title) {
        super(title);
        add(new JLabel("JFrame with maximize button disabled"), BorderLayout.CENTER);
        setSize(350, 275);
        setLocationRelativeTo(null);
        setResizable(false); // maximize button disable
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String args[]) {
        new JFrameCustom("JFrame Demo");
    }
}