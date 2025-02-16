package hse.kpo.factories;

import hse.kpo.domains.Catamaran;
import hse.kpo.domains.LevitateEngine;
import hse.kpo.interfaces.ICatamaranFactory;
import hse.kpo.params.EmptyEngineParams;
import org.springframework.stereotype.Component;

@Component
public class LevitatingCatamaranFactory implements ICatamaranFactory<EmptyEngineParams> {
    @Override
    public Catamaran createCatamaran(EmptyEngineParams catamaranParams, int catamaranNumber) {
        var engine = new LevitateEngine();

        return new Catamaran(catamaranNumber, engine);
    }
}