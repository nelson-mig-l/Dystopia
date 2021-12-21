package dystopia;

import dystopia.map.Map;
import dystopia.ui.TitleFrame;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Rohans
 */
public class Game implements Runnable {

    public static Car[] cars = new Car[Map.CITY_SIZE * Map.CITY_SIZE / 10];

    public static void makeCars() {
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car();
        }
    }

    @Override
    public void run() {
        makeCars();
        Cops.bounty = 0;
        Cops.numCops = 10;
        int i = 0;
        Cops.createCops();
        while (TitleFrame.playing.get()) {
            if (i % 50 == 0) {
                for (int j = 0; j < 10; j++)
                    Cops.addCop();
            }
            Cops.bounty += 100;
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (Car c : cars) {
                c.move();
            }
            Cops.moveAllCops();
            i++;
        }
    }
}
