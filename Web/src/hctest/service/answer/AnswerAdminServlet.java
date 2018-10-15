package hctest.service.answer;

import hctest.base.LoginBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AnswerAdminServlet",urlPatterns = "/answer/admin")
public class AnswerAdminServlet extends LoginBaseServlet {
}
