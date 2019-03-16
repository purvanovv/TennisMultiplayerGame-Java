package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class StartUp {
	private static int SERVER_PORT = 8888;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket playerSocket = null;
		ConnectionHandler connectionHandler = null;
		BufferedReader playerInputStream = null;
		OutputStream writer = null;

		try {
			serverSocket = new ServerSocket(SERVER_PORT);
			System.out.println("Server listen on:" + SERVER_PORT);
			while (true) {
				playerSocket = serverSocket.accept();
				if (connectionHandler != null && connectionHandler.isCheckSecondPlayerConnected() == false) {
					connectionHandler.setSocketSecondPlayer(playerSocket);
					playerInputStream = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
					writer = playerSocket.getOutputStream();
					writer.write(("You are connected.Wait for start game.\n").getBytes());

				} else {
					connectionHandler = new ConnectionHandler();
					connectionHandler.setSocketFirstPlayer(playerSocket);
					playerInputStream = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
					writer = playerSocket.getOutputStream();
					writer.write(("You are connected.Wait for another player.\n").getBytes());

				}
				if (connectionHandler.isCheckFirstPlayerConnected() && connectionHandler.isCheckSecondPlayerConnected()
						&& connectionHandler.isCheckInGame() == false) {
					connectionHandler.start();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
