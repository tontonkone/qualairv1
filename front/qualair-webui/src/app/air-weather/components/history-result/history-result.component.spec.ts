import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryResultComponent } from './history-result.component';

describe('HistoryResultComponent', () => {
  let component: HistoryResultComponent;
  let fixture: ComponentFixture<HistoryResultComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HistoryResultComponent]
    });
    fixture = TestBed.createComponent(HistoryResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
