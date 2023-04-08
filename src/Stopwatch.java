import java.text.DecimalFormat;
import java.time.*;
import java.util.Timer;
import java.util.TimerTask;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import javax.swing.*;

/**
 * The stopwatch class handles the stopwatch that appears on screen during 
 * any game and times your play. It extends the Runnable interface, which 
 * allows it to be run in a separate Thread. That allows the stopwatch to 
 * continue running during play.

 */

public class Stopwatch implements Runnable {
	private LocalTime startTime;
	private LocalTime currTime;
	private LocalTime endTime;
	private Timer timer;
	private UpdateTimer task;
	private JLabel label;
	private boolean stopped = false;
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm:ss");

	public Stopwatch() {
		timer = new Timer();
		label = new JLabel("Current Time: 00:00", JLabel.CENTER);
	}

	public void startTimer() {
		startTime = LocalTime.now();
		task = new UpdateTimer(startTime);
		timer.scheduleAtFixedRate(task, 0, 1000);
	}

	public void stopTimer() {
		endTime = task.getTime();
		timer.cancel();
	}
	
	public void finalLabel() {
		endTime = task.getTime();
		timer.cancel();
		label.setText("Final Time: " + endTime);
	}

	public LocalTime returnTime() {
		return endTime;
	}

	public void updateTime(LocalTime time) {
		currTime = time;
		label.setText("Current Time: " + dtf.format(currTime));
	}

	public JLabel display() {
		return label;
	}

	public void run() {

		startTimer();

		while (true) {
			if (stopped) {
				break;
			} else {
				continue;
			}
		}

		stopTimer();
	}

	public void stopThread() {
		stopped = true;
	}

	class UpdateTimer extends TimerTask {
		private LocalTime startTime;
		private LocalTime watchTime;
		private int hours;
		private int minutes;
		private int seconds;
		private DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_TIME;

		public UpdateTimer(LocalTime startTime) {
			this.startTime = startTime;
		}

		public LocalTime getTime() {
			return watchTime;
		}

		public void checkValues(LocalTime currentTime) {
			if (hours < 0) {
				hours = (24 + currentTime.get(ChronoField.HOUR_OF_DAY)) - startTime.get(ChronoField.HOUR_OF_DAY);
			}
			if (minutes < 0) {
				minutes = (60 + currentTime.get(ChronoField.MINUTE_OF_HOUR))
						- startTime.get(ChronoField.MINUTE_OF_HOUR);
				hours -= 1;
			}
			if (seconds < 0) {
				seconds = (60 + currentTime.get(ChronoField.SECOND_OF_MINUTE))
						- startTime.get(ChronoField.SECOND_OF_MINUTE);
				minutes -= 1;
			}
		}

		@Override
		public void run() {
			DecimalFormat decFormat = new DecimalFormat("00");

			LocalTime currentTime = LocalTime.now();

			hours = currentTime.get(ChronoField.HOUR_OF_DAY) - startTime.get(ChronoField.HOUR_OF_DAY);
			minutes = currentTime.get(ChronoField.MINUTE_OF_HOUR) - startTime.get(ChronoField.MINUTE_OF_HOUR);
			seconds = currentTime.get(ChronoField.SECOND_OF_MINUTE) - startTime.get(ChronoField.SECOND_OF_MINUTE);
			checkValues(currentTime);

			String timeString = decFormat.format(hours) + ":" + decFormat.format(minutes) + ":"
					+ decFormat.format(seconds);
			watchTime = LocalTime.parse(timeString, dtf);

			Stopwatch.this.updateTime(watchTime);
		}
	}
}
