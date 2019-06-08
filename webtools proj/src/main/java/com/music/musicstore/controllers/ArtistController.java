package com.music.musicstore.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.music.musicstore.dao.AlbumDAO;
import com.music.musicstore.dao.TrackDAO;
import com.music.musicstore.pojo.Album;
import com.music.musicstore.pojo.Artist;
import com.music.musicstore.pojo.Track;
import com.music.musicstore.pojo.User;

@Controller
public class ArtistController {

	@RequestMapping(value = "/viewalbums", method = RequestMethod.GET)
	public String viewalbums(HttpServletRequest request, AlbumDAO albumdao) {
		HttpSession session = request.getSession();
		User user = (Artist) session.getAttribute("user");
		Set<Album> albumsList = albumdao.getAlbumList(user.getEmail());
		System.out.println(albumsList.size());
		if (albumsList != null)
			request.setAttribute("albumsList", albumsList);

		return "viewalbums";
	}

	@RequestMapping(value = "/viewtracks", method = RequestMethod.GET)
	public String viewtracks(HttpServletRequest request, AlbumDAO albumdao) {
		return "viewtracks";
	}

	@RequestMapping(value = "/addNew", method = RequestMethod.GET)
	public String addPage(HttpServletRequest request) {
		String type = request.getParameter("type");
		request.setAttribute("addType", "ALBUM");
		return "add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, AlbumDAO albumdao) {
		if (request.getParameter("addType").equals("ALBUM")) {
			Album album = new Album();
			Artist artist = (Artist) request.getSession().getAttribute("user");
			album.setAlbumName(request.getParameter("albumName"));
			album.setArtist((Artist) request.getSession().getAttribute("user"));
			artist.getAlbums().add(album);
			albumdao.addAlbum(album, artist);
		}

		return "redirect:./homepage";
	}

	@RequestMapping(value = "/addTrack", method = RequestMethod.GET)
	public String addTrackForm(HttpServletRequest request, AlbumDAO albumdao, Track track) {
		HttpSession session = request.getSession();
		User user = (Artist) session.getAttribute("user");
		List<Album> albums = albumdao.getAlbums(user.getEmail());
		if (albums != null)
			request.setAttribute("albums", albums);
		request.setAttribute("addType", "TRACK");
		return "add";
	}

	@RequestMapping(value = "/addTrack", method = RequestMethod.POST)
	public String addTrack(HttpServletRequest request, AlbumDAO albumdao, @ModelAttribute("track") Track track,
			TrackDAO trackDAO) {
		if (request.getParameter("addType").equals("TRACK")) {
			System.out.println("Here");
			String localPath = "D:\\Web Tools\\ProjectFiles";
			String albumId = null;
			if ((albumId = request.getParameter("albumId")) != null) {
				track.setFileName(Calendar.getInstance().getTimeInMillis() + track.getMusic().getOriginalFilename());
				File file = new File(localPath, track.getFileName());
				try {
					track.getMusic().transferTo(file);
				} catch (IllegalStateException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				Album album = albumdao.getAlbum(albumId);
				album.getTracks().add(track);
				track.setAlbum(album);
				trackDAO.addTrack(track, album);
			} else {
				System.out.println("Track not uploaded");
			}
		}
		return "redirect:./homepage";
	}

	@RequestMapping(value = "/requeststatus", method = RequestMethod.GET)
	public String requeststatus(HttpServletRequest request, AlbumDAO albumdao) {
		HttpSession session = request.getSession();
		User user = (Artist) session.getAttribute("user");
		Set<Album> albumsList = albumdao.getPendingAlbumList(user.getEmail());
		System.out.println(albumsList.size());
		if (albumsList != null)
			request.setAttribute("albumsList", albumsList);
		return "requeststatus";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addNew(HttpServletRequest request) {
		return "add";

	}

	@RequestMapping(value = "/viewalbumtracks", method = RequestMethod.GET)
	public String viewalbumtracks(HttpServletRequest request, AlbumDAO albumdao) {
		int album_id = Integer.parseInt(request.getParameter("albumid"));
		List<Track> tracks = albumdao.getviewalbumtracks(album_id);
		request.setAttribute("tracks", tracks);
		return "/viewalbumtracks";
	}

}
