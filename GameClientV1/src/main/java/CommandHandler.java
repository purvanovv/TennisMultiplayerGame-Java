import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.PrintWriter;

class CommandHandler extends Thread {
	BufferedReader reader;
	HumanPaddle p1;
	HumanPaddle p2;

	public CommandHandler(BufferedReader mSocketReader, HumanPaddle p1, HumanPaddle p2) {
		this.reader = mSocketReader;
		this.p1 = p1;
		this.p2 = p2;
	}

	@Override
	public void run() {
		try {
			while (!isInterrupted()) {
				String data = reader.readLine();
				System.out.println(data);

				if (data.length() < 5) {
					processCommand(data, p1, p2);
				}

			}
		} catch (Exception e) {
			System.err.println("Lost connection to server.");
			System.exit(-1);
		}
	}

	private void processCommand(String data, HumanPaddle p1, HumanPaddle p2) {
		char player = data.charAt(3);
		char keyType = data.charAt(0);
		int keyNumber = Integer.parseInt(data.substring(1, 3));
		switch (player) {
		case '1': {

			if (keyType == '1') {
				if (keyNumber == KeyEvent.VK_UP) {
					p1.setUpAccel(true);
				} else if (keyNumber == KeyEvent.VK_DOWN) {
					p1.setDownAccel(true);
				} else if (keyNumber == KeyEvent.VK_ENTER && TennisV2.isGameStarted == false && TennisV2.isFirstPlayerReady == false) {
					TennisV2.isFirstPlayerReady = true;
					System.out.println("First Player ready!");
					if (TennisV2.isFirstPlayerReady == true && TennisV2.isSecondPlayerReady == true) {
						TennisV2.isGameStarted = true;
						System.out.println("Game start!");
					}
				}

			} else if (keyType == '0') {
				if (keyNumber == KeyEvent.VK_UP) {
					p1.setUpAccel(false);
				} else if (keyNumber == KeyEvent.VK_DOWN) {
					p1.setDownAccel(false);
				}
			}
			break;
		}
		case '2': {
			if (keyType == '1') {
				if (keyNumber == KeyEvent.VK_UP) {
					p2.setUpAccel(true);
				} else if (keyNumber == KeyEvent.VK_DOWN) {
					p2.setDownAccel(true);
				} else if (keyNumber == KeyEvent.VK_ENTER && TennisV2.isGameStarted == false && TennisV2.isSecondPlayerReady == false) {
					TennisV2.isSecondPlayerReady = true;
					System.out.println("Second Player ready!");
					if (TennisV2.isFirstPlayerReady == true && TennisV2.isSecondPlayerReady == true) {
						TennisV2.isGameStarted = true;
						System.out.println("Game start!");
					}
				}
			} else if (keyType == '0') {
				if (keyNumber == KeyEvent.VK_UP) {
					p2.setUpAccel(false);
				} else if (keyNumber == KeyEvent.VK_DOWN) {
					p2.setDownAccel(false);
				}
			}
			break;
		}
		}
	}
}
