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
		//功能1针对围棋设置，功能234针对国际象棋设置
		System.out.println("1、将未在棋盘上的棋子放在棋盘指定位置");
		System.out.println("2、移动棋盘上的某个位置上的棋子到新的位置");
		System.out.println("3、提子(移除对方棋子)");
		System.out.println("4、吃子(使自己棋子吃掉对方棋子)");
		System.out.println("5、查询某个位置的占用情况");
		System.out.println("6、计算两个玩家分别在棋盘上的棋子总数");
		System.out.println("7、跳过");
		System.out.println("end、结束操作");
	}
	
	public void gameMain() {
		BufferedReader reader=null;
		String[] splitItems;
		String strLine;
		try {
			reader=new BufferedReader(new InputStreamReader(System.in));
			while(true) {
				System.out.println(String.format("输入游戏类型\t%s",supportType.
			    stream().reduce((a,b)->a+",\t"+b)));
				strLine=reader.readLine().trim();
				
				if(supportType.contains(strLine)) {
					break;
				}																																
				else {
					System.out.println("输入错误，请重新输入");
				}
			}
			System.out.println(strLine);
			System.out.println("[用户A]\t请输入您的名称");
			player1Name=reader.readLine().trim();
			System.out.println("[用户B]\t请输入您的名称");
			player2Name=reader.readLine().trim();
			game=new Game(strLine,player1Name,player2Name);
			
			players.add(game.getPlayer1());			
			players.add(game.getPlayer2());			
			System.out.println(String.format("%s，%s，游戏开始，请依次操作",player1Name,player2Name));
			
			int pNI=0;
			while(true) {
				System.out.println();				
				System.out.println(String.format("->[%s]", players.get(pNI).getPlayerName()));				
				printMenu();				
				strLine=reader.readLine().trim();				
				boolean exitFlag =false;				
				switch(strLine) {
				
				//将尚未在棋盘上的一颗棋子放置在棋盘的指定位置
				case "1":
					System.out.println("输入(pieceName,edX,edY):");
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
						System.out.println("输入错误，请重新输入：");
						continue;
					}//else
					break;
					
				case "2":
					//移动棋盘上某个位置的棋子到新的位置
					System.out.println("输入(stX,stY,edX,edY):");
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
						System.out.println("输入错误，请重新输入：");
						continue;
					}//else
					break;
					
					
				case "3":
					//提子
					System.out.println("输入(edX,edY):");
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
						System.out.println("输入错误，请重新输入：");
						continue;
					}//else
					break;
					
				case "4":
					//吃子：
					System.out.println("输入(stX,stY,edX,edY):");
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
						System.out.println("输入错误，请重新输入：");
						continue;
					}//else
					break;
					
				case "5":
					//查询某个位置的占用情况
					System.out.println("输入(edX,edY):");
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
								System.out.println("该位置没有棋子");								
								} else {									
									System.out.println(String.format("该位置为 %s 的 %s 棋子",											
											player.getPlayerName(),piece.getPieceName()));								
								}
					
						}catch(Exception e) {
							System.out.println(e.toString());
							continue;
						}
					}
					else {
						System.out.println("输入错误，请重新输入：");
						continue;
					}//else
					break;
					
				case "6":
					//计算两个玩家分别在棋盘上的棋子总数
					System.out.println(String.format("玩家\t%s\t在棋盘上的棋子总数是%d",players.get(0).getPlayerName(),
							game.getNumOfPiecesInBoard(players.get(0))));
					System.out.println(String.format("玩家\t%s\t在棋盘上的棋子总数是%d",players.get(1).getPlayerName(),
							game.getNumOfPiecesInBoard(players.get(1))));
					//pNI=(pNI+1)%2;
					break;
					
				case "7":
					System.out.println("跳过");
					pNI=(pNI+1)%2;
					break;
					
				case "end":
					System.out.println("---------game over!--------");
					exitFlag=true;
					break;
				default:
					System.out.println("输入错误，请重新输入");
					break;
				}//switch
				if(exitFlag)break;
				
			}//while
			
			pNI=0;
			for(pNI=0;pNI<2;pNI++) {
				while(true) {
					System.out.println();
					System.out.println(String.format("->%s", players.get(pNI).getPlayerName()));
					System.out.println("是否查看本次操作历史？[yes,no]");
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
			System.out.println("游戏结束，再见！");
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
