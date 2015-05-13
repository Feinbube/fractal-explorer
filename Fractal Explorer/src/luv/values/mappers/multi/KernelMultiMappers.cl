__kernel void minOf2(
    __global float *input1,
    __global float *input2,
    __global float *output
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = min(input1[iy*get_global_size(0)+ix], input2[iy*get_global_size(0)+ix]);
}

__kernel void minOf3(
    __global float *input1,
    __global float *input2,
    __global float *input3,
    __global float *output
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = min(input1[iy*get_global_size(0)+ix], min(input2[iy*get_global_size(0)+ix], input3[iy*get_global_size(0)+ix]));
}

__kernel void minOf4(
    __global float *input1,
    __global float *input2,
    __global float *input3,
    __global float *input4,
    __global float *output
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = min(min(input1[iy*get_global_size(0)+ix], input2[iy*get_global_size(0)+ix]), min(input3[iy*get_global_size(0)+ix], input4[iy*get_global_size(0)+ix]));
}

__kernel void maxOf2(
    __global float *input1,
    __global float *input2,
    __global float *output
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = max(input1[iy*get_global_size(0)+ix], input2[iy*get_global_size(0)+ix]);
}

__kernel void maxOf3(
    __global float *input1,
    __global float *input2,
    __global float *input3,
    __global float *output
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = max(input1[iy*get_global_size(0)+ix], max(input2[iy*get_global_size(0)+ix], input3[iy*get_global_size(0)+ix]));
}

__kernel void maxOf4(
    __global float *input1,
    __global float *input2,
    __global float *input3,
    __global float *input4,
    __global float *output
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = max(max(input1[iy*get_global_size(0)+ix], input2[iy*get_global_size(0)+ix]), max(input3[iy*get_global_size(0)+ix], input4[iy*get_global_size(0)+ix]));
}

__kernel void avgOf2(
    __global float *input1,
    __global float *input2,
    __global float *output
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = (input1[iy*get_global_size(0)+ix] + input2[iy*get_global_size(0)+ix]) / 2;
}

__kernel void avgOf3(
    __global float *input1,
    __global float *input2,
    __global float *input3,
    __global float *output
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = (input1[iy*get_global_size(0)+ix] + input2[iy*get_global_size(0)+ix] + input3[iy*get_global_size(0)+ix]) / 3;
}

__kernel void avgOf4(
    __global float *input1,
    __global float *input2,
    __global float *input3,
    __global float *input4,
    __global float *output
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = (input1[iy*get_global_size(0)+ix] + input2[iy*get_global_size(0)+ix] + input3[iy*get_global_size(0)+ix] + input4[iy*get_global_size(0)+ix]) / 4;
}

__kernel void sumOf2(
    __global float *input1,
    __global float *input2,
    __global float *output
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = min(1.0f, max(0.0f, input1[iy*get_global_size(0)+ix] + input2[iy*get_global_size(0)+ix]));
}

__kernel void sumOf3(
    __global float *input1,
    __global float *input2,
    __global float *input3,
    __global float *output
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = min(1.0f, max(0.0f, input1[iy*get_global_size(0)+ix] + input2[iy*get_global_size(0)+ix] + input3[iy*get_global_size(0)+ix]));
}

__kernel void sumOf4(
    __global float *input1,
    __global float *input2,
    __global float *input3,
    __global float *input4,
    __global float *output
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = min(1.0f, max(0.0f, input1[iy*get_global_size(0)+ix] + input2[iy*get_global_size(0)+ix] + input3[iy*get_global_size(0)+ix] + input4[iy*get_global_size(0)+ix]));
}

__kernel void productOf2(
    __global float *input1,
    __global float *input2,
    __global float *output
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = min(1.0f, max(0.0f, input1[iy*get_global_size(0)+ix] * input2[iy*get_global_size(0)+ix]));
}

__kernel void productOf3(
    __global float *input1,
    __global float *input2,
    __global float *input3,
    __global float *output
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = min(1.0f, max(0.0f, input1[iy*get_global_size(0)+ix] * input2[iy*get_global_size(0)+ix] * input3[iy*get_global_size(0)+ix]));
}

__kernel void productOf4(
    __global float *input1,
    __global float *input2,
    __global float *input3,
    __global float *input4,
    __global float *output
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = min(1.0f, max(0.0f, input1[iy*get_global_size(0)+ix] * input2[iy*get_global_size(0)+ix] * input3[iy*get_global_size(0)+ix] * input4[iy*get_global_size(0)+ix]));
}