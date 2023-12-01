package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.dtos.ZipDto;
import fr.diginamic.qualairapi.entities.Zip;

public interface ZipService
    extends SimpleEntityCreator<Zip, ZipDto>,
            SimpleEntityUpdater<Zip, ZipDto>,
            SimpleEntityFinder<Zip, ZipDto>,
            SimpleEntityDeleter<Zip> {
}
