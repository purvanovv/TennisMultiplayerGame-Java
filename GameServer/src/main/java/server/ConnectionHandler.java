package server;

import java.net.Socket;

public class ConnectionHandler extends Thread {
	private PlayerHandler firstPLayer;
	private PlayerHandler secondPlayer;
	private Socket socketFirstPlayer;

	private Socket socketSecondPlayer;

	private boolean checkFirstPlayerConnected;

	private boolean checkSecondPlayerConnected;

	private boolean checkInGame;

	public ConnectionHandler() {
		this.socketFirstPlayer = null;
		this.socketSecondPlayer = null;
		this.checkFirstPlayerConnected = false;
		this.checkSecondPlayerConnected = false;
	}

	public Socket getSocketFirstPlayer() {
		return socketFirstPlayer;
	}

	public void setSocketFirstPlayer(Socket socketFirstPlayer) {
		this.socketFirstPlayer = socketFirstPlayer;
		this.setCheckFirstPlayerConnected(true);
		this.setCheckInGame(false);
	}

	public Socket getSocketSecondPlayer() {
		return socketSecondPlayer;
	}

	public void setSocketSecondPlayer(Socket socketSecondPlayer) {
		this.socketSecondPlayer = socketSecondPlayer;
		this.setCheckSecondPlayerConnected(true);
	}

	public boolean isCheckFirstPlayerConnected() {
		return checkFirstPlayerConnected;
	}

	public void setCheckFirstPlayerConnected(boolean checkFirstPlayerConnected) {
		this.checkFirstPlayerConnected = checkFirstPlayerConnected;
	}

	public boolean isCheckSecondPlayerConnected() {
		return checkSecondPlayerConnected;
	}

	public void setCheckSecondPlayerConnected(boolean checkSecondPlayerConnected) {
		this.checkSecondPlayerConnected = checkSecondPlayerConnected;
	}

	public boolean isCheckInGame() {
		return checkInGame;
	}

	public void setCheckInGame(boolean checkInGame) {
		this.checkInGame = checkInGame;
	}

	@Override
	public void run() {
		this.setCheckInGame(true);
		this.firstPLayer = new PlayerHandler(socketFirstPlayer, socketSecondPlayer, "1");
		this.secondPlayer = new PlayerHandler(socketSecondPlayer, socketFirstPlayer, "2");
		this.firstPLayer.start();
		this.secondPlayer.start();

	}

}
