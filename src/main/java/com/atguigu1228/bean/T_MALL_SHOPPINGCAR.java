package com.atguigu1228.bean;

import java.math.BigDecimal;
import java.util.Date;

public class T_MALL_SHOPPINGCAR {
	private int id;
	private String sku_mch;
	private BigDecimal sku_jg;
	private int tjshl;
	private BigDecimal hj;
	private int yh_id;
	private int shp_id;
	private Date chjshj;
	private int sku_id;
	private String shp_tp;
	private String shfxz;

	private String kcdz;

	public String getKcdz() {
		return kcdz;
	}

	public void setKcdz(String kcdz) {
		this.kcdz = kcdz;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSku_mch() {
		return sku_mch;
	}

	public void setSku_mch(String sku_mch) {
		this.sku_mch = sku_mch;
	}

	public int getTjshl() {
		return tjshl;
	}

	public void setTjshl(int tjshl) {
		this.tjshl = tjshl;
	}

	public BigDecimal getSku_jg() {
		return sku_jg;
	}

	public void setSku_jg(BigDecimal sku_jg) {
		this.sku_jg = sku_jg;
	}

	public BigDecimal getHj() {
		return hj;
	}

	public void setHj(BigDecimal hj) {
		this.hj = hj;
	}

	public int getYh_id() {
		return yh_id;
	}

	public void setYh_id(int yh_id) {
		this.yh_id = yh_id;
	}

	public int getShp_id() {
		return shp_id;
	}

	public void setShp_id(int shp_id) {
		this.shp_id = shp_id;
	}

	public Date getChjshj() {
		return chjshj;
	}

	public void setChjshj(Date chjshj) {
		this.chjshj = chjshj;
	}

	public int getSku_id() {
		return sku_id;
	}

	public void setSku_id(int sku_id) {
		this.sku_id = sku_id;
	}

	public String getShp_tp() {
		return shp_tp;
	}

	public void setShp_tp(String shp_tp) {
		this.shp_tp = shp_tp;
	}

	public String getShfxz() {
		return shfxz;
	}

	public void setShfxz(String shfxz) {
		this.shfxz = shfxz;
	}

}
