package luv.opencl;

import static org.jocl.CL.clSetKernelArg;
import org.jocl.Pointer;
import org.jocl.cl_kernel;

public class ComputeKernel {

    private final OpenCL openCL;
    private final cl_kernel kernel;

    public ComputeKernel(OpenCL openCL, String sourceFile, String kernelName) {
        this.openCL = openCL;
        kernel = openCL.getKernel(sourceFile, kernelName);
    }

    public void run(long[] globalWorkSize, long[] localWorkSize, Object... parameters) {
        for (int i = 0; i < parameters.length; i++) {
            clSetKernelArg(kernel, i, openCL.getSizeOf(parameters[i]), getPointer(parameters[i]));
        }

        if (localWorkSize != null) {
            if (localWorkSize.length > 0) {
                globalWorkSize[0] = fixGlobalSize(globalWorkSize[0], localWorkSize[0]);
            }
            if (localWorkSize.length > 1) {
                globalWorkSize[1] = fixGlobalSize(globalWorkSize[1], localWorkSize[1]);
            }
            if (localWorkSize.length > 2) {
                globalWorkSize[2] = fixGlobalSize(globalWorkSize[2], localWorkSize[2]);
            }
        }

        openCL.run(kernel, globalWorkSize, localWorkSize);
    }

    protected long fixGlobalSize(long globalSize, long localSize) {
        long remainder = globalSize % localSize;
        return remainder == 0 ? globalSize : globalSize + localSize - remainder;
    }

    public void runInGroups(long workSizeX, long workSizeY, long workSizeZ, long localSizeX, long localSizeY, long localSizeZ, Object... parameters) {
        run(workSize(workSizeX, workSizeY, workSizeZ), workSize(localSizeX, localSizeY, localSizeZ), parameters);
    }

    public void run(long workSizeX, long workSizeY, long workSizeZ, Object... parameters) {
        run(workSize(workSizeX, workSizeY, workSizeZ), null, parameters);
    }

    public long[] workSize(long workSizeX, long workSizeY, long workSizeZ) {
        return workSizeZ > 0 ? new long[]{workSizeX, workSizeY, workSizeZ} : workSizeY > 0 ? new long[]{workSizeX, workSizeY} : new long[]{workSizeX};
    }

    public Pointer getPointer(Object parameter) {
        if (parameter instanceof Integer) {
            return Pointer.to(new int[]{(int) parameter});
        } else if (parameter instanceof Float) {
            return Pointer.to(new float[]{(float) parameter});
        } else if (parameter instanceof HybridMemory) {
            return ((HybridMemory) parameter).getPointer();
        } else if (parameter instanceof int[]) {
            return Pointer.to((int[]) parameter);
        } else if (parameter instanceof float[]) {
            return Pointer.to((float[]) parameter);
        } else {
            throw new RuntimeException("Unknown parameter type: " + parameter.toString());
        }
    }
}
