package marco.jsoup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

import com.asprise.ocr.Ocr;

public class ReadPicFromUrl {

	public static String getPhoneNumber(String url) throws Exception {

		String RCW = "http://www.wlmqrc.cn";
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(RCW + url);
		int statusCode = httpClient.executeMethod(getMethod);
		if (statusCode != HttpStatus.SC_OK) {
			System.err.println("Method failed: " + getMethod.getStatusLine());
			return "Connection Error";
		}
		// String picName = "F:\\img\\";
		String picName = "img/";
		File filepic = new File(picName);
		if (!filepic.exists())
			filepic.mkdir();
		File filepicF = new File(picName + new Date().getTime() + ".gif");
		InputStream inputStream = getMethod.getResponseBodyAsStream();
		OutputStream outStream = new FileOutputStream(filepicF);
		IOUtils.copy(inputStream, outStream);

		// 关闭流
		inputStream.close();
		outStream.close();

		// 置空用完的对象
		httpClient = null;
		getMethod = null;

		Ocr.setUp(); // one time setup
		Ocr ocr = new Ocr(); // create a new OCR engine
		ocr.startEngine("eng", Ocr.SPEED_FASTEST); // English
		String s = ocr.recognize(new File[] { filepicF }, Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);
		ocr.stopEngine();
		return s;
	}

}
