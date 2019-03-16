package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class PlayerHandler extends Thread {
	private Socket playerSocket;

	private Socket enemySocket;

	private String playerName;

	public PlayerHandler(Socket playerSocket, Socket enemySocket, String playerName) {
		this.playerSocket = playerSocket;
		this.enemySocket = enemySocket;
		this.playerName = playerName;
	}

	@Override
	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(this.playerSocket.getInputStream()));
			OutputStream playerWriter = this.playerSocket.getOutputStream();
			OutputStream enemyWriter = this.enemySocket.getOutputStream();
			String line = reader.readLine();
			while (line != "exit") {
				// System.out.println(line);
				playerWriter.write((line + playerName + System.lineSeparator()).getBytes());
				enemyWriter.write((line + playerName + System.lineSeparator()).getBytes());
				line = reader.readLine();
			}

			// Da zatvorq soketite
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
