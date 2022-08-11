/*2018111393 류혜정
상품 렌탈샵 재고 등록&삭제&검&전체상품 출력
*/
package RentalShop;

import java.util.*;
import java.util.Calendar;
import java.util.Date;

public class User {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		Calendar cal = Calendar.getInstance();
		//Date date = cal.getTime();
		int day=cal.get(Calendar.DATE);
		int month=cal.get(Calendar.MONTH)+1;
		
		Management management = new Management(); //매니저 객체 생성
		
		while(true) {
			//메뉴 출력
			System.out.println("--------편의점 관리 시스템--------");
			System.out.println("1. 사용자");
			System.out.println("2. 관리자");
			System.out.println("3. 프로그램 종료");
			System.out.println("--------------------------");
			System.out.printf("원하는 메뉴의 번호를 입력해주세요: ");	

			try {
				int menu = scan.nextInt();
				switch(menu) {
				case 1://사용자 
					while(true) {
						System.out.println("-------사용자 모드--------");
						System.out.println("1: 사용자 등록");
						System.out.println("2: 사용자 삭제");
						System.out.println("3: 사용자 출력");
					    System.out.println("4: 사용자 렌탈/반납");
					    System.out.println("5: 물품 검색");
						System.out.println("6: 관리자 모드로 가기");
					    System.out.println("--------------------------");
					    System.out.printf("원하는 메뉴의 번호를 입력해주세요: ");
						
						try {
							int menuU = scan.nextInt();
							scan.nextLine();
							
							switch(menuU) {
							case 1://사용자 등록 
								try {
									System.out.printf("사용자 이름 : ");
									String userName=scan.nextLine();
									System.out.printf("사용자 핸드폰번호(뒷자리 4자리) : ");
									int userPN=scan.nextInt();
									int code=management.getTotalUserCount()+1;
									if((int)(Math.log10(userPN))!=3) {
										System.out.println("사용자 등록에 실패했습니다.");
										System.out.println("핸드폰번호 뒷자리 4자리를 정확하게 입력해주세요.");
									}else {
										UserInfo user = new UserInfo(code,userName,userPN); //사용자 객체 생성
										
										try {
											management.insertUser(user); //새로운 사용자 삽입
											scan.nextLine();
										}
										catch (Exception e) { //수용 가능 사용자 수 초과 
											String msg = e.getMessage();
											System.out.println(msg);
										}
									}
								}
								catch(java.util.InputMismatchException e) {//숫자->문자 입력 오류
									System.out.println("잘못 입력하셨습니다.");
									scan.nextLine();
								}
							break;
							case 2://사용자 삭제 
								if(management.getTotalUserCount()!=0){
									System.out.printf("삭제할 사용자 이름을 입력해주세요 : ");
									String deleteUserName=scan.nextLine();
									try {
										int deleteIndex = management.findUserIndex(deleteUserName); //삭제할 사용자의 배열 인덱스를 찾음
										System.out.println(management.viewUser(deleteIndex)); //물품 출력
										management.deleteUser(deleteIndex); //물품 삭제
										System.out.println("삭제되었습니다.");
									}
									catch(Exception e) { //찾는 물품이 없는 경우
										String msg = e.getMessage();
										System.out.println(msg);
									}
								}else{//물품이 없는 경우
									System.out.println("추가된 사용자가 없습니다.");
								}
								break;
							case 3://사용자 출력 
								if(management.getTotalUserCount()==0)
								{
									System.out.println("추가된 사용자가 없습니다.");
								}
								else {
									System.out.println("--------전체 사용--------");
									for(int i=0;i<management.getTotalUserCount();i++) {
										System.out.println("["+(i+1)+"] "+management.viewUser(i));
									}
								}
								break;
							case 4://사용자 렌탈/반납 
							
								while(true) {
									//메뉴 출력
									System.out.println("--------사용자 렌탈/반납--------");
									System.out.println("1: 사용자 물품 렌탈");
									System.out.println("2: 사용자 물품 반납");
									System.out.println("3: 뒤로가기");
									System.out.println("--------------------------");
									System.out.printf("원하는 메뉴의 번호를 입력해주세요: ");
									
									int searchMenu = scan.nextInt(); //메뉴를 입력받는다
									scan.nextLine();
									
									switch(searchMenu) { //물품 검색
									
									case 1://물품 렌탈 
										try {
											System.out.printf("사용자 이름 : ");
											String userName=scan.nextLine();
											System.out.printf("렌탈할 물품코드 : ");
											int code=scan.nextInt();
											System.out.printf("렌탈할 물품개수 : ");
											int stock=scan.nextInt();
											System.out.printf("렌탈할 기간 : ");
											int date=scan.nextInt();
											try {
												int goodsI = management.findGoodsCode(code);
												int userI = management.findUserIndex(userName);
												System.out.println("-----렌탈할 물품 정보-----");
												System.out.println(management.viewGoods(goodsI));
												System.out.printf("위의 물품을 렌탈하는게 맞나요?(맞으면0, 아니면1):");
												try {
													int yesNo = scan.nextInt();
													if(yesNo==0) {
														try {
															management.rentalGoods(day, userName, code, stock, date);
															System.out.println("렌탈 성공했습니다. ");
															System.out.println(management.viewUser(userI));
											
														}catch(Exception e) {
															String msg = e.getMessage();
															System.out.println(msg);
														}
													}else if(yesNo==1) {
														System.out.println("물품 수정을 취소했습니다.");
													}else {
														System.out.println("번호를 잘못 입력했습니다.");
													}
												}catch(java.util.InputMismatchException e) {//숫자->문자 입력 오류
													System.out.println("잘못 입력하셨습니다.");
													scan.nextLine();
												}
											}catch(Exception e) {
												String msg = e.getMessage();
												System.out.println(msg);
											}
										}
										catch(java.util.InputMismatchException e) {//숫자->문자 입력 오류
											System.out.println("잘못 입력하셨습니다.");
											scan.nextLine();
										}
										break;
									case 2: //물품 반납 
										try {
											System.out.printf("사용자 이름 : ");
											String userName=scan.nextLine();
											System.out.printf("반납할 물품코드 : ");
											int code=scan.nextInt();
											try {
												management.returnGoods(day, userName, code);
												System.out.println("반납 성공했습니다. ");
											}catch(Exception e) {
												String msg = e.getMessage();
												System.out.println(msg);
											}
										}
										catch(java.util.InputMismatchException e) {//숫자->문자 입력 오류
											System.out.println("잘못 입력하셨습니다.");
											scan.nextLine();
										}
										break;
									case 3:// 뒤로가기
										break;
										
									default:
										System.out.println("다시 입력하세요."); //메뉴 이외의 숫자를 입력
										break;
									}
									if(searchMenu==3) break; //검색 모드 종료
									}
									break;
							case 5://물품 검색 
								System.out.printf("물품코드 입력해 주세요 : ");
								int goodsCode = scan.nextInt();
								try {
									int index = management.findGoodsCode(goodsCode); //물품의 인덱스
									System.out.println(management.viewGoods(index)); //물품 출력
								}
								catch (Exception e) { //찾는 물품 없을 경우
									String msg = e.getMessage();
									System.out.println(msg);
								}
								break;
							case 6:// 종료 
								break;
							default:
								System.out.println("다시 입력하세요."); //메뉴 이외의 숫자를 입력
								break;
							
							}
							if(menuU==6) break;
						}catch(java.util.InputMismatchException e) {//숫자->문자 입력 오류
							System.out.println("잘못 입력하셨습니다.");
							scan.nextLine();
						}
					}
					
				case 2://관리자 
					while(true) {
						System.out.println("--------관리자 모드--------");
						System.out.println("1: 상품 등록");
					    System.out.println("2: 상품 삭제");
					    System.out.println("3: 상품 출력");
					    System.out.println("4: 상품 검색");
					    System.out.println("5: 상품 수정");
					    System.out.println("6: 오늘의 총 매출");
						System.out.println("7: 뒤로가기");
					    System.out.println("--------------------------");
					    System.out.printf("원하는 메뉴의 번호를 입력해주세요: ");
						
						try {
							int menuN = scan.nextInt(); // 번호를 입력받는다
							scan.nextLine();
							
							switch(menuN) { //메뉴
							case 1: // 물품 삽입
								
								try {
									System.out.printf("물품명 : ");
									String name=scan.nextLine();
									System.out.printf("가격 : ");
									int price=scan.nextInt();
									System.out.printf("재고량 : ");
									int stock=scan.nextInt();
									System.out.printf("기본 대여일 : ");
									int term=scan.nextInt();
									int code=management.getTotalCount()+1;
						
									Goods goods = new Goods(code,name,price,stock,term); //Goods 객체 생성
									try {
										management.insertGoods(goods); //새로운 물품 삽입
										scan.nextLine();
									}
									catch (Exception e) { //물품 갯수 초과
										String msg = e.getMessage();
										System.out.println(msg);
									}
								}
								catch(java.util.InputMismatchException e) {//숫자->문자 입력 오류
									System.out.println("잘못 입력하셨습니다.");
									scan.nextLine();
								}
							break;

							case 2: //물품 삭제
								if(management.getTotalCount()!=0){
									System.out.printf("삭제할 물품명을 입력해주세요 : ");
									String deleteName=scan.nextLine();
									try {
										int deleteIndex = management.findGoodsIndex(deleteName); //삭제할 물품의 배열 인덱스를 찾음
										System.out.println(management.viewGoods(deleteIndex)); //물품 출력
										management.deleteGoods(deleteIndex); //물품 삭제
										System.out.println("삭제되었습니다.");
									}
									catch(Exception e) { //찾는 물품이 없는 경우
										String msg = e.getMessage();
										System.out.println(msg);
									}
								}else{//물품이 없는 경우
									System.out.println("추가된 물품이 없습니다.");
								}
								break;
							
							case 3: // 물품 출력
								if(management.getTotalCount()==0)
								{
									System.out.println("추가된 물품이 없습니다.");
								}
								else {
									System.out.println("--------전체 물품--------");
									for(int i=0;i<management.getTotalCount();i++) {
										System.out.println("["+(i+1)+"] "+management.viewGoods(i));
									}
								}
								break;
								
							case 4: //물품 검색
								while(true) {
								//메뉴 출력
								System.out.println("--------물품 검색--------");
								System.out.println("1: 물품 코드로 검색");
								System.out.println("2: 뒤로가기");
								System.out.println("--------------------------");
								System.out.printf("원하는 메뉴의 번호를 입력해주세요: ");
								
								int searchMenu = scan.nextInt(); //메뉴를 입력받는다
								scan.nextLine();
								
								switch(searchMenu) { //물품 검색
								
								case 1:
									System.out.printf("검색할 물품코드 입력해 주세요 : ");
									int goodsCode = scan.nextInt();
									try {
										int index = management.findGoodsCode(goodsCode); //물품의 인덱스
										System.out.println(management.viewGoods(index)); //물품 출력
									}
									catch (Exception e) { //찾는 물품 없을 경우
										String msg = e.getMessage();
										System.out.println(msg);
									}
									break;
									
								case 2: // 뒤로가기
									break;
									
								default:
									System.out.println("다시 입력하세요."); //메뉴 이외의 숫자를 입력
									break;
								}
								if(searchMenu==2) break; //검색 모드 종료
								}
								break;
							case 5://수정 
								System.out.printf("수정할 물품코드 입력해 주세요 : ");
								int goodsCode = scan.nextInt();
								try {
									int index = management.findGoodsCode(goodsCode); //물품의 인덱스
									System.out.println(management.viewGoods(index)); //물품 출력
									System.out.printf("위의 물품을 수정하는게 맞나요?(맞으면0, 아니면1):");
									try {
										int yesNo = scan.nextInt();
										if(yesNo==0) {
											System.out.printf("수정할 가격 : ");
											int price=scan.nextInt();
											System.out.printf("수정할 재고량 : ");
											int stock=scan.nextInt();
											System.out.printf("수정할 기본 대여일 : ");
											int term=scan.nextInt();
											management.correctionGoods(index, price, stock, term);
											System.out.println("물품 수정을 완료했습니다.");
											
										}else if(yesNo==1) {
											System.out.println("물품 수정을 취소했습니다.");
										}else {
											System.out.println("번호를 잘못 입력했습니다.");
										}
									}catch(java.util.InputMismatchException e) {//숫자->문자 입력 오류
										System.out.println("잘못 입력하셨습니다.");
										scan.nextLine();
									}
								}
								catch (Exception e) { //찾는 물품 없을 경우
									String msg = e.getMessage();
									System.out.println(msg);
								}
								break;
							case 6: //매출 
								/*while(true) {
									//메뉴 출력
									System.out.println("--------매출 보기--------");
									System.out.println("1: 오늘 매출 확인");
									System.out.println("2: 이 달의 총 매출 확인");
									System.out.println("3: 뒤로가기");
									System.out.println("--------------------------");
									System.out.printf("원하는 메뉴의 번호를 입력해주세요: ");
									
									int searchMenu = scan.nextInt(); //메뉴를 입력받는다
									scan.nextLine();
									
									switch(searchMenu) { //물품 검색
									
									case 1:
										System.out.println("[ "+day+"일의 매출 : "+management.todaySales(day)+" ]");
										
										break;
									case 2://이달의 총 매출 확인 
										System.out.println("[ "+month+"월의 총 매출 : "+management.totalSales()+" ]");
									case 3: // 뒤로가기
										break;
										
									default:
										System.out.println("다시 입력하세요."); //메뉴 이외의 숫자를 입력
										break;
									}
									if(searchMenu==3) break; //검색 모드 종료
									}
									break;*/
								System.out.println("[ "+day+"일의 매출 : "+management.todaySales(day)+" ]");
								break;
							case 7: //종료
								break;
								
							default :
								System.out.println("다시 입력하세요."); //메뉴 이외의 숫자를 입력
								break;
							}
							
							if(menuN==7) break; //종료
						}catch(java.util.InputMismatchException e) {//숫자->문자 입력 오류
							System.out.println("잘못 입력하셨습니다.");
							scan.nextLine();
						}
						
					}
					
				case 3:// 종료 
					break;
				default : 
					System.out.println("다시 입력해주세요 ");
					break;
				}
				if(menu==3) break; //프로그램 종료 
			}catch(java.util.InputMismatchException e) {//숫자->문자 입력 오류
				System.out.println("잘못 입력하셨습니다.");
				scan.nextLine();
			}
			
			
		}
	}
}
