__kernel void rgbScale(
    __global float *inputR,
    __global float *inputG,
    __global float *inputB,
    __global uchar4 *output
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);
    
    int i = iy*get_global_size(0)+ix;
    uchar R = (uchar)(inputR[i] * 255);
    uchar G = (uchar)(inputG[i] * 255);
    uchar B = (uchar)(inputB[i] * 255);
    
    output[iy*get_global_size(0)+ix] = (uchar4)(R, G, B, 255);
}
