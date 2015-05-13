#define SQRT3    1.7320508f   // Math.sqrt(3.0);
#define SKEW     0.36602542f  // 0.5 * (SQRT3 - 1.0);
#define UNSKEW   0.21132487f  // (3.0 - SQRT3) / 6.0;
#define UNSKEW2 -0.57735026f  // UNSKEW * 2.0 - 1.0;
#define WRAP    12            // GRADIENTS.length;

__constant int PERMS[512] = {
        151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225,
        140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148,
        247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, 203, 117, 35, 11, 32,
        57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68,
        175, 74, 165, 71, 134, 139, 48, 27, 166, 77, 146, 158, 231, 83, 111,
        229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55, 46, 245, 40, 244,
        102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208,
        89, 18, 169, 200, 196, 135, 130, 116, 188, 159, 86, 164, 100, 109, 198,
        173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5, 202, 38, 147, 118,
        126, 255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28,
        42, 223, 183, 170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153,
        101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79, 113,
        224, 232, 178, 185, 112, 104, 218, 246, 97, 228, 251, 34, 242, 193,
        238, 210, 144, 12, 191, 179, 162, 241, 81, 51, 145, 235, 249, 14, 239,
        107, 49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204, 176, 115, 121,
        50, 45, 127, 4, 150, 254, 138, 236, 205, 93, 222, 114, 67, 29, 24, 72,
        243, 141, 128, 195, 78, 66, 215, 61, 156, 180, 151, 160, 137, 91, 90,
        15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36, 103, 30, 69,
        142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26,
        197, 62, 94, 252, 219, 203, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149,
        56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139,
        48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230,
        220, 105, 92, 41, 55, 46, 245, 40, 244, 102, 143, 54, 65, 25, 63, 161,
        1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 200, 196, 135,
        130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217,
        226, 250, 124, 123, 5, 202, 38, 147, 118, 126, 255, 82, 85, 212, 207,
        206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183, 170, 213,
        119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172,
        9, 129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178, 185,
        112, 104, 218, 246, 97, 228, 251, 34, 242, 193, 238, 210, 144, 12, 191,
        179, 162, 241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31,
        181, 199, 106, 157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4, 150,
        254, 138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195,
        78, 66, 215, 61, 156, 180
    };

__constant int2 GRADIENTS[12] = {
        {1, 1}, {-1, 1}, {1, -1}, {-1, -1}, {1, 0}, {-1, 0},
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {0, 1}, {0, -1}
    };
    

//private static int floor(float n) {
//    return n >= 0 ? (int) n : (int) n - 1;
//}

private static float dotInt2(int2 grid, float a, float b) {
    return grid.x * a + grid.y * b;
}

private static float at(float x, float y) {
    float HAIRY = (x + y) * SKEW;

    // indices into simplex cell space of skewed input 
    int i = floor(x + HAIRY);
    int j = floor(y + HAIRY);

    // first simplex corner
    float ratio = (i + j) * UNSKEW;
    float x_0 = x - (i - ratio);
    float y_0 = y - (j - ratio);

    // second simplex corner
    int xOffset = 0;
    int yOffset = 0;
    if (x_0 > y_0) {
        xOffset = 1;
    } else {
        yOffset = 1;
    }

    float x_1 = x_0 - xOffset + UNSKEW;
    float y_1 = y_0 - yOffset + UNSKEW;

    // third simplex corner
    float x_2 = x_0 + UNSKEW2;
    float y_2 = y_0 + UNSKEW2;

    // compute the three hashed gradient of the simplex corners
    int g_x = i & 0xFF;
    int g_y = j & 0xFF;

    // compute the contributions of the corners to the noise
    //first corner
    float gradRatio_0 = 0.5f - x_0 * x_0 - y_0 * y_0;
    float n_0 = 0.0f;

    if (gradRatio_0 > 0.0f) {
        gradRatio_0 *= gradRatio_0;
        int gI = PERMS[g_x + PERMS[g_y]] % WRAP;
        n_0 = gradRatio_0 * gradRatio_0 * dotInt2(GRADIENTS[gI], x_0, y_0);
    }

    // second corner
    float gradRatio_1 = 0.5f - x_1 * x_1 - y_1 * y_1;
    float n_1 = 0.0f;

    if (gradRatio_1 > 0.0f) {
        gradRatio_1 *= gradRatio_1;
        int gI = PERMS[g_x + xOffset + PERMS[g_y + yOffset]] % WRAP;
        n_1 = gradRatio_1 * gradRatio_1 * dotInt2(GRADIENTS[gI], x_1, y_1);
    }

    // third corner
    float gradRatio_2 = 0.5f - x_2 * x_2 - y_2 * y_2;
    float n_2 = 0.0f;

    if (gradRatio_2 > 0.0f) {
        gradRatio_2 *= gradRatio_2;
        int gI = PERMS[g_x + 1 + PERMS[g_y + 1]] % WRAP;
        n_2 = gradRatio_2 * gradRatio_2 * dotInt2(GRADIENTS[gI], x_2, y_2);
    }
    
    return (70.0f * (n_0 + n_1 + n_2) + 1.0f) / 2.0f;
}    

__kernel void runSimplexNoise(
    __global float *output,
    float x0, float y0,
    float x1, float y1
    )
{
    float x = x0 + get_global_id(0) * (x1-x0) / get_global_size(0);
    float y = y0 + get_global_id(1) * (y1-y0) / get_global_size(1);

    output[get_global_id(1)*get_global_size(0)+get_global_id(0)] = at(x, y);
}

__kernel void runValueNoise(
    __global float *output,
    float x0, float y0,
    float x1, float y1,
    float distortion
    )
{
    float x = x0 + get_global_id(0) * (x1-x0) / get_global_size(0);
    float y = y0 + get_global_id(1) * (y1-y0) / get_global_size(1);

    float dx = at(x + 0.5f, y) * distortion;
    float dy = at(x, y + 0.5f) * distortion;

    output[get_global_id(1)*get_global_size(0)+get_global_id(0)] = at(x + dx, y + dy);
}

__kernel void runRigidFBM(
    __global float *output,
    float x0, float y0,
    float x1, float y1
    )
{
    float x = x0 + get_global_id(0) * (x1-x0) / get_global_size(0);
    float y = y0 + get_global_id(1) * (y1-y0) / get_global_size(1);

    float v = at(x,y);
    v = v < 0 ? -v : v;

    output[get_global_id(1)*get_global_size(0)+get_global_id(0)] = 1.0f-v;
}

private static float atFBM(float x, float y, int octaves, float persistence) {
    float total = 0;
    float frequency = 1.0f;
    float amplitude = persistence;

    for(int i=0; i<octaves; i++)
    {
        total += at(x*frequency, y*frequency) * amplitude;
        
        frequency *= 2;
        amplitude *= persistence;
    }
    
    return total;
}

__kernel void runFBMNoise(
    __global float *output,
    float x0, float y0,
    float x1, float y1,
    int octaves,
    float persistence
    )
{
    float x = x0 + get_global_id(0) * (x1-x0) / get_global_size(0);
    float y = y0 + get_global_id(1) * (y1-y0) / get_global_size(1);
    
    output[get_global_id(1)*get_global_size(0)+get_global_id(0)] = atFBM(x, y, octaves, persistence);
}

__kernel void runStarNoise (
    __global float *output,
    float x0, float y0,
    float x1, float y1
    )
{
    float x = x0 + get_global_id(0) * (x1-x0) / get_global_size(0);
    float y = y0 + get_global_id(1) * (y1-y0) / get_global_size(1);

    output[get_global_id(1)*get_global_size(0)+get_global_id(0)] = max(0.0f, min(1.0f, ((pow(atFBM(x, y, 8, 0.5f), 4)*2-0.8f)*2)));
}

__kernel void runNebulaNoise(
    __global float *output,
    float x0, float y0,
    float x1, float y1
    )
{
    float x = x0 + get_global_id(0) * (x1-x0) / get_global_size(0);
    float y = y0 + get_global_id(1) * (y1-y0) / get_global_size(1);

    output[get_global_id(1)*get_global_size(0)+get_global_id(0)] = pow(atFBM(x, y, 8, 0.5f), 2)*0.6f;
}