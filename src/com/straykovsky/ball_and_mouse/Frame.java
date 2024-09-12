package com.straykovsky.ball_and_mouse;

import javax.swing.*;

public class Frame extends JFrame {

    public Frame() {
        setTitle("ball_and_mouse");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        add(new Panel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

