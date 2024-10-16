package pensionlife;

import java.text.SimpleDateFormat;
import java.util.Date;

public class rename {
	//이름을 재구성하여 결과값을 리턴하는 메소드(모듈)
	String filenm = "";
	public rename(String z) {
		this.filenm = z;
		this.re();
	}
	
	private void re() {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today2 = sdf.format(today);
		String rnd = String.valueOf((int)Math.ceil(Math.random()*1500)); //~1500까지의 랜덤번호
		
		//속성명 핸들링
		int period = this.filenm.lastIndexOf(".");
		String property = this.filenm.substring(period+1); 
		
		this.filenm = today2 + rnd + "." + property;
	}

}
