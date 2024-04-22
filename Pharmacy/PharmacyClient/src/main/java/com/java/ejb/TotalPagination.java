package com.java.ejb;


import java.io.IOException;
import java.util.List;

import javax.faces.component.UICommand;
import javax.faces.event.ActionEvent;



public class TotalPagination {

	private static final long serialVersionUID = 1L;
	private List<MedList> cdList;
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
	private String medName1;
	private static final int default_rows_perpage=5;
	public String getMedName1() {
		return medName1;
	}

	public void setMedName1(String medName1) {
		this.medName1 = medName1;
	}
	/**
	 * Creates a new instance of JsfPaginationBean
	 */
	
	  static 
	  {
		  rowsPerPage = 5; 
		  }
	 
	public TotalPagination() {

		System.out.println("Rows Per Page  " +rowsPerPage);
		queryHelper = new MainImpl();
		/**
		 * the below function should not be called in real world application
		 */
		// Set default values somehow (properties files?).
	  // rowsPerPage = 5; // Default rows per page (max amount of rows to be displayed at once).
		pageRange = 8; // Default page range (max amount of page links to be displayed at once).
	}
	
	 public List<MedList> getMedicinesTotalList(String medName1) { 

		 cdList=null;
		 if(cdList == null) {
			 loadTotal(medName1);
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
		
	public void setCdList(List<MedList> cdList) {
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
		if(rowsPerPage<=0 || rowsPerPage > 20) {
			rowsPerPage=default_rows_perpage;
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
	
	private void loadTotal(String medName1) {
		System.out.println("medName is   :   " +medName1);
		System.out.println("First Row  " +firstRow);
		System.out.println("Count  " +rowsPerPage);
		cdList = queryHelper.totalMedicinesList(firstRow, rowsPerPage, medName1);
		System.out.println("Employ Count is  " +cdList);
		totalRows = queryHelper.countRowsList(medName1);
		System.out.println("Total Rows  " +totalRows);
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
		page(totalRows - ((totalRows % rowsPerPage != 0) ? totalRows % rowsPerPage : rowsPerPage));
	}
	public void page(ActionEvent event) {
		page(((Integer) ((UICommand) event.getComponent()).getValue() - 1) * rowsPerPage);
	}
	private void page(int firstRow) {
		this.firstRow = firstRow;
	}
	
public void clear() throws IOException {
	
	rowsPerPage=default_rows_perpage;
	  firstRow=0;
	  
	  MainImpl main = new MainImpl();
		 if( main.getMedName1()!= null) {
			 main.setMedName1("");
		 }
	  
		 
		
		 MainImpl.orderbyMedIdTot = "sort";
		 MainImpl.orderbyMedNameTot = "sort";
		 MainImpl.orderbyTypeTot ="sort";
		 MainImpl.orderbyQuantityTot = "sort";
		 
		
		
		 
		 
		 MainImpl.orderbyMedIdTotDes = "sort";
		 MainImpl.orderbyMedNameTotDes = "sort";
		 MainImpl.orderbyTypeTotDes ="sort";
		 MainImpl.orderbyQuantityTotDes = "sort";
		
	 
}
}
