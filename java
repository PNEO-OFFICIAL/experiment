import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;

public class EmailServlet extends HttpServlet {

    private Sheets sheetsService;
    private final String SPREADSHEET_ID = "1P5KqCIDB_6G8FzeqyxSvX4NCVpY1hfiUemC3XLgIBZE";

    @Override
    public void init() throws ServletException {
        try {
            sheetsService = GoogleSheetsServiceUtil.getSheetsService();
        } catch (Exception e) {
            throw new ServletException("Google Sheets 서비스 초기화 실패", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");

        ValueRange appendBody = new ValueRange()
                .setValues(Arrays.asList(
                        Arrays.asList(email, new java.util.Date().toString())
                ));

        sheetsService.spreadsheets().values()
                .append(SPREADSHEET_ID, "Sheet1", appendBody)
                .setValueInputOption("USER_ENTERED")
                .execute();

        resp.sendRedirect("/success.html");
    }
}
