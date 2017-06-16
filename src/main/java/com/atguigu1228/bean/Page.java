package com.atguigu1228.bean;

import java.util.List;

public class Page<T> {
	
	
	private int current_page_no;
	private int page_size;
	private int page_count_num;
	private int page_max_num;
	
	private List<T> page_datas;

	public int getCurrent_page_no() {
		return current_page_no;
	}

	public void setCurrent_page_no(int current_page_no) {
		this.current_page_no = current_page_no;
	}

	public int getPage_size() {
		return page_size;
	}

	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}

	public int getPage_count_num() {
		return page_count_num;
	}

	public void setPage_count_num(int page_count_num) {
		this.page_count_num = page_count_num;
	}

	public int getPage_max_num() {
		return page_max_num;
	}

	public void setPage_max_num(int page_max_num) {
		this.page_max_num = page_max_num;
	}

	public List<T> getPage_datas() {
		return page_datas;
	}
	public void setPage_datas(List<T> page_datas) {
		this.page_datas = page_datas;
	}
}
