package marco.jsoup;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import marco.poi.poiExcel;
import marco.pojo.workInfo;
import marco.send.MailUtils;

public class doWork {

	public static void main(String[] args) throws Exception {

		Date currentTime = new Date();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		String xlsName = formatter.format(currentTime);

		String filePath = xlsName + ".xls";

		File file = new File(filePath);

		file.createNewFile();

		System.out.println(filePath);

		String RCW = "http://www.wlmqrc.cn";
		
		/*人事助理地址 http://www.wlmqrc.cn/jobseek/jobtype_3108_0_2_1.aspx*/
		/*行政助理地址 http://www.wlmqrc.cn/jobseek/jobtype_3109_0_2_1.aspx*/
		
		String addr = "http://www.wlmqrc.cn/jobseek/jobtype_3108_0_2_1.aspx";
		

		workUrl wu = new workUrl();

		Map<String, workInfo> downLoadWorkUrl = wu.DownLoadWorkUrl(RCW, addr);

		readWorkUrl rwu = new readWorkUrl();

		List<workInfo> list = rwu.readUrl(downLoadWorkUrl);

		poiExcel.write(list, file, "人事助理");

		MailUtils cn = new MailUtils();

		cn.setAddress("linuxclouds@163.com", "15099421954@163.com", "今日人事职位");

		cn.setAffix(filePath, file.getName());

		cn.send("smtp.163.com", "linuxclouds@163.com", "fermaa1991");

	}
}
