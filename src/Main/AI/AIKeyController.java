package Main.AI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import Entity.EntityList.Tank;
import Entity.Events.DomainEvent;
import Main.Map;

public abstract class AIKeyController implements PropertyChangeListener {
    private Tank tank;
    private Map map;
    private Thread aiThread;
    private boolean isGameOver = false;

    public AIKeyController(Tank tank, Map map) {
        this.tank = tank;
        this.map = map;
        this.tank.addPropertyChangeListener(this);

        this.aiThread = new Thread() {
            @Override
            public void run() {
                while (!isGameOver) {
                    doAction();
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                tank.stop();
            }
        };
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName() == DomainEvent.GameOver.toString()) {
            this.isGameOver = true;
        }

    }

    abstract public void doAction();

    public Tank getTank() {
        return this.tank;
    }

    public Map getMap() {
        return this.map;
    }

    public void startAI() {
        this.aiThread.start();
    }

}
