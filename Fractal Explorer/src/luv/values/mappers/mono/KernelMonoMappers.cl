__kernel void normalize0TO1(
    __global float *input,
    __global float *output,
    float minimum, float maximum
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = (input[iy*get_global_size(0)+ix] - minimum) / (maximum - minimum);
}

__kernel void identity(
    __global float *input,
    __global float *output
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = input[iy*get_global_size(0)+ix];
}

__kernel void invert(
    __global float *input,
    __global float *output
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = 1.0f - input[iy*get_global_size(0)+ix];
}

__kernel void add(
    __global float *input,
    __global float *output,
    float difference
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = min(1.0f, max(0.0f, input[iy*get_global_size(0)+ix] + difference));
}

__kernel void multiply(
    __global float *input,
    __global float *output,
    float factor
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = min(1.0f, max(0.0f, input[iy*get_global_size(0)+ix] * factor));
}

__kernel void power(
    __global float *input,
    __global float *output,
    float value
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = min(1.0f, max(0.0f, pow(input[iy*get_global_size(0)+ix], value)));
}

__kernel void sinus(
    __global float *input,
    __global float *output,
    float value
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = min(1.0f, max(0.0f, sin(input[iy*get_global_size(0)+ix] * value)));
}

__kernel void cosinus(
    __global float *input,
    __global float *output,
    float value
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = min(1.0f, max(0.0f, cos(input[iy*get_global_size(0)+ix] * value)));
}

__kernel void crop(
    __global float *input,
    __global float *output,
    float minimum,
    float maximum
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = min(maximum, max(minimum, input[iy*get_global_size(0)+ix]));
}