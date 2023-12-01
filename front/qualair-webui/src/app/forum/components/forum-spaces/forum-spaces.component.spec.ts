import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForumSpacesComponent } from './forum-spaces.component';

describe('ForumSpacesComponent', () => {
  let component: ForumSpacesComponent;
  let fixture: ComponentFixture<ForumSpacesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ForumSpacesComponent]
    });
    fixture = TestBed.createComponent(ForumSpacesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
