package com.github.chotkiymaster;


import javax.swing.*;
import java.awt.*;

public class Main {


    public static void main(String[] args) {
        Field field = new Field(13, 27);
        display(field);
    }

    private static void display(Field field) {

        JFrame frame = new JFrame("For Testing");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(field);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
