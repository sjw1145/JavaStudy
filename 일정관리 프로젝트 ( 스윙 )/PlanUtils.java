
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Vector;

public class PlanUtils {
	private static final File dataFile = new File("data.dat");

	// 파일이 있는지 검사
	private static void isFileCheck(File dataFile) {
		if (!dataFile.exists()) {
			try {
				dataFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 객체 --> 바이너리
	/**
	 * @MethodName : saveToObject
	 * @작성일 : 2020. 6. 9.
	 * @작성자 : 서종완
	 * @수정내역 : 06.09 (매개변수 Object 에서 Vector<PlanDTO>로 수정함)
	 * @MethodInformation : 객체를 불러와서 기존 파일에 객체가 없으면 Vector 를 하나 만들어서 PlanDTO 담아서 객체를
	 *                    write 하고 이미 데이터가 존재하면 그 데이터를 읽어들어서 PlanDTO 추가로 담아서 write
	 *                    한다.
	 * @param Vector<PlanDTO>
	 */
	public static void saveToObject(Vector<PlanDTO> dtoList) {

		// data.dat 파일이 존재하지 않으면 생성함
		isFileCheck(dataFile);

		// 파일 출력(쓰기) 스트림
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		// 객체 write
		try {
			fos = new FileOutputStream(dataFile);
			oos = new ObjectOutputStream(fos);

			oos.writeObject(dtoList);

			oos.flush();
			oos.reset();

		} catch (IOException e) {
		} finally {
			try {
				oos.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// 바이너리 --> 객체
	/**
	 * @MethodName : loadToObject
	 * @작성일 : 2020. 6. 9.
	 * @작성자 : 서종완
	 * @수정내역 :
	 * @MethodInformation : data.dat 에 있는 객체를 읽어와서 Object 로 리턴함. Vector<PlanDTO>로 형
	 *                    변환 필요
	 * @return Object
	 */
	public static Vector<PlanDTO> loadToObject() {
		isFileCheck(dataFile);

		FileInputStream fis = null;
		ObjectInputStream ois = null;

		Object obj = null;
		try {
			fis = new FileInputStream(dataFile);
			ois = new ObjectInputStream(fis);

			// 객체 가져온다
			obj = ois.readObject();

			Vector<PlanDTO> dtoList = (Vector<PlanDTO>) obj;

			return dtoList;

		} catch (EOFException e) {
			return null;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void deleteToDataFile() {
		dataFile.delete();
	}

	// 디-데이를 계산하여 리턴해주는 메소드
	public static long dayCalculator(PlanDTO plan) {
		Calendar today = Calendar.getInstance();
		Calendar d_day = plan.getStart();

		int year = d_day.get(Calendar.YEAR);
		int month = d_day.get(Calendar.MONTH);
		month--;
		int day = d_day.get(Calendar.DATE);

		Calendar newCal = Calendar.getInstance();
		newCal.set(Calendar.YEAR, year);
		newCal.set(Calendar.MONTH, month);
		newCal.set(Calendar.DATE, day);

		long today1 = today.getTimeInMillis() / (24 * 60 * 60 * 1000);
		long dday = newCal.getTimeInMillis() / (24 * 60 * 60 * 1000);

		long sum = today1 - dday;
		System.out.println("썸 : " + sum);
		if (sum < 0 && sum > -30) {
			sum = Math.abs(sum);
			return sum;
		}
		return -1;
	}

}