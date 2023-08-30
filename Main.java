package com.ClassicArkanoidGame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame=new JFrame();
        GamePlay gamePlay = new GamePlay();
        frame.setBounds(10,10,600,700);
        frame.setTitle("Classic Arkanoid Game");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePlay);  //For that i extends JPanel in GamePlay class.
        frame.setVisible(true);
    }


}
