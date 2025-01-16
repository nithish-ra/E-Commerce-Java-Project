package x;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;




public class notepad {

    public static long getLineCount(String filePath) {
        try {
            return Files.lines(Paths.get(filePath)).count();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return 0; // Return 0 if there is an error
        }
    }
    //MAIN ----------------------------------------------------------------------------
    public static void mainfn(){
         //MAIN FRAME ---------------------------------------------------------------
         Frame frame =new Frame("NOTEPAD APP");
        
         frame.setBackground(Color.LIGHT_GRAY);
 
         Label label=new Label("WELCOME TO WISHLIST");
         frame.add(label);
         label.setBounds(35, 40, 400, 50);
         label.setFont(new Font("Serif", Font.BOLD, 25));
 
         Label label1=new Label("YOUR VIRTUAL ASSISTANT");
         label1.setBounds(70, 80, 400, 50);
         label1.setFont(new Font("Serif", Font.PLAIN, 20));
         frame.add(label1);
 
         frame.addWindowListener(new WindowAdapter() {
             public void windowClosing(WindowEvent e){
                 System.exit(0);
             }
         });
         //------------------------------------------------------------------------------
 
         //CREATE-----------------------------------------------------------------------
         Button b1=new Button("CREATE NOTE");
         b1.setBounds(50, 140, 300, 50);
         b1.setFont(new Font("Serif", Font.BOLD, 20));
         b1.setBackground(Color.WHITE);
         b1.setForeground(Color.BLACK);
 
         frame.add(b1);
         ActionListener create_press=new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 Frame createFrame=new Frame("CREATE");
                 frame.setVisible(false);
 
                 createFrame.setBackground(Color.LIGHT_GRAY);
                 Label label2=new Label("CREATE YOUR NOTES");
                 createFrame.add(label2);
                 label2.setBounds(95, 40, 220, 50);
 
                 label2.setFont(new Font("Serif", Font.BOLD, 20));
                 label2.setForeground(Color.BLACK);
         
 
                 
                 Label label5 =new Label("ADD YOUR NOTE");
                 createFrame.add(label5);
                 label5.setBounds(120, 100, 200, 50);
 
                 
                 label5.setFont(new Font("Serif", Font.PLAIN, 20));
                 
                 label5.setForeground(Color.BLACK);
 
                 
                 TextField t=new TextField("enter note");
                 createFrame.add(t);
                 
                 
 
                 t.setFont(new Font("Serif", Font.BOLD, 20));
                 t.setBackground(Color.WHITE);
                 t.setForeground(Color.BLACK);
 
                 t.setBounds(100, 170, 200, 80);
                 Button b7=new Button("DONE");
                 b7.setBounds(100, 280, 200,50);
                 b7.setFont(new Font("Serif", Font.BOLD, 20));
                 createFrame.add(b7);
                 
                 ActionListener done=new ActionListener(){
                     public void actionPerformed(ActionEvent e){
                         
                         String userData = t.getText();
                         try (BufferedWriter writer = new BufferedWriter(new FileWriter("textfile", true))) {
                             writer.write("==>"+userData+"\n");
                             
                         }
                         catch(IOException m){
                             System.out.println("error");
                         }
                         
                         t.setText("");
                         
 
                         Label label6 =new Label("YOUR NOTE IS ADDED SUCCESSFULLY");
                         label6.setFont(new Font("Serif", Font.CENTER_BASELINE, 15));
                         
                         
 
                         createFrame.add(label6);
                         label6.setBounds(60, 310, 300,100);
                         Timer timer = new Timer();
                         timer.schedule(new TimerTask() {
                             @Override
                             public void run() {
                                 label6.setVisible(false); 
                                 timer.cancel();          
                             }
                         }, 1000);
                         
 
                     }
                 };
                 b7.addActionListener(done);   
 
                     
                
 
 
                 //back start--------------------------------------
                 Button b4=new Button("BACK TO MAIN MENU");
                 b4.setBounds(50, 400, 300,50);
                 b4.setFont(new Font("Serif", Font.BOLD, 20));
                 b4.setBackground(Color.WHITE);
                 b4.setForeground(Color.BLACK);
 
                 createFrame.add(b4);
                 ActionListener back_press=new ActionListener(){
                     public void actionPerformed(ActionEvent e){
                         createFrame.setVisible(false);
                         frame.setVisible(true);
 
                     }
                 };
                 b4.addActionListener(back_press);    
                 //back end------------------------------------------
 
 
                 createFrame.setLayout(null);
                 createFrame.setSize(400,500);
                 createFrame.setVisible(true);
                 
                 createFrame.addWindowListener(new WindowAdapter() {
                     public void windowClosing(WindowEvent e){
                         System.exit(0);
                     }
                 
                 });
             }
         };
         b1.addActionListener(create_press);
         //-------------------------------------------------------------------------
 
         //DELETE------------------------------------------------------------------
 
         Button b2=new Button("DELETE NOTE");
         frame.add(b2);
 
         b2.setBounds(50, 230, 300, 50);
         b2.setFont(new Font("Serif", Font.BOLD, 20));
         b2.setBackground(Color.WHITE);
         b2.setForeground(Color.BLACK);
 
         ActionListener del_press=new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 Frame delFrame=new Frame("DELETE");
                 frame.setVisible(false);
                 
                 delFrame.setBackground(Color.LIGHT_GRAY);
                 Label label3=new Label("DELETE YOUR NOTES");
                 delFrame.add(label3);
                 label3.setBounds(130, 40, 300, 50);
 
                 label3.setBounds(95, 40, 220, 50);
 
                 label3.setFont(new Font("Serif", Font.BOLD, 20));
                 label3.setForeground(Color.BLACK);
 
                 Label label7 =new Label("ADD YOUR NOTE TO BE DELETED");
                 delFrame.add(label7);
                
                 label7.setBounds(50, 100, 300, 50);
 
                 label7.setFont(new Font("Serif", Font.PLAIN, 20));
                 label7.setForeground(Color.BLACK);
 
 
 
                 TextField d=new TextField("enter number");
                 d.setFont(new Font("Serif", Font.BOLD, 20));
                 delFrame.add(d);
                 d.setBounds(100, 160, 200, 80);
             
                 Button b8=new Button("DONE");
                 b8.setBounds(100, 270, 200,50);
                 b8.setFont(new Font("Serif",Font.BOLD,20));
                 delFrame.add(b8);
 
                 ActionListener done=new ActionListener(){
                     public void actionPerformed(ActionEvent e){

                        if(getLineCount("textfile")>=Integer.parseInt(d.getText())){
                        
                         try{
                         Label label8 =new Label("YOUR NOTE IS DELETED SUCCESSFULLY");
                         label8.setFont(new Font("Serif", Font.CENTER_BASELINE, 15));
                         delFrame.add(label8);
                         label8.setBounds(50, 310, 350,100);
                         Timer timer = new Timer();
                         timer.schedule(new TimerTask() {
                             @Override
                             public void run() {
                                 label8.setVisible(false); 
                                 timer.cancel();          
                             }
                         }, 1000);
 
                         try {
                             BufferedWriter writer_tempfile = new BufferedWriter(new FileWriter("temp",false));
                             BufferedReader reader = new BufferedReader(new FileReader("textfile"));
                             
 
                             int userData = Integer.parseInt(d.getText());
                             int c=0;
                             
                             String temp_string="";
 
 
                             
 
                             d.setText("");
 
                             String tempString="";
 
                             while((tempString=reader.readLine()) != null){
                                 c++;
                                 if(c!=userData){
                                     writer_tempfile.write(tempString);
                                     writer_tempfile.write("\n");
 
                                 }
                             }
                             writer_tempfile.close();
                             reader.close();
 
                             BufferedWriter writer = new BufferedWriter(new FileWriter("textfile",false));
                             BufferedReader reader_temp = new BufferedReader(new FileReader("temp"));
 
                             while((tempString=reader_temp.readLine()) != null){
                                 
                                 writer.write(tempString);
                                 writer.write("\n");
                                 
                             }
                             writer.close();
                             reader_temp.close();
                         
                         } 
 
                         catch(IOException b){
                             System.out.println("error");
 
                         }
                     
                     }       
             
                     catch(NumberFormatException g){
                         System.out.println("error");
                     }

                    }
                    else if(getLineCount("textfile")<Integer.parseInt(d.getText())){
                        Label label20 =new Label("YOUR NOTE NUMBER DOES NOT EXIST");
                         label20.setFont(new Font("Serif", Font.CENTER_BASELINE, 15));
                         delFrame.add(label20);
                         label20.setBounds(55, 310, 350,100);
                         Timer timer = new Timer();
                         timer.schedule(new TimerTask() {
                             @Override
                             public void run() {
                                 label20.setVisible(false); 
                                 timer.cancel();          
                             }
                         }, 1000);
 
                    }
                 
                     }
                 };
 
                 b8.addActionListener(done); 
 
                 //back start--------------------------------------
                 Button b5=new Button("BACK TO MAIN MENU");
                 b5.setBounds(50, 400, 300,50);
                 delFrame.add(b5);
                 b5.setFont(new Font("Serif", Font.BOLD, 20));
                 b5.setBackground(Color.WHITE);
                 b5.setForeground(Color.BLACK);
 
 
                 ActionListener back_press=new ActionListener(){
                     public void actionPerformed(ActionEvent e){
                         delFrame.setVisible(false);
                         frame.setVisible(true);
 
                     }
                 };
                 b5.addActionListener(back_press);
 
                 //back end--------------------------------------
 
                 delFrame.setLayout(null);
                 delFrame.setSize(400,500);
                 delFrame.setVisible(true);
 
                 delFrame.addWindowListener(new WindowAdapter() {
                     public void windowClosing(WindowEvent e){
                         System.exit(0);
                     }
                 
                 });
             }
         };
         b2.addActionListener(del_press);
         //-------------------------------------------------------------------------
 
         //VIEW ----------------------------------------------------------------------
         Button b3=new Button("VIEW NOTES");
         frame.add(b3);
         
 
       
         b3.setBounds(50, 320, 300, 50);
         b3.setFont(new Font("Serif", Font.BOLD, 20));
         b3.setBackground(Color.WHITE);
         b3.setForeground(Color.BLACK);
 
         ActionListener view_press=new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 Frame viewFrame=new Frame("VIEW");
                 frame.setVisible(false);
                 
                 viewFrame.setBackground(Color.LIGHT_GRAY);
                 Label label4=new Label("VIEW YOUR NOTES");
                 viewFrame.add(label4);
                 label4.setBounds(100, 40, 300, 50);
                 label4.setFont(new Font("Serif", Font.BOLD, 20));
                 label4.setForeground(Color.BLACK);
                 
                 try(BufferedReader reader = new BufferedReader(new FileReader("textfile"))) {
                     
                     String result="",line;
                     while ((line = reader.readLine()) != null) {
                         result=result+line+"\n"; 
                     
                     }
                 
 
                 TextArea textArea = new TextArea();
                 textArea.setFont(new Font("Serif", Font.BOLD, 20));
                 viewFrame.add(textArea);
                 textArea.setBounds(40,100, 320,250);
                 textArea.setEditable(false);
                 textArea.setText(result);
 
                 
             
                 //back start--------------------------------------
                 Button b6=new Button("BACK TO MAIN MENU");
                 b6.setBounds(50, 400, 300,50);
                 b6.setFont(new Font("Serif", Font.BOLD, 20));
                 b6.setBackground(Color.WHITE);
                 b6.setForeground(Color.BLACK);
                 viewFrame.add(b6);
                 ActionListener back_press=new ActionListener(){
                     public void actionPerformed(ActionEvent e){
                         viewFrame.setVisible(false);
                         frame.setVisible(true);
 
                     }
                 };
                 b6.addActionListener(back_press);
         
                 //back end--------------------------------------
 
                 viewFrame.setLayout(null);
                 viewFrame.setSize(400,500);
                 viewFrame.setVisible(true);
             }
             catch (IOException k) {
                 System.out.println("Error reading file");
             }
 
                 viewFrame.addWindowListener(new WindowAdapter() {
                     public void windowClosing(WindowEvent e){
                         System.exit(0);
                     }
                 
                 });
 
             }
 
         };
         
         b3.addActionListener(view_press);
 
             frame.setLayout(null);
             frame.setSize(400,500);
             frame.setVisible(true);
         
         //-------------------------------------------------------------------------
         
    }
    //-------------------------------------------------------------------------------------
}



