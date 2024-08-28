import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RepresentativeCreateComponent } from './representative-create.component';

describe('RepresentativeCreateComponent', () => {
  let component: RepresentativeCreateComponent;
  let fixture: ComponentFixture<RepresentativeCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RepresentativeCreateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RepresentativeCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
