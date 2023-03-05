import java.text.DecimalFormat;
import java.time.*;
import java.util.Timer;
import java.util.TimerTask;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import javax.swing.*;

public class Stopwatch implements Runnable {
	private LocalTime startTime;
	private LocalTime currTime;
	private LocalTime endTime;
	private Timer timer;
	private UpdateTimer task;
	private JLabel label;
	private boolean stopped = false;
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm:ss");

	// Constructor initializes a new timer and creates a placeholder label.
	public Stopwatch() {
		timer = new Timer();
		label = new JLabel("Current Time: 00:00", JLabel.CENTER);
	}

	/*
	 * startTimer captures the current time, which is the Start Time, then passes it
	 * to the task object before scheduling the task with the timer.
	 */
	public void startTimer() {
		startTime = LocalTime.now();
		task = new UpdateTimer(startTime);
		timer.scheduleAtFixedRate(task, 0, 1000);
	}

	/*
	 * stopTimer stops the timer without stopping the thread. I planned for this to
	 * be used during the game over screens to display the final time But
	 * implementation still needs to be worked out for that.
	 */
	public void stopTimer() {
		endTime = task.getTime();
		timer.cancel();
	}
	
	public void updateLabel() {
		label.setText("Final Time: ");
	}

	// Returns the Final Time for use in game score calculations, where applicable.
	public LocalTime returnTime() {
		return endTime;
	}

	/*
	 * updateTime is called by the inner TimerTask class. It uses the time
	 * calculated by the TimerTask to update the timer label.
	 */
	public void updateTime(LocalTime time) {
		currTime = time;
		label.setText("Current Time: " + dtf.format(currTime));
	}

	/*
	 * display is called by the game class to retrieve the timer panel for use in
	 * the game window
	 */
	public JLabel display() {
		return label;
	}

	/*
	 * run() is a function inherited from the Runnable interface. It is called
	 * automatically by the Thread.start() method.
	 */
	public void run() {

		startTimer();

		// This while loop keeps the run() function running as long as it needs before the stopwatch is stopped by an outside source.
		while (true) {
			if (stopped) {
				break;
			} else {
				continue;
			}
		}

		stopTimer();
	}

	/*
	 * changes a boolean variable which will lead the thread to stop itself in run()
	 */
	public void stopThread() {
		stopped = true;
	}

	/*
	 * UpdateTimer is an inner class of Stopwatch. It extends TimerTask and has methods that are used to return the time
	 * in the format we need it in for display.
	 */
	class UpdateTimer extends TimerTask {
		private LocalTime startTime;
		private LocalTime watchTime;
		private int hours;
		private int minutes;
		private int seconds;
		private DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_TIME;

		/*
		 * Constructor initializes startTime from the outer class.
		 */
		public UpdateTimer(LocalTime startTime) {
			this.startTime = startTime;
		}

		/*
		 * returns the last recorded time. Used to get endTime in outer class.
		 */
		public LocalTime getTime() {
			return watchTime;
		}

		/*
		 * checkValues is called in order to make sure all of the subtracting works
		 * properly. It basically performs the same function that a carry would in
		 * subtraction.
		 */
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

		/*
		 * run() is called by the timer object in the outer class when the scheduled
		 * time has come.
		 */
		@Override
		public void run() {
			// DecimalFormat is used to format the decimals of the ints so they can parse properly into the time object
			DecimalFormat decFormat = new DecimalFormat("00");

			// Captures the current time.
			LocalTime currentTime = LocalTime.now();

			// Subtracts the start time from the current time to get the elapsed time in each time field.
			hours = currentTime.get(ChronoField.HOUR_OF_DAY) - startTime.get(ChronoField.HOUR_OF_DAY);
			minutes = currentTime.get(ChronoField.MINUTE_OF_HOUR) - startTime.get(ChronoField.MINUTE_OF_HOUR);
			seconds = currentTime.get(ChronoField.SECOND_OF_MINUTE) - startTime.get(ChronoField.SECOND_OF_MINUTE);
			checkValues(currentTime);

			// combines the hours, minutes, and seconds into one string formatted as hh:mm:ss and then parses it into a LocalTime object.
			String timeString = decFormat.format(hours) + ":" + decFormat.format(minutes) + ":"
					+ decFormat.format(seconds);
			watchTime = LocalTime.parse(timeString, dtf);

			// Calls the outer class's updateTime() method which updates the label properly.
			Stopwatch.this.updateTime(watchTime);
		}
	}
}
