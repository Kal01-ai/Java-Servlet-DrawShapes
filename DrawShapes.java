package servlet.pbl1;

import java.awt.Color;
import java.awt.Graphics2D;
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
	
	int x = 490;
	int y = 490;

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
        		+ "      </form>\r\n"
        		+ "   </body>\r\n"
        		+ "</html>"); 
        
        sos.println(docType +
   	         "<html>\n" +
   	            "<head><title>" + title + "</title></head>\n" +
   	            "<body bgcolor = \"#f0f0f0\">\n" +
   	               "<h1 align = \"center\">" + title + "</h1>\n" +
   	               "<ul>\n" +
   	                  "  <li><b>Color</b>: Value available (Case Sensitive) = Red, Blue or "
   	                  + request.getParameter("shape_color") + "\n" +
   	                  "  <li><b>Shape</b>: Value available = Any integer (Limit is less than or equal to 1000 only) or "
   	                  + request.getParameter("shape_number") + "\n" +
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
        
        String shapeselected = request.getParameter( "btn" );
        if ( shapeselected.equals( "Circle" ) && i <= 1000)
        {
        	if(colorSelected.equals("Blue"))
        	{
        		g.setColor(Color.BLUE);
        	} else if(colorSelected.equals("Red"))
        	{
        		g.setColor(Color.RED);
        	}
        	for(int j = 0; j < i; j++) {
        	g.fillOval( random.nextInt(x), random.nextInt(y), 10, 10 ); }
            // g.dispose();
            ImageIO.write( bi, "jpeg", sos );
        	
        }
        else if ( shapeselected.equals( "Square" ) && i <= 1000)
        {
        	if(colorSelected.equals("Blue"))
        	{
        		g.setColor(Color.BLUE);
        	} else if(colorSelected.equals("Red"))
        	{
        		g.setColor(Color.RED);
        	}
        	for(int j = 0; j < i; j++) {
            g.fillRect( random.nextInt(x), random.nextInt(y), 10, 10 ); }
            ImageIO.write( bi, "jpeg", sos );
        }
    }
}
