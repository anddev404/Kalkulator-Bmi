package pl.anddev.kalkulatorbmi.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Marcin on 2018-03-15.
 */

public class PobraneDane {



    @Getter
    @Setter
    private float wzrost;

    @Getter
    @Setter
    private float waga;

    public PobraneDane(float wzrost, float waga) {
        this.wzrost = wzrost;
        this.waga = waga;
    }
}
