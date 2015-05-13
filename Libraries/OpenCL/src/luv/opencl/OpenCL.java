package luv.opencl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;
import org.jocl.CL.*;
import org.jocl.*;
import static org.jocl.CL.*;

public class OpenCL {

    private final cl_context context;
    private final cl_command_queue commandQueue;

    private final Map<String, cl_program> programCache;

    public OpenCL() {
        final int platformIndex = 0;
        final long deviceType = CL_DEVICE_TYPE_ALL;
        final int deviceIndex = 0;

        CL.setExceptionsEnabled(true);

        // Obtain the number of platforms
        int numPlatformsArray[] = new int[1];
        clGetPlatformIDs(0, null, numPlatformsArray);
        int numPlatforms = numPlatformsArray[0];

        // Obtain a platform ID
        cl_platform_id platforms[] = new cl_platform_id[numPlatforms];
        clGetPlatformIDs(platforms.length, platforms, null);
        cl_platform_id platform = platforms[platformIndex];

        // Initialize the context properties
        cl_context_properties contextProperties = new cl_context_properties();
        contextProperties.addProperty(CL_CONTEXT_PLATFORM, platform);

        // Obtain the number of devices for the platform
        int numDevicesArray[] = new int[1];
        clGetDeviceIDs(platform, deviceType, 0, null, numDevicesArray);
        int numDevices = numDevicesArray[0];

        // Obtain a device ID 
        cl_device_id devices[] = new cl_device_id[numDevices];
        clGetDeviceIDs(platform, deviceType, numDevices, devices, null);
        cl_device_id device = devices[deviceIndex];

        // Create a context for the selected device
        context = clCreateContext(contextProperties, 1, new cl_device_id[]{device}, null, null, null);

        // Create a command-queue for the selected device
        commandQueue = clCreateCommandQueue(context, device, 0, null);

        programCache = new TreeMap<>();
    }

    public cl_kernel getKernel(String sourceFile, String kernelName) {
        if (!programCache.containsKey(sourceFile)) {
            cl_program cpProgram = clCreateProgramWithSource(context, 1, new String[]{readFile(sourceFile)}, null, null);
            clBuildProgram(cpProgram, 0, null, "-cl-mad-enable", null, null);
            programCache.put(sourceFile, cpProgram);
        }

        return clCreateKernel(programCache.get(sourceFile), kernelName, null);
    }

    /**
     * Helper function which reads the file with the given name and returns the
     * contents of this file as a String. Will exit the application if the file
     * can not be read.
     *
     * @param fileName The name of the file to read.
     * @return The contents of the file
     */
    private String readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            String result = "";
            String line = null;
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                result += line + "\n";
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    void run(cl_kernel kernel, long[] globalWorkSize, long[] localWorkSize) {
        clEnqueueNDRangeKernel(commandQueue, kernel, globalWorkSize.length, null, globalWorkSize, localWorkSize, 0, null, null);
    }

    public int getSizeOf(Object parameter) {
        if (parameter instanceof Class) {
            if (((Class) parameter).equals(int.class) || ((Class) parameter).equals(Integer.class)) {
                return Sizeof.cl_int;
            } else if (((Class) parameter).equals(float.class) || ((Class) parameter).equals(Float.class)) {
                return Sizeof.cl_float;
            } else {
                throw new RuntimeException("Unknown parameter type: " + parameter.toString());
            }
        } else if (parameter instanceof Integer) {
            return Sizeof.cl_int;
        } else if (parameter instanceof Float) {
            return Sizeof.cl_float;
        } else if (parameter instanceof HybridMemory) {
            return Sizeof.cl_mem;
        } else if (parameter instanceof int[]) {
            return Sizeof.cl_int * ((int[]) parameter).length;
        } else if (parameter instanceof float[]) {
            return Sizeof.cl_float * ((float[]) parameter).length;
        } else {
            throw new RuntimeException("Unknown parameter type: " + parameter.toString());
        }
    }

    cl_mem createBuffer(long memoryType, long size) {
        return clCreateBuffer(context, memoryType, size, null, null);
    }

    void readBuffer(cl_mem memory, long size, Pointer to) {
        clEnqueueReadBuffer(commandQueue, memory, CL_TRUE, 0, size, to, 0, null, null);
    }

    void writeBuffer(cl_mem memory, long size, Pointer to) {
        clEnqueueWriteBuffer(commandQueue, memory, true, 0, size, to, 0, null, null);
    }
}
