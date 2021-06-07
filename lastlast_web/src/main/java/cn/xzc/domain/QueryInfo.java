package cn.xzc.domain;
//��װ��ѯ��Ϣ
public class QueryInfo {
	
	private int currentpage = 1;
	private int pagesize = 4;
	private int startindex;
	
	private String queryname;//����ѯ�����������鿴ĳ����������ķ�ҳ���ݣ�����category_id
	private String queryvalue;//��ѯ������ֵ���鿴3�ŷ�������ķ�ҳ���ݣ�����category_id=3
	private String where;//ʹ�����������������Ķ�����װ��һ����ѯ����������where = "where category_id = ?"
	
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getStartindex() {
		this.startindex = (this.currentpage - 1) * this.pagesize;
		return startindex;
	}
	public String getQueryname() {
		return queryname;
	}
	public void setQueryname(String queryname) {
		this.queryname = queryname;
	}
	public String getQueryvalue() {
		return queryvalue;
	}
	public void setQueryvalue(String queryvalue) {
		this.queryvalue = queryvalue;
	}
	public String getWhere() {
		if (this.queryname == null || this.queryname.trim().equals("")) {
			return "";
		} else {
			//this.where = "where category_id=?"
			this.where = "where " + this.queryname + "=?";
		}
		return where;
	}
	
}
