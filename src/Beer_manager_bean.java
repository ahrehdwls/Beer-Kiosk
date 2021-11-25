
public class Beer_manager_bean {

	private int num ;
	private String name;
	private String nation;
	private String style;
	private int stock ;
	private int price ;
	private String inputdate;
	
	
	Beer_manager_bean(){
		
	}
	
	public Beer_manager_bean(int num, String name, String nation, String style, int stock, int price, String inputdate) {
		this.num = num;
		this.name = name;
		this.nation = nation;
		this.style = style;
		this.stock = stock;
		this.price = price;
		this.inputdate = inputdate;
	}



	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getPrice() {
		return price;
	}
	public String getInputdate() {
		return inputdate;
	}

	public void setInputdate(String inputdate) {
		this.inputdate = inputdate;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
