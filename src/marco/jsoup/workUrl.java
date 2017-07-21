package marco.jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import marco.pojo.workInfo;

public class workUrl {

	private Map<String, workInfo> map = new HashMap<>();

	private workInfo wInfo;

	public workInfo getwInfo() {
		return wInfo;
	}

	public void setwInfo(workInfo wInfo) {
		this.wInfo = wInfo;
	}

	public Map<String, workInfo> DownLoadWorkUrl(String hurl, String url) {

		try {

			Document doc = Jsoup.connect(url).get();

			List<Element> elements = doc.getElementsByClass("seaLists");

			for (Element element : elements) {

				String work_url = element.select("li.seaList12").select("a").attr("href");

				String wString = hurl + work_url.substring(2);

				List<String> ZW = element.getElementsByClass("seaList12").tagName("a").eachText();
				List<String> XZ = element.getElementsByClass("seaList15").tagName("span").eachText();
				List<String> GS = element.getElementsByClass("seaList13").tagName("a").eachText();
				List<String> SQ = element.getElementsByClass("seaList14").tagName("span").eachText();
				Elements RQ = element.getElementsByClass("seaList16");
				List<String> ZWJJ = element.getElementsByClass("seaList32").tagName("span").eachText();

				wInfo = new workInfo();
				wInfo.setZhiwei(ZW.toString());
				wInfo.setXinzi(XZ.toString());
				wInfo.setShiqu(SQ.toString());
				wInfo.setGongsi(GS.toString());
				wInfo.setRiqi(RQ.text());
				wInfo.setZhiweijianjie(ZWJJ.toString());

				map.put(wString, wInfo);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;

	}

}
