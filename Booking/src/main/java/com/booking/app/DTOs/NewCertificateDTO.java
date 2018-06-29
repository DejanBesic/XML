package com.booking.app.DTOs;

public class NewCertificateDTO {

	private String cn, startDate, endDate, newCertPass, newCertAlias, issuerPass, issuerAlias, subjectUsername;
	
	public NewCertificateDTO(){
		
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getNewCertPass() {
		return newCertPass;
	}

	public void setNewCertPass(String newCertPass) {
		this.newCertPass = newCertPass;
	}

	public String getNewCertAlias() {
		return newCertAlias;
	}

	public void setNewCertAlias(String newCertAlias) {
		this.newCertAlias = newCertAlias;
	}

	public String getIssuerPass() {
		return issuerPass;
	}

	public void setIssuerPass(String issuerPass) {
		this.issuerPass = issuerPass;
	}

	public String getIssuerAlias() {
		return issuerAlias;
	}

	public void setIssuerAlias(String issuerAlias) {
		this.issuerAlias = issuerAlias;
	}

	public String getSubjectUsername() {
		return subjectUsername;
	}

	public void setSubjectUsername(String subjectUsername) {
		this.subjectUsername = subjectUsername;
	}
}
