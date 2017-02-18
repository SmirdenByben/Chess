package com.chess.engine.board;
import java.util.HashMap;
import java.util.Map;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;
//we cant instantiate this class 
public abstract class Tile {
	protected final int tileCoordinate;
	
	private static final Map<Integer, EmptyTile> EMPTY_TILES_CASHE = createAllPossibleEmptyTiles();
	//Immutable means that once the constructor for an object has completed execution that instance can't be altered.
	static Tile createTile(final int tileCoordinate, final Piece piece){
		return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CASHE.get(tileCoordinate);
	}
	//private constructor (immutable)
	private Tile(final int tileCoordinate){
		this.tileCoordinate = tileCoordinate;
		
	}
	private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
		
		final Map<Integer, EmptyTile>emptyTileMap = new HashMap<>();
		
		for(int i = 0; i < BoardUtils.NUM_TILES; i++){
			emptyTileMap.put(i, new EmptyTile(i));
		}
		return ImmutableMap.copyOf(emptyTileMap);
	}
	public abstract boolean isTileOccupied();
	
	public abstract Piece getPiece();
	
	public int getTileCoordinate(){
		return this.tileCoordinate;
	}
	//Subclass is tile empty
	public static final class EmptyTile extends Tile{
		EmptyTile(final int coordinate){
			super(coordinate);
		}
		@Override
		public String toString(){
			return "-";
		}
		
		
		@Override
		public boolean isTileOccupied(){
			return false;
		}
		@Override
		public Piece getPiece(){
			return null;
		}
	}
	//Subclass for occupied tiles
	public static final class OccupiedTile extends Tile{
		//no way to reference this variable without calling piece on it
		private final Piece pieceOnTile;
		
		OccupiedTile(int tileCoordinate, final Piece pieceOnTile){
			super(tileCoordinate);
			this.pieceOnTile = pieceOnTile;
		}
		@Override
		public boolean isTileOccupied(){
			return true;
		}
		@Override
		public Piece getPiece(){
			return this.pieceOnTile;
		}
		@Override
		public String toString(){
			return getPiece().getPieceAlliance().isBlack() ? getPiece().toString().toLowerCase() : getPiece().toString();
		}
	}
}
