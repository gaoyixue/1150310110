package components;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.lang.*;

public class MyChessAndGoGame {
	public Set<String> supportType=new HashSet<String>() {{
		add("chess");
		add("go");
	}};
	public Game game;
	public String player1Name;
	public String player2Name;
	public ArrayList<player> players=new ArrayList<player>();
	
	public void printMenu() {
		//����1���Χ�����ã�����234��Թ�����������
		System.out.println("1����δ�������ϵ����ӷ�������ָ��λ��");
		System.out.println("2���ƶ������ϵ�ĳ��λ���ϵ����ӵ��µ�λ��");
		System.out.println("3������(�Ƴ��Է�����)");
		System.out.println("4������(ʹ�Լ����ӳԵ��Է�����)");
		System.out.println("5����ѯĳ��λ�õ�ռ�����");
		System.out.println("6������������ҷֱ��������ϵ���������");
		System.out.println("7������");
		System.out.println("end����������");
	}
	
	public void gameMain() {
		BufferedReader reader=null;
		String[] splitItems;
		String strLine;
		try {
			reader=new BufferedReader(new InputStreamReader(System.in));
			while(true) {
				System.out.println(String.format("������Ϸ����\t%s",supportType.
			    stream().reduce((a,b)->a+",\t"+b)));
				strLine=reader.readLine().trim();
				
				if(supportType.contains(strLine)) {
					break;
				}																																
				else {
					System.out.println("�����������������");
				}
			}
			System.out.println(strLine);
			System.out.println("[�û�A]\t��������������");
			player1Name=reader.readLine().trim();
			System.out.println("[�û�B]\t��������������");
			player2Name=reader.readLine().trim();
			game=new Game(strLine,player1Name,player2Name);
			
			players.add(game.getPlayer1());			
			players.add(game.getPlayer2());			
			System.out.println(String.format("%s��%s����Ϸ��ʼ�������β���",player1Name,player2Name));
			
			int pNI=0;
			while(true) {
				System.out.println();				
				System.out.println(String.format("->[%s]", players.get(pNI).getPlayerName()));				
				printMenu();				
				strLine=reader.readLine().trim();				
				boolean exitFlag =false;				
				switch(strLine) {
				
				//����δ�������ϵ�һ�����ӷ��������̵�ָ��λ��
				case "1":
					System.out.println("����(pieceName,edX,edY):");
					strLine=reader.readLine().trim();
					splitItems=strLine.split("\\s");
					if(splitItems.length==3) {
						try {
							int px=Integer.valueOf(splitItems[1]);
							int py=Integer.valueOf(splitItems[2]);
							String pName=splitItems[0];
							player player=players.get(pNI);
							piece p=new piece(pName,0,-1,-1);
							
							game.putPiece(player, p, new Position(px,py));
							pNI=(pNI+1)%2;
							System.out.println("success");
						}catch(Exception e) {
							System.out.println(e.toString());
							continue;
						}
					}
					else {
						System.out.println("����������������룺");
						continue;
					}//else
					break;
					
				case "2":
					//�ƶ�������ĳ��λ�õ����ӵ��µ�λ��
					System.out.println("����(stX,stY,edX,edY):");
					strLine=reader.readLine().trim();
					splitItems=strLine.split("\\s");
					if(splitItems.length==4) {
						try {
							int stX=Integer.valueOf(splitItems[0]);
							int stY=Integer.valueOf(splitItems[1]);
							int edX=Integer.valueOf(splitItems[2]);
							int edY=Integer.valueOf(splitItems[3]);
							player player=players.get(pNI);
							game.movePiece(player, new Position(stX,stY), new Position(edX,edY));
							pNI=(pNI+1)%2;
							System.out.println("success");
						}catch(Exception e) {
							System.out.println(e.toString());
							continue;
						}
					}
					else {
						System.out.println("����������������룺");
						continue;
					}//else
					break;
					
					
				case "3":
					//����
					System.out.println("����(edX,edY):");
					strLine=reader.readLine().trim();
					splitItems=strLine.split("\\s");
					if(splitItems.length==2) {
						try {
							int edX=Integer.valueOf(splitItems[0]);
							int edY=Integer.valueOf(splitItems[1]);
							player player=players.get(pNI);
							game.removePiece(player, new Position(edX,edY));
							pNI=(pNI+1)%2;
							System.out.println("success");
						}catch(Exception e) {
							System.out.println(e.toString());
							continue;
						}
					}
					else {
						System.out.println("����������������룺");
						continue;
					}//else
					break;
					
				case "4":
					//���ӣ�
					System.out.println("����(stX,stY,edX,edY):");
					strLine=reader.readLine().trim();
					splitItems=strLine.split("\\s");
					if(splitItems.length==4) {
						try {
							int stX=Integer.valueOf(splitItems[0]);
							int stY=Integer.valueOf(splitItems[1]);
							int edX=Integer.valueOf(splitItems[2]);
							int edY=Integer.valueOf(splitItems[3]);
							player player1=players.get(pNI);
							player player2=players.get((pNI+1)%2);
							game.eatPiece(player1, new Position(stX,stY), player2,new Position(edX,edY));
							pNI=(pNI+1)%2;
							System.out.println("success");
						}catch(Exception e) {
							System.out.println(e.toString());
							continue;
						}
					}
					else {
						System.out.println("����������������룺");
						continue;
					}//else
					break;
					
				case "5":
					//��ѯĳ��λ�õ�ռ�����
					System.out.println("����(edX,edY):");
					strLine=reader.readLine().trim();
					splitItems=strLine.split("\\s");
					if(splitItems.length==2) {
						try {
							int px=Integer.valueOf(splitItems[0]);
							int py=Integer.valueOf(splitItems[1]);
							piece piece=game.getOccupationOfPos(new Position(px,py));
							
							player player=game.getOwnerAtCord(new Position(px,py));
							//pNI=(pNI+1)%2;
							if(player==null) {
								System.out.println("��λ��û������");								
								} else {									
									System.out.println(String.format("��λ��Ϊ %s �� %s ����",											
											player.getPlayerName(),piece.getPieceName()));								
								}
					
						}catch(Exception e) {
							System.out.println(e.toString());
							continue;
						}
					}
					else {
						System.out.println("����������������룺");
						continue;
					}//else
					break;
					
				case "6":
					//����������ҷֱ��������ϵ���������
					System.out.println(String.format("���\t%s\t�������ϵ�����������%d",players.get(0).getPlayerName(),
							game.getNumOfPiecesInBoard(players.get(0))));
					System.out.println(String.format("���\t%s\t�������ϵ�����������%d",players.get(1).getPlayerName(),
							game.getNumOfPiecesInBoard(players.get(1))));
					//pNI=(pNI+1)%2;
					break;
					
				case "7":
					System.out.println("����");
					pNI=(pNI+1)%2;
					break;
					
				case "end":
					System.out.println("---------game over!--------");
					exitFlag=true;
					break;
				default:
					System.out.println("�����������������");
					break;
				}//switch
				if(exitFlag)break;
				
			}//while
			
			pNI=0;
			for(pNI=0;pNI<2;pNI++) {
				while(true) {
					System.out.println();
					System.out.println(String.format("->%s", players.get(pNI).getPlayerName()));
					System.out.println("�Ƿ�鿴���β�����ʷ��[yes,no]");
					boolean exitFlag=false;
					switch(reader.readLine().trim()) {
					case "yes":
						System.out.println(players.get(pNI).getHistory());
						break;
					case "no":
						exitFlag=false;
						break;
					default:
						exitFlag=false;
						break;
					}
					if(exitFlag)break;
				}
			}
			System.out.println("��Ϸ�������ټ���");
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				reader.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new MyChessAndGoGame().gameMain();
	}
}
