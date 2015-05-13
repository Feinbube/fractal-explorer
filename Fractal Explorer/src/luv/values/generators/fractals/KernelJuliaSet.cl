
//each iteration, it calculates: new = old*old + c, where c is a constant and old starts at current pixel
__kernel void runJuliaSet(
    __global float *output,
    float x0, float y0,
    float x1, float y1,
    float cRe, float cIm,
    int maxIterations
    )
{
    float newRe, newIm, oldRe, oldIm;   //real and imaginary parts of new and old

    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    newRe = x0 + ix * (x1-x0) / get_global_size(0);
    newIm = y0 + iy * (y1-y0) / get_global_size(1);

    //i will represent the number of iterations
    int iteration;
    //start the iteration process
    for(iteration = 0; iteration < maxIterations; iteration++)
    {
        //remember value of previous iteration
        oldRe = newRe;
        oldIm = newIm;
        //the actual iteration, the real and imaginary part are calculated
        newRe = oldRe * oldRe - oldIm * oldIm + cRe;
        newIm = 2 * oldRe * oldIm + cIm;
        //if the point is outside the circle with radius 2: stop
        if((newRe * newRe + newIm * newIm) > 4) break;
    }

    output[iy*get_global_size(0)+ix] = iteration == maxIterations ? 0 : (float)iteration/maxIterations;
}