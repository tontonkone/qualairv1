import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AirResultComponent } from './air-result.component';

describe('AirResultComponent', () => {
  let component: AirResultComponent;
  let fixture: ComponentFixture<AirResultComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AirResultComponent]
    });
    fixture = TestBed.createComponent(AirResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
