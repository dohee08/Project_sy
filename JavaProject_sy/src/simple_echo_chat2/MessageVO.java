package simple_echo_chat2;

import java.io.Serializable;

public class MessageVO implements Serializable{	//JVM�ܺη� ��ü�� ���� ���� ��ü ����ȭ �۾� �ʼ�!
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
