import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class GameUI extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton start, restart;
	Player main;
	JPanel game,centerCenterPanel,centerPanel,scoreBoard;
	static int gameMode,counter,total, lost, flagCounter;
	static JLabel scoreReport;
	static JLabel prompt = new JLabel("Hello");
	static JTextArea exp;
	static JLabel score = new JLabel("Score: 0");
	static JComboBox box;
	static String[] countries = Game.getCountries();
	static ArrayList<String> testing = new ArrayList(Arrays.asList(Game.getCountries()));
	static HashMap<String, String> capMap = Game.getCountryMap();
	static HashMap<String, String[]> bordMap = Game.getBordMap();
	static HashMap<String, ImageIcon> flagMap = Game.getFlagMap();
	static ArrayList<String> flagCountries = Game.getFlagsCountries();
	static ArrayList<String> bordCountries = Game.getBordCountries();
	static SmartButton c1,c2,c3,c4;
	static JPanel menu;
	
	GameUI(){
		ImageIcon icon = new ImageIcon("geo.png");
		this.setIconImage(icon.getImage());
		this.setTitle("Geography Quiz");
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(800,800));
		
		/////////////////////Menu//////////////////////////////////////////
		menu = new JPanel();
	
		menu.setLayout(new GridBagLayout());
		menu.setBorder(BorderFactory.createLineBorder(Color.black));
		GridBagConstraints c = new GridBagConstraints();
		
		JTextField title = new JTextField("Geography Quiz");
		title.setEditable(false);
		title.setBorder(null);
		title.setFont(new Font("",Font.BOLD,80));
		title.setBackground(getForeground());
		
		JPanel selection = new JPanel();
	
		
		c.insets = new Insets(0,0,100,0);
		c.gridx = 0;
		c.gridy= 0;
		menu.add(title, c);
		
		JLabel jl = new JLabel("Game Mode: ");
		selection.add(jl);
		
		String[] gameModes = {"Standard", "Survival", "Timed (Unfinished)"};
		box = new JComboBox(gameModes);
		selection.add(box);
		
		box.setBorder(BorderFactory.createLineBorder(getForeground()));
		box.addActionListener(this);
		c.insets = new Insets(0,0,0,0);
		c.gridx=0;
		c.gridy=1;
		menu.add(selection,c);
		
		exp = new JTextArea("Select a game mode.");
		exp.setBackground(getForeground());
		c.insets = new Insets(0,0,5,0);
		c.gridx=0;
		c.gridy=2;
		menu.add(exp,c);
		
		start = new JButton("Start");
		start.setFocusable(false);
		start.setPreferredSize(new Dimension(start.getPreferredSize().width*2,start.getPreferredSize().height*2));;
		start.addActionListener(this);
		
		c.gridwidth=3;
		c.gridx=0;
		c.gridy=3;
		menu.add(start,c);
		
		
		/////////////////////Game//////////////////////////////////////////
		game = new JPanel();
		game.setLayout(new BorderLayout());
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		
		
		centerCenterPanel = new JPanel();
		centerCenterPanel.setLayout(new GridBagLayout());
		centerCenterPanel.setBorder(BorderFactory.createLineBorder(getForeground()));
		c = new GridBagConstraints();
		
		prompt.setBackground(getForeground());
		prompt.setFont(new Font("",Font.BOLD,15));
		prompt.setHorizontalAlignment(SwingConstants.CENTER);
		prompt.setBorder(BorderFactory.createLineBorder(Color.black));
		prompt.setPreferredSize(new Dimension(this.getWidth(),100));
		prompt.setHorizontalTextPosition(JLabel.LEFT);
		
		
		
		
		//Buttons
		c1 = new SmartButton();
		c1.setFocusable(false);
		c1.addActionListener(this);
		c1.setPreferredSize(new Dimension(250,250));
		c.weightx = .5;
		c.weighty = .5;
		c.gridx = 0;
		c.gridy= 1;
		centerCenterPanel.add(c1,c);
		
		c2 = new SmartButton();
		c2.setFocusable(false);
		c2.setPreferredSize(new Dimension(250,250));
		c2.addActionListener(this);
		c.gridx = 1;
		c.gridy= 1;
		centerCenterPanel.add(c2,c);
		
		c3 = new SmartButton();
		c3.setFocusable(false);
		c3.setPreferredSize(new Dimension(250,250));
		c3.addActionListener(this);
		c.gridx = 0;
		c.gridy= 2;
		centerCenterPanel.add(c3,c);
		
		c4 = new SmartButton();
		c4.setFocusable(false);
		c4.setPreferredSize(new Dimension(250,250));
		c4.addActionListener(this);
		c.gridx = 1;
		c.gridy= 2;
		centerCenterPanel.add(c4,c);
		
		/////////////////////ScoreBoard///////////////////
		scoreBoard = new JPanel();
		scoreBoard.setLayout(new GridBagLayout());
		
		c = new GridBagConstraints();
		scoreReport = new JLabel();
		c.gridx=0;
		c.gridy=0;
		scoreBoard.add(scoreReport,c);
		
		restart = new JButton("Restart");
		restart.addActionListener(this);
		c.gridx=0;
		c.gridy =1;
		scoreBoard.add(restart,c);
		
		
		
		/////////////////////////////////////////////////
		
		centerPanel.add(prompt,BorderLayout.NORTH);
		centerPanel.add(centerCenterPanel, BorderLayout.CENTER);
		game.add(centerPanel, BorderLayout.CENTER);
		game.add(score, BorderLayout.NORTH);
		
		add(menu);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override 
			public void run() {
				new GameUI();
			}
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		flagCounter++;	
		if(e.getSource()==box) {
			if(box.getSelectedIndex()==0) {
				exp.setText("In Standard Mode, you will play only 10 rounds.");
			}
			if(box.getSelectedIndex()==1) {
				exp.setText("In Survival Mode, you will play until all countries are exhausted.");
			}
			if(box.getSelectedIndex()==2) {
				exp.setText("In Timed Mode, you will play until 60 seconds pass.");
			}
			gameMode = box.getSelectedIndex();
		}
		
		if(e.getSource()==start) {
			this.getContentPane().removeAll();
			this.getContentPane().add(game);
			this.repaint();
			this.revalidate();
			
			this.nextCapital();
			String name = JOptionPane.showInputDialog(this,"Before we begin, What is your name?");
			main = new Player(name);
		}
		
		if(e.getSource()==restart) {
			main.points=0;
			total=0;
			counter=0;
			lost =0;
			this.getContentPane().removeAll();
			this.add(menu);
			score.setText("Score: " + main.points + "/" + total);
			repaint();
			revalidate();
		}
		
		if(e.getSource()==c1) {
			if(c1.correct) {
				main.points++;
			} else if(!c4.correct&&prompt.getIcon()==null) {
				lost++;
			}

			if(prompt.getIcon()==null) {
				total++;
			}
			
			score.setText("Score: " + main.points + "/" + total);
			
			if(flagCounter%5==0&&lost>0) {
				lost--;
				if(flagCounter%2==0) nextBord(); 
				else nextFlag();
			}else {
				nextCapital();
			}
		}
		
		if(e.getSource()==c2) {
			if(c2.correct) {
				main.points++;
			} else if(!c4.correct&&prompt.getIcon()==null) {
				lost++;
			}

			if(prompt.getIcon()==null) {
				total++;
			}
			
			score.setText("Score: " + main.points + "/" + total);
			
			if(flagCounter%5==0&&lost>0) {
				lost--;
				if(flagCounter%2==0) nextBord(); 
				else nextFlag();
			}else {
				nextCapital();
			}
		}
		
		if(e.getSource()==c3) {
			if(c3.correct) {
				main.points++;
			} else if(!c4.correct&&prompt.getIcon()==null) {
				lost++;
			}
			
			if(prompt.getIcon()==null) {
				total++;
			}
			
			score.setText("Score: " + main.points + "/" + total);
			
			if(flagCounter%5==0&&lost>0) {
				lost--;
				if(flagCounter%2==0) nextBord(); 
				else nextFlag();
			}else {
				nextCapital();
			}
		}
		
		if(e.getSource()==c4) {
			if(c4.correct) {
				main.points++;
			} else if(!c4.correct&&prompt.getIcon()==null) {
				lost++;
			}
			
			if(prompt.getIcon()==null) {
				total++;
			}
			
			score.setText("Score: " + main.points + "/" + total);
			
			if(flagCounter%5==0&&lost>0) {
				lost--;
				if(flagCounter%2==0) nextBord(); 
				else nextFlag();
			} else {
			nextCapital();
			}
		}
		
	}
	
	public void showScoreBoard() {
		this.getContentPane().removeAll();
		this.add(scoreBoard);
		scoreReport.setText(main.name + ", you have scored " + main.points + "/" + total);
		repaint();
		revalidate();
	}
	
	public void nextCapital() {
		Random rn = new Random();
		prompt.setIcon(null);
		///////////////////////////////////////
		if(gameMode==0) {
			counter++;
			String current = testing.get(rn.nextInt(testing.size()));
			testing.remove(current);
			prompt.setText("What is the capital of " + current + "?");
			SmartButton[] buttons = {c1,c2,c3,c4};
			int ran = rn.nextInt(4);
	
			System.out.println(current + " " + capMap.get(current));
			
			
			for(int i=0;i<4; i++) {
				if(i==ran) {
					buttons[i].setText(capMap.get(current));
					buttons[i].correct = true;
				} else {
					buttons[i].setText(capMap.get(countries[rn.nextInt(countries.length)]));
					buttons[i].correct = false;
				}
			}
			
			if(counter>10) {
				showScoreBoard();
			}
			
			
		}
		///////////////////////////////////////
		else if(gameMode==1) {
			counter++;
			String current = testing.get(rn.nextInt(testing.size()));
			
			prompt.setText("What is the capital of " + current + "?");
			SmartButton[] buttons = {c1,c2,c3,c4};
			int ran = rn.nextInt(4);
			
			for(int i=0;i<4; i++) {
				if(i==ran) {
					buttons[i].setText(capMap.get(current));
					buttons[i].correct = true;
				} else {
					buttons[i].setText(capMap.get(countries[rn.nextInt(countries.length)]));
					buttons[i].correct = false;
				}
			}
			
			if(counter>195) {
				showScoreBoard();
			}
			
			testing.remove(current);
		///////////////////////////////////////////////////////////////
		} else if(gameMode==2) {
			String current = testing.get(rn.nextInt(testing.size()));
			testing.remove(current);
			prompt.setText("What is the capital of " + current + "?");
			SmartButton[] buttons = {c1,c2,c3,c4};
			int ran = rn.nextInt(4);
			
			for(int i=0;i<4; i++) {
				if(i==ran) {
					buttons[i].setText(capMap.get(countries[rn.nextInt(countries.length)]));
					buttons[i].correct = true;
				} else {
					buttons[i].setText(capMap.get(countries[rn.nextInt(countries.length)]));
					buttons[i].correct = false;
				}
			}
			
			if(counter>196) {
				//Show End Board
			}
		}
		/////////////////////////////////////////////////////////////////
	}
	
	public void nextFlag() {
		Random rn = new Random();
		
		String current = flagCountries.get(rn.nextInt(flagCountries.size()));
		prompt.setText("Whose flag is this?");
		prompt.setIcon(flagMap.get(current));
		SmartButton[] buttons = {c1,c2,c3,c4};
		int ran = rn.nextInt(4);
		
		for(int i=0;i<4; i++) {
			if(i==ran) {
				buttons[i].setText(current);
				buttons[i].correct = true;
			} else {
				buttons[i].setText(testing.get(rn.nextInt(testing.size())));
				buttons[i].correct = false;
			}
		}
		
	}
	
	public void nextBord() {
		Random rn = new Random();
		
		String current = bordCountries.get(rn.nextInt(bordCountries.size()));
		
		prompt.setText("Which country borders: "+ current);
		prompt.setIcon(null);
		
		int con = 0;
		String[] temp = new String[4];
		String[] pos = bordMap.get(current);
		
		while(con<4) {
			String x = bordCountries.get(rn.nextInt(bordCountries.size()));
			boolean err = false;
			for(String y: pos) {
				if(x.equals(y)) {
					err = true;
				}
			}
			
			if(!err) {
				temp[con] =x;
				con++;
			}
		}
		
		SmartButton[] buttons = {c1,c2,c3,c4};
		int ran = rn.nextInt(4);
		
		for(int i=0;i<4; i++) {
			if(i==ran) {
				buttons[i].setText(bordMap.get(current)[rn.nextInt(bordMap.get(current).length)]);
				buttons[i].correct = true;
			} else {
				buttons[i].setText(temp[i]);
				buttons[i].correct = false;
			}
		}
		
	}
	
}

class SmartButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean correct;
}
