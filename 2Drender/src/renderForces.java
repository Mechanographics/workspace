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
	      g.fillPolygon(xpoints, ypoints, 4);

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