package com.music.musicstore.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.music.musicstore.dao.PlayListDAO;
import com.music.musicstore.dao.SubscriberDAO;
import com.music.musicstore.dao.TrackDAO;
import com.music.musicstore.pojo.PlayList;
import com.music.musicstore.pojo.Subscriber;
import com.music.musicstore.pojo.Track;
import com.music.musicstore.pojo.User;

@Controller
public class SubscriberController {

	@RequestMapping(value = "/search", method = RequestMethod.GET)

	public String searchTrack() {
		return "search";
	}

	@RequestMapping(value = "playlist", method = RequestMethod.GET)
	public String playlist(HttpServletRequest request, PlayListDAO playListDAO) {
		request.setAttribute("addType", "VIEW_PLAYLIST");
		List<PlayList> playlists = playListDAO
				.getPlaylists(((User) request.getSession().getAttribute("user")).getEmail());
		if (playlists == null) {
			playlists = new ArrayList<PlayList>();
		}
		request.setAttribute("playlists", playlists);
		return "playlist";
	}

	@RequestMapping(value = "/addNewPlaylist", method = RequestMethod.GET)
	public String addPlaylist(HttpServletRequest request) {
		request.setAttribute("addType", "PLAYLIST");
		return "playlist";
	}

	@RequestMapping(value = "/addNewPlaylist", method = RequestMethod.POST)
	public String addNewPlaylist(HttpServletRequest request, PlayListDAO playlistdao) {
		// User user = (User) request.getSession().getAttribute("user");
		PlayList playlist = new PlayList();
		Subscriber subscriber = (Subscriber) request.getSession().getAttribute("user");

		boolean present = false;
		for (PlayList p : subscriber.getPlayList()) {
			if (p.getPlayListName().equalsIgnoreCase(request.getParameter("playlistName"))) {
				present = true;
				break;
			}
		}
		if (!present) {
			playlist.setPlayListName(request.getParameter("playlistName"));
			playlist.setSubscriber(subscriber);
			subscriber.getPlayList().add(playlist);
			playlistdao.addPlaylist(playlist, subscriber);
			return "redirect:./playlist";
		} else {
			request.setAttribute("errormessage", "Playlist with same name already present!!");
			request.setAttribute("addType", "PLAYLIST");
			return "playlist";
		}
	}

	@RequestMapping(value = "/deletePlaylist", method = RequestMethod.GET)
	public String deletePlaylist(HttpServletRequest request, PlayListDAO playlistdao) {
		request.setAttribute("addType", "VIEW_PLAYLIST");
		int id = Integer.parseInt(request.getParameter("playlistid"));
		int count = playlistdao.deletePlaylist(id);
		return "playlist";
	}

	@RequestMapping(value = "/accountsettings", method = RequestMethod.GET)
	public String accountSettings() {
		return "accountsettings";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/playlisttracks")
	public String requestActions(PlayListDAO playlistdao, HttpServletRequest request) {
		System.out.println("qwergbhjknsm");
		String playlistid = request.getParameter("playlistid");
		Set<Track> tracks = playlistdao.playlisttracks(playlistid);
		request.setAttribute("tracks", tracks);
		System.out.println(playlistid);
		request.setAttribute("playlistid", playlistid);
		return "viewtracks";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/addtrackstoplaylist")
	public String addtrackstoplaylist(TrackDAO trackdao, HttpServletRequest request, ModelMap map,
			SubscriberDAO subscriberdao) {
		System.out.println(request.getParameter("trackid"));

		Track track = trackdao.getTrack(Integer.parseInt(request.getParameter("trackid")));
		System.out.println(track.getTitle());
		map.put("track", track);

		Subscriber subscriber = subscriberdao
				.getSubscriber(((User) request.getSession().getAttribute("user")).getEmail());
		map.put("playlists", subscriber.getPlayList());

		return "addtrackstoplaylist";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/addToPlylist")
	public String addtoplaylist(TrackDAO trackdao, HttpServletRequest request, ModelMap map, PlayListDAO playListDAO) {
		System.out.println("qwergbhjknsm");

		Track track = trackdao.getTrack(Integer.parseInt(request.getParameter("trackid")));
		PlayList playList = playListDAO.getplaylist(Integer.parseInt(request.getParameter("playlist")));

		if (track != null && playList != null) {
			playList.getTracks().add(track);
		}
		playListDAO.updatePlayList(playList);
		return "redirect:./playlist";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/deleteTrackFromPlaylist")
	public String deleteTrackFromPlaylist(TrackDAO trackdao, HttpServletRequest request, ModelMap map,
			PlayListDAO playListDAO) {
		System.out.println(request.getParameter("playlistid"));

		Track track = trackdao.getTrack(Integer.parseInt(request.getParameter("trackid")));
		PlayList playList = playListDAO.getplaylist(Integer.parseInt(request.getParameter("playlistid")));

		if (track != null && playList != null) {
			playList.getTracks().remove(track);
		}
		playListDAO.updatePlayList(playList);
		return "redirect:./playlist";
	}

}
