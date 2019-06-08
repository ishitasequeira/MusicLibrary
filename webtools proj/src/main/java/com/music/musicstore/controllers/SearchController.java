package com.music.musicstore.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.music.musicstore.dao.AlbumDAO;
import com.music.musicstore.dao.TrackDAO;
import com.music.musicstore.pojo.Track;

@Controller
public class SearchController {

	@RequestMapping(value = "/searchTrack", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String searchArtist(HttpServletRequest request, TrackDAO trackdao, AlbumDAO albumdao)
			throws JsonProcessingException {
		String param = request.getParameter("search");
		List<Track> tracks = trackdao.getTracks(param);
		ObjectMapper obj = new ObjectMapper();
		return obj.writeValueAsString(tracks);
	}

}
