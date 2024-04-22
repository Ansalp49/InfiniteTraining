package com.java.ejb;

import java.util.List;
import java.util.Map;

import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

public class JsfPaginationBean {

	private static final long serialVersionUID = 1L;
	private List<Prescription> cdList;
	private PrescriptionDAOImpl queryHelper;
	/**
	 * pagination stuff
	 */
	private int totalRows;
	private static int firstRow;
	static private int rowsPerPage;
	private int totalPages;
	private int pageRange;
	private Integer[] pages;
	private int currentPage;

	public List<Prescription> getCdList() {
		return cdList;
	}

	public List<Prescription> getPresList(String prescriptionId) {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		
		cdList = null;
		if (cdList == null) {
			loadInsurance(prescriptionId);
		}
		System.out.println("Size: " + cdList);
		if (cdList.size() != 0) {
			sessionMap.put("error", "");
			return cdList;
		} else {
			sessionMap.put("error", "Record Not Found");
			return null;
		}
	}

	public void setCdList(List<Prescription> cdList) {
		this.cdList = cdList;
	}

	public PrescriptionDAOImpl getQueryHelper() {
		return queryHelper;
	}

	public void setQueryHelper(PrescriptionDAOImpl queryHelper) {
		this.queryHelper = queryHelper;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPageRange() {
		return pageRange;
	}

	public void setPageRange(int pageRange) {
		this.pageRange = pageRange;
	}

	public Integer[] getPages() {
		return pages;
	}

	public void setPages(Integer[] pages) {
		this.pages = pages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JsfPaginationBean() {
		queryHelper = new PrescriptionDAOImpl();
		/**
		 * the below function should not be called in real world application
		 */
		// Set default values somehow (properties files?).
		pageRange = 5;
		rowsPerPage = 5; // Default rows per page (max amount of rows to be displayed at once).
		// Default page range (max amount of page links to be displayed at once).
	}

	private void loadInsurance(String prescriptionId) {
		System.out.println("First Row  " + firstRow);
		System.out.println("Count  " + rowsPerPage);

		cdList = queryHelper.getListOfPres(prescriptionId, firstRow, rowsPerPage);

		System.out.println(cdList);

		totalRows = queryHelper.countRows(prescriptionId);

		System.out.println("Total Rows Pres " + totalRows);
		// Set currentPage, totalPages and pages.

		currentPage = (totalRows / rowsPerPage) - ((totalRows - firstRow) / rowsPerPage) + 1;
		totalPages = (totalRows / rowsPerPage) + ((totalRows % rowsPerPage != 0) ? 1 : 0);

		int pagesLength = Math.min(pageRange, totalPages);
		pages = new Integer[pagesLength];
		// firstPage must be greater than 0 and lesser than totalPages-pageLength.
		int firstPage = Math.min(Math.max(0, currentPage - (pageRange / 2)), totalPages - pagesLength);
		// Create pages (page numbers for page links).
		for (int i = 0; i < pagesLength; i++) {
			pages[i] = ++firstPage;
		}
	}

	// Paging actions
	// -----------------------------------------------------------------------------
	public void pageFirst() {
		page(0);
	}

	public void pageNext() {
		page(firstRow + rowsPerPage);
	}

	public void pagePrevious() {
		page(firstRow - rowsPerPage);
	}

	public void pageLast() {
		int lastPageFirstRow = (totalPages - 1) * rowsPerPage;
		page(lastPageFirstRow);
	}

	public void page(ActionEvent event) {
		page(((Integer) ((UICommand) event.getComponent()).getValue() - 1) * rowsPerPage);
	}

	private void page(int firstRow) {
		System.out.println("Page method called with firstRow: " + firstRow);
		JsfPaginationBean.firstRow = firstRow;
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		String prescriptionId = (String) sessionMap.get("prescriptionId");
		loadInsurance(prescriptionId);
	}

}
