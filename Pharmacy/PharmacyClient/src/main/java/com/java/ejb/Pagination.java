package com.java.ejb;

import java.util.List;
import java.util.Map;

import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

public class Pagination {

	private static final long serialVersionUID = 1L;
	private List<MedicineEntry> cdList;
	private PharmacyImpl queryHelper;
	/**
	 * pagination stuff
	 */
	private int totalRows;
	private static int firstRow;
	private int rowsPerPage;
	private int totalPages;
	private int pageRange;
	private Integer[] pages;
	private int currentPage;

	/**
	 * Creates a new instance of JsfPaginationBean
	 */

	private boolean showErrorMessage;

	public boolean isShowErrorMessage() {
		return showErrorMessage;
	}

	public void setShowErrorMessage(boolean showErrorMessage) {
		this.showErrorMessage = showErrorMessage;
	}

	Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

	public Pagination() {
		queryHelper = new PharmacyImpl();
		rowsPerPage = 10; // Default rows per page (max amount of rows to be displayed at once).
		pageRange = 10; // Default page range (max amount of page links to be displayed at once).
	}

	public void sorttedByMedId() {
		sessionMap.remove("quantityerror");
		PharmacyImpl impl = new PharmacyImpl();
		List<MedicineEntry> sortedList = impl.sortByMedId(firstRow, rowsPerPage);
		setCdList(sortedList);
	}

	public void sorttedByMedIdDesc() {
		sessionMap.remove("quantityerror");
		PharmacyImpl impl = new PharmacyImpl();
		List<MedicineEntry> sortedList = impl.sortByMedIddesc(firstRow, rowsPerPage);
		setCdList(sortedList);
	}

	public void sorttedByMedName() {
		sessionMap.remove("quantityerror");
		PharmacyImpl impl = new PharmacyImpl();
		List<MedicineEntry> sortedList = impl.sortByMedName(firstRow, rowsPerPage);
		setCdList(sortedList);
	}

	public void sorttedByMedNameDesc() {
		sessionMap.remove("quantityerror");
		PharmacyImpl impl = new PharmacyImpl();
		List<MedicineEntry> sortedList = impl.sortByMedNamedesc(firstRow, rowsPerPage);
		setCdList(sortedList);
	}

	public void sorttedByEntry() {
		PharmacyImpl impl = new PharmacyImpl();
		List<MedicineEntry> sortedList = impl.sortByEntry(firstRow, rowsPerPage);
		setCdList(sortedList);
	}

	public void sorttedByEntryDesc() {
		sessionMap.remove("quantityerror");
		PharmacyImpl impl = new PharmacyImpl();
		List<MedicineEntry> sortedList = impl.sortByEntrydesc(firstRow, rowsPerPage);
		setCdList(sortedList);
	}

	public void sorttedByMfg() {
		sessionMap.remove("quantityerror");
		PharmacyImpl impl = new PharmacyImpl();
		List<MedicineEntry> sortedList = impl.sortByMfg(firstRow, rowsPerPage);
		setCdList(sortedList);
	}

	public void sorttedByMfgDesc() {
		sessionMap.remove("quantityerror");
		PharmacyImpl impl = new PharmacyImpl();
		List<MedicineEntry> sortedList = impl.sortByMfgdesc(firstRow, rowsPerPage);
		setCdList(sortedList);
	}

	public void sorttedByExp() {
		sessionMap.remove("quantityerror");
		PharmacyImpl impl = new PharmacyImpl();
		List<MedicineEntry> sortedList = impl.sortByExp(firstRow, rowsPerPage);
		setCdList(sortedList);
	}

	public void sorttedByExpDesc() {
		sessionMap.remove("quantityerror");
		PharmacyImpl impl = new PharmacyImpl();
		List<MedicineEntry> sortedList = impl.sortByExpdesc(firstRow, rowsPerPage);
		setCdList(sortedList);
	}

	public void sorttedByQuantity() {
		sessionMap.remove("quantityerror");
		PharmacyImpl impl = new PharmacyImpl();
		List<MedicineEntry> sortedList = impl.sortByQuantity(firstRow, rowsPerPage);
		setCdList(sortedList);
	}

	public void sorttedByQuantityDesc() {
		sessionMap.remove("quantityerror");
		PharmacyImpl impl = new PharmacyImpl();
		List<MedicineEntry> sortedList = impl.sortByQuantitydesc(firstRow, rowsPerPage);
		setCdList(sortedList);
	}

	public void sorttedByPrice() {
		sessionMap.remove("quantityerror");
		PharmacyImpl impl = new PharmacyImpl();
		List<MedicineEntry> sortedList = impl.sortByPrice(firstRow, rowsPerPage);
		setCdList(sortedList);
	}

	public void sorttedByPriceDesc() {
		sessionMap.remove("quantityerror");
		PharmacyImpl impl = new PharmacyImpl();
		List<MedicineEntry> sortedList = impl.sortByPricedesc(firstRow, rowsPerPage);
		setCdList(sortedList);
	}

	public List<MedicineEntry> getMedicineList(String medName) {
		if (cdList == null) {
			loadMed(medName);
		}
		return cdList;
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
		Pagination.firstRow = firstRow;
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

	private void loadMed(String medName) {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		System.out.println("First Row  " + firstRow);
		System.out.println("Count  " + rowsPerPage);
		cdList = queryHelper.getListOfMedicine(firstRow, rowsPerPage);
		System.out.println("Med Count is  " + cdList);
		// totalRows = queryHelper.countRows(medName);
		totalRows = (int) sessionMap.get("rowsCount");
		System.out.println("Total Rows Sale " + totalRows);
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
		int lastPageFirstRow = totalRows - ((totalRows % rowsPerPage != 0) ? totalRows % rowsPerPage : rowsPerPage);
		page(lastPageFirstRow);

	}

	public void page(ActionEvent event) {
		page(((Integer) ((UICommand) event.getComponent()).getValue() - 1) * rowsPerPage);
	}

	private void page(int firstRow) {
		this.firstRow = firstRow;
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		String medName = (String) sessionMap.get("medFoundForSale");
		loadMed(medName);
	}

}
