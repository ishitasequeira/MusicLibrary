package com.music.musicstore.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.music.musicstore.pojo.Artist;
import com.music.musicstore.pojo.Subscriber;

public class PdfReportView extends AbstractPdfView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, Object> report = (Map<String, Object>) model.get("report");

		Table tableArtist = new Table(2);
		tableArtist.addCell("Artist");
		tableArtist.addCell("Number of Albums");

		List<Artist> artists = (List<Artist>) report.get("artists");

		for (Artist artist : artists) {
			tableArtist.addCell(artist.getFname() + " " + artist.getLname());
			tableArtist.addCell(String.valueOf(artist.getAlbums().size()));
		}

		Table tableSubscriber = new Table(2);
		tableSubscriber.addCell("Subscriber");
		tableSubscriber.addCell("Number of Playlist");

		List<Subscriber> subscribers = (List<Subscriber>) report.get("subscribers");

		for (Subscriber subscriber : subscribers) {
			tableSubscriber.addCell(subscriber.getFname() + " " + subscriber.getLname());
			tableSubscriber.addCell(String.valueOf(subscriber.getPlayList().size()));
		}

		Paragraph p = new Paragraph(new Chunk("Number of Artists : " + report.get("numArtists")));

		document.add(p);
		document.add(tableArtist);
		p = new Paragraph(new Chunk(" \n "));
		p = new Paragraph(new Chunk("Number of Subscribers : " + report.get("numSubscribers")));
		document.add(p);
		document.add(tableSubscriber);
	}
}
