/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import telegramapi.Functions;

/**
 *
 * @author Lorenzo
 */
public class UpdatesReceiver extends Thread {

    private ArrayList<Listener> listeners;
    private telegramapi.responses.UpdatesResponse oldUpdates, newUpdates;

    public UpdatesReceiver() {
        listeners = new ArrayList<>();
        oldUpdates = Functions.GetUpdatesResponse();
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void run() {
        while (true) {
            newUpdates = Functions.GetUpdatesResponse();
            new Thread(() -> {
                if (newUpdates.GetLength() != oldUpdates.GetLength()){
                    for (Listener listener : listeners) {
                        listener.actionPerformed(newUpdates.GetLastResult().message);
                    }
                }
            }).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(BotPubblicita.class.getName()).log(Level.SEVERE, null, ex);
            }
            oldUpdates = newUpdates;
        }
    }

}
