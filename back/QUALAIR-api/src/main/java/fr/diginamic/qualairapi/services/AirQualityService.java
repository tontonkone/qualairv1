package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.dtos.AirQualityDto;
import fr.diginamic.qualairapi.entities.AirQuality;

public interface AirQualityService
        extends SimpleEntityCreator<AirQuality, AirQualityDto>,
                SimpleEntityUpdater<AirQuality,AirQualityDto>,
                SimpleEntityFinder<AirQuality,AirQualityDto>,
                SimpleEntityDeleter<AirQuality>{
}
