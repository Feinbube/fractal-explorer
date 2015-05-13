
__kernel void colorMap(
    __global float *input,
    __global uint *output,
    __global uint *colorMap,
    int colorMapSize
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);
    
    float value = input[iy*get_global_size(0)+ix];
    output[iy*get_global_size(0)+ix] = value == 0 ? colorMap[0] : colorMap[(int)(value * (colorMapSize-2)) + 1];
}

__kernel void grayScale(
    __global float *input,
    __global uint *output
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);
    
    float value = input[iy*get_global_size(0)+ix];
    output[iy*get_global_size(0)+ix] = (uint)convert_uchar4_sat_rte(((float4)(value, value, value, 1.0f)) * 255.0f);
}

__kernel void linearScale(
    __global float *input,
    __global uint *output,
    uchar4 c1, uchar4 c2
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);
    
    float value = input[iy*get_global_size(0)+ix];
    
    uchar4 diff = 0; 
    
    diff.x = c2.x * value + c1.x * (1.0f - value);
    diff.y = c2.y * value + c1.y * (1.0f - value);
    diff.z = c2.z * value + c1.z * (1.0f - value);
    diff.w = c2.w * value + c1.w * (1.0f - value);
    
    output[iy*get_global_size(0)+ix] = (uint)diff;
}

__kernel void colorScaleMap(
    __global float *input,
    __global uint *output,
    __global uchar4 *colorMap,
    int colorMapSize
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);
    
    float value = input[iy*get_global_size(0)+ix];

    float fIndex = value * (colorMapSize-1);
    int index = (int)(fIndex);    
    
    uchar4 c1 = colorMap[index];
    uchar4 c2 = colorMap[min(index+1, colorMapSize-1)];

    float v = fIndex - index;

    uchar4 diff = 0; 

    diff.x = c2.x * v + c1.x * (1.0f - v);
    diff.y = c2.y * v + c1.y * (1.0f - v);
    diff.z = c2.z * v + c1.z * (1.0f - v);
    diff.w = c2.w * v + c1.w * (1.0f - v);

    output[iy*get_global_size(0)+ix] = (uint)diff;
}


float4 HSVtoRGB(float4 HSV)
{
        float4 RGB = (float4)0;
        if (HSV.z != 0) 
        {
                float var_h = HSV.x * 6;
                float var_i = floor(var_h-0.000001f);   
                float var_1 = HSV.z * (1.0f - HSV.y);
                float var_2 = HSV.z * (1.0f - HSV.y * (var_h-var_i));
                float var_3 = HSV.z * (1.0f - HSV.y * (1-(var_h-var_i)));
                switch((int)(var_i))
                {
                        case 0: RGB = (float4)(HSV.z, var_3, var_1, HSV.w); break;
                        case 1: RGB = (float4)(var_2, HSV.z, var_1, HSV.w); break;
                        case 2: RGB = (float4)(var_1, HSV.z, var_3, HSV.w); break;
                        case 3: RGB = (float4)(var_1, var_2, HSV.z, HSV.w); break;
                        case 4: RGB = (float4)(HSV.z, var_1, var_2, HSV.w); break;
                        default: RGB = (float4)(HSV.z, var_1, var_2, HSV.w); break;
                }
        }
        RGB.w = HSV.w;
        return (RGB);
}

__kernel void hsbScale(
    __global float *input,
    __global uint *output
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);
    
    uint result;
    float value = input[iy*get_global_size(0)+ix];
    if(value == 0) {
        result = 0xFF000000;
    } else {
        float4 hsv = (float4)(value,1.0f,1.0f, 1.0f);
        result = (uint)convert_uchar4_sat_rte(HSVtoRGB(hsv)*255);
    }    
    output[iy*get_global_size(0)+ix] = result;
}