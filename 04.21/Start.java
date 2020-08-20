
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

//응시과목
public class Subjects {
	// 과목이름
	private String title;
	// 과목의 점수
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
			info += "\t점수 :" + scores[i];
		}
		return info;
	}
}

public class Test {

	// 응시자 정보(여러명)
	private Human[] candidate;
	// 시험과목정보
	private Subjects[] subject;

	// 사람 수
	private int human_count;
	// 교과목의 수
	private int subject_count;
	
	//과목별 총점
	private int sum;
	//과목별 평균
	private int avg;

	public Test() {
		// 과목을 만들어요.
		Scanner kb = new Scanner(System.in);

		System.out.print("몇 개의 시험 과목을 만들까요 ? ");

		subject = new Subjects[kb.nextInt()];

		for (int i = 0; i < subject.length; i++) {
			System.out.print("교과목 이름 : ");
			String info = kb.next();
			subject[i] = new Subjects(info);
		}

		subject_count = subject.length;
	}

	public Test(Human... humans) {
		this();
		// 응시자 정보를 초기화
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
		
		//점수를 사람에게 넘기는 작업
		for(int i = 0; i < human_count; i++) {

			int[] resultTemp = new int[subject_count];
			
			for(int j = 0; j < subject_count; j++) {
				resultTemp[j] = subject[j].getScores()[i];
			}
			
			candidate[i].setSub(resultTemp);
		}
	}

	// 과목별 총점, 평균계산
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
		System.out.println("============과목별===========");
		for(Subjects temp : subject ) {
			System.out.println(temp.toString());
			System.out.println("총점 : " + subjectSum(temp));
			System.out.println("평균 : " + subjectAvg(temp));
		}
		
		System.out.println("============개인별===========");
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

		// 사람들을 만듦
		Human[] humans = { new Human("철수"), new Human("영희"), new Human("민수") };

		// 시험을 만듦
		Test zz = new Test(humans);
		zz.testStart();
		
		zz.printAll();
	}
}