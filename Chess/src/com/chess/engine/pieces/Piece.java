package com.chess.engine.pieces;
import java.util.Collection;

import com.chess.engine.board.*;


public abstract class Piece {
	
	
	protected final PieceType pieceType;
	protected int piecePosition;
	protected final Alliance pieceAlliance;
	protected final boolean isFirstMove;
	private final int cashedHashCode;
	
	public Piece (final PieceType pieceType, final int piecePosition, final Alliance pieceAlliance){
		this.pieceType = pieceType;
		this.pieceAlliance = pieceAlliance;
		this.piecePosition = piecePosition;
		//Adjust the method
		this.isFirstMove = false;
		this.cashedHashCode = computeHashCode();
	}
	
	private int computeHashCode() {
		int result = pieceType.hashCode();
		result = 31 * result + pieceAlliance.hashCode();
		result = 31 * result + piecePosition;
		result = 31 * result + (isFirstMove ? 1 : 0);
		return result;
	}

	public Alliance getPieceAlliance(){
		return this.pieceAlliance;
	}
	public boolean isFirstMove(){
		return this.isFirstMove;
	}

	public int getPiecePosition() {
		return this.piecePosition;
	}
	
	public PieceType getPieceType(){
		return this.pieceType;
	}
	@Override
	public boolean equals(final Object other){
		if(this == other){
			return true;
		}
		if(!(other instanceof Piece)){
			return false;
		}
		final Piece otherPiece = (Piece) other;
		return piecePosition == otherPiece.getPiecePosition() && pieceType == otherPiece.getPieceType() &&
				pieceAlliance == otherPiece.getPieceAlliance() && isFirstMove == otherPiece.isFirstMove();
	}
	@Override
	public int hashCode(){
		return this.cashedHashCode;
	}
	
	public abstract Collection<Move> calculateLegalMoves(final Board board);
	
	public abstract Piece movePiece(Move move);
	
	public enum PieceType{
		
		PAWN("P") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
			public boolean isRook() {
				// TODO Auto-generated method stub
				return false;
			}
		},
		KNIGHT("N") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
			public boolean isRook() {
				// TODO Auto-generated method stub
				return false;
			}
		},
		BISHOP("B") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
			public boolean isRook() {
				// TODO Auto-generated method stub
				return false;
			}
		},
		ROOK("R") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
			public boolean isRook() {
				// TODO Auto-generated method stub
				return true;
			}
		},
		QUEEN("Q") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
			public boolean isRook() {
				// TODO Auto-generated method stub
				return false;
			}
		},
		KING("K") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
			public boolean isRook() {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		private String pieceName;
		
		PieceType(final String pieceName){
			this.pieceName = pieceName;
		}
		@Override
		public String toString(){
			return this.pieceName;
		}
		
		public abstract boolean isKing();
		public abstract boolean isRook();
	}
}
