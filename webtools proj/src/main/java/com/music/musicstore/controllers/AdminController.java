package com.music.musicstore.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.music.musicstore.dao.AlbumDAO;
import com.music.musicstore.dao.ArtistDao;
import com.music.musicstore.dao.SubscriberDAO;
import com.music.musicstore.pojo.Album;
import com.music.musicstore.pojo.Artist;
import com.music.musicstore.pojo.Subscriber;
import com.music.musicstore.pojo.User.UserRole;

@Controller
public class AdminController {

	@RequestMapping(method = RequestMethod.GET, value = "/viewartists")
	public String viewArtists(ArtistDao artistdao, ModelMap map) {
		ArrayList<Artist> artistList = artistdao.getAllArtist();
		System.out.println(artistList.size());
		map.put("display", UserRole.ARTIST.getRole());
		map.addAttribute("userList", artistList);
		return "viewusers";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/viewsubscribers")
	public String viewSubscriber(SubscriberDAO subscriberdao, ModelMap map) {
		ArrayList<Subscriber> subscriberList = subscriberdao.getAllSubscriber();
		System.out.println(subscriberList.size());
		map.put("display", UserRole.SUBSCRIBER.getRole());
		map.addAttribute("userList", subscriberList);
		return "viewusers";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/viewalbumrequests")
	public String viewAlbumRequests(AlbumDAO albumdao, ModelMap map) {
		List<Album> albumlist = albumdao.getviewalbumrequests();
		System.out.println(albumlist.size());
		map.addAttribute("albumlist", albumlist);
		return "viewalbumrequests";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/deletesubscriber")
	public String deleteSubscribers(SubscriberDAO subscriberdao, ModelMap map, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("user"));
		int count = subscriberdao.deleteSubscribers(id);
		map.put("display", UserRole.SUBSCRIBER.getRole());
		return "viewusers";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/deleteartist")
	public String deleteArtist(ArtistDao artistdao, ModelMap map, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("user"));
		int count = artistdao.deleteartist(id);
		return "redirect:./viewartists";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/requestalbum")
	public String requestActions(AlbumDAO albumdao, HttpServletRequest request) {
		System.out.println("qwer");
		String status = request.getParameter("status");
		String albumid = request.getParameter("albumid");
		int count = albumdao.updateAlbumStatus(status, albumid);

		return "redirect:./viewalbumrequests";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/report")
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response,
			ArtistDao artistdao, SubscriberDAO subscriberDAO) throws Exception {

		Map<String, Object> report = new HashMap<String, Object>();

		long numArtists = (Long) artistdao.getNumberOfArtists().get(0);
		report.put("numArtists", numArtists);

		List<Artist> artists = artistdao.getAllArtist();
		report.put("artists", artists);

		long numSubscribers = (Long) subscriberDAO.getNumberOfSubscribers().get(0);
		report.put("numSubscribers", numSubscribers);

		List<Subscriber> subscribers = subscriberDAO.getAllSubscriber();
		report.put("subscribers", subscribers);

		// return normal view
		return new ModelAndView(new PdfReportView(), "report", report);

	}

}
