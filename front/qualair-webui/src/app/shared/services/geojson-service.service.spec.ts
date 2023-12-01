import { TestBed } from '@angular/core/testing';

import { GeojsonServiceService } from './geojson-service.service';

describe('GeojsonServiceService', () => {
  let service: GeojsonServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GeojsonServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
