package test.com.jxutcm.controllers.model;

 
public class ResponseObject <T>{

	private String msg;//���ص���Ϣ
	private int state=1;;//״̬   0��ʧ��   1���ɹ�
	private T datas;//��Ž�������
	private int page;
	private int count;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	private int size;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public T getDatas() {
		return datas;
	}
	public void setDatas(T datas) {
		this.datas = datas;
	}
 

}
