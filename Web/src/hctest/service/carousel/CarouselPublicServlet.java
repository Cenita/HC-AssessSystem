package hctest.service.carousel;

import hctest.Dao.CarouselDao;
import hctest.base.PostBaseServlet;
import hctest.domain.Carousel;
import hctest.util.Config;
import hctest.util.Status;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CarouselPublicServlet",urlPatterns = "/carousel")
public class CarouselPublicServlet extends PostBaseServlet {
    public void getCarouselList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        JSONObject jo = (JSONObject)request.getAttribute("jo");

        List<Carousel> carouselList = CarouselDao.getListOpenCarousel();

        JSONObject list = new JSONObject();
        int i=0;
        for(Carousel ca : carouselList)
        {
            list.put(String.valueOf(i++),ca.toJson());
        }
        jo.put("carouselList",list);
        jo.put("size",carouselList.size());
        jo.put(Config.Status, Status.Succeed);
        jo.put(Config.Message,"获取成功");
    }
}
