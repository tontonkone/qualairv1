import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookmarkDetailsComponent } from './bookmark-details.component';

describe('BookmarkDetailsComponent', () => {
  let component: BookmarkDetailsComponent;
  let fixture: ComponentFixture<BookmarkDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BookmarkDetailsComponent]
    });
    fixture = TestBed.createComponent(BookmarkDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
