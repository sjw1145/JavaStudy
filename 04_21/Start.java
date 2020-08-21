
import java.util.Scanner;

public class Human {
	private String name;

	private int[] result;

	public Human(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getSub() {
		return result;
	}

	public void setSub(int[] result) {
		this.result = result;
	}

	public String toString() {
		String info = "[" + getName() + "]";
		for (int i = 0; i < result.length; i++) {
			info += "\t" + result[i];
		}
		return info;
	}
}

//���ð���
public class Subjects {
	// �����̸�
	private String title;
	// ������ ����
	private int[] scores;

	public Subjects(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int[] getScores() {
		return scores;
	}

	public void setScores(int[] scores) {
		this.scores = scores;
	}

	public String toString() {
		String info = getTitle();
		for (int i = 0; i < scores.length; i++) {
			info += "\t���� :" + scores[i];
		}
		return info;
	}
}

public class Test {

	// ������ ����(������)
	private Human[] candidate;
	// �����������
	private Subjects[] subject;

	// ��� ��
	private int human_count;
	// �������� ��
	private int subject_count;
	
	//���� ����
	private int sum;
	//���� ���
	private int avg;

	public Test() {
		// ������ ������.
		Scanner kb = new Scanner(System.in);

		System.out.print("�� ���� ���� ������ ������ ? ");

		subject = new Subjects[kb.nextInt()];

		for (int i = 0; i < subject.length; i++) {
			System.out.print("������ �̸� : ");
			String info = kb.next();
			subject[i] = new Subjects(info);
		}

		subject_count = subject.length;
	}

	public Test(Human... humans) {
		this();
		// ������ ������ �ʱ�ȭ
		candidate = humans;

		human_count = humans.length;

	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getAvg() {
		return avg;
	}

	public void setAvg(int avg) {
		this.avg = avg;
	}

	public Human[] getCandidate() {
		return candidate;
	}

	public void setCandidate(Human[] candidate) {
		this.candidate = candidate;
	}

	public Subjects[] getSubject() {
		return subject;
	}

	public void setSubject(Subjects[] subject) {
		this.subject = subject;
	}

	public void testStart() {
		for (int i = 0; i < subject_count; i++) {
			int[] temp = new int[subject_count];

			for (int j = 0; j < temp.length; j++) {
				temp[j] = (int) (Math.random() * (100 + 1));
			}
			subject[i].setScores(temp);
		}
		
		//������ ������� �ѱ�� �۾�
		for(int i = 0; i < human_count; i++) {

			int[] resultTemp = new int[subject_count];
			
			for(int j = 0; j < subject_count; j++) {
				resultTemp[j] = subject[j].getScores()[i];
			}
			
			candidate[i].setSub(resultTemp);
		}
	}

	// ���� ����, ��հ��
	public int subjectSum(Subjects sub) {
		int[] temp = sub.getScores();
		int sum = 0;

		for (int i = 0; i < temp.length; i++) {
			sum += temp[i];
		}
		return sum;
	}

	public int subjectAvg(Subjects sub) {
		return subjectSum(sub) / subject_count;
	}
	
	public void printAll() {
		System.out.println("============����===========");
		for(Subjects temp : subject ) {
			System.out.println(temp.toString());
			System.out.println("���� : " + subjectSum(temp));
			System.out.println("��� : " + subjectAvg(temp));
		}
		
		System.out.println("============���κ�===========");
		for(Human m : candidate) {
			System.out.println(m.toString());
		}
	}
	
	public String toString() {
		String info = "";
		
		return info;
	}
}

public class Start {

	public static void main(String[] args) {

		// ������� ����
		Human[] humans = { new Human("ö��"), new Human("����"), new Human("�μ�") };

		// ������ ����
		Test zz = new Test(humans);
		zz.testStart();
		
		zz.printAll();
	}
}