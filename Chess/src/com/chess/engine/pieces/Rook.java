package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Piece.PieceType;
import com.google.common.collect.ImmutableList;

public class Rook extends Piece {

	public Rook( Alliance pieceAlliance, final int piecePosition) {
		super(PieceType.ROOK, piecePosition, pieceAlliance);
	}
	
	private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-1, -8, 1, 8};
	public Collection<Move> calculateLegalMoves(final Board board) {
		final List<Move> legalMoves = new ArrayList<>();
		for(final int candidateCoordinateOffset: CANDIDATE_MOVE_VECTOR_COORDINATES){
			int candidateDestinationCoordinate = this.piecePosition;
			//Keep going through the loop checking if moves are valid including offset moves being not valid
			while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){	
				if((isFirstCollumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset))||
						isEightCollumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)){
					break;
				}
				candidateDestinationCoordinate += candidateCoordinateOffset;
				if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
					final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);		
					if(!candidateDestinationTile.isTileOccupied()){
						legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
					}else{
						final Piece pieceAtDestination = candidateDestinationTile.getPiece();
						final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();	
						//if enemy piece is in the way making attack move
						if(this.pieceAlliance != pieceAlliance){
							legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
						}
						//to break the loop if something is in the way of diagonal moves
						break;
					}
				}
			}
		}
		return ImmutableList.copyOf(legalMoves);
	}
	@Override
	public String toString(){
		return Piece.PieceType.ROOK.toString();
	}
	// Exclusions (out of bounds rules)
	private static boolean isFirstCollumnExclusion(final int currentPosition, final int candidateOffset){
		return BoardUtils.FIRST_COLUMN[currentPosition] && candidateOffset == -1;
	}
	private static boolean isEightCollumnExclusion(final int currentPosition, final int candidateOffset){
		return BoardUtils.EIGHT_COLUMN[currentPosition] && candidateOffset == 1;
	}
	@Override
	public Rook movePiece(final Move move) {
		return new Rook(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
	}
}
