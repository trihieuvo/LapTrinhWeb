package com.baitap02.login.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import com.baitap02.login.util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/image")
public class DownloadImageController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = req.getParameter("fname");

		// 1. Kiểm tra xem filename có hợp lệ không
		if (fileName == null || fileName.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing file name.");
			return;
		}

		// 2. Lấy đường dẫn thực tế của thư mục uploads
		String realPath = req.getServletContext().getRealPath("/" + Constant.UPLOAD_DIR);
		File file = new File(realPath + File.separator + fileName);

		// 3. Kiểm tra file có tồn tại không
		if (file.exists() && file.isFile()) {
			// 4. Tự động xác định Content-Type (image/jpeg, image/png, ...)
			String mimeType = getServletContext().getMimeType(file.getAbsolutePath());
			if (mimeType == null) {
				// Mặc định nếu không xác định được
				mimeType = "application/octet-stream";
			}
			resp.setContentType(mimeType);
			resp.setContentLength((int) file.length());

			// 5. Đọc file và gửi về cho trình duyệt
			try (FileInputStream in = new FileInputStream(file)) {
				IOUtils.copy(in, resp.getOutputStream());
			}
		} else {
			// 6. Nếu không tìm thấy file, trả về lỗi 404
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found.");
		}
	}
}