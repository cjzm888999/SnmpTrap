package com.bdcom.porttrap.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;

import com.bdcom.porttrap.TrapSend;

import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SendTrapFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_ip;
	private JTextField textField_interval;
	private JTextField textField_port;
	private JPanel panel_1;
	private JButton btnSend;
	private JButton btnStop;
	private TrapSend trapSend=null;
	private JLabel statusLabel;


	/**
	 * Create the frame.
	 */
	public SendTrapFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 100));
		
		JLabel lblNewLabel = new JLabel("目的IP");
		panel.add(lblNewLabel);
		
		textField_ip = new JTextField();
		panel.add(textField_ip);
		textField_ip.setPreferredSize(new Dimension(100, 25));
		
		JLabel lblNewLabel_1 = new JLabel("间隔(ms)");
		panel.add(lblNewLabel_1);
		
		textField_interval = new JTextField();
		textField_interval.setText("100");
		panel.add(textField_interval);
		textField_interval.setPreferredSize(new Dimension(80, 25));
		
		JLabel lblNewLabel_2 = new JLabel("端口");
		panel.add(lblNewLabel_2);
		
		textField_port = new JTextField();
		textField_port.setText("162");
		panel.add(textField_port);
		textField_port.setPreferredSize(new Dimension(50, 25));
		
		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setPreferredSize(new Dimension(0, 40));
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		btnSend = new JButton("发送");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ip=textField_ip.getText();
				int port=Integer.valueOf(textField_port.getText());
				long interval=Long.valueOf(textField_interval.getText());
				trapSend=new TrapSend(ip, port, interval);
				new Thread(trapSend).start();
				statusLabel.setText("发送中......");
			}
		});
		panel_1.add(btnSend);
		
		btnStop = new JButton("停止");
		panel_1.add(btnStop);
		btnStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (null!=trapSend) {
					trapSend.stop();
					statusLabel.setText("停止");
				}
				
			}
		});
		JPanel statusPanel=new JPanel();
		statusPanel.setPreferredSize(new Dimension(0,30));
		statusPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		statusLabel=new JLabel();
		statusPanel.add(statusLabel);
		contentPane.add(statusPanel, BorderLayout.SOUTH);
		setTitle("虚拟告警发送测试");
		Toolkit tk=Toolkit.getDefaultToolkit();
		Image image=tk.createImage("images/bd32x32.gif"); 
		setIconImage(image);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static void main(String[] args) {
		SendTrapFrame frame=new SendTrapFrame();
		frame.setVisible(true);
	}

}
