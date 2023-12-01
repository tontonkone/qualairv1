package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.dtos.RegionDto;
import fr.diginamic.qualairapi.entities.Region;

public interface RegionService
    extends
        SimpleEntityCreator<Region, RegionDto>,
        SimpleEntityUpdater<Region, RegionDto>,
        SimpleEntityFinder<Region, RegionDto>,
        SimpleEntityDeleter<Region> {
}
