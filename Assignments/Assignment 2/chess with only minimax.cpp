#include "..\\include\\autoPlayer.h"
#include<iostream>
#include "..\\include\\ENUM.h"
#include<cstdlib>
using namespace std;

autoPlayer::autoPlayer(Color playerColor):chessPlayer("Auto Player Name", playerColor)
{

}

int evaluationFunction(gameState state)
{
	//pieces counting: part of middle strategy
	int evaluatingValue = 0;
	for (int a = 0; a < 8; a++)
	{
		for (int b = 0; b < 8; b++)
		{
			evaluatingValue += state.Board.board[a][b];
		}
	}
	return evaluatingValue;
}

void autoPlayer::decideMove(gameState* state, action* Move, int maxDepth ){

    int TotalMovesOfCurrentState = state->Actions.getActionCount(); //= state->computePossibleActions();
    cout<<endl<<"Total Possible Moves "<< TotalMovesOfCurrentState;
    if(TotalMovesOfCurrentState == 0){
        Move->fromRow = Move->fromCol= Move->toRow = Move->toCol = 0;
        return;
    }

	gameState*OneStepAheadState;

	//no of states one step ahead will be equal to current actions/moves 
	OneStepAheadState = new gameState[TotalMovesOfCurrentState];

	int maxEvaluationIndex = -500; 
	int maxEvaluationValue = -500; //alpha
	int minEvaluationIndex = 500;  
	int minEvaluationValue = 500; //beta
	//Checking every move of the present state and then apply that move and check that state
	for (int i = 0; i < TotalMovesOfCurrentState ; i++)
	{
		action currAction;
		gameState GameStateAfterCurrAction;
		GameStateAfterCurrAction.setPlayer(state->getPlayer());
		GameStateAfterCurrAction.Actions= state->Actions;
		for (int e = 0; e < 8; e++)
		{
			for (int f = 0; f < 8; f++)
			{
				GameStateAfterCurrAction.Board.board[e][f] = state->Board.board[e][f];
			}
		}

		state->Actions.getAction(i, &currAction);
		GameStateAfterCurrAction.applyMove(currAction);
		OneStepAheadState[i] = GameStateAfterCurrAction;


		//base case
		if (maxDepth == 1)
		{
			int temp;
			temp = evaluationFunction(OneStepAheadState[i]);
			//maximizing for white: How good this move was for current white
			if (state->getPlayer() == White && temp >= maxEvaluationValue)
			{
				maxEvaluationValue = temp;
				maxEvaluationIndex = i;
			}
			else if (state->getPlayer() == Black && temp <= minEvaluationValue)//Minimizing for Black:How good was this move for Current Black
			{
				minEvaluationValue = temp;
				minEvaluationIndex = i;
			}
		}
		else //recursive call
		{
			action CurrBestMove;
			gameState GameStateAfterCurrBestAction;
			GameStateAfterCurrBestAction.setPlayer(OneStepAheadState[i].getPlayer());
			GameStateAfterCurrBestAction.Actions = OneStepAheadState[i].Actions;

			for (int e = 0; e < 8; e++)
			{
				for (int f = 0; f < 8; f++)
				{
					GameStateAfterCurrAction.Board.board[e][f] = OneStepAheadState[i].Board.board[e][f];
				}
			}

			decideMove(&OneStepAheadState[i], &CurrBestMove, maxDepth - 1);

			GameStateAfterCurrBestAction.applyMove(CurrBestMove);

			//getting best state of next level: if the next level was of white then it has returned its local maxima

			int temp; // v in alpha beta pruning
			temp = evaluationFunction(GameStateAfterCurrBestAction);
			//maximizing for white: If I am white I will try to maximize value
			if (state->getPlayer() == White && temp >= maxEvaluationValue)
			{
				maxEvaluationValue = temp;
				maxEvaluationIndex = i;
			}
			else if (state->getPlayer() == Black && temp <= minEvaluationValue)//Minimizing for Black: If I am black I will try to minimize value
			{
				minEvaluationValue = temp;
				minEvaluationIndex = i;
			}
		}
	}
	if (state->getPlayer() == White)
	{
		state->Actions.getAction(maxEvaluationIndex, Move);
	}
	else
		state->Actions.getAction(minEvaluationIndex, Move);
    return;
}


