/*2018111393 류혜정
상품 렌탈샵 재고 등록&삭제&검&전체상품 출력
*/
package RentalShop;

public class Goods {
	private String name; //제품명
	private int price; //가격
	private int stock; //재고
	private int term; //대여일
	private int id=1000; // 물품번호 (자동 부여)
	
	public Goods(int id, String name, int price, int stock, int term) { //생성자 함수
		this.name=name;
		this.price=price;
		this.stock=stock;
		this.term=term;
		this.id+=id;
	}
	//생성자&접근자 함수들
	public void setName(String name) { 
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setPrice(int price) {
		this.price=price;
	}

	public int getPrice() { 
		return price;
	}

	public void setStock(int stock) { 
		this.stock=stock;
	}

	public int getStock() { 
		return stock;
	}

	public void setTerm(int term){
		this.term = term;
	}

	public int getTerm(){
		return term;
	}

	public void setId(int id) { 
		this.id=id;
	}

	public int getId() { 
		return id;
	}
	
	
	public void addStock(int stock) { //재고를 더하는 함수
		this.stock+=stock;
	}
	
	public void subStock(int stock) throws Exception { //재고를 빼는 함수
		if(this.stock==0)
			throw new Exception("재고가 부족합니다.");
		this.stock-=stock;
	}
}
