package luv.values.generators.noise.cell;

import java.io.Serializable;

public class CellDataStruct implements Serializable {

	private static final long serialVersionUID = 8631347361924210602L;
	// Max number of 'closest' points.
    public int max_order;
    // Position in space/plane.
    public float[] at;
    // Distance to closest points.
    public float[] F;
    // Vector to closest points.
    public float[][] delta;
    // Unique id depending on the seed of the closest point.
    public long[] ID;
    // Distance measure type.
    public int dist_type;

    // Constructor initiating the structure.
    public CellDataStruct(float[] at, int dist_type) {
        this.max_order = 2;
        this.at = at;
        this.dist_type = dist_type;
        F = new float[max_order];
        delta = new float[2][2];
        ID = new long[max_order];
    }
}
