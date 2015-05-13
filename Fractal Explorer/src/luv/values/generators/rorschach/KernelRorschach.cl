
__kernel void runRohrschach2(
    __global float *heatPointsX,
    __global float *heatPointsY,
    __global float *heatPointsR,
    __global float *output,
    float x0, float y0,
    float x1, float y1,
    int nrHeatPoints,
    float slim
    )
{
    float x = fabs(x0 + get_global_id(0) * (x1-x0) / get_global_size(0));
    float y = y0 + get_global_id(1) * (y1-y0) / get_global_size(1);

    float result = 0.0f;
    for (int i = 0; i < nrHeatPoints; i++) {
        float hx = heatPointsX[i];
        float hy = heatPointsY[i];
        float d = (hx-x)*(hx-x) + (hy-y)*(hy-y);
        float hr = heatPointsR[i] * slim;
        if (d < hr*hr) {
            result = 1.0f;
        }
    }
    output[get_global_id(1)*get_global_size(0)+get_global_id(0)] = result < 0.0f ? 0.0f : result > 1.0f ? 1.0f : (float) result;
}