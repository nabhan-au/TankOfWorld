package Main.GameState;

import Main.Game;
import Presentation.Layers.GameLayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends State {
    private Game stateOwner;
    private static int TILE_SIZE = Game.BOARD_SIZE/12;
    private MenuLayer menuLayer = new MenuLayer();

    public Menu(Game stateOwner) {
        this.stateOwner = stateOwner;
        stateOwner.removeAllLayer();
        stateOwner.addLayer(menuLayer);
        stateOwner.addKeyListener(new Controller());
    }

    @Override
    public void repaint() {
        menuLayer.repaint();
    }

    @Override
    public GameLayer getEffectGameLayer() {
        return null;
    }

    class MenuLayer extends JPanel {
        Font font;
        JLabel title, imageLabel;
        JButton single, multi, exit;
        BufferedImage titleImage;


        public MenuLayer() {
            super();
            setBounds(0, 0, Game.BOARD_SIZE, Game.BOARD_SIZE);

            try{
                font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/ka1.ttf"));
            } catch (IOException | FontFormatException e) {
                System.out.println("font not found");
            }
            this.setBackground(new Color(70, 120, 80));

            this.setLayout(null);
            title = new JLabel("TANK OF WORLD");
            title.setBounds(getXforCenterText(0, Game.BOARD_SIZE, 550), 2*TILE_SIZE, 550, 80);
            title.setFont(font.deriveFont(Font.BOLD, 50F));
            title.setForeground(Color.BLACK);

            single = new JButton("SINGLEPLAYER");
            single.setBounds(getXforCenterText(0, Game.BOARD_SIZE, 340), 7*TILE_SIZE, 340, 50);
            single.setBackground(Color.BLACK);
            single.setFont(font.deriveFont(Font.BOLD,30F));
            single.setForeground(Color.BLACK);
            single.setFocusPainted(false);
            single.setOpaque(false);
            single.setBorderPainted(false);
            single.setFocusable(false);

            multi = new JButton("MULTIPLAYER");
            multi.setBounds(getXforCenterText(0, Game.BOARD_SIZE, 305), 8*TILE_SIZE, 305, 50);
            multi.setBackground(Color.BLACK);
            multi.setFont(font.deriveFont(Font.BOLD,30F));
            multi.setForeground(Color.BLACK);
            multi.setFocusPainted(false);
            multi.setOpaque(false);
            multi.setBorderPainted(false);
            multi.addActionListener(e ->startGame());
            multi.setFocusable(false);

            exit = new JButton("EXIT");
            exit.setBounds(getXforCenterText(0, Game.BOARD_SIZE, 125), 9*TILE_SIZE, 125, 50);
            exit.setBackground(Color.BLACK);
            exit.setFont(font.deriveFont(Font.BOLD,30F));
            exit.setForeground(Color.BLACK);
            exit.setFocusPainted(false);
            exit.setOpaque(false);
            exit.setBorderPainted(false);
            exit.setFocusable(false);
            exit.addActionListener(e -> exitGame());

            add(title);
            add(single);
            add(multi);
            add(exit);
        }

        private void startGame() {
            stateOwner.setState(new TwoPlayerMode(stateOwner));
            stateOwner.remove(single);
            stateOwner.remove(multi);
        }

        private void exitGame() {
            System.exit(0);
        }

        public int getXforCenterText(int x, int width, int textWidth) {
            return x + (width - textWidth) / 2;
        }
    }

    class Controller extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                stateOwner.setState(new TwoPlayerMode(stateOwner));
            }
        }
    }
}