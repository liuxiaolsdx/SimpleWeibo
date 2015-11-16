package com.weibo.web;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.weibo.model.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UserInfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9119172205604783075L;

	private Configuration cfg;

	public void init() throws ServletException {
		cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setServletContextForTemplateLoading(getServletContext(), "./");
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String account = null;
		// UserInfo userinfo = new UserInfo();
		HttpSession session = request.getSession();
		if (request.getParameter("account") != null) {
			account = request.getParameter("account");
		} else {
			account = (String) session.getAttribute("s_account");
		}
		// delete origin img
		UserInfoDao userdao = new UserInfoDao();
		UserInfo userinfo = userdao.getUserInfoByAccount(account);
		File file = new File(this.getServletContext().getRealPath("/"), userinfo.getU_img());
		if (file.isFile() && file.exists())
			file.delete();

		String u_img = null;
		try {
			u_img = getImgAndSetUerinfo(request, userinfo);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		userinfo.setU_img(u_img);

		Writer out = response.getWriter();
		Map<String, Object> root = new HashMap<>();
		root.put("account", account);
		boolean flag = false;
		if (userdao.updateUserInfo(account, userinfo)) {
			flag = true;
			root.put("SetRs", flag);
		} else {
			root.put("SetRs", flag);
		}
		Template template = cfg.getTemplate("setUserInfoRs.ftl");
		try {
			template.process(root, out);
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}

	public String getImgAndSetUerinfo(HttpServletRequest request, UserInfo userinfo) throws Exception {
		String ImgUrl = null;
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload fileload = new ServletFileUpload(factory);

		fileload.setSizeMax(4194304);
		@SuppressWarnings("unchecked")
		List<FileItem> items = fileload.parseRequest(request);
		for (FileItem item : items) {

			if (item.isFormField()) {
				switch (item.getFieldName()) {
				case "nickname":
					userinfo.setU_nickname(item.getString("utf-8"));
					break;
				case "name":
					userinfo.setU_name(item.getString("utf-8"));
					break;
				case "sex":
					userinfo.setU_sex(item.getString("utf-8"));
					break;
				case "content":
					userinfo.setU_sign(item.getString("utf-8"));
					break;

				}

			} else {
				String filename = item.getName();
				// System.out.println(filename);
				if (filename != null) {
					File file = new File(filename);
					filename = getDateTime() + "_" + file.getName();
					// System.out.println(filename);

					File filetoserver = new File(this.getServletContext().getRealPath("/face"), filename);

					// File filetoserver = new
					// File("D:\\javaworkspace\\SimpleWeibo\\WebRoot\\face",
					// filename);
					// System.out.println(this.getServletContext().getRealPath("/face"));
					item.write(filetoserver);
					// ImgUrl = request.getContextPath() + "/face/" +
					// filename.substring(filename.lastIndexOf("\\") + 1);
					// ImgUrl = "D:\\javaworkspace\\SimpleWeibo\\face\\" +
					// filename;
					ImgUrl = "face/" + filename.substring(filename.lastIndexOf("\\") + 1);
					// System.out.println(ImgUrl);
				}

			}
		}
		return ImgUrl;
	}

	public String getDateTime() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int mon = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		int mi = calendar.get(Calendar.MILLISECOND);
		return "" + year + mon + day + hour + min + sec + mi + "";

	}

}
