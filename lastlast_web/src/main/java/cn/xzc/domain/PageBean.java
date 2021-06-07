package cn.xzc.domain;

import java.util.List;

public class PageBean {
	
	private List<Good> list;
	private int totalrecord;
	private int pagesize;
	
	private int totalpage;
	
	private int currentpage;
	private int previouspage;
	private int nextpage;
	private int[] pagebar;
	public List<Good> getList() {
		return list;
	}
	public void setList(List<Good> list) {
		this.list = list;
	}
	public int getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getTotalpage() {
		if (this.totalrecord % this.pagesize == 0) {
			this.totalpage = this.totalrecord / this.pagesize;
		} else {
			this.totalpage = this.totalrecord / this.pagesize + 1;
		}
		return totalpage;
	}
	
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public int getPreviouspage() {
		if ((this.currentpage - 1) > 1) {
			this.previouspage = this.currentpage - 1;
		} else {
			this.previouspage = 1;
		}
		return previouspage;
	}
	
	public int getNextpage() {
		if ((this.currentpage + 1) > this.totalpage) {
			this.nextpage = this.totalpage;
		} else {
			this.nextpage = this.currentpage + 1;
		}
		return nextpage;
	}
	
	public int[] getPagebar() {
		int startpage;//ҳ��������ʼҳ
		int endpage;//ҳ�����Ľ���ҳ
		if (this.totalpage <= 10) {
			startpage = 1;
			endpage = this.totalpage;
		} else {
			startpage = this.currentpage - 4;
			endpage = this.currentpage + 5;
			
			if (startpage < 1) {
				startpage = 1;
				endpage = 10;
			}
			if (endpage > this.totalpage) {
				endpage = this.totalpage;
				startpage = this.totalpage - 9;
			}
		}
		
		this.pagebar = new int[endpage - startpage + 1];
		int index = 0;
		for (int i = startpage; i <= endpage; i++) {
			this.pagebar[index++] = i;
		}
		return pagebar;
	}
	
}
