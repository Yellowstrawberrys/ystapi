package cf.ystapi.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
* Event Manager for the this Api
*
*
* */
public class Manager {
    static List<DateEvent> listeners = new ArrayList<DateEvent>();
    private boolean isthread = false;
    private static final Logger l = Logger.getLogger(Manager.class.getName());

    /*
    * Add Event Listener
    * */
    public void addListener(DateEvent toAdd){
        listeners.add(toAdd);
        if(!isthread) {
            isthread = true;
            Thread t = new th();
            t.setPriority(1);
            t.start();
        }
    }

    static void restart(long id){
        l.log(Level.WARNING, "Is Machine Overloaded? Date Event Thread has been crashed. Restarting Date Event Thread");
        //Give you set of Threads
        Set<Thread> setOfThread = Thread.getAllStackTraces().keySet();

        //Iterate over set to find yours
        for(Thread thread : setOfThread){
            if(thread.getId()==id){
                thread.interrupt();
            }
        }
        Thread t = new th();
        t.setPriority(1);
        t.start();
    }



}
class th extends Thread{
    int y = 0;
    int w = 0;
    int d = 0;
    int h = 0;
    int m = 0;
    int s = 0;
    public void run() {
        try {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Manager.restart(this.getId());
                }
                //sec
                if (s == 60) {
                    for (DateEvent de : Manager.listeners)
                        de.OnMinuteChange();
                    s = 0;
                    m++;
                }
                //min
                if (m == 60) {
                    for (DateEvent de : Manager.listeners)
                        de.OnHourChange();
                    m = 0;
                    h++;
                }
                //hour
                if (h == 24) {
                    for (DateEvent de : Manager.listeners)
                        de.OnDateChange();
                    h = 0;
                    d++;
                }
                //week
                if (d == 7) {
                    for (DateEvent de : Manager.listeners)
                        de.OnWeekChange();
                    d = 0;
                    w++;
                }
                //day
                if (w == (365/7)) {
                    for (DateEvent de : Manager.listeners)
                        de.OnYearChange();
                    w = 0;
                    y++;
                }
                s++;
                for (DateEvent de : Manager.listeners)
                    de.OnSecondChange();
            }
        }catch (Exception e){
            Manager.restart(this.getId());
        }
    }
}
