package com.prabha.inventory.model;

import java.util.Date;

public class ReportGenerateDetails {
	
	private Integer reportId;
	
	private Double incomePrice;
	
	private Date reportedAt;

	/**
	 * @return the reportId
	 */
	public final Integer getReportId() {
		return reportId;
	}

	/**
	 * @param reportId the reportId to set
	 */
	public final void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	/**
	 * @return the incomePrice
	 */
	public final Double getIncomePrice() {
		return incomePrice;
	}

	/**
	 * @param incomePrice the incomePrice to set
	 */
	public final void setIncomePrice(Double incomePrice) {
		this.incomePrice = incomePrice;
	}

	/**
	 * @return the reportedAt
	 */
	public final Date getReportedAt() {
		return reportedAt;
	}

	/**
	 * @param reportedAt the reportedAt to set
	 */
	public final void setReportedAt(Date reportedAt) {
		this.reportedAt = reportedAt;
	}
	
	

}
