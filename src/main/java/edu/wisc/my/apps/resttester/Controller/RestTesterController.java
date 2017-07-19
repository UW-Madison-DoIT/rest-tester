package edu.wisc.my.apps.resttester.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@Controller
public class RestTesterController{
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private Environment env;
    private ResourceLoader resourceLoader;

    @Autowired
    public void setEnv(Environment env) { this.env = env; }

    @RequestMapping(value="/")
    public @ResponseBody void getJson(HttpServletRequest request, HttpServletResponse response){
    StringBuilder result = new StringBuilder("");
    try {
          String linkLocation = env.getProperty("link");
          logger.error(linkLocation);

	//Get file from resources folder
	ClassLoader classLoader = getClass().getClassLoader();
	if(classLoader==null){
        logger.error("No class loader");
    }

    if(classLoader.getResource(linkLocation)==null){
        logger.error("Cannot get resource at " + linkLocation);
    }

    if(classLoader.getResource(linkLocation).getFile()==null){
        logger.error("Screwy step 3");
    }
    File file = new File(classLoader.getResource(linkLocation).getFile());

	try (Scanner scanner = new Scanner(file)) {

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			result.append(line).append("\n");
		}

		scanner.close();

	} catch (IOException e) {
		e.printStackTrace();
	}
          String links = result.toString();
          
          JSONObject obj = new JSONObject(links);
          JSONObject responseObj = new JSONObject();
          responseObj.put("status", "up");
          response.getWriter().write(responseObj.toString());
          response.setContentType("application/json");
          response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            logger.error("Issues happened while trying to write Status", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}