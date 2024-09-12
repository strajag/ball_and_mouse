package com.straykovsky.ball_and_mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Panel extends JPanel implements KeyListener, ActionListener {

    int screenResolution = 800;

    List<Integer> keysPressed = new ArrayList<>();
    Timer timer = new Timer(16, this);

    Point mousePoint;
    Ball ball;
    public Panel() {
        setPreferredSize(new Dimension(screenResolution, screenResolution));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
        set();
    }

    void set() {
        ball = new Ball();
        ball.size = 50;
        ball.x = screenResolution / 2;
        ball.y = screenResolution / 2;
        ball.speed = 50;

        timer.start();
    }

    void draw(Graphics graphics) {
        //draw ball
        graphics.setColor(Color.RED);
        graphics.fillOval((int) Math.round(ball.x - ball.size / 2), (int) Math.round(ball.y - ball.size / 2), (int) Math.round(ball.size), (int) Math.round(ball.size));
    }

    void getMouseXY () {
        mousePoint = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(mousePoint, this);
    }

    void moveBall() {
        double differenceX = ball.x - mousePoint.x;
        double differenceY = ball.y - mousePoint.y;
        double differenceSUM = Math.abs(differenceX) + Math.abs(differenceY);
        ball.vectorX = -differenceX / differenceSUM;
        ball.vectorY = -differenceY / differenceSUM;

        double distance = Math.sqrt(differenceX * differenceX + differenceY * differenceY);
        double speed = distance * ball.speed / screenResolution;

        ball.x += ball.vectorX * speed;
        ball.y += ball.vectorY * speed;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        getMouseXY();
        moveBall();
        repaint();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (!keysPressed.contains(KeyEvent.VK_UP)) {
                    keysPressed.add(KeyEvent.VK_UP);
                    ball.vectorY -= ball.speed;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (!keysPressed.contains(KeyEvent.VK_DOWN)) {
                    keysPressed.add(KeyEvent.VK_DOWN);
                    ball.vectorY += ball.speed;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (!keysPressed.contains(KeyEvent.VK_LEFT)) {
                    keysPressed.add(KeyEvent.VK_LEFT);
                    ball.vectorX -= ball.speed;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (!keysPressed.contains(KeyEvent.VK_RIGHT)) {
                    keysPressed.add(KeyEvent.VK_RIGHT);
                    ball.vectorX += ball.speed;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (keysPressed.contains(KeyEvent.VK_UP)) {
                    keysPressed.remove(Integer.valueOf(KeyEvent.VK_UP));
                    ball.vectorY = 0;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (keysPressed.contains(KeyEvent.VK_DOWN)) {
                    keysPressed.remove(Integer.valueOf(KeyEvent.VK_DOWN));
                    ball.vectorY = 0;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (keysPressed.contains(KeyEvent.VK_LEFT)) {
                    keysPressed.remove(Integer.valueOf(KeyEvent.VK_LEFT));
                    ball.vectorX = 0;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (keysPressed.contains(KeyEvent.VK_RIGHT)) {
                    keysPressed.remove(Integer.valueOf(KeyEvent.VK_RIGHT));
                    ball.vectorX = 0;
                }
                break;
        }
    }
}
