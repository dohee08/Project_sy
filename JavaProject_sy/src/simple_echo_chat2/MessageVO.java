package simple_echo_chat2;

import java.io.Serializable;

public class MessageVO implements Serializable{	//JVM외부로 객체를 담을 때는 객체 직렬화 작업 필수!
	String name, msg;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
