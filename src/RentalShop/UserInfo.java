/*2018111393 류혜정
사용자 정보 등록 
*/
package RentalShop;

public class UserInfo {
	private String userName; //사용자 이름 
	private int phonN; //사용자 전화번호 
	private int id=2000; // 사용자 코드 (자동 부여)
	private int rentalCode;//렌탈한 물품 코드
	private int reantalTerm;//렌탈한 기간 
	private int rentalStock;//렌탈한 양 
	
	private int rentalP=0; //렌탈 물품 개수(최대 3개)
	private int rental1[]=new int[3];
	
	int rentalTime;
	int returnTime;
	
	public UserInfo(int id, String userName, int phonN) { //생성자 함수
		this.userName=userName;
		this.phonN=phonN;
		this.id+=id; // 번호 변화
	}
	//생성자&접근자 함수들
	public void setUserName(String userName) { 
		this.userName=userName;
	}

	public String getUserName() {
		return userName;
	}
	public void setPhoneN(int phonN) {
		this.phonN=phonN;
	}

	public int getPhoneN() { 
		return phonN;
	}
	public void setId(int id) { 
		this.id=id;
	}

	public int getId() { 
		return id;
	}
	public void setRentalCode(int rentalCode) { 
		this.rentalCode=rentalCode;
	}

	public int getRentalCode() { 
		return rentalCode;
	}
	public void setRentalTerm(int reantalTerm) { 
		this.reantalTerm=reantalTerm;
	}

	public int getRentalTerm() { 
		return reantalTerm;
	}
	public void setRentalStock(int rentalStock) { 
		this.rentalStock=rentalStock;
	}

	public int getRentalStock() { 
		return rentalStock;
	}
	public void setRentalTime(int rentalTime) { 
		this.rentalTime=rentalTime;
	}

	public int getRentalTime() { 
		return rentalTime;
	}
	public void setReturnTime(int returnTime) { 
		this.returnTime=returnTime;
	}

	public int getReturnTime() { 
		return returnTime;
	}

}
