package com.atguigu1228.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OBJECT_T_MALL_ORDER {
	

	
	
	private int id,yh_id,dzh_id,jdh;
	
	private String dzh_mch,shhr,jdmsh;
	
	private BigDecimal zje;
	
	private Date chjshj,yjsdshj;
	
	
	private List<T_MALL_ORDER_INFO> list_order_info;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYh_id() {
		return yh_id;
	}

	public void setYh_id(int yh_id) {
		this.yh_id = yh_id;
	}

	public int getDzh_id() {
		return dzh_id;
	}

	public void setDzh_id(int dzh_id) {
		this.dzh_id = dzh_id;
	}

	public int getJdh() {
		return jdh;
	}

	public void setJdh(int jdh) {
		this.jdh = jdh;
	}

	public String getDzh_mch() {
		return dzh_mch;
	}

	public void setDzh_mch(String dzh_mch) {
		this.dzh_mch = dzh_mch;
	}

	public String getShhr() {
		return shhr;
	}

	public void setShhr(String shhr) {
		this.shhr = shhr;
	}

	public BigDecimal getZje() {
		return zje;
	}

	public void setZje(BigDecimal zje) {
		this.zje = zje;
	}

	public Date getChjshj() {
		return chjshj;
	}

	public void setChjshj(Date chjshj) {
		this.chjshj = chjshj;
	}

	public Date getYjsdshj() {
		return yjsdshj;
	}

	public void setYjsdshj(Date yjsdshj) {
		this.yjsdshj = yjsdshj;
	}

	public List<T_MALL_ORDER_INFO> getList_order_info() {
		return list_order_info;
	}

	public void setList_order_info(List<T_MALL_ORDER_INFO> list_order_info) {
		this.list_order_info = list_order_info;
	}

	public String getJdmsh() {
		return jdmsh;
	}

	public void setJdmsh(String jdmsh) {
		this.jdmsh = jdmsh;
	}

}
