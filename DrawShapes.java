package servlet.pbl1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class CreateShapes
 */
@WebServlet("/CreateShapes")
public class DrawShapes extends HttpServlet
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Random random = new Random();
	int xPoints[] = {9, 15, 0, 18, 3};
	int yPoints[] = {0, 18, 6, 6, 18};
	int x = 480;
	int y = 480;
	
	protected void doGet ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        response.setContentType( "text/html" );
        ServletOutputStream sos = response.getOutputStream();
        String title = "Drawing Shapes with Java Servlets";
        String docType =
           "<!doctype html public \"-//w3c//dtd html 4.0 " +
           "transitional//en\">\n";
        
        sos.println( "<html>\r\n"
        		+ "   <body>\r\n"
        		+ "      <form action = \"PickYourShapes\" method = \"post\">\r\n"
        		+ "         Choose Color: <input type = \"text\" name = \"shape_color\">\r\n"
        		+ "         <br />\r\n"
        		+ "         How many shapes: <input type = \"text\" name = \"shape_number\">\r\n"
        		+ "         <input type = \"submit\" name = \"btn\" value = \"Circle\" />\r\n"
        		+ "         <input type = \"submit\" name = \"btn\" value = \"Square\" />\r\n"
        		+ "         <input type = \"submit\" name = \"btn\" value = \"Rectangle\" />\r\n"
        	    + "         <input type = \"submit\" name = \"btn\" value = \"Star\" />\r\n"
        		+ "      </form>\r\n"
        		+ "   </body>\r\n"
        		+ "</html>"); 
        
        sos.println(docType +
   	         "<html>\n" +
   	            "<head><title>" + title + "</title></head>\n" +
   	            "<body bgcolor = \"#f0f0f0\">\n" +
   	               "<h1 align = \"center\">" + title + "</h1>\n" +
   	               "<ul>\n" +
   	                  "  <li><b>Color (Case Sensitive)</b>: Red, Green, Blue, Yellow, Orange and Cyan OR NULL (White)"
   	                  +  "\n" +
   	                  "  <li><b>Number of Shape (CANNOT BE NULL)</b>: ZERO or Any Integer (Limit is less than or equal to 1000 only)"
   	                  +  "\n" +
   	               "</ul>\n" +
   	            "</body>"+
   	         "</html>"
   	      );
    }

    protected void doPost ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {	
        response.setContentType( "image/jpeg" );
        ServletOutputStream sos = response.getOutputStream();

        BufferedImage bi = new BufferedImage( 500, 500, BufferedImage.TYPE_INT_RGB );
        Graphics2D g = bi.createGraphics();
        // g.setBackground(Color.WHITE);
        // g.clearRect(0, 0, 300, 300);
        
        String numberOfShapes = request.getParameter("shape_number");
        int amount = Integer.parseInt(numberOfShapes);
        int i = amount;
        		
        String colorSelected = request.getParameter("shape_color");
        switch(colorSelected) {
        case "Red":
        	g.setColor(Color.RED); break;
        case "Green":
        	g.setColor(Color.GREEN); break;
        case "Blue":
        	g.setColor(Color.BLUE); break;
        case "Yellow":
        	g.setColor(Color.YELLOW); break;
        case "Orange":
        	g.setColor(Color.ORANGE); break;
        case "Cyan":
        	g.setColor(Color.CYAN); break;
        }
        
        String shapeSelected = request.getParameter( "btn" );
        if(shapeSelected.equals("Circle") && i <= 1000)
        {
        	for(int j = 0; j < i; j++) {
        	g.fillOval(random.nextInt(x), random.nextInt(y), 10, 10); }
            ImageIO.write(bi, "jpeg", sos);
        }
        else if(shapeSelected.equals("Square") && i <= 1000)
        {
        	for(int j = 0; j < i; j++) {
            g.fillRect(random.nextInt(x), random.nextInt(y), 10, 10); }
            ImageIO.write(bi, "jpeg", sos);
        }
        else if(shapeSelected.equals("Rectangle") && i <= 1000)
        {
        	for(int j = 0; j < i; j++) {
        	g.fillRect(random.nextInt(x), random.nextInt(y), 20, 10); }
        	ImageIO.write(bi, "jpeg", sos);
        }
        else if(shapeSelected.equals("Star") && i <= 1000)
        {	
    		GeneralPath star = new GeneralPath();
    		for(int j = 0; j < i; j++) {
    		int maxX = random.nextInt(x);
    		int maxY = random.nextInt(y);
    		star.moveTo(xPoints[0] + maxX, yPoints[0] + maxY);
    		
    		for(int iStar = 1; iStar < xPoints.length; iStar++) {
    		star.lineTo(xPoints[iStar] + maxX, yPoints[iStar] + maxY); }
    		star.closePath();
    		
    		g.fill(star); }
    		ImageIO.write(bi, "jpeg", sos);
        }
    }
}
