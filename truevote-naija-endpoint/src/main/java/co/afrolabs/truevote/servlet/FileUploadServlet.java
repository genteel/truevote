/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.afrolabs.truevote.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import co.afrolabs.truevote.service.StateService;
import co.afrolabs.truevote.util.ConstituencyExcelReadUtil;
import co.afrolabs.truevote.util.ManageUpload;
import co.afrolabs.truevote.util.SenatorialZoneExcelReadUtil;
import co.afrolabs.truevote.vo.ResponseObject;

import com.google.appengine.repackaged.com.google.gson.Gson;

@SuppressWarnings("serial")
public class FileUploadServlet extends HttpServlet {

	ManageUpload manageUpload = new ManageUpload();

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		try {
			/* TODO output your page here. You may use following sample code. */
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet FileUploadServlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet FileUploadServlet at "
					+ request.getContextPath() + "</h1>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		PrintWriter out = response.getWriter();
		String action = null;
		Gson gson = new Gson();
		List<?> items;
		boolean uploaded = false;
		System.out.println("step 1");
		if (isMultipart) {
			System.out.println("is multipart");
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				items = upload.parseRequest(request);
				try {
					Map<String, Object> processedData = manageUpload
							.administratorDataExtractor(items);
					for (Map.Entry<String, Object> entry : processedData
							.entrySet()) {
						String key = entry.getKey();
						Object value = entry.getValue();
						System.out.println("Key is : " + key);
						if (key.equalsIgnoreCase("action")) {
							action = (String) value;
						}
					}
					System.out.println("Action is : " + action);
					if (action != null) {
						if (action
								.equalsIgnoreCase("senatorialAndConstituencyFileupload")) {
							byte[] result = (byte[]) processedData
									.get("filesheet");
							String country = (String) processedData
									.get("country");
							if (result != null && country != null) {
								StateService service = new StateService();
								service.createConstituency(ConstituencyExcelReadUtil
										.readSheet(result));
								service.createSenatorialDistrict(SenatorialZoneExcelReadUtil
										.readSheet(result));
								uploaded = true;
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					if (action != null
							&& action
									.equalsIgnoreCase("senatorialAndConstituencyFileupload")) {
						request.setAttribute("message", "UnSuccessful");
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}

		} else {
		}
		try {
			if (uploaded) {
				ResponseObject fileUploadResponse = new ResponseObject();
				fileUploadResponse.setMessage("Successful");
				fileUploadResponse.setResponseCode("200");
				out.println(gson.toJson(fileUploadResponse));
			} else {
				ResponseObject fileUploadResponse = new ResponseObject();
				fileUploadResponse.setMessage("UnSuccessful");
				fileUploadResponse.setResponseCode("400");
				out.println(gson.toJson(fileUploadResponse));
			}
		} finally {
			out.close();
		}

	}

}
