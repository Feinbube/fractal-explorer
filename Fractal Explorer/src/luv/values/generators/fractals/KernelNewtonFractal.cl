// http://people.csail.mit.edu/adonovan/hacks/newt-term.c

__kernel void runNewtonFractal(
		__global float *output,
		float x0, float y0,
		float x1, float y1,
		int maxIterations
)
{
	unsigned int ix = get_global_id(0);
	unsigned int iy = get_global_id(1);

	const float macheps = 1E-10;
	const float sin60 = 0.86602496151913422;
	const float cos60 = 0.5;

	float x = x0 + ix * (x1-x0) / get_global_size(0);
	float y = y0 + iy * (y1-y0) / get_global_size(1);

	unsigned root = 0;
	int iteration;
	for (iteration = 0; iteration < maxIterations; ++iteration)
	{
		float x2 = x*x;
		float y2 = y*y;
		float x2_y2 = x2 - y2;

		float a,b,c,d,e,f,g;

		a = x2*x2 - 6*x2*y2 + y2*y2 - 1;
		b = 4*x*y * x2_y2;
		c = 4*x * (x2_y2 - 2*y2);
		d = 4*y * (x2_y2 + 2*x2);

		e = c*c + d*d;
		e = (e == 0) ? 1E10 : (1.0f / e);

		f = a*c + b*d;
		g = b*c - a*d;

		f *= e;
		g *= e;

		x -= f;
		y -= g;

		if((fabs(x - 1) < macheps) &&// 1
				(fabs(y + 0) < macheps))
		{
			root = 1;
		}
		else if((fabs(x + 1) < macheps) && // -1
				(fabs(y + 0) < macheps))
		{
			root = 2;
		}
		else if((fabs(x + 0) < macheps) && // i
				(fabs(y - 1) < macheps))
		{
			root = 3;
		}
		else if((fabs(x + 0) < macheps) && // -i
				(fabs(y + 1) < macheps))
		{
			root = 4;
		}
		if (root) break;
	}

	output[iy*get_global_size(0)+ix] = iteration == maxIterations ? 0 : (float)iteration/maxIterations;
}
