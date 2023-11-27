package com.koko.practicespringboot.interfaceexample;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Mappable> mappables = new ArrayList<>();
        mappables.add(new Building("Town Hall", UsageType.GOVERNMENT));
        mappables.add(new Building("Opera House", UsageType.ENTERTAINMENT));
        mappables.add(new Building("Stadium", UsageType.SPORT));

        mappables.add(new UtilityLine("College St", UtilityType.FIBER_OPTIC));
        mappables.add(new UtilityLine("Olympic Blvd", UtilityType.WATER));

        for (Mappable m : mappables) {
            Mappable.mapIt(m);
        }
    }
}
