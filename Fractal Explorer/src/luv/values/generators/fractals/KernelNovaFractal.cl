__kernel void runNovaFractal(
		__global float *output,
		float x0, float y0,
		float x1, float y1,
		int maxIterations
)
{
	unsigned int ix = get_global_id(0);
	unsigned int iy = get_global_id(1);

	const float macheps = 1E-10;

	float x = x0 + ix * (x1-x0) / get_global_size(0);
	float y = y0 + iy * (y1-y0) / get_global_size(1);

	int iteration;
	for (iteration = 0; iteration < maxIterations; ++iteration)
	{
		const float phi = atan2(y, x) * 2.0f;
		const float d = x*x + y*y;
		const float r = sqrt(d);
		if (fabs(r) < macheps || d > 56.0f) break;

		const float r2 = r*r;
		x = (cos(phi)/r2) + x;
		y = (-sin(phi)/r2) + y;
	}

	output[iy*get_global_size(0)+ix] = iteration == maxIterations ? 0 : (float)iteration/maxIterations;
}
