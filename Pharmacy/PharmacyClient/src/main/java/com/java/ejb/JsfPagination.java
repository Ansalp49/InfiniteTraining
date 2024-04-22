package com.java.ejb;

import java.io.IOException;
import java.util.List;

import javax.faces.component.UICommand;
import javax.faces.event.ActionEvent;

public class JsfPagination {

	private static final long serialVersionUID = 1L;
	private List<MedicineEntry> cdList;
	private MainImpl queryHelper;

	/**
	 * pagination stuff
	 */
	private int totalRows;
	static private int firstRow;
	static private int rowsPerPage;
	private int totalPages;
	private int pageRange;
	private Integer[] pages;
	private int currentPage;
	private String medName;
	private static final int default_rows_perpage = 5;

	public String getMedName() {
		return medName;
	}

	public void setMedName(String medName) {
		this.medName = medName;
	}

	/**
	 * Creates a new instance of JsfPaginationBean
	 */

	static {
		rowsPerPage = 5;

	}

	public JsfPagination() {

		System.out.println("Rows Per Page  " + rowsPerPage);
		queryHelper = new MainImpl();
		/**
		 * the below function should not be called in real world application
		 */
		// Set default values somehow (properties files?).
		// rowsPerPage = 5; // Default rows per page (max amount of rows to be displayed
		// at once).
		pageRange = 8; // Default page range (max amount of page links to be displayed at once).
	}

	public List<MedicineEntry> getMedicinesList(String medName) {

		// Delete expired medicines from the database
		// queryHelper.deleteExpiredMedicinesFromDatabase();

//		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
//		sessionMap.remove(queryHelper.deleteExpiredMedicinesFromDatabase()); 	

		cdList = null;

		if (cdList == null) {
			loadMed(medName);
		}

		return cdList;
	}

	private boolean showErrorMessage;

	public boolean isShowErrorMessage() {
		return showErrorMessage;
	}

	public void setShowErrorMessage(boolean showErrorMessage) {
		this.showErrorMessage = showErrorMessage;
	}

	public void setCdList(List<MedicineEntry> cdList) {
		this.cdList = cdList;
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
		if (rowsPerPage <= 0 || rowsPerPage > 20) {
			rowsPerPage = default_rows_perpage;
		}
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

	private void loadMed(String medName) {

		System.out.println("medName is   :   " + medName);
		System.out.println("First Row  " + firstRow);
		System.out.println("Count  " + rowsPerPage);
		cdList = queryHelper.searchByMedName(firstRow, rowsPerPage, medName);
		System.out.println(" Count is  " + cdList);
		totalRows = queryHelper.countRows(medName);
		System.out.println("Total Rows Med " + totalRows);
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
		page(totalRows - ((totalRows % rowsPerPage != 0) ? totalRows % rowsPerPage : rowsPerPage));
	}

	public void page(ActionEvent event) {
		page(((Integer) ((UICommand) event.getComponent()).getValue() - 1) * rowsPerPage);
	}

	private void page(int firstRow) {
		this.firstRow = firstRow;
	}

	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void clear() throws IOException {

		rowsPerPage = default_rows_perpage;
		firstRow = 0;

		MainImpl main = new MainImpl();
		if (main.getMedName() != null) {
			main.setMedName("");
		}

		MainImpl.orderbyEntryID = "sort";
		MainImpl.orderbyMedId = "sort";
		MainImpl.orderbyMedName = "sort";
		MainImpl.orderbyType = "sort";
		MainImpl.orderbyQuantity = "sort";
		MainImpl.orderbyEntryDate = "sort";
		MainImpl.orderbyManfactureDate = "sort";
		MainImpl.orderbyExpiryDate = "sort";
		MainImpl.orderbyPrice = "sort";

		MainImpl.orderbyEntryIDDes = "sort";
		MainImpl.orderbyMedIdDes = "sort";
		MainImpl.orderbyMedNameDes = "sort";
		MainImpl.orderbyTypeDes = "sort";
		MainImpl.orderbyQuantityDes = "sort";
		MainImpl.orderbyEntryDateDes = "sort";
		MainImpl.orderbyManfactureDateDes = "sort";
		MainImpl.orderbyExpiryDateDes = "sort";
		MainImpl.orderbyPriceDes = "sort";

	}
}
