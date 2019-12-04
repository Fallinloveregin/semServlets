package admin;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import dao.ItemsDao;
import dao.impl.ItemsDaoImpl;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class AddItem extends HttpServlet {
    private Random random = new Random();
    private Item newItem = new Item();
    private String filePath = "";

    public void init() {

        filePath = getServletContext().getInitParameter("file-upload");
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, java.io.IOException {

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 1024);

        File tempDir = (File) getServletContext().getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(tempDir);

        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setSizeMax(1024 * 1024 * 10);

        try {
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();

            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (item.isFormField()) {
                    processFormField(item);
                } else {
                    processUploadedFile(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        ItemsDao impl = new ItemsDaoImpl();
        impl.add(newItem);
        request.setAttribute("Message", "Product Added!");
        getServletConfig().getServletContext().getRequestDispatcher("/addItem.ftl").forward(request, response);
    }

    private void processUploadedFile(FileItem item) throws Exception {
        File uploadedFile;
        int randName = random.nextInt();
        do {
            String path = filePath + randName + item.getName();
            newItem.setPicture(randName + item.getName());
            uploadedFile = new File(path);
        } while (uploadedFile.exists());

        uploadedFile.createNewFile();
        item.write(uploadedFile);

    }

    private void processFormField(FileItem item) {
        if (item.getFieldName().equals("name")) {
            newItem.setName(item.getString());
        }

        if (item.getFieldName().equals("description")) {
            newItem.setDescription(item.getString());
        }

        if (item.getFieldName().equals("genre")) {
            newItem.setGenre(item.getString());
        }
        if (item.getFieldName().equals("price")) {
            float price = Float.parseFloat(item.getString());

            newItem.setPrice(price);
        }
    }


    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp)
            throws ServletException, java.io.IOException {

        getServletConfig().getServletContext().getRequestDispatcher("/addItem.ftl").forward(req, resp);
    }
}
