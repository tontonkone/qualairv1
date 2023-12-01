import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeatherDetailedResultComponent } from './weather-detailed-result.component';

describe('WeatherDetailedResultComponent', () => {
  let component: WeatherDetailedResultComponent;
  let fixture: ComponentFixture<WeatherDetailedResultComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [WeatherDetailedResultComponent]
    });
    fixture = TestBed.createComponent(WeatherDetailedResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
