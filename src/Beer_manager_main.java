import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;



public class Beer_manager_main extends JFrame implements ActionListener {
	private String[] columnNames = {"번호","제품명","제조국","스타일","재고량","가격","입고일"};
	private Object[][] rowData = null;
	JTable table = null;
	private JScrollPane scrollPane = null;
	private Beer_manager_dao dao = null;
	ArrayList<Beer_manager_bean> lists = null;
	ArrayList<Beer_manager_bean> lists2 = null;
	Container contentPane = null;
	String[] btnTitle = { "등록/수정", "삭제", "조회", "프로그램 종료" };
	private JButton[] btn = new JButton[btnTitle.length];

	JLabel lblnum = null;
	JLabel lblname = null;
	JLabel lblnation = null;
	JLabel lblstyle = null;
	JLabel lblstock = null;
	JLabel lblprice = null;
	JLabel lblinputdate = null;

	JTextField txtNum =  null;
	JTextField txtName =  null;
	JTextField txtNation =  null;
	JTextField txtStyle =  null;
	JTextField txtStock =  null;
	JTextField txtPrice =  null;
	JTextField txtInputdate =  null;
	String[] BeerTitle = { "설탕-물", "애국-자", "달고-셔", "흑탕-물", "포도즙" ,"안-쓴거","덜-쓴거","핵-쓴거","기타"};
	JButton[] BeerBtn = new JButton[BeerTitle.length];


	JButton BeerBtn1= new JButton();
	JButton BeerBtn2= new JButton();
	JButton BeerBtn3= new JButton();
	JButton BeerBtn4= new JButton();
	JButton BeerBtn5= new JButton();
	JButton BeerBtn6= new JButton();
	JButton BeerBtn7= new JButton();
	JButton BeerBtn8= new JButton();
	JButton BeerBtn9= new JButton();
	JButton search_bt = null;
	//JTextField search_txt = null;
	public Beer_manager_main(String string) {
		super("오늘도 한캔 따라냥");

		dao = new Beer_manager_dao();
		lists = dao.getAllBeer();


		rowData = new  Object[lists.size()][7];
		//compose();
		South_Button();
		side_table();
		fillRowData();

		table = new JTable(rowData,columnNames);
		table.addMouseListener(new MouseHandler());
		//table.setSize(, 800);
		scrollPane = new JScrollPane(table);
		scrollPane.setSize(600,800);
		scrollPane.setBounds(800, 0, 600, 800);
		contentPane=getContentPane();
		//contentPane.setSize(1200, 800);
		contentPane.add(scrollPane);
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(1600,800);
		setVisible(true);		




	}

	private void getAllBeer() {
		lists = dao.getAllBeer();
		rowData = new  Object[lists.size()][7];

		fillRowData();
		table = new JTable(rowData,columnNames);
		table.addMouseListener(new MouseHandler());

		scrollPane.remove(table);
		table.revalidate();
		table.repaint();
		scrollPane.setViewportView(table);

	}


	class MouseHandler extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			System.out.println("마우스클립됨");
			int row = table.getSelectedRow();
			System.out.println("내가 선택한 행 "+ row);

			Object num = table.getValueAt(row, 0);
			Object name = table.getValueAt(row, 1);
			Object nation = table.getValueAt(row, 2);
			Object style = table.getValueAt(row, 3);
			Object stock = table.getValueAt(row, 4);
			Object price = table.getValueAt(row, 5);
			Object inputdate = table.getValueAt(row, 6);

			txtNum.setText(num.toString());
			txtName.setText(name.toString());
			txtNation.setText(nation.toString());
			txtStyle.setText(style.toString());
			txtStock.setText(stock.toString());
			txtPrice.setText(price.toString());
			txtInputdate.setText(inputdate.toString());
		}
	}

	public void fillRowData() {
		Object[] arr = lists.toArray();

		int cnt = 0;
		for(int i=0;i<arr.length;i++) {
			//System.out.println(arr[i]);

			Beer_manager_bean BMB = (Beer_manager_bean)arr[i];
			rowData[i][cnt++] = BMB.getNum(); 
			rowData[i][cnt++] = BMB.getName();
			rowData[i][cnt++] = BMB.getNation();
			rowData[i][cnt++] = BMB.getStyle();
			rowData[i][cnt++] = BMB.getStock();
			rowData[i][cnt++] = BMB.getPrice();
			rowData[i][cnt++] = BMB.getInputdate();
			cnt = 0;
		}
	}
	
	
	private void compose() {
		JFrame compose = new JFrame("설탕물");
	
		
		lblnum = new JLabel("번호");
		lblname = new JLabel("제품명");
		lblnation = new JLabel("제조국");
		lblstyle = new JLabel("스타일명");
		lblstock = new JLabel("재고량");
		lblprice = new JLabel("가격");
		lblinputdate = new JLabel("입고일");
		// 번호
		txtNum = new JTextField(15);
		txtNum.setText("0");
		txtNum.setEnabled(false);
		
		
		txtName = new JTextField(15);
		txtNation = new JTextField(15);
		txtStyle = new JTextField(15);
		txtStock = new JTextField(15);
		txtPrice = new JTextField(15);
		txtInputdate = new JTextField(15);
		
		
		lblnum.setBounds(60, 20,100,20);
		lblname.setBounds(60, 40,100,20);
		lblnation.setBounds(60, 60,100,20);
		lblstyle.setBounds(60, 80,100,20);
		lblstock.setBounds(60, 100,100,20);
		lblprice.setBounds(60, 120,100,20);
		lblinputdate.setBounds(60, 140,100,20);

		txtNum.setBounds(150, 20, 100, 20);
		txtName.setBounds(150, 40, 100, 20);
		txtNation.setBounds(150, 60, 100, 20);
		txtStyle.setBounds(150, 80, 100, 20);
		txtStock.setBounds(150, 100, 100, 20);
		txtPrice.setBounds(150, 120, 100, 20);
		txtInputdate.setBounds(150, 140, 100, 20);

		

		JPanel pSouth = new JPanel();
		pSouth.setLayout(null);
		pSouth.add(lblnum);
		pSouth.add(lblname);
		pSouth.add(lblnation);
		pSouth.add(lblstyle);
		pSouth.add(lblstock);
		pSouth.add(lblprice);
		pSouth.add(lblinputdate);

		pSouth.add(txtNum);
		pSouth.add(txtName);
		pSouth.add(txtNation);
		pSouth.add(txtStyle);
		pSouth.add(txtStock);
		pSouth.add(txtPrice);
		pSouth.add(txtInputdate);
		
	
		JPanel insert_Panel = new JPanel();
		JButton insert_button = new JButton("제품 - 등록");
		insert_Panel.add(insert_button);
		insert_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertData();
			}
		});
		JButton update_button = new JButton("정보 - 수정");
		insert_Panel.add(update_button);
		update_button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				updateData();
			}
		});
		
		insert_Panel.setLayout(new GridLayout(1, 2));
		pSouth.setLayout(null);
		compose.add(pSouth);
		compose.add("South",insert_Panel);
		
		compose.setSize(300,400);
		
		compose.setVisible(true);	
	}

	public static void main(String[] args) {
		new Beer_manager_main("오늘도 한캔 따라냥"); 


	}
	
	public void South_Button() {
		JPanel bSouth = new JPanel();
		bSouth.setLayout(new GridLayout(4, 1));

		//		button=>ActionEvent=>ActionListener
		for(int i=0;i<btnTitle.length;i++) {
			btn[i] = new JButton(btnTitle[i]);
			btn[i].addActionListener(this); // new ActionEvent()
			bSouth.add(btn[i]);
		}
		//bSouth.setPreferredSize(new Dimension(750, 150));
		contentPane=getContentPane();
		contentPane.add("East",bSouth);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btn[0]) {
			System.out.println("등록");
			compose();
			//insertData();
		}
//		else if(obj == btn[1]) {
//			System.out.println("수정");
//			//updateData();
//		}
		else if(obj == btn[1]) {
			System.out.println("삭제");
			deleteData();
		}
		else if(obj == btn[2]) {
			System.out.println("조회");
			search_beer();
			//selectcell();
			//newFrame();
		}
		
		else if(obj == search_bt){
			System.out.println("검색");
		}
		else {
			System.out.println("종료");
			System.exit(0);

		}
	}

	public void search_beer() {
		//getAllBeer();
		JFrame search_beer= new JFrame("조회창");
		JPanel search_pl =  new JPanel();
		
			//제조국
			JLabel Com_Nation = new JLabel("제조국 : ");
				String[] arrNation = {"제품명", "제조국", "스타일명"};
				JComboBox cbNation = new JComboBox(arrNation);
				search_pl.add(cbNation);	
				
			
					
		 //검색창
		 JLabel search_lb =  new JLabel("검색창");
		 JTextField search_txt = new JTextField(30);
		 //lists = dao.getAllBeer();
		// rowData = new  Object[lists.size()][7];
		// fillRowData();
		// JTable search_beer_table = new JTable();//(rowData,columnNames);//
		 //JScrollPane scrollPane1 = new JScrollPane(search_beer_table);
		 search_beer.add("Center",search_pl);
		// search_beer.add("North",scrollPane1);
		
		 
		 class MouseHandler10 extends MouseAdapter{
				public void mouseClicked(MouseEvent e) {
						System.out.println("검색 조회");
						getBeerFromColumn();
				}

				private void getBeerFromColumn() {
					
					
					
					lists = dao.getBeerFromColumn(search_txt.getText());
					rowData = new Object[lists.size()][7];
					System.out.println("1");
					fillRowData();
					
					JTable search_beer_table = new JTable(rowData,columnNames);
					JScrollPane	scrollPane1 = new JScrollPane(search_beer_table);
					System.out.println("2");
		
					System.out.println("3");
					//search_beer.remove(scrollPane1);
					search_beer.add("North",scrollPane1);
					System.out.println("4");
					 
					 
				}
//				public void fillRowData2() {
//					Object[] arr = lists2.toArray();
//
//					int cnt = 0;
//					for(int i=0;i<arr.length;i++) {
//						//System.out.println(arr[i]);
//
//						Beer_manager_bean BMB = (Beer_manager_bean)arr[i];
//						rowData[i][cnt++] = BMB.getNum(); 
//						rowData[i][cnt++] = BMB.getName();
//						rowData[i][cnt++] = BMB.getNation();
//						rowData[i][cnt++] = BMB.getStyle();
//						rowData[i][cnt++] = BMB.getStock();
//						rowData[i][cnt++] = BMB.getPrice();
//						rowData[i][cnt++] = BMB.getInputdate();
//						cnt = 0;
//					}
//				}

			}
		 JButton search_bt = new JButton("검색");
		 search_bt.addMouseListener(new MouseHandler10());
		
		 
		 search_lb.setBounds(30, 20,100 , 30);
		 search_txt.setBounds(150, 20, 100, 20);
		 search_bt.setBounds(200, 20, 30, 20);
		 
		 search_pl.add(search_lb);
		 search_pl.add(search_txt);
		 search_pl.add(search_bt);
		 
		 
		
		 
		 JLabel order_by = new JLabel("정렬");
		 JRadioButton upstrem = new JRadioButton("오름차순",true);
		 JRadioButton downstrem = new JRadioButton("내림차순",true);
			ButtonGroup group = new ButtonGroup();
			group.add(upstrem);
			group.add(downstrem);
			search_pl.add(upstrem);
			search_pl.add(downstrem);
			
			 search_beer.setSize(800,800);
			 search_beer.setVisible(true);	

	}
	

//	private void newFrame() {
//
//		JFrame newJFrame = new JFrame("상세 조회 화면");
//		Container newJFramecontentPane = newJFrame.getContentPane();
//		newJFramecontentPane.setLayout(null);
//
//		lists = dao.getAllBeer();
//		rowData = new  Object[lists.size()][7];
//
//		fillRowData();
//		table = new JTable(rowData,columnNames);
//		//table.addMouseListener(new MouseHandler());
//
//		scrollPane.remove(table);
//		table.revalidate();
//		table.repaint();
//		scrollPane.setViewportView(table);
//
//
//		newJFrame.setSize(800,800);
//		newJFrame.setVisible(true);	
///////////////////////////////
		
		
//	}

	private void deleteData() {
		int row = table.getSelectedRow();
		int num = Integer.parseInt(table.getValueAt(row, 0).toString());
		int count = dao.deleteData(num);

		if(count == 0) {
			System.out.println("삭제실패");
		}
		else {
			System.out.println("삭제성공");
			clearData();
			getAllBeer();
		}
	}

	private void updateData() {
		boolean result = checkData();
		if(result == true)
			System.out.println("문제없이 완료됨");
		else 
			System.out.println("입력 누락있음");

		int num = Integer.parseInt(txtNum.getText());
		String name = txtName.getText();
		String nation = txtNation.getText();
		String style = txtStyle.getText();
		int stock = Integer.parseInt(txtStock.getText());
		int price = Integer.parseInt(txtPrice.getText());
		String inputdate = txtInputdate.getText();

		Beer_manager_bean BMB =  new Beer_manager_bean(num,name,nation,style,stock,price,inputdate);
		int cnt = dao.updateData(BMB);

		if(cnt == 0) {
			System.out.println("업데이트실패");
		}
		else {
			System.out.println("업데이트성공");
			clearData();
			getAllBeer();

		}
	}

	private void insertData() {
		boolean result = checkData();
		if(result == true)
			System.out.println("문제없이 완료됨");
		else 
			System.out.println("입력 누락있음");

		String name = txtName.getText();
		String nation = txtNation.getText();
		String style = txtStyle.getText();
		int stock = Integer.parseInt(txtStock.getText());
		int price = Integer.parseInt(txtPrice.getText());
		String inputdate = txtInputdate.getText();

		Beer_manager_bean BMB =  new Beer_manager_bean(0,name,nation,style,stock,price,inputdate);
		int cnt = dao.insertData(BMB);

		if(cnt == 0) {
			System.out.println("삽입실패");
		}
		else {
			System.out.println("삽입성공");
			clearData();
			getAllBeer();

		}
	}

	private void clearData() {
		txtNum.setText(null);
		txtName.setText(null);
		txtNation.setText(null);
		txtStyle.setText(null);
		txtStock.setText(null);
		txtPrice.setText(null);
		txtInputdate.setText(null);

	}

	private boolean checkData() {
		if(txtName.getText().length() == 0){// 아무것도 입력된게 없냐?
			JOptionPane.showMessageDialog(this,"이름을 입력하세요","에러발생",JOptionPane.ERROR_MESSAGE); // 금지 표시
			txtName.requestFocus();// 커서를 이위치에 놔줘 라는 명령
			return false;
		}
		if(txtNation.getText().length() == 0){// 아무것도 입력된게 없냐?
			JOptionPane.showMessageDialog(this,"제조국을 입력하세요","에러발생",JOptionPane.ERROR_MESSAGE); // 금지 표시
			txtNation.requestFocus();// 커서를 이위치에 놔줘 라는 명령
			return false;
		}
		if(txtStyle.getText().length() == 0){// 아무것도 입력된게 없냐?
			JOptionPane.showMessageDialog(this,"스타일명을 입력하세요","에러발생",JOptionPane.ERROR_MESSAGE); // 금지 표시
			txtStyle.requestFocus();// 커서를 이위치에 놔줘 라는 명령
			return false;
		}
		if(txtStock.getText().length() == 0){// 아무것도 입력된게 없냐?
			JOptionPane.showMessageDialog(this,"재고량을 입력하세요","에러발생",JOptionPane.ERROR_MESSAGE); // 금지 표시
			txtStock.requestFocus();// 커서를 이위치에 놔줘 라는 명령
			return false;
		}
		if(txtPrice.getText().length() == 0){// 아무것도 입력된게 없냐?
			JOptionPane.showMessageDialog(this,"가격을 입력하세요","에러발생",JOptionPane.ERROR_MESSAGE); // 금지 표시
			txtPrice.requestFocus();// 커서를 이위치에 놔줘 라는 명령
			return false;
		}
		if(txtInputdate.getText().length() == 0){// 아무것도 입력된게 없냐?
			JOptionPane.showMessageDialog(this,"입고일을 입력하세요","에러발생",JOptionPane.ERROR_MESSAGE); // 금지 표시
			txtInputdate.requestFocus();// 커서를 이위치에 놔줘 라는 명령
			return false;
		}

		return true;
	}



	
	public void side_table() {

		JPanel bEast = new JPanel();
		bEast.setLayout(new GridLayout(3, 3));

		JButton BeerBtn1= new JButton(new ImageIcon("images/설탕물.jpg"));
		BeerBtn1.addMouseListener(new MouseHandler1());
		bEast.add(BeerBtn1);

		JButton BeerBtn2= new JButton(new ImageIcon("images/애국자.jpg"));
		BeerBtn2.addMouseListener(new MouseHandler2());
		bEast.add(BeerBtn2);

		JButton BeerBtn3= new JButton(new ImageIcon("images/시고단거.jpg"));
		BeerBtn3.addMouseListener(new MouseHandler3());
		bEast.add(BeerBtn3);

		JButton BeerBtn4= new JButton(new ImageIcon("images/포도즙.jpg"));
		BeerBtn4.addMouseListener(new MouseHandler4());
		bEast.add(BeerBtn4);

		JButton BeerBtn5= new JButton(new ImageIcon("images/안쓴거.jpg"));
		BeerBtn5.addMouseListener(new MouseHandler5());
		bEast.add(BeerBtn5);

		JButton BeerBtn6= new JButton(new ImageIcon("images/덜쓴거.jpg"));
		BeerBtn6.addMouseListener(new MouseHandler6());
		bEast.add(BeerBtn6);

		JButton BeerBtn7= new JButton(new ImageIcon("images/핵쓴거.jpg"));
		BeerBtn7.addMouseListener(new MouseHandler7());
		bEast.add(BeerBtn7);

		JButton BeerBtn8= new JButton(new ImageIcon("images/흑탕물.jpg"));
		BeerBtn8.addMouseListener(new MouseHandler8());
		bEast.add(BeerBtn8);

		JButton BeerBtn9= new JButton(new ImageIcon("images/기타.jpg"));
		BeerBtn9.addMouseListener(new MouseHandler9());
		bEast.add(BeerBtn9);
		contentPane=getContentPane();
		contentPane.add("West",bEast);

		bEast.setPreferredSize(new Dimension(750, 150));
		setVisible(true);

	}
	
	
	class MouseHandler1 extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			popup1_sugar();
		}

	}
	public void popup1_sugar() {
		JFrame popup1 = new JFrame("설탕물");
		JPanel select_beer = new JPanel();
		select_beer.setLayout(new GridLayout(2, 3));
		popup1.add(select_beer);
		JButton sugar1 =  new JButton(new ImageIcon("sugar_images/닥터페퍼.jpg"));
		select_beer.add(sugar1);
		JButton sugar2 =  new JButton(new ImageIcon("sugar_images/스프라이트.jpg"));
		select_beer.add(sugar2);
		JButton sugar3 =  new JButton(new ImageIcon("sugar_images/코카콜라.jpg"));
		select_beer.add(sugar3);
		JButton sugar4 =  new JButton(new ImageIcon("sugar_images/펩시콜라.jpg"));
		select_beer.add(sugar4);
		

		popup1.setSize(550,550);
		popup1.setVisible(true);	
	}
	
	class MouseHandler2 extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			popup2_kr();
		}
	}
	public void popup2_kr() {
		JFrame popup2 = new JFrame("애국자");
		JPanel select_beer = new JPanel();
		select_beer.setLayout(new GridLayout(3, 3));
		popup2.add(select_beer);
		JButton kobeer1 =  new JButton(new ImageIcon("koimages/고릴라 여우비.png"));
		select_beer.add(kobeer1);
		JButton kobeer2 =  new JButton(new ImageIcon("koimages/맥파이 신개구리.png"));
		select_beer.add(kobeer2);
		JButton kobeer3 =  new JButton(new ImageIcon("koimages/어메이징 첫사랑.png"));
		select_beer.add(kobeer3);
		JButton kobeer4 =  new JButton(new ImageIcon("koimages/어메이징 헬레스 라거.png"));
		select_beer.add(kobeer4);
		JButton kobeer5 =  new JButton(new ImageIcon("koimages/크래머리 IPA.png"));
		select_beer.add(kobeer5);
		JButton kobeer6 =  new JButton(new ImageIcon("koimages/크래머리 스타우트.png"));
		select_beer.add(kobeer6);
		JButton kobeer7 =  new JButton(new ImageIcon("koimages/크래머리 라거.png"));
		select_beer.add(kobeer7);
		JButton kobeer8 =  new JButton(new ImageIcon("koimages/핸드앤몰트 모카스타우트.png"));
		select_beer.add(kobeer8);
		JButton kobeer9 =  new JButton(new ImageIcon("koimages/크래머리 트로피컬 스무디.png"));
		select_beer.add(kobeer9);
		JButton kobeer10 =  new JButton(new ImageIcon("koimages/카브루 구미호 피치.png"));
		select_beer.add(kobeer10);


		select_beer.setPreferredSize(new Dimension(750, 150));
		popup2.setSize(800,800);
		popup2.setVisible(true);	
	}
	
	class MouseHandler3 extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			popup3_sour();
		}
	}
	public void popup3_sour() {
		JFrame popup3 = new JFrame("시고-단거");
		JPanel select_beer = new JPanel();
		select_beer.setLayout(new GridLayout(1, 3));
		popup3.add(select_beer);
		JButton sour1 =  new JButton(new ImageIcon("import_images/분 브루어리 괴즈 분.jpg"));
		select_beer.add(sour1);
		JButton sour2 =  new JButton(new ImageIcon("import_images/분 크릭 마리아주 파르페.jpg"));
		select_beer.add(sour2);
		JButton sour3 =  new JButton(new ImageIcon("import_images/마튼즈 라들러.jpg"));
		select_beer.add(sour3);

		//select_beer.setPreferredSize(new Dimension(750, 150));
		popup3.setSize(750,300);
		popup3.setVisible(true);	
	}
	
	class MouseHandler4 extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			popup4_wine();
		}
	}
	public void popup4_wine() {
		JFrame popup4 = new JFrame("포도즙");
		JPanel select_beer = new JPanel();
		select_beer.setLayout(new GridLayout(1, 2));
		popup4.add(select_beer);
		JButton whine1 =  new JButton(new ImageIcon("import_images/샤또 까망삭.jpg"));
		select_beer.add(whine1);
		JButton whine2 =  new JButton(new ImageIcon("import_images/펜리 에스테이트 피닉스 카베르네 소비뇽.jpg"));
		select_beer.add(whine2);

		//select_beer.setPreferredSize(new Dimension(750, 150));
		popup4.setSize(550,300);
		popup4.setVisible(true);	
	}
	
	class MouseHandler5 extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			popup5_dul();
		}
	}
	public void popup5_dul() {
		JFrame popup5 = new JFrame("안쓴거");
		JPanel select_beer = new JPanel();
		select_beer.setLayout(new GridLayout(3, 3));
		popup5.add(select_beer);
		JButton dul1 =  new JButton(new ImageIcon("import_images/마튼즈 라들러.jpg"));
		select_beer.add(dul1);
		JButton dul2 =  new JButton(new ImageIcon("import_images/바이엔 슈테판 라거.jpg"));
		select_beer.add(dul2);
		JButton dul3 =  new JButton(new ImageIcon("import_images/분 브루어리 괴즈 분.jpg"));
		select_beer.add(dul3);
		JButton dul4 =  new JButton(new ImageIcon("import_images/분 크릭 마리아주 파르페.jpg"));
		select_beer.add(dul4);
		JButton dul5 =  new JButton(new ImageIcon("import_images/칼스버스 서머스비.jpg"));
		select_beer.add(dul5);
		JButton dul6 =  new JButton(new ImageIcon("koimages/크래머리 라거.png"));
		select_beer.add(dul6);
		JButton dul7 =  new JButton(new ImageIcon("koimages/어메이징 헬레스 라거.png"));
		select_beer.add(dul7);

		//select_beer.setPreferredSize(new Dimension(750, 150));
		popup5.setSize(770,800);
		popup5.setVisible(true);	
	}
	
	class MouseHandler6 extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			popup6_duldul();
		}
	}
	public void popup6_duldul() {
		JFrame popup6 = new JFrame("안쓴거");
		JPanel select_beer = new JPanel();
		select_beer.setLayout(new GridLayout(2, 2));
		popup6.add(select_beer);
		JButton duldul1 =  new JButton(new ImageIcon("import_images/팻타이어 부두레인저.jpg"));
		select_beer.add(duldul1);
		JButton duldul2 =  new JButton(new ImageIcon("koimages/어메이징 첫사랑.png"));
		select_beer.add(duldul2);
		JButton duldul3 =  new JButton(new ImageIcon("koimages/고릴라 여우비.png"));
		select_beer.add(duldul3);
		JButton duldul4 =  new JButton(new ImageIcon("koimages/맥파이 신개구리.png"));
		select_beer.add(duldul4);

		//select_beer.setPreferredSize(new Dimension(750, 150));
		popup6.setSize(530,550);
		popup6.setVisible(true);	
	}
	
	class MouseHandler7 extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			popup7_extrem();
		}
	}
	public void popup7_extrem() {
		JFrame popup7 = new JFrame("핵쓴거");
		JPanel select_beer = new JPanel();
		select_beer.setLayout(new GridLayout(2, 2));
		popup7.add(select_beer);
		JButton extrem_B1 =  new JButton(new ImageIcon("import_images/토플링 골리앗 파이어 스컬스 &머니 IPA.jpg"));
		select_beer.add(extrem_B1);
		JButton extrem_B2 =  new JButton(new ImageIcon("import_images/구스 아일랜드 IPA.jpg"));
		select_beer.add(extrem_B2);
		JButton extrem_B3 =  new JButton(new ImageIcon("koimages/크래머리 트로피컬 스무디.png"));
		select_beer.add(extrem_B3);
		JButton extrem_B4 =  new JButton(new ImageIcon("koimages/크래머리 IPA.png"));
		select_beer.add(extrem_B4);

		//select_beer.setPreferredSize(new Dimension(750, 150));
		popup7.setSize(530,550);
		popup7.setVisible(true);	
	}
	
	class MouseHandler8 extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			popup8_black();
		}
	}
	public void popup8_black() {
		JFrame popup8 = new JFrame("흑탕물");
		JPanel select_beer = new JPanel();
		select_beer.setLayout(new GridLayout(1, 2));
		popup8.add(select_beer);
		JButton staut1 =  new JButton(new ImageIcon("koimages/핸드앤몰트 모카스타우트.png"));
		select_beer.add(staut1);
		JButton staut2 =  new JButton(new ImageIcon("koimages/크래머리 스타우트.png"));
		select_beer.add(staut2);

		//select_beer.setPreferredSize(new Dimension(750, 150));
		popup8.setSize(530,300);
		popup8.setVisible(true);	
	}
	
	class MouseHandler9 extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			popup9_acc();
		}
	}
	public void popup9_acc() {
		JFrame popup9 = new JFrame("기타");
		JPanel select_beer = new JPanel();
		select_beer.setLayout(new GridLayout(2, 2));
		popup9.add(select_beer);
		JButton acc1 =  new JButton(new ImageIcon("acc/015.jpg"));
		select_beer.add(acc1);
		JButton acc2 =  new JButton(new ImageIcon("acc/016.jpg"));
		select_beer.add(acc2);
		JButton acc3 =  new JButton(new ImageIcon("acc/017.jpg"));
		select_beer.add(acc3);
		JButton acc4 =  new JButton(new ImageIcon("acc/018.jpg"));
		select_beer.add(acc4);

		//select_beer.setPreferredSize(new Dimension(750, 150));
		popup9.setSize(530,550);
		popup9.setVisible(true);	
	}
}




