/*2018111393 류혜정
상품 렌탈샵 재고 등록&삭제&검&전체상품 출력
*/
package RentalShop;

public class Management {
	private int totalCount=0; //전체 물품 종류
	private Goods goods[]; //물품 배열
	
	private int userCount=0; //전체 사용자 수
	private UserInfo user[]; //사용자 배열
	
	private int monthSales[]=new int[31];
	int todaySales=0, totalSales=0; //오늘의 매출&총 매출 
	
	
	Management() { //생성자 함수
		goods = new Goods[100];
		user = new UserInfo[10];
	}
	//물품 관련 함수 
	public void insertGoods(Goods newgoods) throws Exception { //물품 리스트에 물품 객체 삽입	
		if(totalCount==goods.length)
			throw new Exception("최대 물품 수를 초과했습니다.");
		int num=avoidOverlap(newgoods.getName());
		if(num==0)//중복 없다면 
			goods[totalCount++]=newgoods;//물품 추가 
		else//중복이 있다면 
			throw new Exception("이미 존재하는 물품입니다.");//물품 추가 안해줌 
	}
	public int avoidOverlap(String goodsName) {//중복 검색하는 함수 
		int overlap=0;
		
		for(int i = 0; i<totalCount; i++)
		{
			if(goodsName.equals(goods[i].getName())) {
				overlap=1;//중복 
			}else {
				overlap=0;//중복아님 
			}
		}
		
		return overlap;
	}
	
	public String viewGoods(int i){ //물품 출력
		return "물품코드: "+ goods[i].getId()+" 물품명 : "+goods[i].getName()+" 가격 : "+goods[i].getPrice()+" 재고량 : "+goods[i].getStock()+" 대여일 : "+goods[i].getTerm();
		
	}
	
	public int getTotalCount() { //전체 물품 수 반환
		return totalCount;
	}
	
	public int findGoodsIndex(String goodsName) throws Exception { //파라미터로 물품명 찾음
		int goodsIndex = 0; // 배열 인덱스
		boolean find = false; // 물품을 찾았는지
		
		for(int i = 0; i<totalCount; i++)
		{
			if(goodsName.equals(goods[i].getName())) {
				goodsIndex = i; 
				find=true; // 물품을 찾음
				break;
			}
		}
		if(find==false) {
			throw new Exception("찾는 물품이 없습니다.");//찾는 물품 없는 경우
		}
		
		return goodsIndex;
	}
	public int findGoodsCode(int goodsCode) throws Exception{
		int goodsIndex = 0; // 배열 인덱스
		boolean find = false; // 물품을 찾았는지
		
		for(int i = 0; i<totalCount; i++)
		{
			if(goodsCode == (goods[i].getId())) {
				goodsIndex = i; 
				find=true; // 물품을 찾음
				break;
			}
		}
		if(find==false) {
			throw new Exception("찾는 물품이 없습니다.");//찾는 물품 없는 경우
		}
		
		return goodsIndex;
	}
	
	public void deleteGoods(int index) throws Exception { 
		for(int i=index;i<totalCount;i++)
		{
			goods[i]=goods[i+1]; // i+1의 값을 i로 이동
		}
		totalCount--; // 전체 물품 수 감소
	}
	public void correctionGoods(int index, int price ,int stock, int term) {//물품 수정 함수 
		goods[index].setPrice(price);
		goods[index].setStock(stock);
		goods[index].setTerm(term);
	}
	
	//사용자 관련 함수
	public void insertUser(UserInfo newUser) throws Exception { //물품 리스트에 물품 객체 삽입	
		if(userCount==user.length)
			throw new Exception("최대 사용자 수를 초과했습니다.");
		int num=avoidUserOverlap(newUser.getUserName());
		if(num==0)//중복 없다면 
			user[userCount++]=newUser;//사용자 추가 
		else//중복이 있다면 
			throw new Exception("이미 존재하는 사용자입니다.");//사용자 추가 안해줌 
	}
	public int avoidUserOverlap(String userName) {//중복 검색하는 함수 
		int overlap=0;
		
		for(int i = 0; i<userCount; i++)
		{
			if(userName.equals(user[i].getUserName())) {
				overlap=1;//중복 
			}else {
				overlap=0;//중복아님 
			}
		}
		
		return overlap;
	}
	public int getTotalUserCount() { //전체 사용자 수 반환
		return userCount;
	}
	public int findUserIndex(String UserName) throws Exception { //파라미터로 물품명 찾음
		int userIndex = 0; // 배열 인덱스
		boolean find = false; // 물품을 찾았는지
		
		for(int i = 0; i<userCount; i++)
		{
			if(UserName.equals(user[i].getUserName())) {
				userIndex = i; 
				find=true; // 물품을 찾음
				break;
			}
		}
		if(find==false) {
			throw new Exception("찾는 사용자가 없습니다.");//찾는 물품 없는 경우
		}
		
		return userIndex;
	}
	public String viewUser(int i){ //사용자 출력
		return "사용자 정보: "+ user[i].getId()+" 사용자 이름 : "+user[i].getUserName()+" 사용자 핸드폰 번호 : "+user[i].getPhoneN()+" 렌탈한 물품 : "+user[i].getRentalCode()+" 렌탈 기간 : "+user[i].getRentalTerm()+" 렌탈 개수 : "+user[i].getRentalStock();		
	}
	public void deleteUser(int index) throws Exception { 
		for(int i=index;i<userCount;i++)
		{
			user[i]=user[i+1]; // i+1의 값을 i로 이동
		}
		userCount--; // 전체 물품 수 감소
	}
	public void rentalGoods(int today, String userName, int code, int stock, int date) throws Exception{
		//int index = findGoodsCode(code);
		int goodsI = findGoodsCode(code);
		int userI = findUserIndex(userName);
		boolean rental = false;
		//빌리자고 하는 날이 기본 대여일보다 길다면 
		if(date >goods[goodsI].getTerm()) {
			throw new Exception("대여 가능일수를 초과했습니다.");
		}else if(user[userI].getRentalStock()!=0&&user[userI].getRentalTerm()!=0){
			throw new Exception("이미 대여하고 있는 물품이 있습니다.");
		}else if(stock>goods[goodsI].getStock()) {
			throw new Exception("대여 가능 물품 수량을 초과했습니다.");
		}
		else {
			goods[goodsI].subStock(stock);
			user[userI].setRentalCode(goods[goodsI].getId());
			user[userI].setRentalTerm(date);
			user[userI].setRentalStock(stock);
			
			user[userI].setRentalTime(date);
			
		}
	}
	public void returnGoods(int today, String userName, int code) throws Exception{
		int goodsI = findGoodsCode(code);
		int userI = findUserIndex(userName);
		boolean returnG = false;
		int defaultDate=goods[goodsI].getTerm();
		int lateDate=today-user[userI].getRentalTime();
		
		if(defaultDate<lateDate+1) {//초과했을 때 
			todaySales=(lateDate-defaultDate)*goods[goodsI].getPrice()*user[userI].getRentalStock();
		}else {
			todaySales=goods[goodsI].getPrice()*user[userI].getRentalStock();
		}
		monthSales[today-1]+=todaySales;
		goods[goodsI].addStock(user[userI].getRentalStock());
		user[userI].setRentalCode(0);
		user[userI].setRentalTerm(0);
		user[userI].setRentalStock(0);
		user[userI].setReturnTime(today);
	}
	public int todaySales(int today) {
		return monthSales[today-1];
	}
	public int totalSales() {
		for(int i=0;i<31;i++) {
			totalSales+=monthSales[i];
		}
		return totalSales;
	}
}
