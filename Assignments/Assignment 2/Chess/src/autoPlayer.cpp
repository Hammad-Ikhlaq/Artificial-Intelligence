#include "..\\include\\autoPlayer.h"
#include<iostream>
#include "..\\include\\ENUM.h"
#include<cstdlib>
using namespace std;

autoPlayer::autoPlayer(Color playerColor):chessPlayer("Auto Player Name", playerColor)
{

}
int maxVal(gameState state, int alpha, int beta, int depth);
int minVal(gameState state, int alpha, int beta, int depth);
int evaluationFunction(gameState state)
{
	//pieces counting: part of middle strategy
	int evaluatingValue = 0;
	for (int a = 0; a < 8; a++)
	{
		for (int b = 0; b < 8; b++)
		{
			evaluatingValue += (state.Board.board[a][b]*100);
		}
	}
	return evaluatingValue;
}
int maxVal(gameState state, int alpha, int beta, int depth)//depth is required for terminal test
{
	if (depth == 0)
		return evaluationFunction(state);

	//recursive case
	int v = -500;
	for (int i = 0; i < state.Actions.getActionCount(); i++)
	{
		action currAction;
		gameState GameStateAfterCurrAction;
		GameStateAfterCurrAction.setPlayer(state.getPlayer());
		GameStateAfterCurrAction.Actions = state.Actions;
		for (int e = 0; e < 8; e++)
		{
			for (int f = 0; f < 8; f++)
			{
				GameStateAfterCurrAction.Board.board[e][f] = state.Board.board[e][f];
			}
		}
		state.Actions.getAction(i, &currAction);
		GameStateAfterCurrAction.applyMove(currAction);
		if (minVal(GameStateAfterCurrAction,alpha,beta,depth-1)>v)
		{
			v = minVal(GameStateAfterCurrAction, alpha, beta, depth - 1);
		}
		if (v >= beta)
			return v;
		if (v > alpha)
			alpha = v;
	}
	return v;
}

int minVal(gameState state,int alpha,int beta,int depth)//depth is required for terminal test
{
	if (depth == 0)
		return evaluationFunction(state);

	//recursive case
	int v = 500;
	for (int i = 0; i < state.Actions.getActionCount(); i++)
	{
		action currAction;
		gameState GameStateAfterCurrAction;
		GameStateAfterCurrAction.setPlayer(state.getPlayer());
		GameStateAfterCurrAction.Actions = state.Actions;
		for (int e = 0; e < 8; e++)
		{
			for (int f = 0; f < 8; f++)
			{
				GameStateAfterCurrAction.Board.board[e][f] = state.Board.board[e][f];
			}
		}
		state.Actions.getAction(i, &currAction);
		GameStateAfterCurrAction.applyMove(currAction);
		if (maxVal(GameStateAfterCurrAction, alpha, beta, depth - 1) < v)
		{
			v = maxVal(GameStateAfterCurrAction, alpha, beta, depth - 1);
		}
		if (alpha >= v)
			return v;
		if (v <beta)
			beta = v;
	}
	return v;
}

action alphaBetaSearch(gameState state,int depth)
{
	//v has evaluation value
	int v, minbestMoveScore=500, maxbestMoveScore=-500;
	action currMove,bestMove;
	gameState*OneStepAheadState;

	//no of states one step ahead will be equal to current actions/moves 
	OneStepAheadState = new gameState[state.Actions.getActionCount()];

	for (int i = 0; i < state.Actions.getActionCount(); i++)
	{
		gameState GameStateAfterCurrAction;
		GameStateAfterCurrAction.setPlayer(state.getPlayer());
		GameStateAfterCurrAction.Actions = state.Actions;
		for (int e = 0; e < 8; e++)
		{
			for (int f = 0; f < 8; f++)
			{
				GameStateAfterCurrAction.Board.board[e][f] = state.Board.board[e][f];
			}
		}

		state.Actions.getAction(i, &currMove);
		GameStateAfterCurrAction.applyMove(currMove);
		OneStepAheadState[i] = GameStateAfterCurrAction;
		if (state.getPlayer() == Black)
		{
			v = minVal(OneStepAheadState[i], -500, 500, depth - 1);//because autoplayer is black and black will try to minimize

			// only for opening phase
			if ((currMove.fromRow == 6 && currMove.toRow == 4 && currMove.fromCol == 0 && currMove.fromCol == 0) ||  (currMove.fromRow == 6 && currMove.toRow == 4 && currMove.fromCol == 2 && currMove.fromCol == 2) ||  (currMove.fromRow == 6 && currMove.toRow == 4 && currMove.fromCol == 4 && currMove.fromCol == 4) || (currMove.fromRow == 6 && currMove.toRow == 4 && currMove.fromCol == 7 && currMove.fromCol == 7))
			{
				v -= 50;//giving lesser priority for border and their alternate for first double moves
			}

			if ((currMove.fromRow == 6 && currMove.toRow == 4 && currMove.fromCol == 1 && currMove.fromCol == 1) || (currMove.fromRow == 6 && currMove.toRow == 4 && currMove.fromCol == 3 && currMove.fromCol == 3) || (currMove.fromRow == 6 && currMove.toRow == 4 && currMove.fromCol == 5 && currMove.fromCol == 5) || (currMove.fromRow == 6 && currMove.toRow == 4 && currMove.fromCol == 6 && currMove.fromCol == 6))
			{
				v -= 75;//giving more priority for middle and their alternate for first double moves
			}

			if (v <= minbestMoveScore)
			{
				state.Actions.getAction(i, &bestMove);
				minbestMoveScore = v;
			}
		}
		else
		{
			v = maxVal(OneStepAheadState[i], -500, 500, depth - 1);//because autoplayer is black and black will try to minimize

			// only for opening phase
			if ((currMove.fromRow == 1 && currMove.toRow == 3 && currMove.fromCol == 0 && currMove.fromCol == 0) ||  (currMove.fromRow == 1 && currMove.toRow == 3 && currMove.fromCol == 2 && currMove.fromCol == 2)  || (currMove.fromRow == 1 && currMove.toRow == 3 && currMove.fromCol == 4 && currMove.fromCol == 4) || (currMove.fromRow == 1 && currMove.toRow == 3 && currMove.fromCol == 7 && currMove.fromCol == 7))
			{
				v += 50; //giving lesser priority for border and their alternate for first double moves
			}

			if ((currMove.fromRow == 1 && currMove.toRow == 3 && currMove.fromCol == 1 && currMove.fromCol == 1) || (currMove.fromRow == 1 && currMove.toRow == 3 && currMove.fromCol == 3 && currMove.fromCol == 3) || (currMove.fromRow == 1 && currMove.toRow == 3 && currMove.fromCol == 5 && currMove.fromCol == 5) || (currMove.fromRow == 1 && currMove.toRow == 3 && currMove.fromCol == 6 && currMove.fromCol == 6))
			{
				v += 75; //giving more priority for middle and their alternate for first double moves
			}

			if (v >= maxbestMoveScore)
			{
				state.Actions.getAction(i, &bestMove);
				maxbestMoveScore = v;
			}
		}
	}

	return bestMove;
}


void autoPlayer::decideMove(gameState* state, action* Move, int maxDepth ){

    int TotalMovesOfCurrentState = state->Actions.getActionCount(); //= state->computePossibleActions();
    cout<<endl<<"Total Possible Moves "<< TotalMovesOfCurrentState;
    if(TotalMovesOfCurrentState == 0){
        Move->fromRow = Move->fromCol= Move->toRow = Move->toCol = 0;
        return;
    }

	*Move = alphaBetaSearch(*state,maxDepth);

}


