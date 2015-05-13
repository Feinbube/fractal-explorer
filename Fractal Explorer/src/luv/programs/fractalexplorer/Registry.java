package luv.programs.fractalexplorer;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import luv.opencl.OpenCL;
import luv.values.generators.*;
import luv.values.generators.fractals.*;
import luv.values.generators.lsystems.dragoncurve.PojoDragonCurve;
import luv.values.generators.lsystems.fractalplant.PojoFractalPlant;
import luv.values.generators.lsystems.levycurve.PojoLevyCurve;
import luv.values.generators.lsystems.sandboxsystem.PojoSandboxSystem;
import luv.values.generators.lsystems.sierpinskitriangle.PojoSierpinskiTriangle;
import luv.values.generators.noise.cell.*;
import luv.values.generators.noise.simplex.*;
import luv.values.generators.noise.perlin.*;
import luv.values.colorizers.*;
import luv.values.colorizers.mono.*;
import luv.values.colorizers.multi.*;
import luv.values.generators.fractals.ifs.PojoCoralIFS;
import luv.values.generators.fractals.ifs.PojoSierpinskiIFS;
import luv.values.generators.fractals.ifs.fractint.PojoDragonIFS;
import luv.values.generators.fractals.ifs.fern.PojoBarnsleyFernIFS;
import luv.values.generators.fractals.ifs.fractint.PojoFractIntIFS;
import luv.values.generators.fractals.ifs.PojoTreeIFS;
import luv.values.generators.fractals.ifs.fern.PojoCulcitaFernIFS;
import luv.values.generators.fractals.ifs.fern.PojoCyclosorusFernIFS;
import luv.values.generators.fractals.ifs.fern.PojoFishboneFernIFS;
import luv.values.generators.fractals.ifs.fern.PojoSedgewickFernIFS;
import luv.values.generators.fractals.ifs.fractint.PojoFloorIFS;
import luv.values.generators.fractals.ifs.fractint.PojoKochIFS;
import luv.values.generators.fractals.ifs.fractint.PojoSpiralIFS;
import luv.values.generators.fractals.ifs.fractint.PojoSwirlIFS;
import luv.values.generators.fractals.ifs.fractint.PojoZigZagIFS;
import luv.values.generators.rorschach.HybridRohrschach2;
import luv.values.generators.rorschach.PojoHeatTransfer;
import luv.values.generators.rorschach.PojoRohrschach;
import luv.values.generators.rorschach.PojoRohrschach2;
import luv.values.mappers.ValueBag;
import luv.values.mappers.mono.*;
import luv.values.mappers.multi.*;

public class Registry {

    public ValueGenerator[] getGenerators(OpenCL openCL) {
        return new ValueGenerator[]{
            // Fractals
            new PojoMandelbrotSet(),
            new PojoJuliaSet(),
            new PojoNewtonFractal(),
            // NOT IMPLEMENTED YET new PojoNovaFractal(),
            // NOT IMPLEMENTED YET new PojoBuddhabrot(),
            new PojoAttractor(1),
            new PojoAttractor(2),
            new PojoAttractor(3),
            new HybridMandelbrotSet(openCL),
            new HybridJuliaSet(openCL),
            new HybridNewtonFractal(openCL),
            new HybridNovaFractal(openCL),
            new HybridBuddhabrot(openCL),
            // L-Systems
            new PojoSierpinskiTriangle(),
            new PojoDragonCurve(),
            new PojoFractalPlant(),
            new PojoLevyCurve(),
            new PojoSandboxSystem(),
            // Cell-Noise
            new PojoCellNoise(),
            new PojoCellProduct(),
            new PojoIDMap(),
            new PojoMosaicNoise(),
            new PojoNoisyNoise(0),
            new PojoNoisyNoise(1),
            new PojoNoisyNoise(2),
            new PojoPointNoise(),
            new HybridCellNoise(openCL),
            new HybridCellProduct(openCL),
            new HybridIDMap(openCL),
            new HybridMosaicNoise(openCL),
            new HybridNoisyNoise(openCL, 0),
            new HybridNoisyNoise(openCL, 1),
            new HybridNoisyNoise(openCL, 2),
            new HybridPointNoise(openCL),
            // Simplex-Noise
            new PojoSimplexNoise(),
            new PojoRigidFBM(),
            new PojoFBMNoise(),
            new PojoNebulaNoise(),
            new PojoStarNoise(),
            new PojoValueNoise(),
            new HybridSimplexNoise(openCL),
            new HybridRigidFBM(openCL),
            new HybridFBMNoise(openCL),
            new HybridNebulaNoise(openCL),
            new HybridStarNoise(openCL),
            new HybridValueNoise(openCL),
            // Perlin-Noise
            // NOT IMPLEMENTED YET new PojoGradientNoise(),
            // NOT IMPLEMENTED YET new PojoMonoFractal(),
            // NOT IMPLEMENTED YET new PojoRidgedMultiFractal(),
            // NOT IMPLEMENTED YET new PojoTurbulenceArray(),
            new HybridGradientNoise(openCL),
            new HybridMonoFractal(openCL),
            new HybridRidgedMultiFractal(openCL),
            new HybridTurbulenceArray(openCL),
            // IFS
            new PojoCoralIFS(),
            new PojoSierpinskiIFS(),
            new PojoTreeIFS(),
            // IFS - Fern
            new PojoBarnsleyFernIFS(),
            new PojoCulcitaFernIFS(),
            new PojoCyclosorusFernIFS(),
            new PojoFishboneFernIFS(),
            new PojoSedgewickFernIFS(),
            // IFS  FractInt
            new PojoDragonIFS(),
            new PojoFloorIFS(),
            new PojoFractIntIFS(),
            new PojoKochIFS(),
            new PojoSpiralIFS(),
            new PojoSwirlIFS(),
            new PojoZigZagIFS(),
            // Misc
            new PojoHeatTransfer(),
            new PojoRohrschach(),
            new PojoRohrschach2(),
            new HybridRohrschach2(openCL)
        };
    }

    public ValueGenerator[] getMappers(OpenCL openCL) {
        return new ValueGenerator[]{
            // Mono
            new PojoIdentity(),
            new PojoNormalize(),
            new PojoInvert(),
            new PojoAdd(),
            new PojoMultiply(),
            new PojoPow(),
            new PojoSin(),
            new PojoCos(),
            new PojoCrop(),
            new HybridIdentity(openCL),
            new HybridNormalize(openCL),
            new HybridInvert(openCL),
            new HybridAdd(openCL),
            new HybridMultiply(openCL),
            new HybridPow(openCL),
            new HybridSin(openCL),
            new HybridCos(openCL),
            new HybridCrop(openCL),
            // Multi
            new PojoAverage(),
            new PojoMax(),
            new PojoMin(),
            new PojoSum(),
            new PojoProduct(),
            new HybridAverage(openCL),
            new HybridMax(openCL),
            new HybridMin(openCL),
            new HybridSum(openCL),
            new HybridProduct(openCL)
        };
    }

    public Colorizer[] getColorizers(OpenCL openCL) {
        return new Colorizer[]{
            // Mono
            new PojoColorMap(),
            new PojoGrayScale(),
            new PojoHSBScale(),
            new PojoHeatMap(),
            new PojoLinearScale(Color.BLUE.darker(), Color.ORANGE),
            new PojoRainbowMap(),
            new PojoRandomMap(50),
            new HybridColorMap(openCL),
            new HybridGrayScale(openCL),
            new HybridHSBScale(openCL),
            new HybridHeatMap(openCL),
            new HybridLinearScale(openCL, Color.WHITE, Color.BLUE.darker().darker()),
            new HybridLinearScale(openCL, Color.BLUE.darker(), Color.ORANGE),
            new HybridRainbowMap(openCL),
            new HybridRandomMap(openCL, 50),
            // Multi
            new PojoRGBScale(),
            new HybridRGBScale(openCL),};
    }

    public ValueBag[] getExamples(OpenCL openCL) {
        return new ValueBag[]{
            new ValueBag(
            new HybridAverage(openCL,
            new HybridNormalize(openCL, new HybridNewtonFractal(openCL)),
            new HybridNormalize(openCL, new HybridJuliaSet(openCL)),
            new HybridNormalize(openCL, new HybridNovaFractal(openCL))
            )),
            new ValueBag(new HybridBuddhabrot(openCL, 0, 50), new HybridBuddhabrot(openCL, 50, 100), new HybridBuddhabrot(openCL, 100, 150)),
            new ValueBag(new HybridBuddhabrot(openCL, 0, 200), new HybridBuddhabrot(openCL, 0, 2000), new HybridBuddhabrot(openCL, 0, 20)),
            new ValueBag(new HybridBuddhabrot(openCL, 0, 100), new HybridBuddhabrot(openCL, 0, 1000), new HybridBuddhabrot(openCL, 0, 10000)),
            new ValueBag(new HybridNormalize(openCL, new HybridCellNoise(openCL))),
            new ValueBag(new HybridNoisyNoise(openCL, 0), new HybridNoisyNoise(openCL, 1), new HybridNoisyNoise(openCL, 2)),
            new ValueBag(new HybridAverage(openCL, new PojoMandelbrotSet().setup(2.0, 0.5, 3.0), new HybridMandelbrotSet(openCL)))
        };
    }

    public static ValueBag loadExample(final String path) {
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ValueBag vb = (ValueBag) in.readObject();
            in.close();
            fileIn.close();
            return vb;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("ValueBag class not found");
            c.printStackTrace();
            return null;
        }
    }

    public static void saveExample(final ValueBag example, final String path) {
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(example);
            out.close();
            fileOut.close();

        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
