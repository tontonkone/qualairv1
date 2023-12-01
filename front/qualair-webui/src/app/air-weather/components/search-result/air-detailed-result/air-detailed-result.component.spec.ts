import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AirDetailedResultComponent } from './air-detailed-result.component';

describe('AirDetailedResultComponent', () => {
  let component: AirDetailedResultComponent;
  let fixture: ComponentFixture<AirDetailedResultComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AirDetailedResultComponent]
    });
    fixture = TestBed.createComponent(AirDetailedResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
