import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminForumItemComponent } from './admin-forum-item.component';

describe('ForumsItemComponent', () => {
  let component: AdminForumItemComponent;
  let fixture: ComponentFixture<AdminForumItemComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminForumItemComponent]
    });
    fixture = TestBed.createComponent(AdminForumItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
