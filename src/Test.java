import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Test extends JFrame{
	JTextField field;
	public Test instance;
	public Test()
	{
		
		instance=this;
		setSize(500, 300);
		
		JLabel label=new JLabel("Test Label");
		field =new JTextField();
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(label,BorderLayout.SOUTH);
		getContentPane().add(field,BorderLayout.NORTH);
		field.addMouseListener(new MouseAdapter() {
		
		
			
		
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				int value =Integer.valueOf(field.getText());
				if (value<10||value>100) {
					JOptionPane.showMessageDialog(instance, "error value");
				}
			}
			
		});
		
		
		
		
	}
	
	public static void main(String[] args) {
		Test test=new Test();
		//test.setVisible(true);
		byte[] byteArray=test.toBytes(-1);
		System.out.println(byteArray);
	}
	
	public byte[] toBytes(int v)
	  {
	    byte[] byteValue = new byte[4];

	    int i = byteValue.length;

	    while (v != 0)
	    {
	      byteValue[(--i)] = ((byte)(v & 0xFF));

	      v >>>= 8;
	      
	    }

	    return byteValue;
	  }
}
