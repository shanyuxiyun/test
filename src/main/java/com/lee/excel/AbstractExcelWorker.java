package com.lee.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

public abstract class AbstractExcelWorker<T> {

	private String filePath;

	private Properties header;

	private ThreadLocal<HSSFWorkbook> workbook = new ThreadLocal<HSSFWorkbook>();

	private ThreadLocal<Integer> rowCounter = new ThreadLocal<Integer>();

	protected void initHeader(String sheetName) {
		HSSFSheet sheet = getSheet(sheetName);
		if (sheet != null) {
			return;
		}
		sheet = this.workbook.get().createSheet(sheetName);
		HSSFRow headerRow = sheet.createRow(0);
		HSSFCellStyle style = this.workbook.get().createCellStyle();
		style.setAlignment(CellStyle.ALIGN_GENERAL);
		style.setFillBackgroundColor(new HSSFColor.BLUE_GREY().getIndex());
		HSSFFont font = this.workbook.get().createFont();
		font.setBold(true);
		font.setColor(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		headerRow.setRowStyle(style);
		rowCounter.set(0);
		for (Object key : this.header.keySet()) {
			headerRow.createCell(Integer.valueOf(key.toString())).setCellValue(this.header.get(key).toString());
		}
		this.saveWorkbook(sheetName);
	}

	protected HSSFSheet getSheet(String sheetName) {
		if (workbook.get() == null) {
			workbook.set(new HSSFWorkbook());
		}
		return this.workbook.get().getSheet(sheetName);
	}

	protected abstract void objectToRow(T m, HSSFRow row);

	public synchronized void saveWorkbook(String tag) {
		try {
			File file = new File(filePath, tag + ".xls");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			this.workbook.get().write(fos);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected HSSFRow createNextRow(String sheetName) {
		HSSFSheet sheet = getSheet(sheetName);
		rowCounter.set(rowCounter.get() + 1);
		return sheet.createRow(rowCounter.get());
	}

	private void saveRow(String sheet, T t) {
		HSSFRow row = createNextRow(sheet);
		this.objectToRow(t, row);
		this.saveWorkbook(sheet);
	}

	private void saveRows(String sheet, List<T> t) {
		HSSFRow row = null;
		for (T m : t) {
			row = createNextRow(sheet);
			objectToRow(m, row);
		}
		this.saveWorkbook(sheet);
	}

	public final void save(String sheet, T t) {
		this.initHeader(sheet);
		this.saveRow(sheet, t);
	}

	public final void batchSave(String sheet, List<T> t) {
		this.initHeader(sheet);
		this.saveRows(sheet, t);
	}

	public Properties getHeader() {
		return header;
	}

	public void setHeader(Properties header) {
		this.header = header;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
