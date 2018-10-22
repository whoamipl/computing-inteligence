package pl.psvm;

import org.jenetics.BitChromosome;
import org.jenetics.BitGene;
import org.jenetics.Chromosome;
import org.jenetics.Genotype;
import org.jenetics.engine.Engine;
import org.jenetics.engine.EvolutionResult;

import java.util.Map;
public class Main {
    private final static Map<Integer, ItemProperties<Integer, Integer>> data = Map.of(
            1, new ItemProperties<Integer, Integer>(100, 7),
            2, new ItemProperties<Integer, Integer>(300, 7),
            3, new ItemProperties<Integer, Integer>(200, 6),
            4, new ItemProperties<Integer, Integer>(40, 2),
            5, new ItemProperties<Integer, Integer>(500, 5),
            6, new ItemProperties<Integer, Integer>(70, 6),
            7, new ItemProperties<Integer, Integer>(100, 1),
            8, new ItemProperties<Integer, Integer>(250, 3),
            9, new ItemProperties<Integer, Integer>(300, 10),
            10, new ItemProperties<Integer, Integer>(280, 3));
    private static final int limit = 25;

    private static int fitnessFunction(Genotype<BitGene> geneGenotype) {
        var ref = new Object() {
            public int totalWeiht = 0;
            int totalValue = 0;
        };
        int totalWeiht = 0;
        int i = 1;
        geneGenotype.stream().forEach( g -> {
            if (g.getGene().booleanValue()) {
                ref.totalValue += data.get(i).value;
                ref.totalWeiht += data.get(i).weight;
            };
        });

        if (ref.totalWeiht > limit) {
            return 0;
        }
        return ref.totalValue;
    }

    public static void main(String[] args) {
        var gtf = Genotype.of(BitChromosome.of(10));
        var engine = Engine.builder(Main::fitnessFunction, gtf).build();
        var result = engine.stream().limit(100).collect(EvolutionResult.toBestEvolutionResult());
        result.getGenotypes().stream().forEach(System.out::println);
    }
}

