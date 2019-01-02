import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

class tttlib{
   //initialize scanner for user input
   static Scanner input=new Scanner(System.in);

   public static void main(String [] args){
   	  int player_number=0;
   	  boolean valid=false;
   	  //ask for how many players
   	  while (valid==false){
   	  System.out.println("How many players? Enter 1 or 2:");
          player_number=input.nextInt();
          //execute 1 player method if 1 is entered
          if (player_number==1){
             game_one_player();
             valid=true;
          }
          //execute 2 player method if 2 is entered
          else if (player_number==2){
          	 game_two_players();
          	 valid=true;
          }
       }
   }

   //0=empty spot
   //1=X occupies the spot
   //2=O occupies the spot


   //establish helper function to error check
   public static int errorCheck(ArrayList <Integer> T){
		//check if size of board is 9
		if (T.size() != 9){
			return -1;
		}	
		//checks if the values in the board is 0, 1,or 2 only
		for (int i:T){
			if ((i != 0) && (i != 1) && (i != 2)){
				return -1;
			}
		}
		return 0;	
	}

   
   //method that initializes the board to contain only empty spots (must be a list of 0s)
   public static ArrayList <Integer> genBoard(){
      ArrayList <Integer> T= new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,0));
   	     return T;
   }

   //method that prints the tic tac toe board
   public static boolean printBoard(ArrayList <Integer> T){
      int i=0;;
      //set array list that represents the board count for printing
      ArrayList <String> temp= new ArrayList <String> (Arrays.asList("0","1","2","3","4","5","6","7","8"));
      //error check
      if (T.size()!=9){
        return false;
      }else if ((T.get(i)!=0) & (T.get(i)!=1) & (T.get(i)!=2)){
        	return false;
      //inserting Xs and Os instead of 1s and 2s for printing in respective slots
      }for (i=0;i<9;i++){
      	//insert X
        if (T.get(i)==1){
           temp.set(i, "X");
        }else if (T.get(i)==2){
           //insert O
           temp.set(i,"O");
      }
      }
        //print board
        System.out.println(" "+temp.get(0)+" | "+temp.get(1)+" | "+temp.get(2));
        System.out.println("---|---|---");
        System.out.println(" "+temp.get(3)+" | "+temp.get(4)+" | "+temp.get(5));
        System.out.println("---|---|---");
        System.out.println(" "+temp.get(6)+" | "+temp.get(7)+" | "+temp.get(8));
        return true;
    }
     

    //method that anaylyzes the board and returns a value that represents the state of the game
    //0=in play
    //1=X won
    //2=O won
    //3=Draw game
    //-1=Error  
    public static int analyzeBoard(ArrayList <Integer> T){
       //error checking
       if (errorCheck(T) == -1){
			return -1;
	   }
       //X wins
       //horizontal win
       else if (((T.get(0)*T.get(1)*T.get(2))==1) || ((T.get(3)*T.get(4)*T.get(5))==1) || ((T.get(6)*T.get(7)*T.get(8))==1)){
          return 1;
       }
       //vertical win
       else if (((T.get(0)*T.get(3)*T.get(6))==1) || ((T.get(1)*T.get(4)*T.get(7))==1) || ((T.get(2)*T.get(5)*T.get(8))==1)){
          return 1;
       }
       //diagonal win
       else if (((T.get(0)*T.get(4)*T.get(8))==1)){
          return 1;
       }
       else if (((T.get(2)*T.get(4)*T.get(6))==1)){
          return 1;
       }
       //O wins
       //horizontal win
       else if (((T.get(0)*T.get(1)*T.get(2))==8) || ((T.get(3)*T.get(4)*T.get(5))==8) || ((T.get(6)*T.get(7)*T.get(8))==8)){
          return 2;
       }
       //vertical win
       else if (((T.get(0)*T.get(3)*T.get(6))==8) || ((T.get(1)*T.get(4)*T.get(7))==8) || ((T.get(2)*T.get(5)*T.get(8))==8)){
          return 2;
       }
       //vertical win
       else if (((T.get(0)*T.get(4)*T.get(8))==8)){
          return 2;
       }
       else if (((T.get(2)*T.get(4)*T.get(6))==8)){
          return 2;
       //board in play
       }
       else if (((T.get(0)*T.get(1)*T.get(2))==0) || ((T.get(3)*T.get(4)*T.get(5))==0) || ((T.get(6)*T.get(7)*T.get(8))==0)){
          return 0;
       //draw
       }
       else if (((T.get(0)*T.get(1)*T.get(2)==4) || ((T.get(3)*T.get(4)*T.get(5))==4) || ((T.get(6)*T.get(7)*T.get(8))==4))){
          return 3;
       }
       else if (((T.get(0)*T.get(1)*T.get(2))==2) || ((T.get(3)*T.get(4)*T.get(5))==2) || ((T.get(6)*T.get(7)*T.get(8))==2)){
          return 3;
       }
       return -1;
    }
  

    //method that executes games for 2 players
    public static void game_two_players (){
       ArrayList <Integer> T= new ArrayList<Integer>();
       int moveX=0;
       int moveO=0;
       int state=0;
       boolean valid=false;
       //initializes board using method in class
       T=genBoard();
       while (true){
          printBoard(T);
          //asks X player to enter spot they wish to play in
          System.out.println("X move?");
          moveX=input.nextInt();
          //error check that move is valid (valid spot entered and spot is open)
          while (valid==false){
				if ((moveX>=0) && (moveX<=8)){
					if (T.get(moveX)==0){
						valid=true;
					}
					//check if spot is open
					else{
						System.out.println("Error: Spot is taken");
						System.out.println("X move?");
						moveX=input.nextInt();
					}
				}
				//check if input is in range
				else{
					System.out.println("Error: Spot is out of range");
					System.out.println("X move?");
					moveX=input.nextInt();
				}
			}
		  valid=false;
          //puts player in the position entered
          T.set(moveX,1);
          //analyze the state of the board after player plays
          state=analyzeBoard(T);
          //returns final state of game
          if (state==1){
          	System.out.println("X wins");
          	break;
          }
          else if (state==3){
          	System.out.println("Draw");
          	break;
          }

          printBoard(T);
          //asks O player to enter spot they wish to play in
          System.out.println("O move?");
          moveO=input.nextInt();
          //error check that move is valid (valid spot entered and spot is open)
          while (valid==false){
				if ((moveO>=0) && (moveO<=8)){
					if (T.get(moveX)==0){
						valid=true;
					}
					//check if spot is open
					else{
						System.out.println("Error: Spot is taken");
						System.out.println("O move?");
						moveO=input.nextInt();
					}
				}
				//check if input is in range
				else{
					System.out.println("Error: Spot is out of range");
					System.out.println(") move?");
					moveO=input.nextInt();
				}
			}
		  valid=false;
          //puts player in the position entered
          T.set(moveO,2);
          //analyze the state of the board after player plays
          state=analyzeBoard(T);
          //returns final state of game
          if (state==2){
          	System.out.println("O wins");
          	break;
          }
          else if (state==3){
          	System.out.println("Draw");
          	break;
          }
       }

    }

    //helper function that determines the opponent
    public static int opponent (int player){
       int opponent=0;
       if (player==1){
          opponent=2;
       }
       else if (player==2){
       	  opponent=1;
       }
       return opponent;
    }


    //method that determines the first slot that is unoccupied
    public static int genOpenMove(ArrayList <Integer> T){
       //error check
       if (errorCheck(T) == -1){
			return -1;
	   }
       int cnt=0;
          for (int i:T){
             if (i==0){
             	return cnt;
             }
             cnt=cnt+1;
          }
       return -1;
    }

    
    //method that returns a random unoccupied spot
    public static int genRandomMove(ArrayList <Integer> T){
       //error check
       if (errorCheck(T) == -1){
			return -1;
	   }
	   //variable that checks if a spot is unoccupied
       boolean haszero=false;
       //random function that will generate random integers
       Random random_int=new Random();
       int random_move;
       for (int i:T){
          if (i==0){
             haszero=true;
          }
       }
       if (haszero==false){
          return -1;
       }
       while (true){
       	  //generates random integer between 0-8 (corresponding to the spot numbers) and checks if the spot if open
          random_move=random_int.nextInt(9);
          if (T.get(random_move)==0){
             return random_move;
          }
       }
    }
   


    //method that returns the value of an unoccupied spot that will prevent the opponent from winning the game (defensive play)
    public static int genNonLoser(ArrayList <Integer> T, int player){
       //error check
       if (errorCheck(T) == -1){
			return -1;
	   }
       int cnt=0;
       int state=0;
       ArrayList <Integer> copyT= new ArrayList <Integer>();
       //check every unoccupied spots to see if it is a potential non losing move
       for (int i:T){
          if (i==0){
          	//copy original game state (T)
          	copyT = (ArrayList) T.clone();
          	copyT.set(cnt,opponent(player));
          	state=analyzeBoard(copyT);
          	if (state==opponent(player)){
               return cnt;
          	}
          }
          cnt=cnt+1;
       }
       //if there are no non losing moves, then execute random move method
       return genRandomMove(T);
    }


    //method that returns the value of an unoccupied spot that will win the game (offensive play)
    public static int genWinningMove (ArrayList <Integer> T, int player){
       //error check
       if (errorCheck(T) == -1){
			return -1;
	   }
       int cnt=0;
       int state=0;
       ArrayList <Integer> copyT= new ArrayList <Integer>();
       //copy original game state (T)
       copyT = (ArrayList) T.clone();
       //check every unoccupied spots to see if it is a potential winning move
       for (int i:copyT){
          if (i==0){
          	copyT.set(cnt,player);
          	state=analyzeBoard(copyT);
          	//detects winning move
          	if (state==player){
               return cnt;
          	}
          }
          cnt=cnt+1;
       }
       //if there are no winning moves, then execute random move method
       return genRandomMove(T);
    }

    public static int play (ArrayList <Integer> T, int player){
       int goal;
       int state;
       ArrayList <Integer> copyT= new ArrayList <Integer>();
       goal=genWinningMove(T,player);
       //copy original game state (T)
       copyT = (ArrayList) T.clone();
       copyT.set(goal,player);
       state=analyzeBoard(copyT);
       if (state==player){
          return goal;
       }
       else{
          return genNonLoser(T,player);
       }
    }
    

    //method that executes games for 1 player (plays against CPU)
    public static void game_one_player(){
       ArrayList <Integer> T= new ArrayList<Integer>();
       int moveX=0;
       int moveO=0;
       int state=0;
       boolean valid=false;
       //initializes board using method in class
       T=genBoard();
       //while the game is still in play
       while (true){
          printBoard(T);
          //asks player to enter spot they wish to play in
          System.out.println("X move? Enter number from 0-8:");
          moveX=input.nextInt();
          //error check that move is valid (valid spot entered and spot is open)
          while (valid==false){
				if ((moveX>=0) && (moveX<=8)){
					if (T.get(moveX)==0){
						valid=true;
					}
					//check if spot is open
					else{
						System.out.println("Error: Spot is taken");
						System.out.println("X move?");
						moveX=input.nextInt();
					}
				}
				//check if input is in range
				else{
					System.out.println("Error: Spot is out of range");
					System.out.println("X move?");
					moveX=input.nextInt();
				}
			}
		  valid=false;
          //puts player in the position entered
          T.set(moveX,1);
          //analyze the state of the board after player plays
          state=analyzeBoard(T);
          //returns final state of game
          if (state==1){
          	System.out.println("X wins");
          	break;
          }
          else if (state==3){
          	System.out.println("Draw");
          	break;
          }
          
          //CPU plays
          printBoard(T);
          moveO=play(T,2);
          T.set(moveO,2);
          state=analyzeBoard(T);
          if (state==2){
          	System.out.println("O wins");
          	break;
          }
          else if (state==3){
          	System.out.println("Draw");
          	break;
          }
       }

    }



}