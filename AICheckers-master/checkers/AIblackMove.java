/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dustin
 */
public class AIblackMove {

    //This is the current state of the game
    CheckersGame currentGame;
    //This array contains the legal moves at this point in the game for black.
    CheckersMove legalMoves[];

    // The constructor.
    public AIblackMove(CheckersGame game, CheckersMove moves[]) {
        currentGame = game;
        legalMoves = moves;
    }

    public int randomsafe (CheckersData board){
        int test = currentGame.generator.nextInt(legalMoves.length);
        
        currentGame.simulateMove(board, legalMoves[test],CheckersData.BLACK);
        int blacks = board.numBlack() + board.numBlackKing();
        CheckersMove[] cur_legalMoves;
        cur_legalMoves = board.getLegalMoves(1);
        if (cur_legalMoves[0] == null){
                return test;
            }
        currentGame.simulateMove(board, cur_legalMoves[0], CheckersData.RED);
        if (blacks > board.numBlack() + board.numBlackKing()){
            randomsafe(board);
        }
        return test;
    }
    
    public int seqsafe (CheckersData board){
        int index = 0;
        if ((this.currentGame.boardData.numRed() == 0) && 
                (this.currentGame.boardData.numRedKing() == 1) || 
                (this.currentGame.boardData.numRedKing() == 0) && 
                (this.currentGame.boardData.numRed() == 1)){
            return randomsafe(board);
        }
        
        for (int i=0; i<legalMoves.length; i++){
            currentGame.simulateMove(board, legalMoves[i],CheckersData.BLACK);
            int temp =evaluate(board);
            CheckersMove[] cur_legalMoves;
            cur_legalMoves = board.getLegalMoves(CheckersData.RED);
            if (cur_legalMoves[0] == null){
                return index;
            }
            currentGame.simulateMove(board, cur_legalMoves[0], CheckersData.RED);
            if (temp >= evaluate(board)){
                index = i;
            }
        }
        return index;
    }
    
    private class score
  {
    int index;
    int score;
    
    public score(int s, int i)
    {
      this.index = i;
      this.score = s;
    }
    
    public int getscore()
    {
      return this.score;
    }
    
    public int getindex()
    {
      return this.index;
    }
  }
    
    public AIblackMove.score minimax (CheckersData board, int color, int depth, long time){
    int index = 0;
    int hiscore = 0;
    CheckersMove[] poslegalmoves;
    
    poslegalmoves = this.legalMoves;
    boolean isRed = color == 1;
    
    
    if (System.nanoTime() - time > 3000000000L){                                    
            AIblackMove.score solu =  new AIblackMove.score(evaluate(board), -1);       //if out of time return last move
        return solu;
        }
    
    if ((depth == 0 || poslegalmoves == null)){
        AIblackMove.score move = new AIblackMove.score (evaluate(board), 0);            //if at terminal or no moves return best 
        return move;
    }
    if (this.legalMoves.length == 1){
        AIblackMove.score move = new AIblackMove.score (hiscore, 0);                    //if only one move make it
        return move;
    }
    for (int i = 0; i < poslegalmoves.length; i++){
        CheckersData new_board = new CheckersData(board);                               //iterate through all moves
        this.currentGame.simulateMove(new_board, poslegalmoves[i], isRed ? 1 : 3);
        
        AIblackMove.score temp = minimax(new_board, isRed ? 1: 3, depth -1, time);             //checking red now 
        
        if (((!isRed) && (temp.getscore() < hiscore)) || 
                ((isRed) && (temp.getscore() > hiscore)) ){ 
            hiscore = temp.getscore();                                                   //if new simulation is better keep it
            index = i;
        }
    }
    AIblackMove.score move = new AIblackMove.score(hiscore, index);
    return move;                                                                         // return best move
}
    // This is where your logic goes to make a move.
         
    public CheckersMove nextMove() {
        // Here are some simple ideas:
        // 1. Always pick the first move
       // return legalMoves[0];
        // 2. Pick a random move
       // return legalMoves[currentGame.generator.nextInt(legalMoves.length)];

        //Or you can create a copy of the current board like this:
//        CheckersData new_board = new CheckersData(currentGame.boardData);
//        //You can then simulate a move on this new board like this:
//        int max = evaluate(new_board);
//        int temp;
//        int bestmove = 0;
//        for (int i=0; i<=legalMoves.length - 1; i++){
//            currentGame.simulateMove(new_board, legalMoves[i],CheckersData.BLACK);
//            if(jumpcheck(new_board) == true) {
//                temp = -100;
//            }
//            else temp = evaluate(new_board);
//            if (temp > max){
//                bestmove = i;
//            }
//         
//        } 
        //After you simulate the move you can evaluate the state of the board
        //after the move and see how it looks.  You can evaluate all the 
        //currently legal moves using a loop and select the best one.
        //return legalMoves[bestmove];
        
        CheckersData new_board = new CheckersData(currentGame.boardData);
        
        if (legalMoves.length == 1){
            return legalMoves[0];
        }
        else
           return legalMoves[seqsafe(new_board)];


//        int bestmove = 0;
//        int temp = 0;
//        int score = 0;
//        
//        for (int i=0; i<=legalMoves.length - 1; i++){
//            currentGame.simulateMove(new_board, legalMoves[i],CheckersData.BLACK);
//            int blacks = new_board.numBlack();
//            currentGame.simulateMove(new_board, legalMoves[0], CheckersData.RED);
//            if(new_board.numBlack() < blacks)
//                temp = -1;
//            if (temp > score){
//                score = temp;
//                bestmove = i;
//            }
//         }
//        return legalMoves[bestmove];

//        if((this.currentGame.boardData.numRed() == 0) && (this.currentGame.boardData.numRedKing() == 1)){
//            return legalMoves[currentGame.generator.nextInt(legalMoves.length)];                                    //if 1 king left just be random
//        }
//        AIblackMove.score best = new AIblackMove.score (100000, 0);
//        long time = System.nanoTime();
//        best = minimax(new_board, 3, 5, time);                                                                      //passing board black depth and time
//                                                                                                                    //time isn't cutting off recursion currently
//        return this.legalMoves[best.getindex()];
    }
        
    
    

    
    // One thing you will probably want to do is evaluate the current
    // goodness of the board.  This is a toy example, and probably isn't
    // very good, but you can tweak it in any way you want.  Not only is
    // number of pieces important, but board position could also be important.
    // Also, are kings more valuable than regular pieces?  How much?
   // int evaluate(CheckersData board) {
     //   return board.numBlack()+ board.numBlackKing() 
       //         - board.numRed() - board.numRedKing();
    //}
    int evaluate(CheckersData board) {
        if (board.numBlack() + board.numBlackKing() == 0) {
            return 10;
        }
        if (board.numRed() + board.numRedKing() == 0) {
            return 100000;
        }
        int blackweight =  board.numBlack() + 2 * board.numBlackKing();
        int redweight =  board.numRed() + 2 * board.numRedKing();
        //int defense = board.pieceAt(0,0) + board.pieceAt(0,2) + board.pieceAt(0,4) + board.pieceAt(0,6);
        return blackweight - redweight;
    }
}
