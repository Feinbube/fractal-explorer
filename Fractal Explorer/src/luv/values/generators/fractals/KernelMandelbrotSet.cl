/*
 * JOCL - Java bindings for OpenCL
 *
 * Copyright 2009 Marco Hutter - http://www.jocl.org/
 */

// A very simple OpenCL kernel for computing the mandelbrot set
//
// output        : A buffer with w*h elements, storing
//                 the colors as RGB ints
// w, h          : The width and height of the buffer
// x0,y0,x1,y1   : The rectangle in which the mandelbrot
//                 set will be computed
// maxIterations : The maximum number of iterations

__kernel void runMandelbrotSet(
    __global float *output,
    float x0, float y0,
    float x1, float y1,
    int maxIterations
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    float r = x0 + ix * (x1-x0) / get_global_size(0);
    float i = y0 + iy * (y1-y0) / get_global_size(1);

    float x = 0;
    float y = 0;

    float magnitudeSquared = 0;
    int iteration = 0;
    while (iteration<maxIterations && magnitudeSquared<4)
    {
        float xx = x*x;
        float yy = y*y;
        y = 2*x*y+i;
        x = xx-yy+r;
        magnitudeSquared=xx+yy;
        iteration++;
    }
    
    output[iy*get_global_size(0)+ix] = iteration == maxIterations ? 0 : (float)iteration/maxIterations;
}