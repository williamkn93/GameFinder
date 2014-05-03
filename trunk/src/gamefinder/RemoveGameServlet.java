package gamefinder;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;

import com.googlecode.objectify.ObjectifyService;

public class RemoveGameServlet extends HttpServlet {
	Date date = new Date();
	List <Game> games = ObjectifyService.ofy().load().type(Game.class).list();{
		for (Game game: games){
			if (game.getDate().before(date)){
				ObjectifyService.ofy().delete().entity(game);
			}
		}
	}
}