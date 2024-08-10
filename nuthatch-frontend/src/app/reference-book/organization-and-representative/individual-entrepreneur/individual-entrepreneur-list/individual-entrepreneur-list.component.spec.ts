import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IndividualEntrepreneurListComponent } from './individual-entrepreneur-list.component';

describe('IndividualEntrepreneurListComponent', () => {
  let component: IndividualEntrepreneurListComponent;
  let fixture: ComponentFixture<IndividualEntrepreneurListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IndividualEntrepreneurListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IndividualEntrepreneurListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
