package Main.GameState;

import Main.Game;
import Main.MapData;
import Main.TankPlayerData;
import Presentation.ImageSet.TankImageSet.TankImage;
import Presentation.Layers.GameLayer;

import javax.swing.*;

import Entity.EntityList.Tank.TankType;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.IOException;

public class Menu extends State {
    private Game stateOwner;
    private static int TILE_SIZE = Game.BOARD_SIZE / 12;
    private MenuLayer menuLayer = new MenuLayer();
    private List<MapData> mapDatas = new ArrayList<MapData>();

    public Menu(Game stateOwner) {
        this.stateOwner = stateOwner;
        stateOwner.removeAllLayer();
        stateOwner.addLayer(menuLayer);
        loadMapData();
    }

    @Override
    public void repaint() {
        menuLayer.repaint();
    }

    @Override
    public GameLayer getEffectGameLayer() {
        return null;
    }

    private void loadMapData() {
        File files = new File("maps/");
        String[] fileLists = files.list();
        List<MapData> mapList = new ArrayList<MapData>();
        for (String fileName : fileLists) {
            try {
                mapList.add(new MapData("maps/" + fileName));
            } catch (Exception exception) {
                System.out.println(exception);
                continue;
            } catch (Error error) {
                System.out.println(error);
                continue;
            }
        }
        this.mapDatas = mapList;
    }

    private List<String> generateMapOptions() {
        List<String> options = new ArrayList<String>();
        for (MapData mapData : this.mapDatas) {
            options.add(mapData.getMapName() + " (" + mapData.getMapSize() + "x"
                    + mapData.getMapSize() + ")");
        }
        return options;

    }

    class MenuLayer extends JPanel {
        Font font;
        JLabel title;
        JButton single, multi, exit;

        public MenuLayer() {
            super();
            setBounds(0, 0, Game.BOARD_SIZE, Game.BOARD_SIZE);

            try {
                font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/ka1.ttf"));
            } catch (IOException | FontFormatException e) {
                System.out.println("font not found");
            }
            this.setBackground(new Color(70, 120, 80));

            this.setLayout(null);
            title = new JLabel("TANK OF WORLD");
            title.setBounds(getXforCenterText(0, Game.BOARD_SIZE, 550), 2 * TILE_SIZE, 550, 80);
            title.setFont(font.deriveFont(Font.BOLD, 50F));
            title.setForeground(Color.BLACK);

            single = new JButton("SINGLEPLAYER");
            single.setBounds(getXforCenterText(0, Game.BOARD_SIZE, 340), 7 * TILE_SIZE, 340, 50);
            single.setBackground(Color.BLACK);
            single.setFont(font.deriveFont(Font.BOLD, 30F));
            single.setForeground(Color.BLACK);
            single.setFocusPainted(false);
            single.setOpaque(false);
            single.setBorderPainted(false);
            single.addActionListener(e -> startGame(1));
            single.setFocusable(false);

            multi = new JButton("MULTIPLAYER");
            multi.setBounds(getXforCenterText(0, Game.BOARD_SIZE, 305), 8 * TILE_SIZE, 305, 50);
            multi.setBackground(Color.BLACK);
            multi.setFont(font.deriveFont(Font.BOLD, 30F));
            multi.setForeground(Color.BLACK);
            multi.setFocusPainted(false);
            multi.setOpaque(false);
            multi.setBorderPainted(false);
            multi.addActionListener(e -> startGame(2));
            multi.setFocusable(false);

            exit = new JButton("EXIT");
            exit.setBounds(getXforCenterText(0, Game.BOARD_SIZE, 125), 9 * TILE_SIZE, 125, 50);
            exit.setBackground(Color.BLACK);
            exit.setFont(font.deriveFont(Font.BOLD, 30F));
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

        private void startGame(int numPlay) {
            int pickOptionNum = pickOptionInputDialogBox("Enter map number you want to play", generateMapOptions());
            List<TankPlayerData> playerNames = new ArrayList<TankPlayerData>();
            for (int i = 1; i <= numPlay; i++) {
                String playerName = askForName(i);
                TankType tankType = askForTankType(i);
                TankImage tankImage = askForTankImage(i);
                TankPlayerData data = new TankPlayerData(playerName, tankType, tankImage);
                playerNames.add(data);
            }
            if (numPlay == 1) {
                Random randomType = new Random();
                playerNames.add(new TankPlayerData("AI",
                        TankType.values()[randomType.nextInt(0, TankType.values().length)],
                        TankImage.values()[randomType.nextInt(0, TankImage.values().length)]));
            }

            stateOwner.setMap(mapDatas.get(pickOptionNum - 1));
            stateOwner.start();
            stateOwner.setState(new PlayingMode(stateOwner, numPlay, playerNames));

        }

        private String askForName(Integer player) {
            return JOptionPane.showInputDialog(this, "Enter Name for Player " + player);
        }

        private TankType askForTankType(Integer player) {
            List<String> options = new ArrayList<String>();
            for (TankType tankType : TankType.values()) {
                options.add(tankType.getName());
            }
            return TankType.values()[pickOptionInputDialogBox("Pick Player " + player + " Tank Type", options) - 1];
        }

        private TankImage askForTankImage(Integer player) {
            List<String> options = new ArrayList<String>();
            for (TankImage tankType : TankImage.values()) {
                options.add(tankType.name());
            }
            return TankImage.values()[pickOptionInputDialogBox("Pick Player " + player + " Tank Image", options) - 1];
        }

        private void exitGame() {
            System.exit(0);
        }

        public int getXforCenterText(int x, int width, int textWidth) {
            return x + (width - textWidth) / 2;
        }
    }

    public String showInputDialogBox(String message) {
        return JOptionPane.showInputDialog(stateOwner, message);
    }

    public int pickOptionInputDialogBox(String message, List<String> options) {
        message += "\n";
        int count = 1;
        for (String option : options) {
            message += count + ") " + option + "\n";
            count++;
        }
        int selectOptionInt = 0;
        while (true) {
            String selectOption = showInputDialogBox(message);
            try {
                selectOptionInt = Integer.parseInt(selectOption);
            } catch (NumberFormatException e) {
                selectOptionInt = -1;
            }
            if (selectOptionInt >= 1 && selectOptionInt <= options.size()) {
                break;
            }
            JOptionPane.showMessageDialog(null, "Invalid Input, Please enter option again.");
        }
        return selectOptionInt;
    }
}