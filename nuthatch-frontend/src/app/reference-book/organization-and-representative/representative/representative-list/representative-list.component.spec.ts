import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RepresentativeListComponent } from './representative-list.component';

describe('RepresentativeListComponent', () => {
  let component: RepresentativeListComponent;
  let fixture: ComponentFixture<RepresentativeListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RepresentativeListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RepresentativeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
