
public class StopwatchTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stopwatch sw = new Stopwatch();
		sw.startTimer();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sw.stop();
	}

}
