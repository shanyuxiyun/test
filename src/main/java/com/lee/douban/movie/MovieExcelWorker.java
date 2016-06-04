package com.lee.douban.movie;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lee.datamapper.DataMapper;
import com.lee.excel.AbstractExcelWorker;

public class MovieExcelWorker extends AbstractExcelWorker<Movie> {

	private DataMapper<Element, Movie> dataMapper;

	public void setDataMapper(DataMapper<Element, Movie> dataMapper) {
		this.dataMapper = dataMapper;
	}

	public void saveElement(String sheetName, Element e) {
		this.save(sheetName, this.dataMapper.mapper(e));
	}

	public void saveElements(String sheetName, Elements elements) {
		this.batchSave(sheetName, this.dataMapper.mapper(elements));
	}

	@Override
	protected void objectToRow(Movie m, HSSFRow row) {
		// the same to the beans.xml
		row.createCell(0).setCellValue(m.getName());
		row.createCell(1).setCellValue(m.getRating());
		row.createCell(2).setCellValue(m.getActors());
		row.createCell(3).setCellValue(m.getNameAliases());
		row.createCell(4).setCellValue(m.getLogoUrl());
		row.createCell(5).setCellValue(m.getStartDate());
	}

}
