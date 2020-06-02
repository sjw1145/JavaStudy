/**
 * @FileName : PropertiesEx.java
 * @Project : 06.02
 * @Date : 2020. 6. 2.
 * @�ۼ��� : user
 * @�����̷� :
 * @���α׷� ���� :
 */
package kr.ac.green;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

/**
 * @author : user
 * @ClassName : PropertiesEx
 * @Year : 2020. 6. 2.
 * @Information :
 */
public class PropertiesEx {
	/*
	 * Properties 1. Map�迭 , Hashtable ��� 2. ���Ͽ� ���� �б� ����� ����. 3. key, value ->
	 * String
	 */

	/**
	 * @MethodName : save
	 * @�ۼ��� : 2020. 6. 2.
	 * @�ۼ��� : ������
	 * @�������� :
	 * @MethodInformation : ����
	 */
	public static void save() {
		Properties prop = new Properties();
		// put �� ����

		prop.setProperty("myKey", "myValue");
		prop.setProperty("yourKey", "yourValue");
		prop.setProperty("someonesKey", "someonesValue");

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("mydata.xml");
			// prop.store(fos, "hello");

			prop.storeToXML(fos, "Hello");

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @MethodName : load
	 * @�ۼ��� : 2020. 6. 2.
	 * @�ۼ��� : ������
	 * @�������� :
	 * @MethodInformation : XML ���� �ε�, properties ���� �ε�
	 * @Parm :
	 */
	public static void load() {
		FileInputStream fis = null;
		Properties prop = new Properties();

		try {
			// fis = new FileInputStream("mydata.properties");
			// prop.load(fis);

			fis = new FileInputStream("mydata.xml");
			prop.loadFromXML(fis);

			System.out.println(prop);
		} catch (IOException e) {
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// save();
		load();
	}
}