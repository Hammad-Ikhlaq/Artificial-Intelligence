#include "autoPlayer.h"
#include<iostream>
#include "ENUM.h"

using namespace std;

autoPlayer::autoPlayer(Color playerColor) :chessPlayer("Auto", playerColor){


}

int autoPlayer::StateEvaluator(gameState g, int alpha, int beta, int depth, bool turn)
{
	//Alpha beta pruning is used here and the function is recursive

	if (depth == 0)
	{
		int evaluation = Evaluator(g);    //Evaluators board
		return evaluation;
	}

	if (turn == false)
	{
		//minimizing white player

		g.computePossibleActions(1);   //to compute possible moves of the minimizing white player

		int newBeta = beta;
		action act;

		// The below for loop applies all possible actions on a dummy successor state and 
		// calls StateEvaluator on them
		
		for (int i = 0; i < g.Actions.getActionCount(); i++)
		{
			g.Actions.getAction(i, act);  //returns actions
			gameState successorState;
			successorState.applyMove(act);
			newBeta = std::min(newBeta, StateEvaluator(successorState, alpha, beta, depth - 1, !turn));
			if (newBeta <= alpha)
			{
				break;
			}
		}
		return newBeta;  //returns the highest score of the possible actions
	}
	else
	{
		//maximizing black player

		g.computePossibleActions(0);   //to compute possible actions of the maximizing black player only

		int newAlpha = alpha;
		action act;

		for (int i = 0; i < g.Actions.getActionCount(); i++)
		{
			g.Actions.getAction(i, act);  //returns action
			gameState successorState;
			successorState.applyMove(act);
			newAlpha = std::max(newAlpha, StateEvaluator(successorState, alpha, beta, depth - 1, !turn));
			if (beta <= newAlpha)
			{
				break;
			}
		}
		return newAlpha;           //returns the highest score of the possible actions	
	}
}

int autoPlayer::Evaluator(gameState g)
{
	int whitescore = 0;
	int blackscore = 0;

	for (int i = 0; i < 8; i++)
	{
		for (int j = 0; j < 8; j++)
		{
			if (g.Board.board[i][j] != 0)
			{
				if (g.Board.board[i][j] > 0)
				{
					//case that piece is white
					if (g.Board.board[i][j] == 5)
					{
						whitescore += 90;
					}
					else if (g.Board.board[i][j] == 4)
					{
						whitescore += 50;
					}
					else if (g.Board.board[i][j] == 2 || g.Board.board[i][j] == 3)
					{
						whitescore += 30;
					}
					else if (g.Board.board[i][j] == 1)
					{
						whitescore += 10;
					}
					else if (g.Board.board[i][j] == 6)
					{
						whitescore += 900;
					}
				}
				else if (g.Board.board[i][j] < 0)
				{
					//case that piece is black

					if (g.Board.board[i][j] == -5)
					{
						blackscore += 90;
					}
					else if (g.Board.board[i][j] == -4)
					{
						blackscore += 50;
					}
					else if (g.Board.board[i][j] == -2 || g.Board.board[i][j] == -3)
					{
						blackscore += 30;
					}
					else if (g.Board.board[i][j] == -1)
					{
						blackscore += 10;
					}
					else if (g.Board.board[i][j] == -6)
					{
						blackscore += 900;
					}
				}
			}
		}
	}
	return blackscore - whitescore; //returns blackscore - whitescore, black player trying to maximize and white player trying to minimize
}

void autoPlayer::decideMove(gameState* state, action* Move, int maxDepth)
{
	action bestMove; //for tracking best move
	int bestMoveScore; //best move score
	int DEPTH = maxDepth;

	state->computePossibleActions(0);       //generates possible actions for black only in the given game state

	gameState *possible_states = new gameState[state->Actions.getActionCount()];

	for (int i = 0; i < state->Actions.getActionCount(); i++)
	{
		gameState g;
		action act;
		state->Actions.getAction(i, act);
		g.applyMove(act);
		possible_states[i] = g;
	}

	//initializing bestMove to the first move
	action act;
	state->Actions.getAction(0, act);
	bestMove = act;
	bestMoveScore = StateEvaluator(possible_states[0], INT_MIN, INT_MAX, maxDepth, false);  // Depth 1 here

	//call StateEvaluator on each move
	//keep track of the move with the best score

	//if (numTurns > 0){
	for (int i = 1; i < state->Actions.getActionCount(); i++)
	{
		/*
		* calls StateEvaluator on each possible state and if the score is higher than the previous,
		* it updates the bestMove
		*/
		int ep = StateEvaluator(possible_states[i], INT_MIN, INT_MAX, DEPTH, false);
		if (ep >= bestMoveScore)
		{
			state->Actions.getAction(i, bestMove);
			bestMoveScore = ep;
		}
	}
	//}
	/*else{
	Random generator = new Random();
	int index = generator.nextInt(moves.size());
	bestMove = moves.get(index);
	}*/

	//numTurns++;
	*Move = bestMove;
	//return doMove(b, bestMove); //doMove performs the move on the original board and returns a string of that move
}

