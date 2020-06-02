package kr.ac.green;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatting {

	public static void main(String[] args) {
		File f = new File("c:\\study\\a.txt");
		// ������ ���� �ð�
		long time = f.lastModified();

		// 2020-05-26
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d(a hh:mm:ss)");

		// ������ ���� �ð��� ������ �¾
		Date toDay = new Date();
		// ���ϴ� �ð� ����
		toDay.setTime(time);

		// �ð��� ���ڿ��� ����
		String strToday = sdf.format(toDay);

		try {
			// ���ڿ� -> �ð�(Date)
			Date newDate = sdf.parse(strToday);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println(strToday);

	}

}