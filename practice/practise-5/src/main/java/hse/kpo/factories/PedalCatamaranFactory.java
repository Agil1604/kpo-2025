package hse.kpo.factories;


import hse.kpo.domains.Catamaran;
import hse.kpo.domains.PedalEngine;
import hse.kpo.interfaces.ICatamaranFactory;
import hse.kpo.params.PedalEngineParams;
import org.springframework.stereotype.Component;

@Component
public class PedalCatamaranFactory implements ICatamaranFactory<PedalEngineParams> {
    @Override
    public Catamaran createCatamaran(PedalEngineParams catamaranParams, int catamaranNumber) {
        var engine = new PedalEngine(catamaranParams.pedalSize()); // создаем двигатель на основе переданных параметров

        return new Catamaran(catamaranNumber, engine); // возвращаем собранный катамаран с установленным двигателем и определенным номером
    }
}
