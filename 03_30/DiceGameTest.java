import java.util.*;
class DiceGame{
	private int diceFace;
	private int userGuess;

	private int getUserInput(){
		System.out.println("������ �Է��Ͻÿ�:");
		Scanner s = new Scanner(System.in);
		return s.nextInt();
	}
	private int rollDice() {
		return (int)(Math.random()*6)+1;
	}
	private boolean checkUserGuess() {
		if (diceFace == userGuess) { 
			System.out.println("�¾ҽ��ϴ�");
			return false;
		}else{
			System.out.println("Ʋ�Ƚ��ϴ�");
			return true;
		}
	}
	public void startPlaying(){
		// ���� �߻�
		diceFace = rollDice();
		do {
			// ����� �Է� ó��
			userGuess = getUserInput() ;
		} while(checkUserGuess());
	}
}

class DiceGameTest{
	public static void main(String[] args) {
		 DiceGame game = new DiceGame();
		 game.startPlaying();

	}
}
