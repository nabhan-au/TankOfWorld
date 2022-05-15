package Main.GameState;

import Main.Game;
import Main.MapData;
import Presentation.Layers.GameLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Menu extends State {
    private Game stateOwner;
    private static int TILE_SIZE = 100;
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

        public MenuLayer() {
            setBounds(0, 0, Game.BOARD_SIZE, Game.BOARD_SIZE);
        }

        @Override
        public void paint(Graphics g) {
            g.setFont(g.getFont().deriveFont(Font.BOLD, 96F));
            String text = "Tank Of World";
            int x = getXforCenterText(g, text, g.getFont(), 0, Game.BOARD_SIZE);
            int y = TILE_SIZE;

            g.setColor(Color.black);
            g.drawString(text, x, y);

            g.setFont(g.getFont().deriveFont(Font.BOLD, 48F));
            text = "SINGLE PLAYER";
            x = getXforCenterText(g, text, g.getFont(), 0, Game.BOARD_SIZE);
            y = TILE_SIZE * 3;
            g.drawString(text, x, y);
        }

        public int getXforCenterText(Graphics g, String text, Font font, int x, int width) {
            FontMetrics metrics = g.getFontMetrics(font);
            int size = metrics.stringWidth(text);
            return x + (width - size) / 2;
        }
    }

    class Controller extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
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

                List<String> options = new ArrayList<String>();
                for (MapData mapData : mapList) {
                    options.add(mapData.getMapName() + " (" + mapData.getMapSize() + "x"
                            + mapData.getMapSize() + ")");
                }

                int pickOptionNum = pickOptionInputDialogBox("Enter map number you want to play", options);
                stateOwner.setMap(mapList.get(pickOptionNum - 1));
                stateOwner.start();
                stateOwner.setState(new TwoPlayerMode(stateOwner));

            }
        }
    }

    public String showInputDialogBox(String message) {
        return JOptionPane.showInputDialog(message);
    }

    public int pickOptionInputDialogBox(String message, List<String> options) {
        message += "\n";
        int count = 1;
        for (String option : options) {
            message += count + ")" + option + "\n";
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