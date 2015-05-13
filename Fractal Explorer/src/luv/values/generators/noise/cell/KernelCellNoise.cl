__constant int Poisson_count[256] = {
        4, 3, 1, 1, 1, 2, 4, 2, 2, 2, 5, 1, 0, 2, 1, 2, 2, 0, 4, 3, 2, 1, 2,
        1, 3, 2, 2, 4, 2, 2, 5, 1, 2, 3, 2, 2, 2, 2, 2, 3, 2, 4, 2, 5, 3, 2,
        2, 2, 5, 3, 3, 5, 2, 1, 3, 3, 4, 4, 2, 3, 0, 4, 2, 2, 2, 1, 3, 2, 2,
        2, 3, 3, 3, 1, 2, 0, 2, 1, 1, 2, 2, 2, 2, 5, 3, 2, 3, 2, 3, 2, 2, 1,
        0, 2, 1, 1, 2, 1, 2, 2, 1, 3, 4, 2, 2, 2, 5, 4, 2, 4, 2, 2, 5, 4, 3,
        2, 2, 5, 4, 3, 3, 3, 5, 2, 2, 2, 2, 2, 3, 1, 1, 5, 2, 1, 3, 3, 4, 3,
        2, 4, 3, 3, 3, 4, 5, 1, 4, 2, 4, 3, 1, 2, 3, 5, 3, 2, 1, 3, 1, 3, 3,
        3, 2, 3, 1, 5, 5, 4, 2, 2, 4, 1, 3, 4, 1, 5, 3, 3, 5, 3, 4, 3, 2, 2,
        1, 1, 1, 1, 1, 2, 4, 5, 4, 5, 4, 2, 1, 5, 1, 1, 2, 3, 3, 3, 2, 5, 2,
        3, 3, 2, 0, 2, 1, 1, 4, 2, 1, 3, 2, 1, 2, 2, 3, 2, 5, 5, 3, 4, 5, 5,
        2, 4, 4, 5, 3, 2, 2, 2, 1, 4, 2, 3, 3, 4, 2, 5, 4, 2, 4, 2, 2, 2, 4,
        5, 3, 2  
  };

#define DENSITY_ADJUSTMENT     0.294631f
#define DENSITY_ADJUSTMENT_INV 3.394076f

#define EUCLIDEAN 1
#define CITYBLOCK 2
#define MANHATTAN 3
#define QUADRATIC 4

typedef struct CellDataStruct
{
    // dim and max_order are always 2
    
    // Position in space/plane.
    float2 at;
    // Distance to closest points.
    float2 F;
    
    // Vector to closest points.
    float4 delta; // since max_order is 2, delta can be 4
    // [0][0]=x [0][1]=y [1][0]=z [1][1]=w
    
    // Uniqe id depending on the seed of the closest point.
    uint2 ID; // since max_order is 2, uint2 is sufficient for the ID
    
    // Distance measure type.
    int dist_type;
} CellDataStruct;

CellDataStruct newCellDataStruct(float2 at, int dist_type)
{
    CellDataStruct result;
    result.at = at;
    result.dist_type = dist_type;
    result.F = (float2)(0);
    result.delta = (float4)(0);
    result.ID = (uint2)(0);
    return result;
}

float ABS(float f) {
    return f < 0 ? -f : f;
}

/*
* Generating the sample points in the grid
* 2D
*/
CellDataStruct AddSamples(int xi, int yi, float2 at, CellDataStruct cd) {
   float dx, dy, fx, fy, d2;
   int count, j;
   uint seed, this_id;

   /*
    * Generating a random seed, based on the cube's ID number. The seed might be 
    * better if it were a nonlinear hash like Perlin uses for noise, but we do very 
    * well with this faster simple one.
    * Our LCG uses Knuth-approved constants for maximal periods.
    */
   seed = 702395077 * xi + 915488749 * yi;

   /* Number of feature points in this cube. */
   count = Poisson_count[(int) (0xFF & (seed >> 24))];

   /* Churn the seed with good Knuth LCG. */
   seed = 1402024253 * seed + 586950981;

   /* Compute the 0..1 feature point location's xyz. */
   for (j = 0; j < count; j++) {
       this_id = seed;
       seed = 1402024253 * seed + 586950981;

       fx = (seed + 0.5f) / 4294967296.0f;
       seed = 1402024253 * seed + 586950981;
       fy = (seed + 0.5f) / 4294967296.0f;
       seed = 1402024253 * seed + 586950981;

       /* Delta from feature point to sample location. */
       dx = xi + fx - at.x;
       dy = yi + fy - at.y;

       /*
        * Calculate distance.
        */
       if (cd.dist_type == CITYBLOCK) {
           d2 = max(ABS(dx), ABS(dy));
           d2 *= d2;
       } else if (cd.dist_type == MANHATTAN) {
           d2 = ABS(dx) + ABS(dy);
           d2 *= d2;
       } else if (cd.dist_type == QUADRATIC) {
           d2 = dx * dx + dy * dy + dx * dy;
           d2 *= d2;
       } else {
           // EUCLEDEAN
           d2 = dx * dx + dy * dy;
       }

       /* Store points that are close enough to remember. */
       if (d2 < cd.F.y) {     
        
           cd.F.y = cd.F.x;
           cd.ID.y = cd.ID.x;
           cd.delta.z = cd.delta.x;
           cd.delta.w = cd.delta.y;
                
           if(d2 < cd.F.x)
           {
                cd.F.x = d2;
                cd.ID.x = this_id;
                cd.delta.x = dx;
                cd.delta.y = dy;
           }
           else
           {
                cd.F.y = d2;
                cd.ID.y = this_id;
                cd.delta.z = dx;
                cd.delta.w = dy;
           }
       }
   }

    return cd;
}

/*
* Noise function for two dimensions. Coordinating the search on the 
* above square level. Deciding in which squares to search.
*/
CellDataStruct noise2D(CellDataStruct cd) {
   float x2, y2, mx2, my2;
   float2 new_at = (float2)(0);
   int2 int_at = (int2)(0);

   // initialize F
   cd.F.x = 99999.9f;
   cd.F.y = 99999.9f;

   new_at.x = DENSITY_ADJUSTMENT * cd.at.x;
   new_at.y = DENSITY_ADJUSTMENT * cd.at.y;

   int_at.x = (int) floor(new_at.x);
   int_at.y = (int) floor(new_at.y);

   /*
    * The center cube. It's very likely that the closest feature 
    * point will be found in this cube.
    */
   cd = AddSamples(int_at.x, int_at.y, new_at, cd);

   /*
    * We test if the cubes are even possible contributors by examining 
    * the combinations of the sum of the squared distances from the 
    * cube's lower or upper corners.
    */
   x2 = new_at.x - int_at.x;
   y2 = new_at.y - int_at.y;
   mx2 = (1.0f - x2) * (1.0f - x2);
   my2 = (1.0f - y2) * (1.0f - y2);
   x2 *= x2;
   y2 *= y2;

   /*
    * The 4 facing neighbours of center square. These are the closest 
    * and most likely to have a close feature point.
    */
   if (x2 < cd.F.y) {
       cd = AddSamples(int_at.x - 1, int_at.y, new_at, cd);
   }
   if (y2 < cd.F.y) {
       cd = AddSamples(int_at.x, int_at.y - 1, new_at, cd);
   }
   if (mx2 < cd.F.y) {
       cd = AddSamples(int_at.x + 1, int_at.y, new_at, cd);
   }
   if (my2 < cd.F.y) {
       cd = AddSamples(int_at.x, int_at.y + 1, new_at, cd);
   }

   /*
    * The 4 edge squares. These are next closest.
    */
   if (x2 + y2 < cd.F.y) {
       cd = AddSamples(int_at.x - 1, int_at.y - 1, new_at, cd);
   }
   if (mx2 + my2 < cd.F.y) {
       cd = AddSamples(int_at.x + 1, int_at.y + 1, new_at, cd);
   }
   if (x2 + my2 < cd.F.y) {
       cd = AddSamples(int_at.x - 1, int_at.y + 1, new_at, cd);
   }
   if (mx2 + y2 < cd.F.y) {
       cd = AddSamples(int_at.x + 1, int_at.y - 1, new_at, cd);
   }

   cd.F.x = sqrt(cd.F.x) * DENSITY_ADJUSTMENT_INV;
   cd.delta.x *= DENSITY_ADJUSTMENT_INV;
   cd.delta.y *= DENSITY_ADJUSTMENT_INV;
   
   cd.F.y = sqrt(cd.F.y) * DENSITY_ADJUSTMENT_INV;
   cd.delta.z *= DENSITY_ADJUSTMENT_INV;
   cd.delta.w *= DENSITY_ADJUSTMENT_INV;

   return cd;
}


__kernel void runCellNoise(
    __global float *output,
    float x0, float y0,
    float x1, float y1,
    int distanceFunction
    )
{    
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);
    
    float posX = x0 + ix * (x1-x0) / get_global_size(0);
    float posY = y0 + iy * (y1-y0) / get_global_size(1);
    
    float sum = 1;

    CellDataStruct cd = newCellDataStruct((float2)(posX, posY), distanceFunction);
    for (int i = 0; i < 4; i++) {
        cd.at.x = 0.1f * (i * 2 + 1) * (posX + 20);
        cd.at.y = 0.1f * (i * 2 + 1) * (posY + 700);
        cd = noise2D(cd);
        sum += cd.F.x;
    }
    
    output[iy*get_global_size(0)+ix] = sum / 10.0f;
}

__kernel void runCellProduct(
    __global float *output,
    float x0, float y0,
    float x1, float y1,
    int distanceFunction
    )
{    
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);
    
    float posX = x0 + ix * (x1-x0) / get_global_size(0);
    float posY = y0 + iy * (y1-y0) / get_global_size(1);
    
    float sum = 1;

    CellDataStruct cd = newCellDataStruct((float2)(posX, posY), distanceFunction);
    for (int i = 0; i < 4; i++) {
        cd.at.x = 0.1f * (i * 2 + 1) * (posX + 20);
        cd.at.y = 0.1f * (i * 2 + 1) * (posY + 700);
        cd = noise2D(cd);
        sum *= cd.F.x;
    }
    
    sum /= 5.0f;
    if(sum > 1.0f) { sum = 1.0f - (sum - 1.0f)/2.0f; }
    output[iy*get_global_size(0)+ix] = sum;
}

__kernel void runPointNoise(
    __global float *output,
    float x0, float y0,
    float x1, float y1,
    int distanceFunction
    )
{    
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);
    
    float posX = x0 + ix * (x1-x0) / get_global_size(0);
    float posY = y0 + iy * (y1-y0) / get_global_size(1);
    
    CellDataStruct cd = newCellDataStruct((float2)(posX, posY), distanceFunction);
    cd = noise2D(cd);
    output[iy*get_global_size(0)+ix] = max(0.0f, 1.0f - cd.F.x);
}

__kernel void runMosaicNoise(
    __global float *output,
    float x0, float y0,
    float x1, float y1,
    int distanceFunction
    )
{    
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);
    
    float posX = x0 + ix * (x1-x0) / get_global_size(0);
    float posY = y0 + iy * (y1-y0) / get_global_size(1);
    
    CellDataStruct cd = newCellDataStruct((float2)(posX, posY), distanceFunction);
    cd = noise2D(cd);
    output[iy*get_global_size(0)+ix] = min(1.0f, (cd.F.y - cd.F.x) / 3.0f);
}

__kernel void runNoisyNoise (
    __global float *output,
    float x0, float y0,
    float x1, float y1,
    int distanceFunction,
    int channel
    )
{    
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);
    
    float posX = x0 + ix * (x1-x0) / get_global_size(0);
    float posY = y0 + iy * (y1-y0) / get_global_size(1);
    
    CellDataStruct cd = newCellDataStruct((float2)(posX, posY), distanceFunction);
    cd = noise2D(cd);
    cd.at = (float2)(cd.F.x * posX * get_global_size(0), cd.F.x * posY * get_global_size(1));
    cd = noise2D(cd);
    
    if(channel == 0) {
        output[iy*get_global_size(0)+ix] = cd.F.y * 0.5f;
    } else if(channel == 2) {
        output[iy*get_global_size(0)+ix] = cd.F.x * 0.5f;
    } else {
        output[iy*get_global_size(0)+ix] = (cd.F.y + cd.F.x) * 0.5f;
    }
}

__kernel void runIDMap(
    __global float *output,
    float x0, float y0,
    float x1, float y1,
    int distanceFunction
    )
{    
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);
    
    float posX = x0 + ix * (x1-x0) / get_global_size(0);
    float posY = y0 + iy * (y1-y0) / get_global_size(0);
    
    CellDataStruct cd = newCellDataStruct((float2)(posX, posY), distanceFunction);
    cd = noise2D(cd);
    
    output[iy*get_global_size(0)+ix] = (cd.ID.x % 255) / 255.0f;
}