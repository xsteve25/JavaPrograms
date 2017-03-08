/*
Programmer Name: Steven Nak
Date: November 24, 2016
Description: Demonstrating OOP through a University Application with various of GUI interfaces such as buttons, lists, text boxes... 
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



@SuppressWarnings("serial")
public class ApplicationCenter extends JApplet implements ActionListener{

private  String bName[]={"Input", "DisplayAll", "DisplayOne"};
private JButton bButton[];
private CardLayout cardStack;
private JPanel cardDeck;
private String[] universities={"Toronto", "York", "Brock", "Guelph", "Waterloo", "McGill", "Concordia", "Laval" , "Macmaster", "Western"};
private String[] university={"Toronto", "90" , "York", "84", "Brock", "75", "Guelph", "76", "Waterloo", "88", "McGill", "90",  "Concordia", "76", "Laval" , "78",  "Macmaster", "82", "Western", "82"};
private JList list1;
final int MAX=100;
private  Student[] std = new Student[MAX];

private int stcount=0;


	public void init(){
		Container c =  getContentPane();
		 
		// create the JPanel with CardLayout
		cardDeck=new JPanel();
		cardStack = new CardLayout();
		cardDeck.setLayout(cardStack);
		
		
		// add cards to JPanel cardDeck
		cardDeck.add(inputpanel(),"Input");
		cardDeck.add(DisplayAllPanel(),"DisplayAll");
		cardDeck.add(DisplayOnePanel(),"DisplayOne");
		JPanel buttons = new JPanel();
		buttons.setLayout( new GridLayout(3,1));
		bButton=new JButton[bName.length];
	      
				for(int x=0;x<bButton.length;x++) {
					bButton[x]=new JButton(bName[x]);
					bButton[x].addActionListener(this);
					buttons.add(bButton[x]);
			}
				std=new Student[100];
				c.add(buttons,BorderLayout.WEST);
				c.add(cardDeck,BorderLayout.EAST);
	    

	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() ==bButton[0] ){
			 cardStack.show(cardDeck,"Input");	 
		}else if(e.getSource()==bButton[1]){
			 cardStack.show(cardDeck,"DisplayAll");
			 }else if(e.getSource()==bButton[2]){
				 cardStack.show(cardDeck,"DisplayOne");
				 
		}
	}
	

	
	@SuppressWarnings("unchecked")
	public JPanel inputpanel(){
	
		JLabel l1 = new JLabel("Enter students name: ");
		JLabel l2 = new JLabel("Enter Mark: ");
		final JTextField t2 = new JTextField(10);
		t2.setEditable(true);
		t2.addActionListener(this);
		final JTextField t1 = new JTextField(10);
		t1.addActionListener(this);
		t1.setEditable(true);
		JPanel p1 = new JPanel(new FlowLayout());
		p1.add(l1);
		p1.add(t1);
		p1.add(l2);
		p1.add(t2);
		
		p1.setBorder(BorderFactory.createTitledBorder("Student Name and Mark: "));
		
		//list panel
		JPanel p2 = new JPanel(new BorderLayout());
	
		list1 = new JList(universities);
		list1.setVisibleRowCount(5);
		list1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		
		
		
		p2.add(new JScrollPane(list1));
		p2.setBorder(BorderFactory.createTitledBorder("Pick Three Universites: "));
		
		//label panels
		JPanel p4 = new JPanel(new BorderLayout());
		final JLabel label1 = new JLabel();
		p4.add(label1);
		
		//button panels
		JPanel p3 = new JPanel(new FlowLayout());
		
		final JButton submit =new JButton("Submit");
		submit.setPreferredSize(new Dimension(90,60));
		submit.addActionListener(new ActionListener(){

			
			public void actionPerformed(ActionEvent e) {
				
				
				String name = t1.getText();
				String mark = t2.getText();
				int x2 = Integer.parseInt(mark);
				
				Object[]control=new Object[3];
				String []choice= new String[3];
				control = list1.getSelectedValues();
				for(int x=0;x<3;x++){
					choice[x]=(String)control[x];
					
				}
				std[stcount++] = new Student(name,x2,choice[0],choice[1],choice[2]);
				t1.setText("");
				t2.setText("");
				list1.clearSelection();
				label1.setText("Number of Students: "+ stcount);
				
				
			}
			
			
		}
		);
		
		p3.add(submit);
		
		JPanel input=new JPanel();
		input.setLayout(new GridLayout(4,1));
		input.add(p1);
		input.add(p2,BorderLayout.EAST);
		input.add(p4,BorderLayout.CENTER);
		input.add(p3,BorderLayout.SOUTH);
	
		input.setBorder(BorderFactory.createTitledBorder("Input: "));
		
		return input;
		
	}
	
	 public JPanel DisplayAllPanel(){
         
         
         JPanel displayP = new JPanel(new BorderLayout());
         JPanel pt = new JPanel(new BorderLayout());
        final JTextArea outputArea = new JTextArea();
         outputArea.setBorder(BorderFactory.createTitledBorder("Record of All students: "));
         outputArea.setFont(new Font("Serif",Font.ITALIC, 12));
         pt.add(outputArea);
         JPanel p1 = new JPanel(new FlowLayout());
         JButton displayAll = new JButton("Display All");
         displayAll.addActionListener(new ActionListener(){
         
        public void actionPerformed(ActionEvent e){
         String output = "";
    
         
          for (int counter = 0; counter < stcount; counter++) {
        	  bubbleSort(std);
        	  output +=std[counter].toString();
        	  outputArea.setText( output );  

                 }         
        }
         }
         ); 
         
         p1.add(displayAll);
         displayP.add(p1,BorderLayout.SOUTH);
         displayP.add(pt);
       
         
         return displayP;
 }
	
	public JPanel DisplayOnePanel(){
		
		JPanel d1=new JPanel(new BorderLayout());
		d1.setBorder(BorderFactory.createTitledBorder("DisplayOne: "));
		
		JPanel p1 = new JPanel(new FlowLayout());
		JLabel lb = new JLabel("Enter student's name");
		p1.add(lb);
		final JTextField t1 = new JTextField(10);
		p1.add(t1);
		
		
		
		JPanel tp = new JPanel(new BorderLayout());
		final JTextArea tArea = new JTextArea();
		tArea.setFont(new Font("Arial", Font.PLAIN, 14));
		
		tp.add(tArea);
		
		JPanel bp = new JPanel(new FlowLayout());
		JButton display = new JButton("Display");
		display.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				for(int x=0;x<stcount;x++)
					if((t1.getText()).equals(std[x].getName()))
					tArea.setText("Student not found");
				for(int y=0;y<stcount;y++){
				if((t1.getText()).equals(std[y].getName())){
					tArea.setText(std[y].toString());
					t1.setText("");
				}
				
			}
		}	
		});
		bp.add(display);
		d1.add(bp,BorderLayout.SOUTH);
		d1.add(p1,BorderLayout.NORTH);
		d1.add(tp,BorderLayout.CENTER);
		
		return d1;
	}

	 public void bubbleSort(Student array2[]){   
	      for (int pass = 1; pass<stcount; pass++) { 
	         for (int x=0; x<stcount - 1; x++) {
	            if (array2[x].getName().charAt(0) > array2[x+1].getName().charAt(0)) 
	               swap(array2, x, x+1);
	         }  
	      }     
	    }
	
	
	
	public void swap (Student array[], int first, int second ) {
		      Student hold;  
		      hold = array[first];         
		      array[first] = array[second];  
		      array[second] = hold;
	}
	
	
		 
	class Student{
		   private String name;
		   private int mark;
		   private String uni1,uni2,uni3;
		  
		  
		   public Student(String aName,int aMark,String univ1,String univ2,String univ3){
			  name=aName;
			  mark=aMark;
			  uni1=univ1;
			  uni2=univ2;
			  uni3=univ3;
			 
			   
		   }
		   //method using string to compare universities with marks.
		   public String compare(String uni1){
			   String yes = "";
			   String no = "";
			   for(int x=0; x<university.length; x++){
				   if(university[x]==uni1){
					   if(mark >= Integer.parseInt(university[x+1])){
						  yes += "accepted";
						  return yes;
					   }else
						no += "rejected";
					  
				   }
			   }
			return no;
		   }
		   
		   public double getMark() {  
		       return mark;
		   }
		   public String getName() {
		       return name;
		   }
		   public String getuni1(){
			   return uni1;
		   }
		   public String getuni2(){
			   return uni2;
		   }
		   public String getuni3(){
			   return uni3;
		   }
		   
		   
		   public String toString() {
            String result = name + "," + " average=" + mark + "," + " " + getuni1() + "-" + " " + compare(uni1) + "," +  " " + getuni2() + "-" + compare(uni2) + "," +" "+  getuni3() + "-" + compare(uni3) + "\n";
			   return result;
		   }		   
		   
	} 
		
}


