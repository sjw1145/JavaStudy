package kr.ac.green;

import java.util.Arrays;

public class WaitingRoomUserList {
	private int capacity;
	private int size;
	private Client[] waitingUser;

	public WaitingRoomUserList() {
		size = -1;
		capacity = 10;
		waitingUser = new Client[capacity];
	}

	public Client[] getWaitingUser() {
		return waitingUser;
	}

	public void setWaitingUser(Client[] waitingUser) {
		this.waitingUser = waitingUser;
	}

	public int getSize() {
		return size;
	}

	public void addClient(String nickName, String gender) {
		if (size >= capacity) {
			reallocate();
		}
		synchronized(waitingUser) {
			waitingUser[size + 1] = new Client(nickName, gender);
			size++;
		}
	}

	public void addClient(Client client) {
		
			if (size+1 >= capacity) {
				reallocate();
			}
		synchronized(waitingUser) {
			waitingUser[size + 1] = client;
			size++;
		}
	}

	public void removeClient(Client client) {
		synchronized(waitingUser) {
			String subNickName = client.getNickName();
			int n = searchClient(subNickName);
			if (n != -1) {
				waitingUser[n] = null;
				for (int i = n + 1; i <= size; i++) {
					if (waitingUser[i] != null) {
						waitingUser[i - 1] = waitingUser[i];
					}
				}
				size--;
			}
		}
	}

	private int searchClient(String nickName) {
		int i = 0;
		for (i = 0; i <= size; i++) {
			if (waitingUser[i].equals(nickName)) {
				return i;
			}
		}
		return -1;
	}

	public Client searchUser(String nickName) {
		int n = searchClient(nickName);
		if (n != -1) {
			return waitingUser[n];
		}
		return null;
	}

	private void reallocate() {
		capacity *= 2;
		System.out.println(capacity);
		waitingUser = Arrays.copyOf(waitingUser, capacity);
	}

	public Client getClient(String nickName) {
		int n = searchClient(nickName);
		if (n != -1) {
			return waitingUser[n];
		} else {
			return null;
		}
	}

	public Client[] returnClient() {
		if (size == -1) {
			return null;
		}

		Client[] temp = new Client[size + 1];

		for (int i = 0; i <= size; i++) {
			temp[i] = waitingUser[i];
		}
		return temp;
	}
}

