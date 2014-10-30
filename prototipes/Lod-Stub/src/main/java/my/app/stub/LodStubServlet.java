package my.app.stub;

import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by user06 on 01.09.2014.
 */
public class LodStubServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();

        //mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF8"));
            StringBuilder json = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null)
            {
                json.append(str);
            }

            System.out.println(json.toString());
        }
        catch (Exception e)
        {
        }
        finally
        {
            LodResponse lodResponse = new LodResponse();
            lodResponse.setStatus(0);
            lodResponse.setComment("comment");
            lodResponse.setLicenseRequestId(101L);

            resp.setContentType("application/json");
            mapper.writeValue(new OutputStreamWriter(resp.getOutputStream(), "UTF8"), lodResponse);
        }
    }
}
