import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class renderForces extends Frame{

	/*
	 * 
	 */
	private static final long serialVersionUID = 5104216951709522191L;

	public renderForces(){
	      super("Java AWT Examples");
	      prepareGUI();
	   }
	
	public static LinkedList<Integer> upForces = new LinkedList<>();
    public static LinkedList<Integer> downForces = new LinkedList<>();
    public static LinkedList<Integer> leftForces = new LinkedList<>();
    public static LinkedList<Integer> rightForces = new LinkedList<>();
    public int inclination_angle = 30;
	   public static void main(String[] args){
	      renderForces  awtGraphicsDemo = new renderForces();  
	      awtGraphicsDemo.setVisible(true);
	      
	      upForces.add(5);
	      upForces.add(15);
	      downForces.add(10);
	      leftForces.add(25);
	      rightForces.add(20);
	      
	   }

	   private void prepareGUI(){
	      setSize(400,400);
	      addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      }); 
	   }

	   
	   @SuppressWarnings("null")
	@Override
	   public void paint(Graphics g) {
	      g.setColor(Color.GRAY);
	      
	      int numLeft = leftForces.size();
	      int numRight = rightForces.size();
	      int numUp = upForces.size();
	      int numDown = downForces.size();
	      
	      int xpoints[] = {175, 245, 245, 175};
	      int ypoints[] = {175, 175, 245, 245};
	      int x1 = 200;		int x2 = 200;
	      int yd1 = 245;	int yd2 = 300;
	      int yu1 = 175;	int yu2 = 120;
	      int temp_x1[] = null, temp_y1[] = null;

	      
	      if(inclination_angle == 0){
	    	  g.fillPolygon(xpoints, ypoints, 4);	    	  
	    	  x1 = 200;		x2 = 200;
	    	  yd1 = 245;	yd2 = 300;
	    	  yu1 = 175;	yu2 = 120;
	      }
	      else{
	    	  
	    	  for(int i=0; i<4; i++){
	    		  temp_x1[i] = (int) (xpoints[i] * Math.cos(inclination_angle) - ypoints[i] * Math.sin(inclination_angle));
	    		  temp_y1[i] = (int) (xpoints[i] * Math.sin(inclination_angle) + ypoints[i] * Math.cos(inclination_angle));
	    	  }
	    	  g.fillPolygon(temp_x1,temp_y1,4);
	      }
	      
	      for(int i=0; i<numDown; i++){
	    	  g.drawLine(x1+(i*10), yd1, x2+(i*10), yd2);
	      }
	      for(int i=0; i<numUp; i++){
	    	  g.drawLine(x1+(i*10), yu1, x2+(i*10), yu2);
	      }
	      for(int i=0; i<numLeft; i++){
		      g.drawLine(yd1, x1+(i*10), yd2, x2+(i*10));
	      }
	      for(int i=0; i<numRight; i++){
		      g.drawLine(yu1, x1+(i*10), yu2, x2+(i*10));
	      }
	      String msg = "";
	      Font font = new Font("Serif", Font.PLAIN, 24);
	      g.setFont(font);
	      g.drawString(msg, 50, 150);      
	   }
}