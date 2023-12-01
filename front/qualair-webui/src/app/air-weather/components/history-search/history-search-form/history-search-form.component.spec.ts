import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistorySearchFormComponent } from './history-search-form.component';

describe('HistorySearchFormComponent', () => {
  let component: HistorySearchFormComponent;
  let fixture: ComponentFixture<HistorySearchFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HistorySearchFormComponent]
    });
    fixture = TestBed.createComponent(HistorySearchFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
