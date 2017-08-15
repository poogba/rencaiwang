package marco.jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import marco.pojo.workInfo;

public class readWorkUrl {

	private String img;

	private String lxr;

	private String phoneNum;

	private String addr;

	private List<workInfo> wlList = new ArrayList<>();

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public List<workInfo> readUrl(Map<String, workInfo> map) {

		Iterator<Map.Entry<String, workInfo>> it = map.entrySet().iterator();

		while (it.hasNext()) {

			Map.Entry<String, workInfo> entry = it.next();

			try {

				// jsoup 打开每个工作连接
				Document doc = Jsoup.connect(entry.getKey()).get();

				// doc获得该网页的标头,包含职位名称和公司名
				String title = doc.title();

				// jsoup选择器过滤出联系人div块
				Elements select = doc.select("div.comtitle");

				// 获得联系人姓名
				String attr = select.select("ul").select("li").last().select("a").attr("href").substring(2);
				Document docCon = Jsoup.connect("http://www.wlmqrc.cn" + attr).get();

				Elements liEle = docCon.select("div#lit_Contact__ctl0_pan_Contact").select("ul").select("li");

				lxr = liEle.first().text();

				// 获得联系人信息图片
				img = liEle.select("img").first().attr("src").substring(2);
				phoneNum = ReadPicFromUrl.getPhoneNumber(img);
				// 公司详细地址
				addr = select.select("li").last().text();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println("图片加载错误...");
			}

			workInfo workInfo = entry.getValue();
			workInfo.setLianxiren(lxr);
			workInfo.setLianxidianhua(phoneNum);

			wlList.add(workInfo);

		}
		return wlList;
	}

}
