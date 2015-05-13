package luv.opencl;

import static org.jocl.CL.*;
import org.jocl.Pointer;
import org.jocl.cl_mem;

public class HybridMemory {

    private OpenCL openCL;
    private cl_mem memory;

    private int size;
    private int typeSize;

    private HybridMemoryType type;

    public HybridMemory(OpenCL openCL, Class aClass, int size, HybridMemoryType memoryType) {
        this.openCL = openCL;
        this.size = size;
        this.type = memoryType;
        this.typeSize = openCL.getSizeOf(aClass);
        this.memory = memoryType == HybridMemoryType.Local ? null : openCL.createBuffer(getFlags(memoryType), size * typeSize);
    }

    public HybridMemory(OpenCL openCL, int[] data, HybridMemoryType memoryType) {
        if (memoryType == HybridMemoryType.Local) {
            throw new UnsupportedOperationException("Local memory can not be written from host. Use another Constructor.");
        }
        this.openCL = openCL;
        this.size = data.length;
        this.type = memoryType;
        this.typeSize = openCL.getSizeOf(int.class);
        this.memory = openCL.createBuffer(getFlags(memoryType), size * typeSize);
        openCL.writeBuffer(memory, size * typeSize, Pointer.to(data));
    }

    public HybridMemory(OpenCL openCL, float[] data, HybridMemoryType memoryType) {
        if (memoryType == HybridMemoryType.Local) {
            throw new UnsupportedOperationException("Local memory can not be written from host. Use another Constructor.");
        }
        this.openCL = openCL;
        this.size = data.length;
        this.type = memoryType;
        this.typeSize = openCL.getSizeOf(float.class);
        this.memory = openCL.createBuffer(getFlags(memoryType), size * typeSize);
        openCL.writeBuffer(memory, size * typeSize, Pointer.to(data));
    }

    private long getFlags(HybridMemoryType memoryType) {
        if (memoryType == HybridMemoryType.WriteOnly) {
            return CL_MEM_WRITE_ONLY;
        } else if (memoryType == HybridMemoryType.ReadWrite) {
            return CL_MEM_READ_WRITE;
        } else if (memoryType == HybridMemoryType.ReadOnly) {
            return CL_MEM_WRITE_ONLY;
        } else {
            throw new RuntimeException("Unknown HybridMemoryType: " + memoryType.toString());
        }
    }

    cl_mem getCLMem() {
        return memory;
    }

    public int getSize() {
        return size;
    }

    public void readTo(int[] data) {
        openCL.readBuffer(memory, size * typeSize, Pointer.to(data));
    }

    public void readTo(float[] data) {
        openCL.readBuffer(memory, size * typeSize, Pointer.to(data));
    }

    public float[] toArray() {
        float[] result = new float[size];
        readTo(result);
        return result;
    }

    public float minValue() {
        float[] values = toArray();
        float result = Float.MAX_VALUE;

        for (float value : values) {
            if (value < result) {
                result = value;
            }
        }

        return result;
    }

    public float maxValue() {
        float[] values = toArray();
        float result = Float.MIN_VALUE;

        for (float value : values) {
            if (value > result) {
                result = value;
            }
        }

        return result;
    }

    Pointer getPointer() {
        return type == HybridMemoryType.Local ? null : Pointer.to(memory);
    }

    public void become(HybridMemory other) {
        this.openCL = other.openCL;
        this.memory = other.memory;
        this.size = other.size;
        this.type = other.type;
        this.typeSize = other.typeSize;
    }
}
