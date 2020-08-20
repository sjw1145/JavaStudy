package kr.ac.green;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Vector;

public class MainServer {
	private HashMap<Client, ObjectOutputStream> clientList;
	private Vector<ChattingRoomInfo> roomList;
	private WaitingRoomUserList waitingRoomUserList;

	public MainServer() {
		clientList = new HashMap<Client, ObjectOutputStream>();
		roomList = new Vector<ChattingRoomInfo>();
		waitingRoomUserList = new WaitingRoomUserList();
	}

	public void serverConn(Socket clientSocket) {
		ServerListener sl = new ServerListener(clientList, roomList, waitingRoomUserList, clientSocket);
		sl.start();
	}

	public static void main(String[] args) throws ConnectException, SocketTimeoutException {

		MainServer ms = new MainServer();
		Socket clientSocket = null;
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(10010);
			while (true) {
				synchronized (serverSocket) {
					System.out.println("접속을 기다립니다..");
					clientSocket = serverSocket.accept();
					System.out.println("접속!!");

					ms.serverConn(clientSocket);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}