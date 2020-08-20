
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

	// ������ �ִ��� �˻�
	private static void isFileCheck(File dataFile) {
		if (!dataFile.exists()) {
			try {
				dataFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// ��ü --> ���̳ʸ�
	/**
	 * @MethodName : saveToObject
	 * @�ۼ��� : 2020. 6. 9.
	 * @�ۼ��� : ������
	 * @�������� : 06.09 (�Ű����� Object ���� Vector<PlanDTO>�� ������)
	 * @MethodInformation : ��ü�� �ҷ��ͼ� ���� ���Ͽ� ��ü�� ������ Vector �� �ϳ� ���� PlanDTO ��Ƽ� ��ü��
	 *                    write �ϰ� �̹� �����Ͱ� �����ϸ� �� �����͸� �о�� PlanDTO �߰��� ��Ƽ� write
	 *                    �Ѵ�.
	 * @param Vector<PlanDTO>
	 */
	public static void saveToObject(Vector<PlanDTO> dtoList) {

		// data.dat ������ �������� ������ ������
		isFileCheck(dataFile);

		// ���� ���(����) ��Ʈ��
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		// ��ü write
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

	// ���̳ʸ� --> ��ü
	/**
	 * @MethodName : loadToObject
	 * @�ۼ��� : 2020. 6. 9.
	 * @�ۼ��� : ������
	 * @�������� :
	 * @MethodInformation : data.dat �� �ִ� ��ü�� �о�ͼ� Object �� ������. Vector<PlanDTO>�� ��
	 *                    ��ȯ �ʿ�
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

			// ��ü �����´�
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

	// ��-���̸� ����Ͽ� �������ִ� �޼ҵ�
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
		System.out.println("�� : " + sum);
		if (sum < 0 && sum > -30) {
			sum = Math.abs(sum);
			return sum;
		}
		return -1;
	}

}