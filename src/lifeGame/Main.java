package lifeGame;

import service.GameService;
import data.CellArray;
import data.CellState;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends JFrame {
	private JButton[][] btns;
	private JPanel p;
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private Container c;
	private int row = 40;
	private int col = 76;
	private CellArray cells;
	private CellArray cells1;
	private int generation = 0;
//窗口的尺寸、位置等属性的初始化
	public Main(String titile) {
		super(titile);

		setLocation(0, 0);
		setSize(1920, 1080);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();

	}
//界面参数的初始化
	public void initGUI() {
		p = new JPanel();
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p.setLayout(new BorderLayout(0, 0));
		p1.setLayout(new GridLayout(row, col, 0, 0));

		JLabel label = new JLabel("细胞组行数");
		JTextField textField = new JTextField(2);

		JLabel label1 = new JLabel("细胞组列数");
		JTextField textField1 = new JTextField(2);

		JButton b = new JButton("确定");

		final JLabel label2 = new JLabel("繁衍代数：0");
		p2.add(label);
		p2.add(textField);
		p2.add(label1);
		p2.add(textField1);
		p2.add(b);
		p3.add(label2);
		// p.add(p2,BorderLayout.NORTH);
		p.add(p3, BorderLayout.SOUTH);

		c.add(p, BorderLayout.NORTH);
		c.add(p1, BorderLayout.CENTER);

		// b.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent arg0) {
		// String rows = textField.getText();
		// String cols = textField1.getText();
		// row=Integer.parseInt(rows);
		// col=Integer.parseInt(cols);

		btns = new JButton[row][col];
//游戏细胞初始化
		cells = GameService.initMap(row, col);
		//繁衍代数显示
		generation = 1;
		label2.setText("繁衍代数：" + generation);
		//当细胞为生时设置颜色为黑色，为死时设置颜色为白色
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++) {
				btns[i][j] = new JButton();
				btns[i][j].setMargin(new Insets(0, 0, 0, 0));
				if (cells.getCell(i, j) == CellState.LIVE.getValue()) {

					btns[i][j].setBackground(Color.black);
					p1.add(btns[i][j]);

				} else {
					btns[i][j].setBackground(Color.white);
					p1.add(btns[i][j]);
				}
			}
//设置游戏回合时间
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			//游戏每一回合的运行函数
			public void run() {
				//将本回合细胞状态赋给cells
				cells = GameService.generate(cells);
				//繁衍代数每回合增加
				++generation;
				//繁衍代数跟随回合显示
				label2.setText("繁衍代数：" + generation);
				//当细胞为生时设置颜色为黑色，为死时设置颜色为白色
				for (int i = 0; i < row; i++) {
					for (int j = 0; j < col; j++) {
						if (cells.getCell(i, j) == CellState.LIVE.getValue()) {
							btns[i][j].setBackground(Color.black);

						} else {
							btns[i][j].setBackground(Color.white);
						}
					}
				}
			}
		}, 0, 500);
		setVisible(true);
	}

	// });
	//
	//

	public void initMap(JButton btns[][]) {

	}

	public void generate(JButton btns[][]) {

	}

	public static void main(String args[]) {
		Main main = new Main("生命游戏");
		main.initGUI();
	}

}