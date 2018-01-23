package gheppettoleathers;

import java.io.Serializable; // TODO So you can easily save/load using an ObjectStream
import java.util.ArrayList;

/**
 * A storage structure used to maintain the inventory counts for use in Charles's (cnix3297) inventory software.
 *
 * @author Mark Zeagler
 * @version 0.1
 */
public class Belts implements Serializable {
	private static final long serialVersionUID = -9083083898244500649L;

	/**
	 * The color of the belt material.
	 */
	public enum Color {
		Black( 0 ), Brown( 1 );

		public int index;

		Color( int index ) {
			this.index = index;
		}
	} // TODO Not modifiable at runtime, also difficult to modify if using saved data

	// An enum would make it much easier to work with from a programming perspective, but it would make it much more difficult to change the available patterns.
	//	public enum Pattern {
	//		Football, Hearts, Love, Basketball, Baseball, Eagle, Trucker, Basket_Weave, Edge, Rebel, UGA, Gamecocks, Flowers, Deer_Scene, Raccoon_Scene, Hunter_Scene, Horse_Scene, Fish_Scene;
	//
	//		DesignPattern pattern;
	//	}

	/**
	 * The width of the belt.
	 */
	public enum Width {
		Quarter_Inch( 0 ), Half_Inch( 1 ), Inch(
				2 ); // TODO I randomly came up with these names, leave the values as is, though

		int index;

		Width( int index ) {
			this.index = index;
		}
	} // TODO Not modifiable at runtime, also difficult to modify if using saved data

	// TODO There might be a better way to do this. You might want to create a Patterns object that mimics an enum, but can be changed at runtime.
	public ArrayList<DesignPattern> patterns = new ArrayList<>();

	/**
	 * Use this inventory to store the quantities of each belt type.
	 */
	private int[][][][] inventory;

	/**
	 * The minimum size that a belt is allowed to be.
	 */
	public static final int MIN_BELT_SIZE = 5; // TODO I randomly came up with these, modify them as needed.

	/**
	 * The maximum size that a belt is allowed to be.
	 */
	public static final int MAX_BELT_SIZE = 29; // TODO I randomly came up with these, modify them as needed.

	//	/**
	//	 * The minimum width that the belt is allowed to be.
	//	 */
	//	public static final int MIN_BELT_WIDTH = 0;
	//
	//	/**
	//	 * The maximum width that the belt is allowed to be.
	//	 */
	//	public static final int MAX_BELT_WIDTH = 2;

	/**
	 * Creates an empty inventory of belts with all amounts set to 0.
	 */
	public Belts() {
		initialize();
		for ( int w = 0; w < inventory.length; w++ ) {
			for ( int x = 0; x < inventory[0].length; x++ ) {
				for ( int y = 0; y < inventory[0][0].length; y++ ) {
					for ( int z = 0; z < inventory[0][0][0].length; z++ ) {
						this.inventory[w][x][y][z] = 0;
					}
				}
			}
		}
	}

	/**
	 * Constructs a copy belt inventory from the given inventory.
	 *
	 * @param oldBelts The old inventory to be copied.
	 */
	public Belts( Belts oldBelts ) {
		initialize();
		for ( int w = 0; w < inventory.length; w++ ) {
			for ( int x = 0; x < inventory[0].length; x++ ) {
				for ( int y = 0; y < inventory[0][0].length; y++ ) {
					for ( int z = 0; z < inventory[0][0][0].length; z++ ) {
						this.inventory[w][x][y][z] = oldBelts.inventory[w][x][y][z];
					}
				}
			}
		}
	}

	// To reduce some redundancies
	private void initialize() {
		patterns.add( new ImprintedPattern( "Plain" ) );

		// Add painted patterns
		// "Football", "Hearts", "Love", "Basketball", "Baseball",
		// "Eagle", "Trucker", "Basket Weave", "Edge",
		// "Rebel", "UGA", "GameCocks"
		patterns.add( new PaintedPattern( "Football" ) );
		patterns.add( new PaintedPattern( "Hearts" ) );
		patterns.add( new PaintedPattern( "Love" ) );
		patterns.add( new PaintedPattern( "Basketball" ) );
		patterns.add( new PaintedPattern( "Baseball" ) );
		patterns.add( new PaintedPattern( "Eagle" ) );
		patterns.add( new PaintedPattern( "Trucker" ) );
		patterns.add( new PaintedPattern( "Basket Weave" ) );
		patterns.add( new PaintedPattern( "Edge" ) );
		patterns.add( new PaintedPattern( "Rebel" ) );
		patterns.add( new PaintedPattern( "UGA" ) );
		patterns.add( new PaintedPattern( "Gamecocks" ) );

		// Add imprinted patterns
		//"Flower", "Dear Scene", "Racoon Scene",
		// "Hunter Scene", "Fish Scene", "Horse Scene"
		patterns.add( new ImprintedPattern( "Flower" ) );
		patterns.add( new ImprintedPattern( "Deer Scene" ) );
		patterns.add( new ImprintedPattern( "Raccoon Scene" ) );
		patterns.add( new ImprintedPattern( "Hunter Scene" ) );
		patterns.add( new ImprintedPattern( "Fish Scene" ) );
		patterns.add( new ImprintedPattern( "Horse Scene" ) );

		this.inventory = new int[Color.values().length][MAX_BELT_SIZE - MIN_BELT_SIZE][patterns.size()][Width
				.values().length];
	}

	/**
	 * Set the inventory amount of the given belt.
	 *
	 * @param color   The color of the given belt.
	 * @param pattern The pattern used on the given belt
	 * @param size    The size of the given belt.
	 * @param width   The width of the given belt.
	 * @param amount  The new inventory amount of the given belt.
	 */
	public void setAmount( Color color, DesignPattern pattern, int size, Width width, int amount ) {
		if ( size < MIN_BELT_SIZE || size > MAX_BELT_SIZE ) {
			throw new IllegalSizeException( size );
		}
		//		if ( width < MIN_BELT_WIDTH || width > MAX_BELT_WIDTH ) {
		//			throw new IllegalWidthException( width );
		//		}
		if ( amount < 0 ) {
			throw new IllegalAmountException( amount );
		}
		this.inventory[color.index][this.patterns.indexOf( pattern )][size - MIN_BELT_SIZE][width.index] = amount;
	}

	/**
	 * Retrieves the inventory amount of the given belt.
	 *
	 * @param color   The color of the given belt.
	 * @param pattern The pattern used on the given belt
	 * @param size    The size of the given belt.
	 * @param width   The width of the given belt.
	 * @return The current inventory amount of the given belt.
	 */
	public int getAmount( Color color, DesignPattern pattern, int size, Width width ) {
		if ( size < MIN_BELT_SIZE || size > MAX_BELT_SIZE ) {
			throw new IllegalSizeException( size );
		}
		//		if ( width < MIN_BELT_WIDTH || width > MAX_BELT_WIDTH ) {
		//			throw new IllegalWidthException( width );
		//		}
		return this.inventory[color.index][this.patterns.indexOf( pattern )][size - MIN_BELT_SIZE][width.index];
	}

	// TODO If you don't care about them being separated, just use this one instead.
	public abstract class DesignPattern implements Serializable {
		String name;

		@Override public String toString() {
			return name;
		}
	}

	public class PaintedPattern extends DesignPattern {

		private static final long serialVersionUID = 2424075031916656494L;

		public PaintedPattern( String name ) {
			super.name = name;
		}
	}

	public class ImprintedPattern extends DesignPattern {

		private static final long serialVersionUID = 5552968870362694334L;

		public ImprintedPattern( String name ) {
			super.name = name;
		}
	}

	public class IllegalSizeException extends IllegalArgumentException {
		public IllegalSizeException( int size ) {
			super( "The given size, " + size + ", is invalid. The size must be between " + Belts.MIN_BELT_SIZE + " and "
					+ Belts.MAX_BELT_SIZE + "." );
		}
	}

	//	public class IllegalWidthException extends IllegalArgumentException {
	//		public IllegalWidthException( int width ) {
	//			super( "The given width, " + width + ", is invalid. The size must be between " + Belts.MIN_BELT_WIDTH
	//					+ " and " + Belts.MAX_BELT_WIDTH + "." );
	//		}
	//	}

	public class IllegalAmountException extends IllegalArgumentException {
		public IllegalAmountException( int amount ) {
			super( "The given amount, " + amount + ", is invalid. The amount must be greater than 0." );
		}
	}
}
