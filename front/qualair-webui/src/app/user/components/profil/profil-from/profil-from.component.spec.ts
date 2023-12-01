import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilFromComponent } from './profil-from.component';

describe('ProfilFromComponent', () => {
  let component: ProfilFromComponent;
  let fixture: ComponentFixture<ProfilFromComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProfilFromComponent]
    });
    fixture = TestBed.createComponent(ProfilFromComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
