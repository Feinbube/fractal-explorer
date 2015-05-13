__kernel void runBuddhabrot(
     int samplesPerWorkingItemSide, 
     __global float *output,
     float x0, float y0,
     float x1, float y1,
     int minIters,
     int maxIters)
{ 

    //x = -2..1; y = -1..1;
    float xp = -2.0f + 3.0f * get_global_id(0) / get_global_size(0);
    float yp = -1.0f + 2.0f * get_global_id(1) / get_global_size(1);
    
    float sxp = 3.0f / get_global_size(0) / samplesPerWorkingItemSide;
    float syp = 2.0f / get_global_size(1) / samplesPerWorkingItemSide;
        
    int ix, iy;
    for(iy = 0; iy < samplesPerWorkingItemSide; ++iy){
        for(ix = 0; ix < samplesPerWorkingItemSide; ++ix){
            float2 p = (float2) (xp + sxp * ix, yp + syp * iy);
            float4 n = p.xyxx * p.xyyx;

            float q = n.x - p.x/2.0f + 0.125f + n.y;
            //skip main cardioid
            if (q * (q + (p.x - 0.25f)) < n.y*0.25f) { continue; }
            //skip left sphere 
            if (n.x + 2.0f*p.x + n.y < -0.9375f) { continue; }
                        
            int iterations = 0;
            float2 z = (float2)(0.0f,0.0f);
            float e1=0.0f,e2=0.0f,e3=0.0f,e4=0.0f,e5=0.0f,e6=0.0f,e7=0.0f;
            for(iterations = 0; iterations < maxIters; iterations += 8){
                n = z.xyxx * z.xyyx;                    
                z.y = (2.0f * n.z); 
                z.x = n.x - n.y;
                z += p;
                e1 = n.x + n.y;
                
                n = z.xyxx * z.xyyx;                    
                z.y = (2.0f * n.z); 
                z.x = n.x - n.y;
                z += p;
                e2 = n.x + n.y;

                n = z.xyxx * z.xyyx;                    
                z.y = (2.0f * n.z); 
                z.x = n.x - n.y;
                z += p;
                e3 = n.x + n.y;

                n = z.xyxx * z.xyyx;                    
                z.y = (2.0f * n.z); 
                z.x = n.x - n.y;
                z += p;
                e4 = n.x + n.y;

                n = z.xyxx * z.xyyx;                    
                z.y = (2.0f * n.z); 
                z.x = n.x - n.y;
                z += p;
                e5 = n.x + n.y;

                n = z.xyxx * z.xyyx;                    
                z.y = (2.0f * n.z); 
                z.x = n.x - n.y;
                z += p;
                e6 = n.x + n.y;

                n = z.xyxx * z.xyyx;                    
                z.y = (2.0f * n.z); 
                z.x = n.x - n.y;
                z += p;
                e7 = n.x + n.y;

                n = z.xyxx * z.xyyx;                    
                z.y = (2.0f * n.z); 
                z.x = n.x - n.y;
                z += p;
                if (n.x + n.y > 4.0f) { break; }
            }

            if (e1 > 4.0f) { iterations -= 7;}
            else if (e2 > 4.0f) { iterations -= 6;}
            else if (e3 > 4.0f) { iterations -= 5;}
            else if (e4 > 4.0f) { iterations -= 4;}
            else if (e5 > 4.0f) { iterations -= 3;}
            else if (e6 > 4.0f) { iterations -= 2;}
            else if (e7 > 4.0f) { iterations -= 1;}
           
            if (iterations == maxIters){
                continue;
            }
            
            if (iterations <= minIters || iterations >= maxIters){
                continue;
            }
                        
            z = (float2)(0.0f,0.0f);
            int imageX, imageY;
            float imgPX, imgPY;
            imgPX = get_global_size(0)/(x1-x0);
            imgPY = get_global_size(1)/(y1-y0);
            
            int result = 0;
            for( ;iterations > 0; --iterations){
                n = z.xyxx * z.xyyx;                    
                z.y = (2.0f * n.z); 
                z.x = n.x - n.y;
                z += p;
                
                imageX = (z.x - x0) * imgPX;
                imageY = (z.y - y0) * imgPY;
                
                if (imageX >= 0 && imageX < get_global_size(0) && imageY >= 0 && imageY < get_global_size(1)){
                	output[imageX + imageY * get_global_size(0)] += 1; // output[imageX + imageY * imageWidth] + 1.0f / (maxIters - minIters + 1));
                }
            } 
        }
    }    
}    

__kernel void normalize0TO1 (
    __global float *input,
    __global float *output,
    float minimum, float maximum
    )
{
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    output[iy*get_global_size(0)+ix] = (input[iy*get_global_size(0)+ix] - minimum) / (maximum - minimum);
}
