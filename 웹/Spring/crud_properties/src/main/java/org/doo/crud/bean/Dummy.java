package org.doo.crud.bean;

public class Dummy {
	private int dnum;
	private String dcontent;
	public Dummy() {
	}
	
	public Dummy(String dcontent) {
		setDcontent(dcontent);
	}
	
	public Dummy(int dnum, String dcontent) {
		this.dnum = dnum;
		this.dcontent = dcontent;
	}

	public int getDnum() {
		return dnum;
	}

	public void setDnum(int dnum) {
		this.dnum = dnum;
	}

	public String getDcontent() {
		return dcontent;
	}

	public void setDcontent(String dcontent) {
		this.dcontent = dcontent;
	}
	
}
