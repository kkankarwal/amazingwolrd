package sap.code.excer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerUtil {

	public static void registerTimer(long timeout) {
		Timer timer = new Timer();
		TimerTask exitApp = new TimerTask() {
			public void run() {
				System.out
						.println("Can not continue operation ...! Existing now ...");
				System.exit(0);
			}
		};

		timer.schedule(exitApp, new Date(System.currentTimeMillis() + timeout
				* 1000));

	}

}
